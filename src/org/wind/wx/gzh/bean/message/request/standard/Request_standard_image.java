package org.wind.wx.gzh.bean.message.request.standard;

/**
 * @描述 : 【请求类】消息类型__消息_图片
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 20:25:02
 */
public class Request_standard_image extends Request_standard{

	private String PicUrl;			//图片链接（由系统生成）
	private String MediaId;		//图片消息媒体id，可以调用获取临时素材接口拉取数据

	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
