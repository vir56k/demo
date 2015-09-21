package vir56k.logdemo;

import java.text.SimpleDateFormat;

/**
 * 
 * @ClassName: Config
 *   项目位置参数
 * @author wqr
 * @date 2014-11-3 下午5:25:03
 * 
 */
public class CommonConstants {
	
	// 日志引擎 tag
	public static String LogTag = "[Cabinet]";
	public static int LOG_INFO = 0;
	public static int LOG_DEBUG = 1;
	public static int LOG_WARNING = 2;
	public static int LOG_ERROR = 3;
	public static int LOG_LEVEL = LOG_INFO;
	public static String logpath = "/jlb/Log/";

	
	// 日志引擎 日期格式化的格式
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat SDF3 = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * 推送action
	 */
	public static final String ACTION_PUSH = "cn.jlb.pro.intelligentcabinet.push";
	
	/**
	 * 推送action
	 */
	public static final String ACTION_UPDATE = "cn.jlb.pro.intelligentcabinet.update";
	
	/**
	 * 启动推送action
	 */
	public static final String ACTION_START = "com.jlb.cabinet.start";
	
	/**
	 * update package
	 */
	public static final String PACKAGE_NAME_UPDATE = "cn.jlb.pro.update";
	
	/**
	 * cabinet package
	 */
	public static final String PACKAGE_NAME_CABINET = "cn.jlb.pro.intelligentcabinet";
	
	/**
	 * push service name
	 */
	public static final String SERVICE_NAME_UPDATE_PUSH = "cn.jlb.pro.update.PushService";
	/**
	 * key MsgResult
	 */
	public static final String KEY_MSGRESULT = "MsgResult";
	
	/**
	 * key cabinet_code
	 */
	public static final String KEY_CABINET_CODE = "cabinet_code";
	
	/**
	 * key net change
	 */
	public static final String KEY_NET_CHANGE = "key_net_change";
	
}