package com.hc.common.param_checkd;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.param_checkd.annotation.ParamCheck;
import com.hc.common.param_checkd.annotation.ParamCheckInfo;
import com.hc.common.param_checkd.annotation.ParamChecks;
import com.hc.pojo.resBean.ResponseMess;

@Aspect
@Component
public class ServiceParamCheck {
	private static Logger logger = LoggerFactory.getLogger(ServiceParamCheck.class);

	@Around("execution(* com.hc.service.impl.*.*(..)) && @annotation(check)")
	public Object doAccessCheck(ProceedingJoinPoint jp, ParamCheck check) throws Throwable {
		logger.info("检查单个注解");
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		if (null != check) {
			Integer index = check.param() - 1;
			if (0 > index) {
				index = 0;
			}
			Object[] params = jp.getArgs();
			if (params.length <= index) {
				logger.error("参数检查，位置不正确");
				return null;
			}
			if (params[index] == null) {
				returnResponse(response, new ResponseMess(false, StatusCode.PARAM_NULL.toInteger(), "参数不能为空"));
				return null;
			}
			if (checkParam(params[index], check.value(), check.names(), response)) {
				return jp.proceed();
			}
			return null;
		}
		return null;
	}

	@Around("execution(* com.hc.service.impl.*.*(..)) && @annotation(checks)")
	public Object doAccessChecks(ProceedingJoinPoint jp, ParamChecks checks) throws Throwable {
		logger.info("检查多个注解");
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		if (null != checks) {
			Object[] params = jp.getArgs();
			for (ParamCheck check : checks.params()) {
				Integer index = check.param() - 1;
				if (0 > index) {
					index = 0;
				}
				if (params.length <= index) {
					logger.error("参数检查，位置不正确");
					return null;
				}
				if (params[index] == null) {
					returnResponse(response, new ResponseMess(false, StatusCode.PARAM_NULL.toInteger(), "参数不能为空"));
					return null;
				}
				if (checkParam(params[index], check.value(), check.names(), response)) {
					return jp.proceed();
				}
				return null;
			}
		}
		return null;
	}

	/**
	 * 检查参数
	 * 
	 * @author DDM 2018年6月19日
	 * @throws Exception
	 */
	public boolean checkParam(Object obj, ParamCheckInfo[] checks, String[] names, HttpServletResponse response)
			throws Exception {
		// 判断是不是数组
		/*
		 * if (obj.getClass().isArray()) { Object[] objs = (Object[]) obj; for (Object
		 * object : objs) { if (!ChoiceCheck(checks, names, object, response)) { return
		 * false; } } return true; } // 判断是不是 list 集合 if (obj instanceof List) {
		 * 
		 * @SuppressWarnings("unchecked") List<Object> list = (List<Object>) obj; for
		 * (Object object : list) { if (!ChoiceCheck(checks, names, object, response)) {
		 * return false; } } return true; }
		 */
		// 不是集合，直接判断
		return ChoiceCheck(checks, names, obj, response);
	}

	/**
	 * 选择检查类型
	 * 
	 * @author DDM 2018年6月19日
	 * @throws Exception
	 */
	private boolean ChoiceCheck(ParamCheckInfo[] checks, String[] names, Object object, HttpServletResponse response)
			throws Exception {
		// 判断是否只检查 指定条件属性
		if (null != checks && checks.length > 0) {
			if (!CheckValue(object, checks, response)) {
				return false;
			}
		}
		// 判断是否只是检查指定非空属性
		if (null != names && names.length > 0) {
			if (!checkValueEmpty(object, names, response)) {
				return false;
			}
		}
		// 判断是否需要检查所有属性非空
		if ((null == names || names.length == 0) && (null == checks || checks.length == 0)) {
			return checkValueEmptyAll(object, response);
		}
		return true;
	}

