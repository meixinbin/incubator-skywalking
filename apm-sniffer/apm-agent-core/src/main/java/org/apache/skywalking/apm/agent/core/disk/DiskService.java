package org.apache.skywalking.apm.agent.core.disk;

import org.apache.skywalking.apm.agent.core.boot.BootService;
import org.apache.skywalking.apm.agent.core.boot.DefaultImplementor;
import org.apache.skywalking.apm.agent.core.boot.DefaultNamedThreadFactory;
import org.apache.skywalking.apm.agent.core.conf.Config;
import org.apache.skywalking.apm.agent.core.conf.RemoteDownstreamConfig;
import org.apache.skywalking.apm.agent.core.disk.model.Disk;
import org.apache.skywalking.apm.agent.core.disk.model.DiskMetrics;
import org.apache.skywalking.apm.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.agent.core.remote.dubbo.DubboConfig;
import org.apache.skywalking.apm.service.ApplicationService;
import org.apache.skywalking.apm.util.RunnableWithExceptionProtection;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author meixinbin
 */
@DefaultImplementor
public class DiskService  implements BootService, Runnable{

	private static final ILog logger = LogManager.getLogger(DiskService.class);

	private LinkedBlockingQueue<Disk> queue;
	private volatile ScheduledFuture<?> collectMetricFuture;
	private volatile ScheduledFuture<?> sendMetricFuture;
	private ApplicationService applicationService;
	private DiskService.Sender sender;

	@Override
	public void run() {
		long currentTimeMillis = System.currentTimeMillis();
		try {
			Disk diskMetric = DiskProvider.INSTANCE.getDiskMetric();
			diskMetric.setTime(currentTimeMillis);
			if (!queue.offer(diskMetric)) {
				queue.poll();
				queue.offer(diskMetric);
			}
		} catch (Exception e) {
			logger.error(e, "Collect JVM info fail.");
		}
	}

	@Override
	public void prepare() throws Throwable {
		queue = new LinkedBlockingQueue(Config.Jvm.BUFFER_SIZE);
		sender = new DiskService.Sender();
	}

	@Override
	public void boot() throws Throwable {
		applicationService = DubboConfig.getApplicationSerivce();
		collectMetricFuture = Executors
				.newSingleThreadScheduledExecutor(new DefaultNamedThreadFactory("DiskService-produce"))
				.scheduleAtFixedRate(new RunnableWithExceptionProtection(this, new RunnableWithExceptionProtection.CallbackWhenException() {
					@Override public void handle(Throwable t) {
						logger.error("JVMService produces metrics failure.", t);
					}
				}), 0, 1, TimeUnit.SECONDS);
		sendMetricFuture = Executors
				.newSingleThreadScheduledExecutor(new DefaultNamedThreadFactory("DiskService-consume"))
				.scheduleAtFixedRate(new RunnableWithExceptionProtection(sender, new RunnableWithExceptionProtection.CallbackWhenException() {
					@Override public void handle(Throwable t) {
						logger.error("JVMService consumes and upload failure.", t);
					}
				}
				), 0, 1, TimeUnit.SECONDS);
	}


	@Override
	public void onComplete() throws Throwable {

	}

	@Override
	public void shutdown() throws Throwable {
		collectMetricFuture.cancel(true);
		sendMetricFuture.cancel(true);
	}
	private class Sender implements Runnable {

		@Override
		public void run() {
			try {
				DiskMetrics diskMetrics = new DiskMetrics();
				LinkedList<Disk> buffer = new LinkedList<Disk>();
				queue.drainTo(buffer);
				if (buffer.size() > 0) {
					diskMetrics.setList(buffer);
					diskMetrics.setApplicationInstanceId(RemoteDownstreamConfig.Agent.APPLICATION_INSTANCE_ID);
					applicationService.diskMetrics(diskMetrics);
				}
			} catch (Throwable t) {
				logger.error(t, "send Disk metrics to Collector fail.");
			}
		}

	}
}
