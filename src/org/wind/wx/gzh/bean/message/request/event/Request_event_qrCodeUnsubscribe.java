package org.wind.wx.gzh.bean.message.request.event;

/**
 * @描述 : 【请求类】消息类型_事件_二维码（用户未关注）
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 17:44:23
 */
public class Request_event_qrCodeUnsubscribe extends Request_event{

	private String EventKey;		//事件KEY值，qrscene_为前缀，后面为二维码的参数值
	private String Ticket;			//二维码的ticket，可用来换取二维码图片

	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}

}
