package org.wind.wx.gzh.init;

import java.util.ArrayList;
import java.util.List;

import org.wind.wx.gzh.action.ReceiveAction;
import org.wind.wx.gzh.annotation.An_ScanInterface;
import org.wind.wx.gzh.service.GZHService;
import org.wind.wx.gzh.service.message.WX;
import org.wind.wx.gzh.util._FileOS;
import org.wind.wx.gzh.util._LogUtil;
import org.wind.wx.gzh.util._SysConstant;

/**
 * @描述 : 初始化_扫描器
 * @详情 : 扫描微信推送的消息服务处理等。
 * @作者 : 胡璐璐
 * @时间 : 2021年1月28日 13:25:20
 */
public class Init_scan extends Init{
	
	/**构造方法**/
	public Init_scan() {
		
	}
	
	/**初始化**/
	public void init() throws Exception{
		/**扫描**/
		try {
			String projectPath=_FileOS.getProjectPath(null, 0);
			List<List<Class<?>>> allClassList=new ArrayList<List<Class<?>>>();
			/*处理服务（解析微信推送的消息后，将对应消息的数据传给对应的处理服务）*/
	    	StringBuffer servicePath=new StringBuffer(projectPath);
	    	String scan_servicePackage=_SysConstant.scan_servicePackage;		//扫描的目录
	    	if(scan_servicePackage!=null) {
	    		servicePath.append(scan_servicePackage.trim().replace(".", "/")).append("/");
	    	}
	    	List<Class<?>> classList=_FileOS.getAllClassList(servicePath.toString(), scan_servicePackage);
	    	allClassList.add(classList);
	    	//
	    	for(List<Class<?>> t_classList:allClassList) {
	    		for(Class<?> t_class_main:t_classList) {
	    			//不允许是接口类
	    			if(!t_class_main.isInterface()) {
		    			Class<?> interfaceClassArr[]=t_class_main.getInterfaces();		//接口集
		    			for(Class<?> t_class_interface:interfaceClassArr) {
				    		/*消息、公众号服务、前置处理等*/
				    		if(WX.class.isAssignableFrom(t_class_interface) || t_class_interface.isAnnotationPresent(An_ScanInterface.class)) {
				    			//重复
				    			if(ReceiveAction.serviceImplClassMap.containsKey(t_class_interface)) {
				    				_LogUtil.service("【"+t_class_interface.getSimpleName()+"】重复注册："+t_class_main.getName());
				    			//首次
				    			}else{
				    				ReceiveAction.serviceImplClassMap.put(t_class_interface, t_class_main);
				    				_LogUtil.service("【"+t_class_interface.getSimpleName()+"】注册成功："+t_class_main.getName());
				    				break;
				    			}
				    		}
		    			}
	    			}
		    	}
	    	}
	    	
	    	/**********验证*********/
	    	_LogUtil.service("验证服务是否有实现类");
	    	//公众号接口
	    	if(!ReceiveAction.serviceImplClassMap.containsKey(GZHService.class)) {
	    		_LogUtil.service("类【"+GZHService.class.getSimpleName()+"】没有实现");
	    	}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
