package org.apache.skywalking.apm.agent.core.disk;

import org.apache.skywalking.apm.agent.core.disk.model.Disk;
import org.apache.skywalking.apm.agent.core.disk.model.RootPath;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author meixinbin
 */
public class DiskProviderTest {

	@Test
	public void test(){
		Disk diskMetric = DiskProvider.INSTANCE.getDiskMetric();
		System.out.println(diskMetric);

		List<RootPath> pathList = diskMetric.getPathList();
		for(RootPath rootPath:pathList){
			String s = readableFileSize(rootPath.getTotalSpace());
			String s1 = readableFileSize(rootPath.getFreeSpace());
			System.out.println(s);
			System.out.println(s1);
		}
	}

	public static String readableFileSize(long size) {
		if (size <= 0) return "0";
		final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}


}
