package org.wind.wx.gzh.bean.message.request.event;

import org.wind.wx.gzh.bean.message.request.Request;

/**
 * @描述 : 【请求类】消息类型_事件
 * @排重 : 发来的【FromUserName+CreateTime】做排重处理 
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 17:44:23
 */
public class Request_event extends Request{

	private String Event;		/*
											 * 事件类型
											 * subscribe=订阅；unsubscribe=取消订阅
											 * SCAN=二维码（用户已关注时）
											 * CLICK=自定义菜单（点击菜单拉取消息时）；VIEW=自定义菜单（点击菜单跳转链接时）
											 * LOCATION=上报地理位置
											 * 
											 * --订阅通知--
											 * subscribe_msg_popup_event=用户操作订阅通知弹窗
											 * subscribe_msg_change_event=用户管理订阅通知
											 * subscribe_msg_sent_event=发送订阅通知
											 */
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	
}
