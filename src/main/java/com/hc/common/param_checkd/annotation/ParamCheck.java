package com.hc.common.param_checkd.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只能检查封装类(bean)，不能直接检查基本数据类型，及 String 等参数。
 * 检查SeerviceImpl方法上的参数，是否符合条件，注：日期类型只能判断是否为空，否则判断错错误
 * 
 * @ClassName: ParamCheck
 * @Description:
 * @author DDM
 * @date 2018年6月19日 下午8:45:56
 *
 */
@Documented // 说明该注解将被包含在javadoc中
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target(ElementType.METHOD) // 限制只能用于方法上
public @interface ParamCheck {
	/**
	 * 要检查的参数名称
	 * 
	 * @author DDM 2018年6月19日
	 */
	public int param() default 1;

	/**
	 * 要检查的参数的属性
	 * 
	 * @author DDM 2018年6月19日
	 */
	public ParamCheckInfo[] value() default {};

	/**
	 * 只检查不能为空的属性
	 * 
	 * @author DDM 2018年6月19日
	 */
	public String[] names() default {};
}
