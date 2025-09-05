package org.wind.wx.gzh.bean.message.request.event;

/**
 * @描述 : 【请求类】消息类型_事件_二维码（用户已关注）
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 17:44:23
 */
public class Request_event_qrCodeSubscribe extends Request_event{

	private String EventKey;		//事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
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
