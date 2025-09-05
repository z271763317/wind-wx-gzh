package org.wind.wx.gzh.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @描述 : XML工具类（文本内容）
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 11:25:17
 */
public final class _XMLTextUtil{
	
	public static void main(String[] args) {
		String s="<aa>\r\n" + 
				"  <ToUserName><![CDATA[toUser]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA[FromUser]]></FromUserName>\r\n" + 
				"  <CreateTime>123456789</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[event]]></MsgType>\r\n" +
				"  <Event><![CDATA[subscribe]]></Event>\r\n" +
				"  <ttt><ttt1>1</ttt1><ttt1>2</ttt1></ttt>\r\n" + 
				"</aa>\r\n" + 
				"";
		try {
			Map<String,Object> map=getAllText(s);
			System.out.println(map);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**********************value为text文本式**********************/
	/**获取 : 所有并解析成map（字符串式）**/
	public static Map<String,Object> getAllText(String data) throws ParserConfigurationException, SAXException, IOException{
		ByteArrayInputStream bais=new ByteArrayInputStream(data.getBytes("UTF-8"));
		return getAllText(bais);
	}
	/**获取 : 所有并解析成map（项目根根文件路径式，web项目则是classes，工程则是src下）**/
	public static Map<String,Object> getAllText_rootFile(String filePath) throws ParserConfigurationException, SAXException, IOException{
		URL fileURL=Thread.currentThread().getContextClassLoader().getResource(filePath);
	    String filePath_absolute=java.net.URLDecoder.decode(fileURL.getFile(),"UTF-8");
		return getAllText(new File(filePath_absolute));
	}
	/**获取 : 所有并解析成map（File式）**/
	public static Map<String,Object> getAllText(File file) throws ParserConfigurationException, SAXException, IOException{
		InputStream is=null;
		BufferedInputStream bis=null;
		try {
			is=new FileInputStream(file);
			bis=new BufferedInputStream(is);
			return getAllText(bis);
		}finally{
			if(bis!=null) {try {bis.close();}catch(IOException e) {}}
			if(is!=null) {try {is.close();}catch(IOException e) {}}
		}
	}
	/**获取 : 所有并解析成map（IO流式）**/
	public static Map<String,Object> getAllText(InputStream is) throws ParserConfigurationException, SAXException, IOException{
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）   
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();   
        // step 2:获得具体的dom解析器   
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document=db.parse(is);
        return getAllText(document);
	}
	/**获取 : 所有并解析成map（Document对象式）**/
	public static Map<String,Object> getAllText(Document document){
		Node node=document.getDocumentElement();
		String nodeName=node.getNodeName();
		Map<String,Object> resultMap=new HashMap<String, Object>();
		resultMap.put(nodeName, getAllText(document.getDocumentElement()));
		return resultMap;
	}
	/**
	 * 获取 : 所有并解析成map。Node对象式<br />
	 * 说明：层级感依据原文形式，key=标签名；value=标签text文本（若是子列表，则该值为List&lt;Map&lt;String,Object&gt;&gt;，深层次到底）<br/>
	 * 			 子节点如果全是同样的标签名，则是List数组结构，否则Map结构，重复的则会被替换
	 * @return 返回Object。如果有子节点，则返回List或Map。否贼为纯text文本
	 */
	public static Object getAllText(Node node){
        //是元素
        if(node.getNodeType()==Node.ELEMENT_NODE){
        	NodeList nodeList=node.getChildNodes();	//子节点
        	List<Node> childNodeList=new ArrayList<Node>();
        	Set<String> isListNodeSet=new HashSet<String>();	//存储所有子节点标签名。如果有多个，则是列表结构（子节点如果全是同样的标签名，则是List数组结构，否则Map结构）
        	for(int k=0;nodeList!=null && k<nodeList.getLength();k++){
			    Node child_node=nodeList.item(k);
			    //子元素
			    if(child_node.getNodeType()==Node.ELEMENT_NODE){
			    	childNodeList.add(child_node);
			    	isListNodeSet.add(child_node.getNodeName());
			    }
			}
        	//子元素（该节点若拥有子元素）
    		if(childNodeList.size()>0){
    			//列表
    			if(isListNodeSet.size()==1 && childNodeList.size()>1) {
	    			List<Object> list=new ArrayList<Object>();
	        		for(int k=0;k<childNodeList.size();k++){
	    			    Node child_node=childNodeList.get(k);
	    			    Object contenrt= getAllText(child_node);
	    			    list.add(contenrt);
	    			}
	        		return list;
	        	//Map
    			}else{
    				Map<String,Object> map=new HashMap<String, Object>();
	        		for(int k=0;k<childNodeList.size();k++){
	    			    Node child_node=childNodeList.get(k);
	    			    String nodeName=child_node.getNodeName();
	    			    Object contenrt= getAllText(child_node);
	    			    map.put(nodeName,contenrt);
	    			}
	        		return map;
    			}
    		}else{
    			return node.getTextContent();
    		}
        }
		return null;
	}
	
}