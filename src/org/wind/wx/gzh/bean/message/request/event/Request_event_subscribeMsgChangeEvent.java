package org.wind.wx.gzh.bean.message.request.event;

import java.util.List;

import org.wind.wx.gzh.annotation.An_Param;
import org.wind.wx.gzh.bean.message.request.event.subscribeMsgChangeEvent._List;


/**
 * @描述 : 【请求类】消息类型_事件_用户管理订阅通知
 * @注意 : 官方文档的示例里是【SubscribeMsgPopupEvent】，但未来可能取【SubscribeMsgChangeEvent】做为列表项
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 16:06:51
 */
public class Request_event_subscribeMsgChangeEvent extends Request_event{

	@An_Param("SubscribeMsgPopupEvent")
	private List<_List> SubscribeMsgChangeEvent;
	
	public List<_List> getSubscribeMsgChangeEvent() {
		return SubscribeMsgChangeEvent;
	}
	public void setSubscribeMsgChangeEvent(List<_List> subscribeMsgChangeEvent) {
		SubscribeMsgChangeEvent = subscribeMsgChangeEvent;
	}
	
}
