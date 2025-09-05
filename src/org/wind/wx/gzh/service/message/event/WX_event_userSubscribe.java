package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_userSubscribe;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_用户关注
 * @作者 : 胡璐璐
 * @时间 : 2021年2月7日 10:34:05
 */
public interface WX_event_userSubscribe extends WX<Request_event_userSubscribe>{

	public String service(Request_event_userSubscribe param);

}
