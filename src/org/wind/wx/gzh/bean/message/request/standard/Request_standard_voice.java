package org.wind.wx.gzh.bean.message.request.standard;

/**
 * @描述 : 【请求类】消息类型__消息_语音
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 20:36:22
 */
public class Request_standard_voice extends Request_standard{

	private String MediaId;		//语音消息媒体id，可以调用获取临时素材接口拉取数据。
	private String Format;			//语音格式，如：amr（开通语音识别后，只有这1个格式），speex等
	
	/*开通语音识别*/
	private String Recognition;		//语音识别结果，UTF8编码

	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	
}
