package org.wind.wx.gzh.bean;

/**
 * @描述 : 公众号信息
 * @作者 : 胡璐璐
 * @时间 : 2021年2月20日 15:45:36
 */
public class GZH {

	private Boolean verifySuccess;		//验证成功状态（验证公众号后台设置的URL，即服务器地址的是否有效）
	//
	private String wechatId;	//微信号
	private String appId;
	private String token;
	private String encodingAesKey;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEncodingAesKey() {
		return encodingAesKey;
	}
	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}
	public Boolean getVerifySuccess() {
		return verifySuccess;
	}
	public void setVerifySuccess(Boolean verifySuccess) {
		this.verifySuccess = verifySuccess;
	}
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	
}
