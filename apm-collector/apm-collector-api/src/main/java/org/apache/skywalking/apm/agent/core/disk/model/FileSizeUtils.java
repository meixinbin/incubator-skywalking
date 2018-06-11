package org.apache.skywalking.apm.agent.core.disk.model;

import java.text.DecimalFormat;

/**
 * @author meixinbin
 */
public class FileSizeUtils {

	/**
	 * 格式化文件大小
	 * 参考：https://stackoverflow.com/a/5599842/1253611
	 * @param size byte * @return readable file size
	 */
	public static String readableFileSize(long size) {
		if (size <= 0) {
			return "0";
		}
		final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}


}
