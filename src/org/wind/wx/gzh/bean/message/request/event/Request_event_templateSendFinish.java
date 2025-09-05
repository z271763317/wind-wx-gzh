package org.wind.wx.gzh.bean.message.request.event;

/**
 * @描述 : 【请求类】消息类型_事件_模版发送完成
 * @作者 : 胡璐璐
 * @时间 :2021年10月1日 09:09:46
 */
public class Request_event_templateSendFinish extends Request_event{

	/**
	 * 发送状态为成功<br />
	 * uccess=成功<br />
	 * failed:user block=失败（failed代表失败，后面的是失败原因）
	 */
	private String Status;		//

	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
