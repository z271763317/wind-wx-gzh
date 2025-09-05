package org.wind.wx.gzh.service.message.event;

import org.wind.wx.gzh.bean.message.request.event.Request_event_subscribeMsgChangeEvent;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_事件_用户管理订阅通知
 * @场景 : 用户在服务通知管理页面做通知管理时的操作
 * @注意 : 官方文档的示例里是【SubscribeMsgPopupEvent】，但未来可能取【SubscribeMsgChangeEvent】做为列表项
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 17:21:15
 */
public interface WX_event_subscribeMsgChangeEvent extends WX<Request_event_subscribeMsgChangeEvent>{

	public String service(Request_event_subscribeMsgChangeEvent param);

}
