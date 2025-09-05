package org.wind.wx.gzh.util;

/**
 * @描述 : 日志工具类
 * @作者 : 胡璐璐
 * @时间 : 2021年2月4日 12:10:13
 */
public class _LogUtil {

	/**处理（异常）**/
	public static void service(Exception e) {
		if(_SysConstant.isOutputLog) {
			e.printStackTrace();
		}
	}
	/**处理（手动）**/
	public static void service(String errorMsg) {
		if(_SysConstant.isOutputLog) {
			System.out.println(errorMsg);
		}
	}
	
}
