package org.wind.wx.gzh.bean.message.request.standard;

/**
 * @描述 : 【请求类】消息类型__消息_视频
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 20:25:02
 */
public class Request_standard_video extends Request_standard{

	private String MediaId;		//视频消息媒体id，可以调用获取临时素材接口拉取数据
	private String ThumbMediaId;		//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
