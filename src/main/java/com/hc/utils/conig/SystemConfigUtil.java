package com.hc.utils.conig;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.hc.common.exception.CustomException;

public class SystemConfigUtil {
	private static Logger logger = LoggerFactory.getLogger(SystemConfigUtil.class);

	@Value("spring.profiles.active")
	private static String system = "dev";
	@Value("test_v")
	public static String test_v = "";

	private SystemConfigUtil() {
	}

	private static ConfigUtil config;

	/**
	 * 获取系统配置的值
	 * 
	 * @title null * * * * * * * * * *
	 * @author ddm
	 * @date 2017年7月26日-下午7:04:51
	 * @param
	 * @retrun {"result":"1"} #ok {"result":"0"} #error
	 */
	public static String getValue(String key) {
		if (null == config) {
			try {
				String path = "";
				switch (system) {
				case "dev":
					path = "dev";
					logger.info("当前运行环境：开发环境");
					break;
				case "test":
					path = "test";
					logger.info("当前运行环境：测试环境");
					break;
				case "pro":
					path = "pro";
					logger.info("当前运行环境：生产环境");
					break;
				default:
					path = "dev";
					logger.info("当前运行环境：开发环境");
					break;
				}
				config = new ConfigUtil("systemConfig-" + path + ".properties");
			} catch (CustomException e) {
				logger.error("系统配置文件异常", e);
				return null;
			}
		}
		return config.getString(key);
	}

	/**
	 * 获取用户登录的 ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获得外网IP
	 * 
	 * @return 外网IP
	 */
	public static String getInternetIp() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String INTRANET_IP = addr.getHostAddress().toString(); // 获取本机ip

			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			Enumeration<InetAddress> addrs;
			while (networks.hasMoreElements()) {
				addrs = networks.nextElement().getInetAddresses();
				while (addrs.hasMoreElements()) {
					ip = addrs.nextElement();
					if (ip != null && ip instanceof Inet4Address && ip.isSiteLocalAddress()
							&& !ip.getHostAddress().equals(INTRANET_IP)) {
						return ip.getHostAddress();
					}
				}
			}
			// 如果没有外网IP，就返回内网IP
			return INTRANET_IP;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getValue("upload_path"));
	}
}
