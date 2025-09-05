package org.wind.wx.gzh.util;

import java.util.List;
import java.util.Map;

/**
 * @描述 : XML的节点信息
 * @作者 : 胡璐璐
 * @时间 : 2021年1月27日 20:43:16
 */
@SuppressWarnings("unchecked")
public class _XMLNode {

	/*子节点类型*/
	public static final int childType_List=1;			//List
	public static final int childType_Map=2;		//Map
	
	private String name;		//节点名（标签名）
	private String text;		//节点文本内容（如果有子节点，这里为空）
	private Map<String,String> attributeMap;		//属性Map（key=属性名；value=属性值）
	//
	private Integer childType;		//子节点类型（NULL=无子节点；1=List【数组】；2=Map）
	private Object child;		//子节点（根据isChildList走）
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}
	public void setAttributeMap(Map<String, String> attributeMap) {
		this.attributeMap = attributeMap;
	}
	public Integer getChildType() {
		return childType;
	}
	public void setChildType(Integer childType) {
		this.childType = childType;
	}
	/**List列表（childType=1时返回，否则将报错）**/
	public List<_XMLNode>  getChildList() {
		return (List<_XMLNode>) child;
	}
	/**Map（childType=2时返回，否则将报错）**/
	public Map<String,_XMLNode>  getChildMap() {
		return (Map<String, _XMLNode>) child;
	}
	public void setChild(Object child) {
		this.child = child;
	}
	public Object getChild() {
		return child;
	}
	
}