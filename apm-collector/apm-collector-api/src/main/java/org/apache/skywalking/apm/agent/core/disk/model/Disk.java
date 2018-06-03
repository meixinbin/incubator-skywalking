package org.apache.skywalking.apm.agent.core.disk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class Disk implements Serializable{

	private long time;

	private List<RootPath> pathList;

	public List<RootPath> getPathList() {
		return pathList;
	}

	public void setPathList(List<RootPath> pathList) {
		this.pathList = pathList;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}


	public void addPath(RootPath path){
		if(pathList ==null){
			pathList = new ArrayList<RootPath>();
		}
		this.pathList.add(path);
	}

	@Override
	public String toString() {
		return "Disk{" +
				"time=" + time +
				"pathList=" + pathList +
				'}';
	}
}
