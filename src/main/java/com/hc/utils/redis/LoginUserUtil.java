package com.hc.utils.redis;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.common.redis.RedisUtil;
import com.hc.pojo.base.TbAdmin;
import com.hc.utils.string.MD5Util;

@Component
public class LoginUserUtil {

	/**
	 * 保存登录用户信息的 Key
	 */
	private static final String KEY_LOGIN_USER = "token.";

	private static final Integer logout_time = 28800;

	/**
	 * 保存登录用户信息
	 * 
	 * @author DDM 2018年6月1日
	 */
	public String setLoginUser(TbAdmin user) {
		String token = MD5Util.MD5(UUID.randomUUID().toString().replaceAll("-", "a")).toLowerCase();
		getRedis().set(KEY_LOGIN_USER + token, user, logout_time);//保存user，并设置保存时间，单位 秒
		getRedis().set("web"+user.getTbId(), KEY_LOGIN_USER + token, logout_time);//保存token，并且设置保存时间
		user.setToken(token);
		return token;
	}
	
	/**
	 * 保存登录用户信息
	 * 
	 * @author DDM 2018年6月1日
	 */
	public boolean setLoginUser2(String token, TbAdmin user) {
		getRedis().set(KEY_LOGIN_USER + token, user, logout_time);
		System.out.println(token);
		return true;
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @author DDM 2018年6月1日
	 */
	public TbAdmin getLoginUser(String token) throws CustomException {
		Object obj = getRedis().get(KEY_LOGIN_USER + token);
		if(null != obj && obj instanceof TbAdmin){
			return (TbAdmin)obj;
		}
		throw new CustomException(StatusCode.NOT_LOGIN, "未登录");
	}

	/**
	 * 退出-删除token
	 * 
	 * @author DDM 2018年6月1日
	 */
	public boolean logout(String token) throws CustomException {
		if ("".equals(token) || token.equals(null)) {
			throw new CustomException(StatusCode.PARAM_NULL, "token 不能为空");
		}
		getRedis().del(token);
		return true;
	}
	
	/**
	 * 退出-删除ID
	 * @author DDM   2019年1月24日
	 */
	public boolean logoutById(String id) throws CustomException {
		if ("".equals(id) || id.equals(null)) {
			throw new CustomException(StatusCode.PARAM_NULL, "token 不能为空");
		}
		getRedis().del(id);
		return true;
	}

	/**
	 * 登录检查
	 * 
	 * @author DDM 2018年6月1日
	 */
	public boolean loginCheck(String token) {
		boolean falg = getRedis().checkKey(KEY_LOGIN_USER + token);
		if (falg) {
			// 更新在线时间
			getRedis().expire(KEY_LOGIN_USER + token, logout_time);
		}
		return falg;
	}

	@Autowired
	RedisUtil redis;
	
	/**
	 * 获取 redis
	 * 
	 * @author DDM 2018年6月1日
	 */
	private  RedisUtil getRedis() {
//		return SpringContextUtil.getBean(RedisUtil.class);
		return redis;
	}
	
}