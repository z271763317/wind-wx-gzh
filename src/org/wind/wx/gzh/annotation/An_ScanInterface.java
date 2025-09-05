package org.wind.wx.gzh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 : 扫描接口注解（扫描时，当前类的接口若有该注解，则本类可以被扫描到。一般只用在本框架的服务接口上）
 * @作者 : 胡璐璐
 * @时间 : 2021年3月4日 22:00:55
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface An_ScanInterface {

	
}
