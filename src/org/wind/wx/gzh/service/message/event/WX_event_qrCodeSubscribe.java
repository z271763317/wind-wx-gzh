package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_qrCodeSubscribe;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_二维码（用户已关注）
 * @作者 : 胡璐璐
 * @时间 : 2021年2月7日 10:33:24
 */
public interface WX_event_qrCodeSubscribe extends WX<Request_event_qrCodeSubscribe>{

	public String service(Request_event_qrCodeSubscribe param);

}
