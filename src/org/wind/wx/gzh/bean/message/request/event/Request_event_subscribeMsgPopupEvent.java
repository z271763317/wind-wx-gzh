package org.wind.wx.gzh.bean.message.request.event;

import java.util.List;

import org.wind.wx.gzh.bean.message.request.event.subscribeMsgPopupEvent._List;

/**
 * @描述 : 【请求类】消息类型_事件_用户操作订阅通知弹窗
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 15:34:58
 */
public class Request_event_subscribeMsgPopupEvent extends Request_event{

	private List<_List> SubscribeMsgPopupEvent;

	public List<_List> getSubscribeMsgPopupEvent() {
		return SubscribeMsgPopupEvent;
	}
	public void setSubscribeMsgPopupEvent(List<_List> subscribeMsgPopupEvent) {
		SubscribeMsgPopupEvent = subscribeMsgPopupEvent;
	}
	
}
