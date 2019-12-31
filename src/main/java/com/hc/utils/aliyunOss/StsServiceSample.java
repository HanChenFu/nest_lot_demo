package com.hc.utils.aliyunOss;

import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

public class StsServiceSample {
	// 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
	public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
	// 当前 STS API 版本
	public static final String STS_API_VERSION = "2015-04-01";

	private static OSSClient ossClient;

	/**
	 * 初始化
	 */
	public static void init() {
		ossClient = new OSSClient(Constants.aliyunEndpoint, Constants.aliyunAccessKeyId,
				Constants.aliyunAccessKeySecret);
	}

	/**
	 * 销毁
	 */
	public static void destory() {
		ossClient.shutdown();
	}

	/**
	 * 服务器给前端/移动端分配角色权限，使他们能操作文件
	 * 
	 * @param file
	 *            上传视频的文件
	 * @param filedir
	 *            视频存储的文件夹目录
	 * @return String 视频的绝对路径
	 */
	public static AliyunBean getAliyun() {
		AliyunBean bean = new AliyunBean();
		String policy = "{\n" 
				+ "    \"Version\": \"1\", \n" 
				+ "    \"Statement\": [\n" 
				+ "        {\n"
				+ "            \"Action\": [\n" 
				+ "                \"oss:*\"\n" 
				+ "            ], \n"
				+ "            \"Resource\": [\n" 
				+ "                \"acs:oss:*:*:*\" \n" 
				+ "            ], \n"
				+ "            \"Effect\": \"Allow\"\n" 
				+ "        }\n" 
				+ "    ]\n" 
				+ "}";
		// 此处必须为 HTTPS
		ProtocolType protocolType = ProtocolType.HTTPS;
		try {
			final AssumeRoleResponse response = assumeRole(Constants.aliyunAccessKeyId, Constants.aliyunAccessKeySecret,
					Constants.aliyunRoleArn, Constants.aliyunRoleSessionName, policy, protocolType);
			System.out.println("Expiration: " + response.getCredentials().getExpiration());
			System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
			System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
			System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
			bean.setExpiration(response.getCredentials().getExpiration());
			bean.setAccessKeyId(response.getCredentials().getAccessKeyId());
			bean.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
			bean.setSecurityToken(response.getCredentials().getSecurityToken());
			bean.setImgName(UUID.randomUUID().toString());
		} catch (ClientException e) {
			System.out.println("Failed to get a token.");
			System.out.println("Error code: " + e.getErrCode());
			System.out.println("Error message: " + e.getErrMsg());
			bean.setErrorCode(e.getErrCode());
			bean.setErrorMessage(e.getErrMsg());
		}
		return bean;
	}

	/**
	 * 删除文件
	 **/
	public static void delAliyunUrl(String url) {
		String key = isAliyunUrl(url);
		if (key != null) {
			// endpoint以杭州为例，其它region请按实际情况填写
			String endpoint = Constants.aliyunEndpoint;
			// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
			// https://ram.console.aliyun.com 创建
			String accessKeyId = Constants.aliyunAccessKeyId;
			String accessKeySecret = Constants.aliyunAccessKeySecret;
			// 创建OSSClient实例
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 删除Object
			ossClient.deleteObject("hy-zhiyin", key);
			// 关闭client
			ossClient.shutdown();
			System.out.println("删除oss成功");
		}
		System.out.println("没有改oss文件");
	}

	/**
	 * 查看文件是否存在
	 **/
	public static String isAliyunUrl(String url) {
		String flag = null;
		if (url != null && !"".equals(url)) {
			String[] split = url.split("/");
			if (split[split.length - 1] != null && !"".equals(split[split.length - 1])) {
				String endpoint = Constants.aliyunEndpoint;
				// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
				// https://ram.console.aliyun.com 创建
				String accessKeyId = Constants.aliyunAccessKeyId;
				String accessKeySecret = Constants.aliyunAccessKeySecret;
				// 创建OSSClient实例
				OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
				// Object是否存在
				System.out.println(split[split.length - 1]);
				boolean found = ossClient.doesObjectExist("hy-zhiyin", split[split.length - 1]);
				if (found) {
					System.out.println(split[split.length - 1]);
					flag = split[split.length - 1];
				}
				System.out.println(found);
				// 关闭client
				ossClient.shutdown();
			}
		}
		return flag;
	}

