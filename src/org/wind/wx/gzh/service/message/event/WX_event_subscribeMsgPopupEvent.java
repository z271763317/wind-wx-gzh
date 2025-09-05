package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_subscribeMsgPopupEvent;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_用户操作订阅通知弹窗
 * @场景 : 用户在图文等场景内订阅通知的操作
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 17:21:15
 */
public interface WX_event_subscribeMsgPopupEvent extends WX<Request_event_subscribeMsgPopupEvent>{

	public String service(Request_event_subscribeMsgPopupEvent param);

}
