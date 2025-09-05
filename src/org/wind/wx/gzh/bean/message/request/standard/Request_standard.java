package org.wind.wx.gzh.bean.message.request.standard;

import org.wind.wx.gzh.bean.message.request.Request;

/**
 * @描述 : 【请求类】消息类型_消息
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 20:25:02
 */
public class Request_standard extends Request{

	private String MsgId;			//消息id，64位整型（可做消息排重处理）

	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
}