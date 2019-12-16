package com.hc.utils.ffmpeg;

import java.io.File;

import com.hc.utils.conig.SystemConfigUtil;

public class FfmpeGUtil {
	
	/**
	 * 	如果是这边运行出现异常，那就需要在配置文件中把配置文件的 ffmpeg_bin_path 的路径改成 ffmpeg.exe的路径
	 * @param sourcePath
	 * @param targetPath
	 * @return
	 * @throws Exception
	 */
    public static String changeAudioToPcm(String sourcePath, String targetPath) throws Exception {
        String webroot = SystemConfigUtil.getValue("ffmpeg_bin_path");
        Runtime run = null;
        try {
            run = Runtime.getRuntime();
            long start=System.currentTimeMillis();
            //执行ffmpeg.exe,前面是ffmpeg.exe的地址，中间是需要转换的文件地址，后面是转换后的文件地址。-i是转换方式，意思是可编码解码，mp3编码方式采用的是libmp3lame
            //wav转pcm
            //Process p=run.exec(new File(webroot).getAbsolutePath()+"/ffmpeg -y -i "+sourcePath+" -acodec pcm_s16le -f s16le -ac 1 -ar 16000 "+targetPath);
            Process p=run.exec(new File(webroot).getAbsolutePath()+"/ffmpeg -y -i "+sourcePath+" -acodec pcm_s16le -f s16le -ac 1 -ar 16000 "+targetPath);
            //释放进程
            p.getOutputStream().close();
            p.getInputStream().close();
            p.getErrorStream().close();
            p.waitFor();
            long end=System.currentTimeMillis();
            return sourcePath;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //run调用lame解码器最后释放内存
            run.freeMemory();
        }
        return null;
    }
    
	public static void main(String[] args) {
        String sPath = "C:/Users/Administrator/Desktop/20191212_113745.m4a";
        String tPath = "C:/Users/Administrator/Desktop/test.pcm";
        try {
            new FfmpeGUtil().changeAudioToPcm(sPath,tPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
