package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_userUnsubscribe;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_用户取消关注
 * @作者 : 胡璐璐
 * @时间 : 2021年2月7日 10:34:00
 */
public interface WX_event_userUnsubscribe extends WX<Request_event_userUnsubscribe>{

	public String service(Request_event_userUnsubscribe param);

}
