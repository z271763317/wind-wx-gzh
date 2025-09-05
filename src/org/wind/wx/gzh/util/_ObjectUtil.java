package org.wind.wx.gzh.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.wind.wx.gzh.annotation.An_Param;

/**
 * @描述 : 对象工具类
 * @作者 : 胡璐璐
 * @时间 : 2021年1月18日 11:08:07
 */
@SuppressWarnings("unchecked")
public class _ObjectUtil {

	/*缓存数据*/
	private static final Map<Class<?>,?> objectMap=new ConcurrentHashMap<Class<?>, Object>();
	private static final Map<Class<?>,Field[]> fieldMap=new ConcurrentHashMap<Class<?>, Field[]>();
	
	/**获取 : 指定className（java格式的路径）的对象**/
	public static <T> T get(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (T) get(Class.forName(className));	
	}
	/**获取 : 指定class的对象**/
	public static <T> T get(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();		//实例化
	}
	/**获取 : 指定class的单例对象**/
	public static <T> T getSingleton(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class<?> clazz=get(className);
		return (T) getSingleton(clazz);
	}
	/**获取 : 指定class的单例对象**/
	public static <T> T getSingleton(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		if(clazz!=null) {
			T result=(T) objectMap.get(clazz);
			if(result==null) {
				synchronized (clazz) {
					result=(T) objectMap.get(clazz);
					if(result==null) {
						result=get(clazz);	//实例化
					}
				}
			}
			return result;
		}
		return null;
	}
	
	/**
	 * 设置 : 指定对象的成员属性（变量），深层次父类到Object截止。<br/>
	 * 			<t style="margin-left:34px">
	 * 				基本数据类型，会转换成对应的变量类型（如：value为String，变量类型为Integer，则value会转成Integer）
	 * 			</t>
	 * @param propertyValueMap : 成员属性（变量）对应的值（key=属性（变量）名；value=值）
	 * @param object : 指定的对象
	 */
  	public static void setProperty(Map<String,Object> propertyValueMap,Object object) throws IllegalAccessException{
  		Class<?> clazz=object.getClass();
  		/*字段*/
  		Field fieldArr[]=fieldMap.get(clazz);
  		if(fieldArr==null) {
  			fieldArr=getField(clazz, Object.class);
  			fieldMap.put(clazz, fieldArr);
  		}
  		out:for(Field t_field:fieldArr){
  			t_field.setAccessible(true);		//取消安全检查
			An_Param t_apiParam=t_field.getAnnotation(An_Param.class);
			String t_key=null;
			boolean isRequired=false;
			//有An_Param注解，且有映射名
			if(t_apiParam!=null){
				t_key=t_apiParam.value()!=null && t_apiParam.value().trim().length()>0?t_apiParam.value():null;
				isRequired=t_apiParam.isRequired();
			}
			//若为空，则取字段名称
			if(t_key==null){
				t_key=t_field.getName();
			}
			//
			Object t_value=propertyValueMap.get(t_key);		//值
			Object t_value_set=null;		//被设置的值
			Class<?> t_fieldTypeClass=t_field.getType();
			//Map
			if(Map.class.isAssignableFrom(t_fieldTypeClass)) {
				
			//List
			}else if(List.class.isAssignableFrom(t_fieldTypeClass)) {
				if(t_value instanceof List) {
					List<Object> t_childPropertyValueList=(List<Object>) t_value;
					if(t_childPropertyValueList!=null && t_childPropertyValueList.size()>0) {
						List<Object> t_value_set_list=new ArrayList<Object>();
						//
						Type fc = t_field.getGenericType(); 	//关键的地方，如果是List类型，得到其Generic的类型  
						if(fc instanceof ParameterizedType){
							ParameterizedType pt = (ParameterizedType) fc;  
							Type tArr[]=pt.getActualTypeArguments();
							Type type=tArr[0];
							Class<?> typeClass=(Class<?>)type;
							//自定义类
							if(typeClass.getClassLoader()!=null) {
								for(Object t_obj:t_childPropertyValueList) {
									//是Map
									if(t_obj instanceof Map) {
										Map<String,Object> t_obj_map=(Map<String,Object>) t_obj;
										try {
											Object t_value_set_item=get(t_fieldTypeClass);
											setProperty(t_obj_map, t_value_set_item);
											t_value_set_list.add(t_value_set_item);
										} catch (InstantiationException e) {
											
										}
									}else{
										continue out;
									}
								}
							//JDK类
							}else{
								t_value_set_list=t_childPropertyValueList;
							}
			            }
						//有值
						if(t_value_set_list.size()>0) {
							t_value_set=t_value_set_list;
						}
					}
		         //子属性值对象不是List，则结束本次循环
				}else{
					continue;
				}
			//自定义类
			}else if(t_fieldTypeClass.getClassLoader()!=null){
				if(t_value instanceof Map) {
					Map<String, Object> t_childPropertyValueMap=(Map<String, Object>) t_value;
					try {
						t_value_set=get(t_fieldTypeClass);
						setProperty(t_childPropertyValueMap, t_value_set);
					} catch (InstantiationException e) {
//						e.printStackTrace();
					} 
				//子属性值对象不是Map，则结束本次循环
				}else{
					continue;
				}
			//普通
			}else{
				t_value_set=cast(t_value, t_field.getType());
			}
			if(t_value_set!=null) {
				try{
					t_field.set(object, t_value_set);
				}catch(IllegalArgumentException e){
//					e.printStackTrace();
//					throw new IllegalArgumentException("参数【"+t_key+"】的值的类型不正确，需要是【"+t_field.getType().getSimpleName()+"】类型");
				}
			//必须
			}else if(isRequired){
				throw new IllegalArgumentException("参数【"+t_key+"】的值不能为空");
			}
		}
  	}
  	
