package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_其他（若事件不在本架构的处理器中，则在该处理器统一处理）
 * @作者 : 胡璐璐
 * @时间 : 2021年2月7日 10:34:19
 */
public interface WX_event extends WX<Request_event>{

	public String service(Request_event param);

}
