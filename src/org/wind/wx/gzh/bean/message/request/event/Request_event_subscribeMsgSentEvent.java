package org.wind.wx.gzh.bean.message.request.event;

import java.util.List;

import org.wind.wx.gzh.bean.message.request.event.subscribeMsgSentEvent._List;


/**
 * @描述 : 【请求类】消息类型_事件_发送订阅通知（失败仅包含因异步推送导致的系统失败）
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 16:06:51
 */
public class Request_event_subscribeMsgSentEvent extends Request_event{

	private List<_List> SubscribeMsgSentEvent;

	public List<_List> getSubscribeMsgSentEvent() {
		return SubscribeMsgSentEvent;
	}
	public void setSubscribeMsgSentEvent(List<_List> subscribeMsgSentEvent) {
		SubscribeMsgSentEvent = subscribeMsgSentEvent;
	}
	
}
