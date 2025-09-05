package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_location;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_上报地理位置
 * @作者 : 胡璐璐
 * @时间 : 2021年2月7日 10:33:07
 */
public interface WX_event_location extends WX<Request_event_location>{

	public String service(Request_event_location param);

}
