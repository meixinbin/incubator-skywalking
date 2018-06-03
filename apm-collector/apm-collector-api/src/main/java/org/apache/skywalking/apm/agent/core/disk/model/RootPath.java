package org.apache.skywalking.apm.agent.core.disk.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class RootPath implements Serializable{

	/**
	 * 文件系统路径
	 */
	private String path;

	/**
	 * 总空间大小
	 */
	private long totalSpace;

	/**
	 * 剩余空间
	 */
	private long freeSpace;

	private long usableSpace;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getTotalSpace() {
		return totalSpace;
	}

	public void setTotalSpace(long totalSpace) {
		this.totalSpace = totalSpace;
	}

	public long getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(long freeSpace) {
		this.freeSpace = freeSpace;
	}

	public long getUsableSpace() {
		return usableSpace;
	}

	public void setUsableSpace(long usableSpace) {
		this.usableSpace = usableSpace;
	}

	@Override
	public String toString() {
		return "RootPath{" +
				"path='" + path + '\'' +
				", totalSpace=" + totalSpace +
				", freeSpace=" + freeSpace +
				", usableSpace=" + usableSpace +
				'}';
	}
}
