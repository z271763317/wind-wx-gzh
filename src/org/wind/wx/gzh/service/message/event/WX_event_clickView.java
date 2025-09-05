package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_clickView;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_自定义菜单（点击菜单跳转链接时）
 * @作者 : 胡璐璐
 * @时间 : 2021年2月7日 10:34:19
 */
public interface WX_event_clickView extends WX<Request_event_clickView>{

	public String service(Request_event_clickView param);

}
