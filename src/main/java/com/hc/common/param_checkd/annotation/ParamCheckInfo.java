package com.hc.common.param_checkd.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented // 说明该注解将被包含在javadoc中
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target(ElementType.METHOD) // 限制只能用于方法上
public @interface ParamCheckInfo {
	/**
	 * 检查的属性名
	 * 
	 * @author DDM 2018年6月19日
	 */
	public String name();
	/**
	 * 是否要以为空
	 * 
	 * @author DDM 2018年6月19日
	 */
	public boolean empty() default false;
	/**
	 * 正则表达式验证
	 * 
	 * @author DDM 2018年6月19日
	 */
	public String reg();
	/**
	 * 针对日期类型，前提参数的类型是日期类型，否则无效
	 * 
	 * @author DDM 2018年6月19日
	 */
	public String date() default "";
}
