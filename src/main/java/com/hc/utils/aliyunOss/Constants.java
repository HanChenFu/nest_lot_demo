package com.hc.utils.aliyunOss;

public class Constants {
	/**
	 * 阿里云oss参数
	 * **/
	// endpoint以杭州为例，其它region请按实际情况填写
	public static final String aliyunEndpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	//public static final String aliyunEndpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	// 只有 RAM用户（子账号）才能调用 AssumeRole 接口
    // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
    // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
	//public static final String aliyunAccessKeyId = "LTAIddTwV1MAiQje";
	public static final String aliyunAccessKeyId = "LTAI4FnzP1DjFFcGfW65tQiT";
	//public static final String aliyunAccessKeySecret = "4BDkL18hliaFGJ2Giwv8V5iTQO5Jg5";
	public static final String aliyunAccessKeySecret = "8AnzPpDYwqksgN4dIHTobRdg0yVzhA";
	// RoleArn 需要在 RAM 控制台上获取
	//public static final String aliyunRoleArn = "acs:ram::1250615082924154:role/hyzb";
	public static final String aliyunRoleArn = "acs:ram::1099376716585117:role/nieliyun-role";
	// RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
    // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '.' '@' 字母和数字等字符
    // 具体规则请参考API文档中的格式要求
	public static final String aliyunRoleSessionName = "lemeng";
	//阿里云bucket_name
	public static String bucketName = "hanchenfu-szemo";
	
}
