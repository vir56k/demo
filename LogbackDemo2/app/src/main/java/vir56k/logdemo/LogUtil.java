package vir56k.logdemo;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * 日志工具类
 * 
 * @ClassName: LogUtil
 * @author wqr 2015-1-14 上午11:12:13
 * 
 */
public class LogUtil {
	public static File file = null;

	public static void info(String tag, String desc) {
		if (CommonConstants.LOG_LEVEL <= CommonConstants.LOG_INFO) {
			appendLog(file, tag, null, CommonConstants.LOG_INFO, desc);
		}
	}

	/**
	 * 添加操作事件信息
	 * 
	 * @param tag
	 * @param desc
	 */
	public static void event(String tag, String desc) {
		appendLog(file, tag, "event", -1, desc);
	}

	public static void debug(String tag, String desc) {
		if (CommonConstants.LOG_LEVEL <= CommonConstants.LOG_DEBUG) {
			appendLog(file, tag, null, CommonConstants.LOG_DEBUG, desc);
		}
	}

	public static void warning(String tag, String desc) {
		if (CommonConstants.LOG_LEVEL <= CommonConstants.LOG_WARNING) {
			appendLog(file, tag, null, CommonConstants.LOG_WARNING, desc);
		}
	}

	public static void error(String tag, String desc) {
		if (CommonConstants.LOG_LEVEL <= CommonConstants.LOG_ERROR) {
			appendLog(file, tag, null, CommonConstants.LOG_ERROR, desc);
		}
	}

	/**
	 * 初始化日志文件
	 */
	private static void initLogFile() {
		try {
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				file = new File(Environment.getExternalStorageDirectory() + CommonConstants.logpath
						+ "ui-log-" + CommonConstants.SDF.format(new Date()) + ".txt");

				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				if (!file.exists()) {
					file.createNewFile();
				}
			}
		} catch (Exception e) {
			error(CommonConstants.LogTag, "appendLog " + e.getMessage());
			error(CommonConstants.LogTag, "appendLog Maybe the SD card not mounted");
		}
	}

	/**
	 * 添加日志
	 * 
	 * @Title: appendLog
	 * @param file
	 *            文件
	 * @param tag
	 *            当前对象
	 * @param event
	 *            事件
	 * @param level
	 *            级别
	 * @param desc
	 *            设定文件 内容
	 */
	private static void appendLog(File file, String tag, String event, int level, String desc) {
		initLogFile();
		BufferedWriter out = null;
		try {
			if (file == null || !file.exists()) {
				error(CommonConstants.LogTag, "appendLog file is null");
				return;
			}
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),
					"UTF-8"), 8000);
			StringBuffer sb = new StringBuffer();

			// 操作时间
			sb.append(CommonConstants.SDF3.format(new Date())).append("\t");
			// 线程id
			sb.append("thread Id: ").append(Thread.currentThread().getId()).append("\t");
			// 当前对象
			sb.append(tag).append("\t");
			// 消息级别
			if (level != -1) {
				if (level == CommonConstants.LOG_INFO) {
					Log.i(tag, desc);
					sb.append("info");
				} else if (level == CommonConstants.LOG_DEBUG) {
					Log.d(tag, desc);
					sb.append("debug");
				} else if (level == CommonConstants.LOG_WARNING) {
					Log.w(tag, desc);
					sb.append("warning");
				} else if (level == CommonConstants.LOG_ERROR) {
					Log.e(tag, desc);
					sb.append("error");
				} else {
					Log.v(tag, desc);
					sb.append("none");
				}
				sb.append("\t");
			}
			// 事件
			if (event != null) {
				sb.append(event).append("\t");
			}
			// 操作说明
			sb.append(desc).append("\r\n");
			out.write(sb.toString());
		} catch (Exception e) {
			error(CommonConstants.LogTag,
					"appendLog log output exception, maybe the log file is not exists, "
							+ e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
				error(CommonConstants.LogTag, "appendLog " + e);
			}
		}

	}
}