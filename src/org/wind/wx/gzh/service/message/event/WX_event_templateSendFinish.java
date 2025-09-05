package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_templateSendFinish;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_模板发送完成
 * @作者 : 胡璐璐
 * @时间 : 2021年10月1日 09:08:56
 */
public interface WX_event_templateSendFinish extends WX<Request_event_templateSendFinish>{

	public String service(Request_event_templateSendFinish param);

}
