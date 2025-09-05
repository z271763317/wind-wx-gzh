package org.wind.wx.gzh.service;

import java.util.Map;

import org.wind.wx.gzh.annotation.An_ScanInterface;
import org.wind.wx.gzh.bean.GZH;

/**
 * @描述 : 公众号接口（用于获取所有或指定的公众号信息）
 * @作者 : 胡璐璐
 * @时间 : 2021年2月20日 15:38:52
 */
@An_ScanInterface
public interface GZHService {

	/**返回所有公众号信息**/
	public Map<String,GZH> all();
	
	/**返回指定公众号信息（ToUserName。根据公众号的原始ID【原始微信号】）**/
	public GZH get(String userName);
	
	/**指定的公众号的服务器地址（URL，在微信后台设置的URL）验证成功，请更改该数据的状态**/
	public void verifySuccess(GZH obj);
	
}
