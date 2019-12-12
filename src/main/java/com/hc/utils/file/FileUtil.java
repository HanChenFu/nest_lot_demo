package com.hc.utils.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import com.hc.common.code.StatusCode;
import com.hc.common.exception.CustomException;
import com.hc.utils.conig.SystemConfigUtil;
import com.hc.utils.date.MyDateUtil;

public class FileUtil {
	/**
	 * 保存 文件，使用默认路径。返回绝对路径
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String save(MultipartFile items_pic) throws Exception {
		// 文件原始名称
		String originalFilename = items_pic.getOriginalFilename();
		// 上传文件
		if (items_pic != null && originalFilename != null && originalFilename.length() > 0) {
			try {
				// 存储文件的物理路径
				String pic_path = SystemConfigUtil.getValue("upload_path");
				// 新文件名称
				String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				newFileName = newFileName.replaceAll("-", "");
				// 新文件
				File newFile = new File(pic_path, newFileName);
				// 判断文件夹是否存在不存在就创建
				if (!newFile.exists()) {
					newFile.mkdirs();
				}

				// 将内存中的数据写入磁盘
				items_pic.transferTo(newFile);
				// 返回存储路径
				return newFile.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
				throw e; // 抛出异常
			}
		}
		return null;
	}

	/**
	 * 保存文件, 在文件默认保存路径下，增加自定义路径。返回保存路径(自定义路径+文件名)。
	 * 
	 * @return
	 * @throws Exception
	 */
    public static String save(MultipartFile items_pic, String path) throws Exception {
        String dateTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 文件原始名称
        String originalFilename = items_pic.getOriginalFilename();
        // 上传图片
        if (items_pic != null && originalFilename != null && originalFilename.length() > 0) {
            try {
                // 存储文件的物理路径
                String pic_path = SystemConfigUtil.getValue("upload_path") + "/" + path + "/" + dateTime;
                // 文件名称
                String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                newFileName = newFileName.replaceAll("-", "");
                // 新文件
                File newFile = new File(pic_path);
                // 判断文件夹是否存在不存在就创建
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }

                newFile = new File(pic_path, newFileName);
                // 判断文件夹是否存在不存在就创建
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }

                // 将内存中的数据写入磁盘
                items_pic.transferTo(newFile);
                // 返回存储路径
                return path + "/" + dateTime + "/" + newFileName;
            } catch (Exception e) {
                e.printStackTrace();
                throw e; // 抛出异常
            }
        }
        return null;
    }
	/**
	 * 保存图片, 在图片默认保存路径下，返回保存路径(上传图片路径前缀+(日期:yyyyMM)+文件名)。
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String saveAudio(MultipartFile items_pic) throws Exception {

		// 文件原始名称
		String originalFilename = items_pic.getOriginalFilename();
		// 上传图片
		if (items_pic != null && originalFilename != null && originalFilename.length() > 0) {
			try {
				// 存储文件的物理路径
				String pic_path = SystemConfigUtil.getValue("upload_path");
				// 图片路径
				String image_path = SystemConfigUtil.getValue("upload_image_path").trim();
				String date = MyDateUtil.getNowByCustom("yyyyMM");
				// 文件名称
				String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				newFileName = newFileName.replaceAll("-", "");
				// 新文件
				File newFile = new File(pic_path + image_path + "/" + date, newFileName);
				// 判断文件夹是否存在不存在就创建
				if (!newFile.exists()) {
					newFile.mkdirs();
				}
				// 将内存中的数据写入磁盘
				items_pic.transferTo(newFile);
				// 返回存储路径
				return image_path + "/" + date + "/" + newFileName;
			} catch (Exception e) {
				e.printStackTrace();
				throw e; // 抛出异常
			}
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param path,使用
	 *            默认路径+自定义路径进行删除。自定义路径可以只是文件名。
	 * @return
	 */
	public static void delete(String path) {
		File file = new File(SystemConfigUtil.getValue("upload_path") + "/" + path);
		// 判断是否存在 存在 就删除
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 检查文件格式
	 * 
	 * @param file
	 */
	public static boolean checkFileFormat(MultipartFile file, String[] formats) throws CustomException, Exception {
		if (null == file) {
			throw new CustomException(StatusCode.PARAM_NULL, "文件不能为空！");
		}
		if (null == formats || 0 == formats.length) {
			throw new CustomException(StatusCode.PARAM_NULL, "校验格式不能为空！");
		}
		// 文件原始名称
		String originalFilename = file.getOriginalFilename();
		String formatStr = originalFilename.substring(originalFilename.lastIndexOf("."));
		if (null == formatStr || "".equals(formatStr)) {
			return false;
		}
		for (String str : formats) {
			if (null == str) {
				continue;
			}
			str = str.replace(".", "");
			if (formatStr.equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查指定音频格式
	 * 
	 * @param file
	 */
	public static boolean checkAudioFormat(MultipartFile file) throws CustomException, Exception {

		if (null == file) {
			throw new CustomException(StatusCode.PARAM_NULL, "文件不能为空！");
		}

		String configFormatStr = SystemConfigUtil.getValue("head_format");
		String[] formats = null;
		if (null != configFormatStr && !"".equals(configFormatStr.trim()))
			formats = configFormatStr.split(",");

		if (null == formats || 0 == formats.length) {
			formats = new String[4];
			formats[0] = "pcm";
			formats[1] = "wav";
			formats[2] = "amr";
			formats[3] = "m4a";
		}
		String originalFilename = file.getOriginalFilename();

		String formatStr = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();

		if (null == formatStr || "".equals(formatStr)) {
			return false;
		}

		for (String str : formats) {
			if (null == str) {
				continue;
			}
			str = str.replace(".", "").toLowerCase();
			if (formatStr.equals(str)) {
				return true;
			}
		}
		return false;
	}
}