  	/**
  	 * 获取：指定类的所有Field字段，包含父类，直到指定父类停止（不在获取）
  	 * @param tableClass : 要获取所有Field字段的Class类
  	 * @param parentClass : 指定要停止获取父类Field的Class（不能为空，否则返回空）
  	 */
  	public static Field[] getField(Class<?> tableClass,Class<?> parentClass){
  		Map<String,Field> fieldMap=getFieldMap(tableClass, parentClass);
		Field fArr[]=new Field[fieldMap.size()];
		int i=0;
		for(Field t_field:fieldMap.values()){
			fArr[i]=t_field;
			i++;
		}
		return fArr;
  	}
  	/**
  	 * 获取：指定类的所有Field字段，包含父类，直到指定父类停止（不在获取，Map式）
  	 * @param tableClass : 要获取所有Field字段的Class类
  	 * @param parentClass : 指定要停止获取父类Field的Class（不能为空，否则返回空）
  	 */
  	public static Map<String,Field> getFieldMap(Class<?> tableClass,Class<?> parentClass){
  		Map<String,Field> fieldMap=new LinkedHashMap<String, Field>();		//key=字段名称
  		if(tableClass!=null){
	  		if(parentClass!=null){
	  			Class<?> t_parentClass=tableClass;
	  			while(t_parentClass!=null && t_parentClass!=parentClass){
	  				Field t_fieldArr[]=t_parentClass.getDeclaredFields();
		  			for(Field f:t_fieldArr){
		  				String t_fieldName=f.getName();
		  				//若不存在
		  				if(!fieldMap.containsKey(t_fieldName)){
		  					fieldMap.put(t_fieldName,f);
		  				}
		  			}
		  			t_parentClass=t_parentClass.getSuperclass();
	  			}
	  			return fieldMap;
	  		}
  		}
  		return fieldMap;
  	}
	/**
	 * 对象类型转换
	 * @param source : 源对象
	 * @param converTypeClass : 目标类型Class
	 * @return 返回目标类型对象
	 */
	public static Object cast(Object source,Class<?> dstTypeClass){
		Object dstObj=null;
		if(source!=null && dstTypeClass!=null){
			if(source instanceof Number){
				Number number=(Number)source;
				if(dstTypeClass==Byte.class){
					dstObj=number.byteValue();
				}else if(dstTypeClass==Double.class){
					dstObj=number.doubleValue();
				}else if(dstTypeClass==Float.class){
					dstObj=number.floatValue();
				}else if(dstTypeClass==Integer.class){
					dstObj=number.intValue();
				}else if(dstTypeClass==Long.class){
					dstObj=number.longValue();
				}else if(dstTypeClass==Short.class){
					dstObj=number.shortValue();
				}
			}
			//不是Number、基础数据类型
			if(dstObj==null){
				if (dstTypeClass.isAssignableFrom(source.getClass())){
					dstObj=dstTypeClass.cast(source);
				//String
				}else if(dstTypeClass==String.class){
					dstObj=source.toString();
				//Integer
				}else if(dstTypeClass==Integer.class){
					dstObj=Integer.parseInt(source.toString());
				//Long
				}else if(dstTypeClass==Long.class){
					dstObj=Long.parseLong(source.toString());
				//Float
				}else if(dstTypeClass==Float.class){
					dstObj=Float.parseFloat(source.toString());
				//Double
				}else if(dstTypeClass==Double.class){
					dstObj=Double.parseDouble(source.toString());
				//Byte
				}else if(dstTypeClass==Byte.class){
					dstObj=Byte.parseByte(source.toString());
				//Short
				}else if(dstTypeClass==Short.class){
					dstObj=Short.parseShort(source.toString());
				//Boolean
				}else if(dstTypeClass==Boolean.class){
					dstObj=Boolean.parseBoolean(source.toString());
				}else{
					dstObj=source;
				}
			}
		}else{
			dstObj=source;
		}
		return dstObj;
	}
	
