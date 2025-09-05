package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_subscribeMsgSentEvent;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_发送订阅通知（失败仅包含因异步推送导致的系统失败）
 * @场景 : 调用 bizsend 接口发送通知
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 17:21:15
 */
public interface WX_event_subscribeMsgSentEvent extends WX<Request_event_subscribeMsgSentEvent>{

	public String service(Request_event_subscribeMsgSentEvent param);

}
