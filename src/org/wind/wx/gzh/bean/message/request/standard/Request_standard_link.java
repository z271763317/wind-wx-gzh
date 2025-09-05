package org.wind.wx.gzh.bean.message.request.standard;

/**
 * @描述 : 【请求类】消息类型__消息_链接
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 20:25:02
 */
public class Request_standard_link extends Request_standard{

	private String Title;		//消息标题
	private String Description;		//消息描述
	private String Url;		//消息链接
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
}