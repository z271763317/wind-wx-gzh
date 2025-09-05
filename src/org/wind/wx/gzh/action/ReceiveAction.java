package org.wind.wx.gzh.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wind.wx.gzh.bean.GZH;
import org.wind.wx.gzh.bean.message.request.Request;
import org.wind.wx.gzh.bean.message.request.event.Request_event;
import org.wind.wx.gzh.bean.message.request.event.Request_event_click;
import org.wind.wx.gzh.bean.message.request.event.Request_event_clickView;
import org.wind.wx.gzh.bean.message.request.event.Request_event_location;
import org.wind.wx.gzh.bean.message.request.event.Request_event_qrCodeSubscribe;
import org.wind.wx.gzh.bean.message.request.event.Request_event_qrCodeUnsubscribe;
import org.wind.wx.gzh.bean.message.request.event.Request_event_subscribeMsgChangeEvent;
import org.wind.wx.gzh.bean.message.request.event.Request_event_subscribeMsgPopupEvent;
import org.wind.wx.gzh.bean.message.request.event.Request_event_subscribeMsgSentEvent;
import org.wind.wx.gzh.bean.message.request.event.Request_event_templateSendFinish;
import org.wind.wx.gzh.bean.message.request.event.Request_event_userSubscribe;
import org.wind.wx.gzh.bean.message.request.event.Request_event_userUnsubscribe;
import org.wind.wx.gzh.bean.message.request.standard.Request_standard;
import org.wind.wx.gzh.init.Init_scan;
import org.wind.wx.gzh.service.GZHService;
import org.wind.wx.gzh.service.ReceiveService;
import org.wind.wx.gzh.service.message.WX;
import org.wind.wx.gzh.service.message.event.WX_event;
import org.wind.wx.gzh.service.message.event.WX_event_click;
import org.wind.wx.gzh.service.message.event.WX_event_clickView;
import org.wind.wx.gzh.service.message.event.WX_event_location;
import org.wind.wx.gzh.service.message.event.WX_event_qrCodeSubscribe;
import org.wind.wx.gzh.service.message.event.WX_event_qrCodeUnsubscribe;
import org.wind.wx.gzh.service.message.event.WX_event_subscribeMsgChangeEvent;
import org.wind.wx.gzh.service.message.event.WX_event_subscribeMsgPopupEvent;
import org.wind.wx.gzh.service.message.event.WX_event_subscribeMsgSentEvent;
import org.wind.wx.gzh.service.message.event.WX_event_templateSendFinish;
import org.wind.wx.gzh.service.message.event.WX_event_userSubscribe;
import org.wind.wx.gzh.service.message.event.WX_event_userUnsubscribe;
import org.wind.wx.gzh.util._LogUtil;
import org.wind.wx.gzh.util._ObjectUtil;
import org.wind.wx.gzh.util._ValidateUtil;
import org.wind.wx.gzh.util._XMLTextUtil;
import org.xml.sax.SAXException;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * @描述 : 接收微信发送（推送）来的消息（如：服务器验证、用户关注、被动用户回复【用户向公众号发送的信息】、客服消息等）
 * @作者 : 胡璐璐
 * @时间 : 2021年1月6日 14:51:49
 */
