package org.wind.wx.gzh.bean.message.request.event.subscribeMsgSentEvent;

/**
 * @描述 : 发送订阅通知_列表项
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 16:49:48
 */
public class _List {

	private String TemplateId;		//模板 id（一次订阅可能有多条通知，带有多个 id）
	private String MsgID;				//消息 id
	private String ErrorCode;			//推送结果状态码（0表示成功）
	private String ErrorStatus;		//推送结果状态码文字含义
	
	public String getTemplateId() {
		return TemplateId;
	}
	public void setTemplateId(String templateId) {
		TemplateId = templateId;
	}
	public String getMsgID() {
		return MsgID;
	}
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}
	public String getErrorCode() {
		return ErrorCode;
	}
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}
	public String getErrorStatus() {
		return ErrorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		ErrorStatus = errorStatus;
	}
	
}
