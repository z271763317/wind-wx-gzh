package org.wind.wx.gzh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wind.wx.gzh.annotation.An_ScanInterface;

/**
 * @描述 : 接收接口（服务器URL验证不会执行该方法）
 * @详情 : 接收到微信服务器发送来的消息后的处理。可做前置处理（如：记录发送来的数据）。若不想进行后续的处理，请直接返回<b style="color:red">true</b>（代表已手动完成处理），否则返回<b>false</b>（交给框架处理及您对应的接口实现类处理）
 * @作者 : 胡璐璐
 * @时间 : 2021年3月4日 21:30:45
 */
@An_ScanInterface
public interface ReceiveService {

	/**处理（xml=微信服务器发送来的消息数据，一般是xml结构）**/
	public boolean service(HttpServletRequest request, HttpServletResponse response,String xml);
	
}
