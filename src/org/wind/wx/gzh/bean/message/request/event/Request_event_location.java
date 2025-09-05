package org.wind.wx.gzh.bean.message.request.event;

/**
 * @描述 : 【请求类】消息类型_事件_上报地理位置
 * @作者 : 胡璐璐
 * @时间 : 2021年2月6日 12:50:27
 */
public class Request_event_location extends Request_event{

	private String Latitude;		//地理位置纬度
	private String Longitude;		//地理位置经度
	private String Precision;		//地理位置精度经度
	
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	
}
