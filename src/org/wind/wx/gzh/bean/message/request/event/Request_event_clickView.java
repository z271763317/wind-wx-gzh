package org.wind.wx.gzh.bean.message.request.event;

/**
 * @描述 : 【请求类】消息类型_事件_自定义菜单（点击菜单跳转链接时）
 * @作者 : 胡璐璐
 * @时间 : 2021年2月6日 12:56:57
 */
public class Request_event_clickView extends Request_event{

	private String EventKey;		//事件KEY值，设置的跳转URL
	
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
