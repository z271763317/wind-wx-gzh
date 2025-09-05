package org.wind.wx.gzh.bean.message.request.event;

/**
 * @描述 : 【请求类】消息类型_事件_自定义菜单（点击菜单拉取消息时）
 * @作者 : 胡璐璐
 * @时间 : 2021年2月6日 12:56:57
 */
public class Request_event_click extends Request_event{

	private String EventKey;		//事件KEY值，与自定义菜单接口中KEY值对应
	
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
