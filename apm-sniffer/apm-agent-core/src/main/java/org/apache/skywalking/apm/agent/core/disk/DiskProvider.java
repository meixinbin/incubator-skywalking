package org.apache.skywalking.apm.agent.core.disk;

import org.apache.skywalking.apm.agent.core.disk.model.Disk;
import org.apache.skywalking.apm.agent.core.disk.model.RootPath;

import java.io.File;

/**
 * @author meixinbin
 */
public enum  DiskProvider {

	INSTANCE;

	public Disk getDiskMetric(){
		Disk disk = new Disk();
		File[] files = File.listRoots();
		for(File file:files){
			RootPath rootPath = new RootPath();
			rootPath.setPath(file.getPath());
			rootPath.setFreeSpace(file.getFreeSpace());
			rootPath.setTotalSpace(file.getTotalSpace());
			rootPath.setUsableSpace(file.getUsableSpace());
			disk.addPath(rootPath);
		}
		return disk;
	}
}
