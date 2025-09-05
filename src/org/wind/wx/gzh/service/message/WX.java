package org.wind.wx.gzh.service.message;

import org.wind.wx.gzh.bean.message.request.Request;

/**
 * @描述 : 消息接口（微信的MsgType类型有：text、image、voice、video、shortvideo、location、link等）
 * @详情 : 根据不同消息类型走。<br />
 * 				even包=事件推送；standard=普通消息
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 17:37:34
 */
public interface WX<T extends Request> {

	/**
	 * 服务处理
	 * @param param : 消息对应的请求参数对象 
	 * @return 返回消息对应的返回数据
	 */
	public String service(T param);
	
}
