package org.wind.wx.gzh.bean.message.request.event.subscribeMsgChangeEvent;

/**
 * @描述 : 用户管理订阅通知_列表项
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 16:00:02
 */
public class _List {

	private String TemplateId;		//模板 id（一次订阅可能有多条通知，带有多个 id）
	private String SubscribeStatusString;		//用户点击行为（仅推送用户拒收通知）——reject=用户点击“取消”
	
	public String getTemplateId() {
		return TemplateId;
	}
	public void setTemplateId(String templateId) {
		TemplateId = templateId;
	}
	public String getSubscribeStatusString() {
		return SubscribeStatusString;
	}
	public void setSubscribeStatusString(String subscribeStatusString) {
		SubscribeStatusString = subscribeStatusString;
	}
	
}
