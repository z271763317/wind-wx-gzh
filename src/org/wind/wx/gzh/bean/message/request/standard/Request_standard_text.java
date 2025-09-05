package org.wind.wx.gzh.bean.message.request.standard;

/**
 * @描述 : 【请求类】消息类型__消息_纯文本
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 20:25:02
 */
public class Request_standard_text extends Request_standard{

	private String Content;		//文本消息内容

	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}
