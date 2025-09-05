package org.wind.wx.gzh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 : 参数通用注解（可用在请求和返回类的属性上）
 * @作者 : 胡璐璐
 * @时间 : 2021年1月17日 18:41:15
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface An_Param {

	public String value() default "";		//映射的参数key名称（若为默认值或NULL，则取字段名称代表）
	public String name() default "";		//参数名称（说明）
	public boolean isRequired() default false;		//是否必须（必要）的
	
}