@SuppressWarnings({"rawtypes","unchecked"})
@WebServlet(value="/receive",loadOnStartup=2)
public class ReceiveAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/***********消息类型***********/
	public static final String standard="standard";		//普通消息
	public static final String event="event";		//事件推送
	
	/*service*/
	private static final String serviceName=WX.class.getSimpleName();		//主service名
	private static final String servicePackage=WX.class.getPackage().getName();		//主
	/*request路径前缀*/
	private static final String requestPathPrefix_standard=Request_standard.class.getName()+"_";		//普通消息
	
	/*格式*/
	private static final String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
	
	/*服务实现类*/
	public static final Map<Class<?>,Class<?>> serviceImplClassMap=new ConcurrentHashMap<Class<?>, Class<?>>(); 
	
	/**初始化**/
     public void init() throws ServletException {
    	 try {
 			/*初始化*/
 			new Init_scan().init();
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
     }
	/**get**/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    /**post**/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String echostr=request.getParameter("echostr");		//echostr
    	String timestamp=request.getParameter("timestamp");		//时间戳
    	String nonce=request.getParameter("nonce");		//随机数
    	String resultData=null;
    	//
    	//验证服务器
    	if(echostr!=null) {
	    	String signature=request.getParameter("signature");		//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	    	//
	    	_ValidateUtil.notEmpty(signature, "缺少【signature】");
	    	_ValidateUtil.notEmpty(timestamp, "缺少【timestamp】");
	    	_ValidateUtil.notEmpty(nonce, "缺少【nonce】");
	    	_ValidateUtil.notEmpty(echostr, "缺少【echostr】");
	    	//
	    	Class<GZHService> gzhServiceClass=getServiceImplClass(GZHService.class);
	    	if(gzhServiceClass!=null) {
				try {
					GZHService gzhService = _ObjectUtil.getSingleton(gzhServiceClass);
		    		Map<String,GZH> list=gzhService.all();
		    		for(GZH obj:list.values()) {
				    	//
				    	String token=obj.getToken();
				    	Boolean status=obj.getVerifySuccess();
				    	if(!status) {
					    	/**验证参数**/
							if (checkSignature(token,signature, timestamp, nonce)) {
								/**返回echostr内容，代表接入成功**/
								resultData=echostr;
								obj.setVerifySuccess(true);
								gzhService.verifySuccess(obj);
							}
				    	}
		    		}
				} catch (Exception e) {
					_LogUtil.service(e);
				}
	    	}
		//业务事件推送（如：用户关注）
    	}else{
			BufferedReader br = null;
			StringBuilder messageData_encrypt = new StringBuilder();
			try {
				request.setCharacterEncoding("UTF-8");
				br = request.getReader();
				String str;
				while ((str = br.readLine())!= null) {
					messageData_encrypt.append(str);
				}
//				br.close();
				/*前置处理*/
				Class<ReceiveService> receiveServiceImplClass=getServiceImplClass(ReceiveService.class);
		    	if(receiveServiceImplClass!=null) {
		    		ReceiveService receiveService=_ObjectUtil.getSingleton(receiveServiceImplClass);
		    		boolean isHandler=receiveService.service(request, response, messageData_encrypt.toString());
		    		if(isHandler) {
		    			return;
		    		}
		    	}
				
				/**消息解密（如果有开启消息加密）**/
				String encrypt_type=request.getParameter("encrypt_type");		//加密类型（一般为：aes）
		    	String msg_signature=request.getParameter("msg_signature");		//消息体签名，用于验证消息体的正确性
		    	//
		    	String messageData=null;		//原文
		    	/*AES*/
		    	if("AES".equalsIgnoreCase(encrypt_type)) {
		    		Map<String,Object> map=_XMLTextUtil.getAllText(messageData_encrypt.toString());
					Map<String,Object> xmlMap=(Map<String, Object>) map.get("xml");
					if(xmlMap!=null) {
						String ToUserName=(String) xmlMap.get("ToUserName");		//原始ID（原始微信号）
						Class<GZHService> gzhServiceImplClass=getServiceImplClass(GZHService.class);
				    	if(gzhServiceImplClass!=null) {
				    		GZHService gzhService=_ObjectUtil.getSingleton(gzhServiceImplClass);
				    		GZH obj=gzhService.get(ToUserName);
				    		if(obj!=null) {
				    			String token=obj.getToken();
				    			String encodingAesKey=obj.getEncodingAesKey();
				    			String appId=obj.getAppId();
					    		//
								String encrypt=(String) xmlMap.get("Encrypt");
					    		String fromXML = String.format(format, encrypt);
					    		//
					    		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
					    		messageData = pc.decryptMsg(msg_signature, timestamp, nonce, fromXML);
				    		}
				    	}
					}
		    	}else{
		    		messageData=messageData_encrypt.toString();
		    	}
		    	//
				if(messageData!=null) {
					try {
						Map<String,Object> map=_XMLTextUtil.getAllText(messageData);
						Map<String,Object> xmlMap=(Map<String, Object>) map.get("xml");
						if(xmlMap!=null) {
							resultData=service(xmlMap);
						}else{
							_LogUtil.service("数据格式不正确："+messageData);
						}
					}catch(SAXException e) {
						_LogUtil.service("数据解析失败（明文）："+messageData);
					}
				}else{
					_LogUtil.service("数据解析失败（密文）："+messageData_encrypt);
				}
			} catch(ClassNotFoundException e) {
				_LogUtil.service(e);
			} catch (Exception e) {
				_LogUtil.service(e);
			} finally {
//				if (null != br) {try {br.close();} catch (IOException e) {}}
			}
    	}
    	if(resultData==null) {
    		resultData="";
    	}
    	/**返回结果**/
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultData);
    }
    
    /**
    * 微信请求验证
    * @param token : 对应公众号自定义的token
    * @param signature 微信签名请求
    * @param timestamp 时间戳（日期的整形表示方式）
    * @param nonce 随机数
    * @return
    */
	private static boolean checkSignature(String token,String signature, String timestamp, String nonce) {
		// 1.将token,timestamp,nonce三个字符装入数组
		String[] arr = new String[] {token, timestamp, nonce};

		// 实现按字典顺序排序
		Arrays.sort(arr);

		// 2.将排序后的数组拼接成一个字符串
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			builder.append(arr[i]);
		}
		_LogUtil.service("拼接后的字符串：" + builder);
		String result = null;
		try {
			// 3.对拼接后的字符串进行sha1加密
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(builder.toString().getBytes());
			// 将字节数组转成密文字符串
			result = byteToString(digest);
			_LogUtil.service("加密后的字符串：" + result);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 4.将加密后的字符串与微信服务器传过来的签名进行对比
		return result != null ? (result.equals(signature.toUpperCase())) : false;
	}
	
    //实现将字节数组转换成为十六进制字符串
    private static String byteToString(byte[] str){
		String strDigest = "";
		for (int i = 0; i < str.length; i++) {
			strDigest += byteToHexStr(str[i]);
		}
		return strDigest;
	}

	//实现将每一个字节转换成十六进制字符串
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] temp = new char[2];
		temp[0] = Digit[(mByte >>> 4) & 0X0F];
		temp[1] = Digit[mByte & 0X0F];
		String s = new String(temp);// 构建一个字符串
		return s;
	}
	
	/***************消息处理***************/
	private static String service(Map<String,Object> xmlMap) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String MsgType=(String) xmlMap.get("MsgType");
		//
		WX service_MsgType=null;
		Request request_MsgType=null;
		if(MsgType!=null) {
			/*事件推送*/
			if(MsgType.equals("event")) {
				String Event=(String) xmlMap.get("Event");
				Event=Event!=null?Event.trim():null;
				if(Event!=null) {
					switch(Event) {
						/**用户关注、二维码（未关注）**/
						case "subscribe":{
							String Ticket=(String) xmlMap.get("Ticket");
							Ticket=Ticket!=null?Ticket.trim():null;
							/*二维码（未关注）*/
							if(Ticket!=null) {
								service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_qrCodeUnsubscribe.class));
								request_MsgType=_ObjectUtil.get(Request_event_qrCodeUnsubscribe.class);
							/*用户关注*/
							}else{
								service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_userSubscribe.class));
								request_MsgType=_ObjectUtil.get(Request_event_userSubscribe.class);
							}
							break;
						}
						/**用户取消关注**/
						case "unsubscribe":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_userUnsubscribe.class));
							request_MsgType=_ObjectUtil.get(Request_event_userUnsubscribe.class);
							break;
						}
						/**二维码（用户已关注）**/
						case "SCAN":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_qrCodeSubscribe.class));
							request_MsgType=_ObjectUtil.get(Request_event_qrCodeSubscribe.class);
							break;
						}
						/**地理位置**/
						case "LOCATION":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_location.class));
							request_MsgType=_ObjectUtil.get(Request_event_location.class);
							break;
						}
						/**自定义菜单（点击）**/
						case "CLICK":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_click.class));
							request_MsgType=_ObjectUtil.get(Request_event_click.class);
							break;
						}
						/**自定义菜单（跳转链接）**/
						case "VIEW":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_clickView.class));
							request_MsgType=_ObjectUtil.get(Request_event_clickView.class);
							break;
						}
						/**模版消息送达完成**/
						case "TEMPLATESENDJOBFINISH":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_templateSendFinish.class));
							request_MsgType=_ObjectUtil.get(Request_event_templateSendFinish.class);
							break;
						}
						
						/*************订阅通知*************/
						/**用户操作订阅通知弹窗**/
						case "subscribe_msg_popup_event":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_subscribeMsgPopupEvent.class));
							request_MsgType=_ObjectUtil.get(Request_event_subscribeMsgPopupEvent.class);
							break;
						}
						/**用户管理订阅通知**/
						case "subscribe_msg_change_event":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_subscribeMsgChangeEvent.class));
							request_MsgType=_ObjectUtil.get(Request_event_subscribeMsgChangeEvent.class);
							break;
						}
						/**发送订阅通知**/
						case "subscribe_msg_sent_event":{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event_subscribeMsgSentEvent.class));
							request_MsgType=_ObjectUtil.get(Request_event_subscribeMsgSentEvent.class);
							break;
						}
						/**其他**/
						default:{
							service_MsgType=_ObjectUtil.getSingleton(getServiceImplClass(WX_event.class));
							request_MsgType=_ObjectUtil.get(Request_event.class);
						}
					}
				}
			/*普通消息*/
			}else if(xmlMap.containsKey("MsgId")){
				service_MsgType=(WX) _ObjectUtil.getSingleton(getServiceImplClass(Class.forName((servicePackage+"."+standard+"."+serviceName+"_"+standard+"_"+MsgType))));
				request_MsgType=_ObjectUtil.get(requestPathPrefix_standard+MsgType);
			}
			//有服务对象
			if(service_MsgType!=null) {
				_ObjectUtil.setProperty(xmlMap, request_MsgType);
				request_MsgType.setXmlMap(xmlMap);
				String result=service_MsgType.service(request_MsgType);
				if(result!=null) {
					return result.toString();
				}
			}else{
				_LogUtil.service("没有找到服务实现类");
				_LogUtil.service(xmlMap.toString());
			}
		}
		return null;
	}
	
	//获取 : 服务实现类
	private static <T> Class<T> getServiceImplClass(Class<T> clazz) {
		return (Class<T>) serviceImplClassMap.get(clazz);
	}
}
