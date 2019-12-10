package com.hc.common.exception;

/**
 * 
 * @ClassName: CustomExceptionHandler
 * @Description: TODO 通用异常处理类
 * @author DDM
 * @date 2019年1月30日 上午9:49:00
 *
 */
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hc.common.code.StatusCode;
import com.hc.common.result.ResultBase;
import com.hc.utils.result.ResultUtil;

@ControllerAdvice
public class CustomExceptionHandler {

	Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResultBase defultExcepitonHandler(HttpServletRequest request, Exception e) {
		// 错误日志
		logger.error("异常信息：" + e.getMessage(), e);
		// 异常分析
		if (e instanceof CustomException) {
			// 自定义异常
			return ResultUtil.getResultBase(false, ((CustomException) e).getStatusCode(), e.getMessage());
		} else if (e instanceof HttpMessageNotReadableException) {
			// 参数异常
			String str = e.getMessage();
			String param = str.substring(str.lastIndexOf("[") + 2, str.lastIndexOf("]") - 1);
			return ResultUtil.getResultBase(false, StatusCode.PARAM_ERROR, param + "参数类型不正确");
		}
		// 未知错误
		return ResultUtil.getResultBase(false, StatusCode.ERROR, "系统异常");
	}
}
