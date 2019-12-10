package com.hc.controller.interceptor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.hc.common.code.StatusCode;
import com.hc.common.login.WebLoginUtil;
import com.hc.common.result.ResultBase;
import com.hc.utils.result.ResultUtil;

/**
 * Web 登录 拦截器
 * 
 * @author DDM
 *
 */
@Component
public class LoginWebInterceptor implements HandlerInterceptor {

	@Autowired
	private WebLoginUtil loginUtil;
	
	
	/**
	 * 进入controller层之前拦截请求
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		// 检查是否登录了
		if (!loginUtil.isLogin(request)) {
			// 未登录
			sendNotLoginMsg(response);
			return false;
		}
		return true;
	}

	/**
	 * 发送未登录信息
	 * 
	 * @author DDM 2019年2月12日
	 * @param response
	 */
	private void sendNotLoginMsg(HttpServletResponse response) {
		// 发送数据到前端。
		try {
			ResultBase res = ResultUtil.getResultBase(true, StatusCode.NOT_LOGIN, "登录超时，请重新登录");
			// 设置返回类型 为 json
			response.setContentType("application/json; charset=utf-8");
			// 设置返回编码类型为 utf-8
			response.setCharacterEncoding("utf-8");
			// 发送数据
			response.getWriter().write(JSON.toJSONString(res));
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流
				response.getWriter().close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 视图渲染之后的操作
	 */
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	/**
	 * 处理请求完成后视图渲染之前的处理操作
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

}
