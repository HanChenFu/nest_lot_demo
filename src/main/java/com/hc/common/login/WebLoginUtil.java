package com.hc.common.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hc.common.redis.RedisUtil;

/**
 * Web登录工具类
 * 
 * @ClassName: WebLoginUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author DDM
 * @date 2019年1月30日 上午10:08:29
 *
 */
@Component
public class WebLoginUtil {

	@Autowired
	private RedisUtil redis;
	/**
	 * 保存登录用户信息
	 * 
	 * @param user
	 * @param session
	 */
	public boolean setLoginUser(Object user, HttpSession session) {
		session.setAttribute("login_user", user);
		return true;
	}

	/**
	 * 保存登录用户信息
	 * 
	 * @param user
	 * @param request
	 */
	public boolean setLoginUser(Object user, HttpServletRequest request) {
		request.getSession().setAttribute("login_user", user);
		return true;
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @param session
	 * @return
	 */
	public Object getLoginUser(HttpSession session) {
		return session.getAttribute("login_user");
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @param request
	 * @return
	 */
	public Object getLoginUser(HttpServletRequest request) {
		return request.getSession().getAttribute("login_user");
	}

	/**
	 * 判断是否登录了
	 * 
	 * @author DDM 2019年2月12日
	 * @param request
	 * @return
	 */
/*	public static boolean isLogin(HttpServletRequest request) {
		if (null != request.getSession().getAttribute("login_user")) {
			return true;
		}
		return false;
	}*/
	
	public boolean isLogin(HttpServletRequest request) {
		System.out.println("token:"+request.getHeader("token"));
		
		if (redis.checkKey("token."+request.getHeader("token"))) {
			return true;
		}
		return false;
	}

}
