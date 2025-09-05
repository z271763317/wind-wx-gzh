package org.wind.wx.gzh.bean.message.request;

import java.util.Map;

import org.wind.wx.gzh.annotation.An_Param;

/**
 * @描述 : 【请求类】消息类型
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 17:44:23
 */
public class Request {

	private Map<String,Object> xmlMap;			//原始数据Map
	
	/**开发者微信号**/
	@An_Param(value="ToUserName",isRequired=true)
	private String ToUserName;
	/**发送方帐号（一个OpenID）**/
	@An_Param(value="FromUserName",isRequired=true)
	private String FromUserName;
	/**消息创建时间 （整型）**/
	@An_Param(value="CreateTime",isRequired=true)
	private String CreateTime;
	/**消息类型**/
	@An_Param(value="MsgType",isRequired=true)
	private String MsgType;
		
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Map<String, Object> getXmlMap() {
		return xmlMap;
	}
	public void setXmlMap(Map<String, Object> xmlMap) {
		this.xmlMap = xmlMap;
	}
	
}