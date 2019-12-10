package com.hc.common.param_checkd.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检查方法上的参数，是否符合条件，注：日期类型只能判断是否为空，否则判断失错
 * 
 * @ClassName: ParamCheck
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author DDM
 * @date 2018年6月19日 下午8:45:56
 *
 */
@Documented // 说明该注解将被包含在javadoc中
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target(ElementType.METHOD) // 限制只能用于方法上
public @interface ParamChecks {
	/**
	 * 要检查的 Param列表， 包含的 ParamCheck 注解 不能为空。
	 * 
	 * @author DDM 2018年6月22日
	 */
	public ParamCheck[] params();
}
