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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @描述 : XML工具类
 * @作者 : 胡璐璐
 * @时间 : 2021年1月27日 20:36:17
 */
public class _XMLUtil{

	public static void main(String[] args) {
		String s="<xml ds=\"11111\" fd='sdf65s4d5f'>\r\n" + 
				"  <ToUserName><![CDATA[toUser]]></ToUserName>\r\n" + 
				"  <FromUserName><![CDATA[FromUser]]></FromUserName>\r\n" + 
				"  <CreateTime>123456789</CreateTime>\r\n" + 
				"  <MsgType><![CDATA[event]]></MsgType>\r\n" +
				"  <Event><![CDATA[subscribe]]></Event>\r\n" +
				"  <ttt><ttt1>1</ttt1><ttt1>2</ttt1></ttt>\r\n" + 
				"</xml>\r\n" + 
				"";
		try {
			_XMLNode map=get(s);
			System.out.println(map.getName());
			System.out.println(map.getChildType());
			System.out.println(map.getChild());
			System.out.println(map.getAttributeMap());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**获取 : 主节点（字符串式）**/
	public static _XMLNode get(String data) throws ParserConfigurationException, SAXException, IOException{
		ByteArrayInputStream bais=new ByteArrayInputStream(data.getBytes());
		return get(bais);
	}
	/**获取 : 主节点（项目根根文件路径式，web项目则是classes，工程则是src下）**/
	public static _XMLNode get_rootFile(String filePath) throws ParserConfigurationException, SAXException, IOException{
		URL fileURL=Thread.currentThread().getContextClassLoader().getResource(filePath);
	    String filePath_absolute=java.net.URLDecoder.decode(fileURL.getFile(),"UTF-8");
		return get(new File(filePath_absolute));
	}
	/**获取 : 主节点（File式）**/
	public static _XMLNode get(File file) throws ParserConfigurationException, SAXException, IOException{
		InputStream is=null;
		BufferedInputStream bis=null;
		try {
			is=new FileInputStream(file);
			bis=new BufferedInputStream(is);
			_XMLNode obj=get(bis);
			return obj;
		}finally{
			if(bis!=null) {try {bis.close();}catch(IOException e) {}}
			if(is!=null) {try {is.close();}catch(IOException e) {}}
		}
	}
	/**获取 : 主节点（IO流式）**/
	public static _XMLNode get(InputStream is) throws ParserConfigurationException, SAXException, IOException{
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）   
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();   
        // step 2:获得具体的dom解析器   
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document=db.parse(is);
        return get(document);
	}
	/**获取 : 主节点（Document对象式）**/
	public static _XMLNode get(Document document){
		return getNode(document.getDocumentElement());
	}
	//获取 : 节点
	private static _XMLNode getNode(Node node) {
		 //是元素
        if(node.getNodeType()==Node.ELEMENT_NODE){
        	_XMLNode obj=new _XMLNode();
        	obj.setName(node.getNodeName());
        	/*属性*/
			Map<String,String> attributeMap=new HashMap<String,String>(); 
			NamedNodeMap attributeNodeMap=node.getAttributes();		//属性节点Map
			for(int k=0;attributeNodeMap!=null && k<attributeNodeMap.getLength();k++){
	          	Node t_child_node=attributeNodeMap.item(k);
	          	String t_key= t_child_node.getNodeName();		//属性名
	          	String t_value=t_child_node.getNodeValue();
	          	//
	          	attributeMap.put(t_key, t_value);
	        }
			obj.setAttributeMap(attributeMap);
        	//
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
        	/*子元素（该节点若拥有子元素）*/
    		if(childNodeList.size()>0){
    			//列表
    			if(isListNodeSet.size()==1 && childNodeList.size()>1) {
	    			List<_XMLNode> list=new ArrayList<_XMLNode>();
	        		for(int k=0;k<childNodeList.size();k++){
	    			    Node child_node=childNodeList.get(k);
	    			    _XMLNode childObj= getNode(child_node);
	    			    list.add(childObj);
	    			}
	        		obj.setChildType(_XMLNode.childType_List);
	        		obj.setChild(list);
	        	//Map
    			}else{
    				Map<String,_XMLNode> map=new HashMap<String, _XMLNode>();
	        		for(int k=0;k<childNodeList.size();k++){
	    			    Node child_node=childNodeList.get(k);
	    			    String nodeName=child_node.getNodeName();
	    			    _XMLNode childObj= getNode(child_node);
	    			    map.put(nodeName,childObj);
	    			}
	        		obj.setChildType(_XMLNode.childType_Map);
	        		obj.setChild(map);
    			}
    		/*没有子元素*/
    		}else{
    			obj.setText(node.getTextContent());
    		}
    		return obj;
        }
		return null;
	}
	
	/**获取 : map里的list**/
	public static List<_XMLNode> getNodeList(Map<String,List<_XMLNode>> childNodeListMap,String tagName) {
		return childNodeListMap.get(tagName);
	}
	/**获取 : map里的list的第1个节点**/
	public static _XMLNode getNode(Map<String,List<_XMLNode>> childNodeListMap,String tagName) {
		List<_XMLNode> scanList=getNodeList(childNodeListMap, tagName);
		if(scanList!=null && scanList.size()>0) {
			return scanList.get(0);
		}
		return null;
	}
	
}