package org.wind.wx.gzh.bean.message.request.event.subscribeMsgPopupEvent;

/**
 * @描述 : 用户操作订阅通知弹窗_列表项
 * @作者 : 胡璐璐
 * @时间 : 2021年2月10日 16:00:02
 */
public class _List {

	private String TemplateId;		//模板 id（一次订阅可能有多条通知，带有多个 id）
	private String SubscribeStatusString;		//用户点击行为（同意、取消发送通知）——accept=用户点击“同意”；reject=用户点击“取消”
	private String PopupScene;		//场景——1=弹窗来自 H5 页面；2=弹窗来自图文消息
	
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
	public String getPopupScene() {
		return PopupScene;
	}
	public void setPopupScene(String popupScene) {
		PopupScene = popupScene;
	}
	
}