	/************解析成map************/
	/**转换成Map，对象属性全转换基础数据类型（普通对象）**/
  	public static Map<Object,Object> convertMap(Object obj) throws IllegalAccessException{
      	Map<Object,Object> t_resultMap=new HashMap<Object, Object>();
      	if(obj!=null) {
      		Field fieldArr[]=fieldMap.get(obj.getClass());
      		if(fieldArr==null) {
      			fieldArr=getField(obj.getClass(), Object.class);
      			fieldMap.put(obj.getClass(), fieldArr);
      		}
	  		if(fieldArr!=null && fieldArr.length>0) {
	  			for(Field t_field:fieldArr) {
	  				t_field.setAccessible(true);		//取消安全检查
	  				String t_key=t_field.getName();
	  				Object t_value=t_field.get(obj);
	  				if(t_value!=null) {
	  					//Map
	  					if(t_value instanceof Map) {
	  						t_resultMap.put(t_key, convertMap((Map<Object,Object>) t_value));
	  					//集合
	  					}else if(t_value instanceof Collection) {
	  						t_resultMap.put(t_key, convertList((Collection<Object>) t_value));
	  					//基础数据类型、String、Boolean
	  					}else if((t_value instanceof Number) || (t_value instanceof String) || (t_value instanceof Boolean)) {
	  						t_resultMap.put(t_key, t_value);
	  					//普通对象
	  					}else{
	  						t_resultMap.put(t_key, convertMap(t_value));
	  					}
	  				}
	  			}
	  		}
      	}
  		return t_resultMap;
      }
  	/**转换成List，对象属性全转换基础数据类型（集合对象）**/
  	public static List<Object> convertList(Collection<Object> collection) throws IllegalAccessException{
      	List<Object> resultList=new ArrayList<Object>();
  		for(Object t_value:collection) {
  			if(t_value!=null) {
  				//Map
  				if(t_value instanceof Map) {
  					resultList.add(convertMap((Map<Object,Object>) t_value));
  				//集合
  				}else if(t_value instanceof Collection) {
  					resultList.add(convertList((Collection<Object>) t_value));
  				//基础数据类型、String、Boolean
				}else if((t_value instanceof Number) || (t_value instanceof String) || (t_value instanceof Boolean)) {
					resultList.add(t_value);
  				//普通对象
  				}else{
  					resultList.add(convertMap(t_value));
  				}
  			}
  		}
  		return resultList;
      }
  	/**转换成Map，对象属性全转换基础数据类型（Map对象）**/
  	public static Map<String,Object> convertMap(Map<Object,Object> valueMap) throws IllegalAccessException{
      	Map<String,Object> t_resultMap=new HashMap<String, Object>();
  		for(Entry<Object,Object> t_entry:valueMap.entrySet()) {
  			String t_map_key=t_entry.getKey().toString();
  			Object t_map_value=t_entry.getValue();
  			if(t_map_value!=null) {
  				//Map
  				if(t_map_value instanceof Map) {
  					t_resultMap.put(t_map_key, convertMap((Map<Object,Object>) t_map_value));
  				//集合
  				}else if(t_map_value instanceof Collection) {
  					t_resultMap.put(t_map_key, convertList((Collection<Object>) t_map_value));
  				//基础数据类型、String、Boolean
					}else if((t_map_value instanceof Number) || (t_map_value instanceof String) || (t_map_value instanceof Boolean)) {
  					t_resultMap.put(t_map_key, t_map_value);
  				//普通对象
  				}else{
  					t_resultMap.put(t_map_key, convertMap(t_map_value));
  				}
  			}
  		}
  		return t_resultMap;
      }
      
}
