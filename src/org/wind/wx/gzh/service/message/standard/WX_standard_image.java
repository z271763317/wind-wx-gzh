package org.wind.wx.gzh.service.message.standard;

import org.wind.wx.gzh.bean.message.request.standard.Request_standard_image;
import org.wind.wx.gzh.service.message.WX;

/**
 * @描述 : 消息类型_普通_图片
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 17:41:07
 */
public interface WX_standard_image extends WX<Request_standard_image>{

	public String service(Request_standard_image param);

}