	/**
	 * 只检查指定属性参数是否为空
	 * 
	 * @author DDM 2018年6月19日
	 * @throws Exception
	 */
	private boolean checkValueEmpty(Object obj, String[] names, HttpServletResponse response) throws Exception {
		try {
			for (String name : names) {
				// 判断 要检查属性名是否正确
				if (null != name && null != name && !"".equals(name)) {
					// 属性
					Field field = null;
					// 属性类
					Class cla2 = obj.getClass();
					do {
						try {
							field = cla2.getDeclaredField(name.trim());
						} catch (NoSuchFieldException e) {

						}
						if (null == field) {
							// 在当前属性类中没有找到，去父类查询，直到 Object 类
							if (cla2 != Object.class) {
								cla2 = cla2.getSuperclass();
								continue;
							} else {
								logger.error("参数检查：" + name + "不存在");
								break;
							}
						}
					} while (null == field);
					// 还是没找到，继续下一下属性
					if (null == field) {
						continue;
					}
					// 设置可以查询私有属性
					field.setAccessible(true);

					Object value = field.get(obj);

					if (null != value) {

						// 判断是不是 boolean 值
						if (Boolean.class.isAssignableFrom(value.getClass())) {
							continue;
						}

						if (Number.class.isAssignableFrom(value.getClass()) || value.getClass() == String.class) {
							if (!"".equals(value.toString().trim())) {
								continue;
							}
						}
						// 判断是不是数组
						if (value.getClass().isArray()) {
							if (0 < ((Object[]) value).length) {
								continue;
							}
						}

						// 判断是不是 list 集合
						if (value instanceof List) {
							if (0 < ((List<Object>) value).size()) {
								continue;
							}
						}
					}
					returnResponse(response,
							new ResponseMess(false, StatusCode.PARAM_NULL.toInteger(), name + "参数不能为空"));
					return false;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

	/**
	 * 检查全部属性参数是否为空
	 * 
	 * @author DDM 2018年6月19日
	 */
	private boolean checkValueEmptyAll(Object obj, HttpServletResponse response) throws CustomException {
		Class cla = obj.getClass();
		try {
			if (cla != Object.class) {
				cla = cla.getSuperclass();
			}
			// 属性
			Field[] fields = cla.getDeclaredFields();

			for (Field field : fields) {
				// 设置可以查询私有属性
				field.setAccessible(true);

				Object value = field.get(obj);

				if (null != value) {
					// 判断是不是 boolean 值
					if (Boolean.class.isAssignableFrom(value.getClass())) {
						continue;
					}
					if (Number.class.isAssignableFrom(value.getClass()) || value.getClass() == String.class) {
						if (!"".equals(value.toString().trim())) {
							continue;
						}
					} else
					// 判断是不是数组
					if (value.getClass().isArray()) {
						if (0 < ((Object[]) value).length) {
							continue;
						}
					} else

					// 判断是不是 list 集合
					if (value instanceof List) {
						if (0 < ((List<Object>) value).size()) {
							continue;
						}
					}
				}
				returnResponse(response,
						new ResponseMess(false, StatusCode.PARAM_NULL.toInteger(), field.getName() + "参数不能为空"));
				return false;
			}
		} catch (Exception e) {
			logger.error("检查发生异常！", e);
		}
		return true;
	}

	/**
	 * 检查值
	 * 
	 * @author DDM 2018年6月19日
	 */
	private boolean CheckValue(Object obj, ParamCheckInfo[] checks, HttpServletResponse response)
			throws CustomException {
		for (ParamCheckInfo check : checks) {
			try {
				// 判断 要检查属性名是否正确
				if (null != check.name() && null != check.name() && !"".equals(check.name())) {
					// 属性
					Field field = null;
					// 属性类
					Class cla2 = obj.getClass();
					do {
						try {
							field = cla2.getDeclaredField(check.name().trim());
						} catch (NoSuchFieldException e) {

						}
						if (null == field) {
							// 在当前属性类中没有找到，去父类查询，直到 Object 类
							if (cla2 != Object.class) {
								cla2 = cla2.getSuperclass();
								continue;
							} else {
								logger.error("参数检查：" + check.name() + "不存在");
								break;
							}
						}
					} while (null == field);
					// 还是没找到，继续下一下属性
					if (null == field) {
						continue;
					}
					// 设置可以查询私有属性
					field.setAccessible(true);
					// 获取属性值
					Object value = field.get(obj);
					// 检查属性
					Boolean falg = checkAttribute(field, value, check, response);
					// 判断是否为null
					if (null == falg) {
						// 为null 表示将该属性设置为 null
						field.set(obj, null);
					}
					// 不正确
					if (false == falg) {
						returnResponse(response,
								new ResponseMess(false, StatusCode.PARAM_ERROR.toInteger(), field.getName() + "参数不正确"));
						return false;
					}
				}
			} catch (CustomException e) {
				throw e;
			} catch (Exception e) {
				logger.error("检查发生异常！", e);
			}
		}
		return true;
	}

	/**
	 * 检查属性
	 * 
	 * @author DDM 2018年6月19日
	 * @throws IOException
	 */
	public Boolean checkAttribute(Field field, Object value, ParamCheckInfo check, HttpServletResponse response)
			throws CustomException, IOException {
		// 判断是否可以为空
		if (check.empty() && null == value) {
			// 可以为空
			return true;
		} else if (!check.empty() && null == value) {
			// 不能为空
			returnResponse(response,
					new ResponseMess(false, StatusCode.PARAM_NULL.toInteger(), field.getName() + "参数不能为空"));
//			throw new CustomException(StatusCode.PARAM_NULL,  + "参数不能为空");
		}

		// 判断是不是基本类型或 String类型
		if (Number.class.isAssignableFrom(value.getClass()) || value.getClass() == String.class) {
			return CheckPrimitive(value, check, response);
		} else
		// 判断是不是数组
		if (value.getClass().isArray()) {

			Object[] values = (Object[]) value;
			for (Object object : values) {
				// 查检值
				if (!CheckPrimitive(object, check, response)) {
					return false;
				}
			}
		} else
		// 判断是不是 list 集合
		if (value instanceof List) {
			List<Object> values = (List<Object>) value;
			for (Object object : values) {
				// 查检值
				if (!CheckPrimitive(object, check, response)) {
					return false;
				}
			}
		} else
		// 判断是不是日期类型
		if (value instanceof Date && null != check.date() && !"".equals(check.date().trim())) {
			// 设置格式化
			try {
				// 设置格式化值
				SimpleDateFormat sdf = new SimpleDateFormat(check.date().trim());
				// 格式化后检查值
				return CheckPrimitive(sdf.format((Date) value), check, response);
			} catch (CustomException ce) {
				throw ce;
			} catch (Exception e) {
				logger.error("日期检查格式设置错误，跳过检查");
			}
		}
		return true;
	}

	/**
	 * 检查基本数据类型的值
	 * 
	 * @author DDM 2018年6月19日
	 * @throws IOException
	 */
	private Boolean CheckPrimitive(Object value, ParamCheckInfo check, HttpServletResponse response)
			throws CustomException, IOException {
		// 判断值
		String val = value.toString();
		if (check.empty() && "".equals(val.trim())) {
			// 在这里应该将为 "" 的值设为 null
			return null;
		} else if (!check.empty() && "".equals(val)) {
			// 参数不能为空
			returnResponse(response,
					new ResponseMess(false, StatusCode.PARAM_NULL.toInteger(), check.name() + "参数不能为空"));
//			throw new CustomException(StatusCode.PARAM_NULL, check.name() + "参数不能为空");
		}
		// 判断是否使用 正则表达式
		if (null != check.reg() && !"".equals(check.reg().trim())) {
			if (val.matches(check.reg().trim())) {
				return true;
			}
			return false;
		}
		return true;
	}

	private void returnResponse(HttpServletResponse response, ResponseMess mess) throws IOException {
		try {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter write = response.getWriter();
			write.write(JSONObject.toJSONString(mess));
			write.flush();
		} catch (Exception e) {
		}finally {
			response.getWriter().close();
			mess = null;
		}
	}

}