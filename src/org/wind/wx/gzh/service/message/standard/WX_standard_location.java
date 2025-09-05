package org.wind.wx.gzh.service.message.standard;

import org.wind.wx.gzh.bean.message.request.standard.Request_standard_location;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_普通_地理位置
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 17:41:07
 */
public interface WX_standard_location extends WX<Request_standard_location>{

	public String service(Request_standard_location param);

}
