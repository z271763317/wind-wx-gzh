package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_click;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_自定义菜单（点击菜单拉取消息时）
 * @作者 : 胡璐璐
 * @时间 : 2021年2月7日 10:32:48
 */
public interface WX_event_click extends WX<Request_event_click>{

	public String service(Request_event_click param);

}