	/**
	 * 服务器上传图片
	 * 
	 * @param file
	 *            上传图片的文件
	 * @param filedir
	 *            图片存储的文件夹目录
	 * @return String 图片的绝对路径
	 */
	public static String uploadImg2Oss(MultipartFile file, String filedir) {
		try {
			// String ret = "";
			if (file.getSize() > 1024 * 1024 * 3) {
				throw new RuntimeException("上传图片大小不能超过3M！");
			}
			String originalFilename = file.getOriginalFilename();
			String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
			Random random = new Random();
			String name = UUID.randomUUID().toString() + substring;
			InputStream inputStream = file.getInputStream();

			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(inputStream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(name.substring(name.lastIndexOf("."))));
			objectMetadata.setContentDisposition("inline;filename=" + name);

			// 上传文件
			ossClient.putObject(Constants.bucketName, filedir + name, inputStream, objectMetadata);
			// ret = putResult.getETag();
			inputStream.close();
			// uploadFile2OSS(inputStream, name);
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("图片上传失败");
		}
	}

	/**
	 * 服务器上传视频
	 * 
	 * @param file
	 *            上传视频的文件
	 * @param filedir
	 *            视频存储的文件夹目录
	 * @return String 视频的绝对路径
	 */
	public static String uploadVideo2Oss(MultipartFile file, String filedir) {
		try {
			// String ret = "";
			// if (file.getSize() > 1024 * 1024 * 3) {
			// throw new RuntimeException("上传图片大小不能超过3M！");
			// }
			String originalFilename = file.getOriginalFilename();
			String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
			Random random = new Random();
			String name = UUID.randomUUID().toString() + substring;
			InputStream inputStream = file.getInputStream();

			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(inputStream.available());
			// objectMetadata.setCacheControl("no-cache");
			// objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType("video/mp4");
			// objectMetadata.setContentDisposition("inline;filename=" + name);

			// 上传文件
			ossClient.putObject(Constants.bucketName, filedir + name, inputStream, objectMetadata);
			// ret = putResult.getETag();
			inputStream.close();
			// uploadFile2OSS(inputStream, name);
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("图片上传失败");
		}
	}

	/**
	 * 测试
	 */
	public static String uploadImg2Oss(MultipartFile file, String type, String filedir) {
		try {
			// String ret = "";
			if (file.getSize() > 1024 * 1024 * 3) {
				throw new RuntimeException("上传图片大小不能超过3M！");
			}
			String originalFilename = file.getOriginalFilename();
			String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
			Random random = new Random();
			String name = UUID.randomUUID().toString() + substring;
			InputStream inputStream = file.getInputStream();

			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(inputStream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(name.substring(name.lastIndexOf("."))));
			objectMetadata.setContentDisposition("inline;filename=" + name);

			// 上传文件
			ossClient.putObject(Constants.bucketName, filedir + name, inputStream, objectMetadata);
			// ret = putResult.getETag();
			inputStream.close();
			// uploadFile2OSS(inputStream, name);
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("视频上传失败");
		}
	}

	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 *
	 * @param instream
	 *            文件流
	 * @param fileName
	 *            文件名称 包括后缀名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	/*
	 * public static String uploadFile2OSS(InputStream instream, String
	 * fileName) { String ret = ""; try {
	 * 
	 * } catch (IOException e) { log.error(e.getMessage(), e); } finally { try {
	 * if (instream != null) { instream.close(); } } catch (IOException e) {
	 * e.printStackTrace(); } } return ret; }
	 */

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String getcontentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase("jpeg") || FilenameExtension.equalsIgnoreCase("jpg")
				|| FilenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase("pptx") || FilenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase("docx") || FilenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}
	// public static void main(String[] args) {
	// MultipartFile file = null;
	// OSSClientUtil.init();
	// String uploadURL = OSSClientUtil.uploadImg2Oss(file);
	// OSSClientUtil.destory();
	// String url = "http://pggshare.oss-us-west-1.aliyuncs.com/pgg/" +
	// uploadURL;
	// System.out.println();
	// }

	static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
			String roleSessionName, String policy, ProtocolType protocolType) throws ClientException {
		try {
			// 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
			IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);
			// 创建一个 AssumeRoleRequest 并设置请求参数
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setVersion(STS_API_VERSION);
			request.setMethod(MethodType.POST);
			request.setProtocol(protocolType);
			long time = 3600;// 15m-60m
			request.setDurationSeconds(time); // 这个是设置授权有效期的
			request.setRoleArn(roleArn);
			request.setRoleSessionName(roleSessionName);
			request.setPolicy(policy);
			// 发起请求，并得到response
			final AssumeRoleResponse response = client.getAcsResponse(request);
			return response;
		} catch (ClientException e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		getAliyun();
		// System.out.println(UUID.randomUUID().toString());
		// delAliyunUrl("http://hy-zhiyin.oss-cn-hangzhou.aliyuncs.com/91f303fe-7a68-4949-8ef4-f2c70d4ef593.jpg");
		// System.out.println(isAliyunUrl("http://hy-zhiyin.oss-cn-hangzhou.aliyuncs.com/upload-file/hy13113101313.jpg"));
	}
}