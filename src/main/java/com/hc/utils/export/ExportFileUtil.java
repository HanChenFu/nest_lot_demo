package com.hc.utils.export;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.hc.utils.file.CustomXWPFDocument;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class ExportFileUtil extends XWPFDocument{
	static String fileName = "SDH0220191226145555000095";
	static String sheetName = "SDH0220191226145555000095";
	/* String[] title = new String[]{"hahahah","hahahah"}; */
	/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	String res = simpleDateFormat.format(date);*/

	static String[][] values = new String[][] { { "档案编号", "SDH0220191226145555000095" }, { "归档日期", "yyyy-MM-dd HH:mm:ss" },
			{ "档案类型", "普通案件" }, { "报案地点", "马田派出所" }, { "事件大小", "小" }, { "关注星级", "2" }, { "案件地址", "哈哈哈哈哈" },
			{ "案件经过",
					"哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济" },
			{ "案件备注",
					"哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济哈桑来房间瓦尔克服务IE案件覅问饿哦发鸡娃儿经济" }, 
			{"案件图片",""}};
	static String img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577364013275&di=e7c46f137a6ee19d19fff563756f04fb&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb151f8198618367aa7f3cc7424738bd4b31ce525.jpg"
			+ ",https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577364013275&di=e7c46f137a6ee19d19fff563756f04fb&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb151f8198618367aa7f3cc7424738bd4b31ce525.jpg"			
			+ ",https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577364013275&di=e7c46f137a6ee19d19fff563756f04fb&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb151f8198618367aa7f3cc7424738bd4b31ce525.jpg"			
			+ ",https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577364013275&di=e7c46f137a6ee19d19fff563756f04fb&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb151f8198618367aa7f3cc7424738bd4b31ce525.jpg"			
			+ ",https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577364013275&di=e7c46f137a6ee19d19fff563756f04fb&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb151f8198618367aa7f3cc7424738bd4b31ce525.jpg"			
			+ ",https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577364013275&di=e7c46f137a6ee19d19fff563756f04fb&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb151f8198618367aa7f3cc7424738bd4b31ce525.jpg";				
	/**
	 * 文件/文件夹删除方法
	 * @param file 文件/文件夹路径
	 * @return boolean 是否
	 * ***/
	static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }
	/**
	 * 根据网络路径下载图片并返回本地路径
	 * @param img 网络图片路径
	 * @return String[] 本地路径数组
	 * ***/
	public static String[] BreakImg(String img) {
		String[] imgs = img.split(",");
		String[] imgsUrl = new String[imgs.length];
		for(int i =0 ; i < imgs.length ; i ++){
			URL url = null;
	        try {
	            url = new URL(imgs[i]);
	            DataInputStream dataInputStream = new DataInputStream(url.openStream());
	            String imageName =  "D:/" + UUID.randomUUID() + ".jpg";
	            imgsUrl[i] = imageName;
	            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
	            ByteArrayOutputStream output = new ByteArrayOutputStream();

	            byte[] buffer = new byte[1024];
	            int length;

	            while ((length = dataInputStream.read(buffer)) > 0) {
	                output.write(buffer, 0, length);
	            }
	            /*byte[] context=output.toByteArray();*/
	            fileOutputStream.write(output.toByteArray());
	            dataInputStream.close();
	            fileOutputStream.close();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		return imgsUrl;
	}
	/**
	 * excel导出到本地
	 * @param img 网络图片路径
	 * @return String[] 本地路径数组
	 * ***/
	public static void exportExcelFile() {
		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		// 创建内容 文本内容
		for (int i = 0; i < values.length; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < values[i].length; j++) {
				// 将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
			}
		}
		// 创建内容 图片内容
		String[] imgs = BreakImg(img);
		for (int h = 0; h < imgs.length; h++) {
			//row = sheet.createRow(h);
			BufferedImage bufferImg = null; 	
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();   
            try {
				bufferImg = ImageIO.read(new File(imgs[h]));
				ImageIO.write(bufferImg, "jpg", byteArrayOut);
				//画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
				HSSFPatriarch patriarch = sheet.createDrawingPatriarch();   
				//anchor主要用于设置图片的属性
				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255,(short) 0, values.length+h*8+1, (short) 4, values.length+(h+1)*8);   
				anchor.setAnchorType(3);   
				//插入图片  
				patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG)); 
				delFile(new File(imgs[h]));
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("D:/" + fileName + ".xlsx");
			wb.write(fout);
			String str = "导出" + fileName + "成功！";
			System.out.println(str);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			String str1 = "导出" + fileName + "失败！";
			System.out.println(str1);
		}
	}
	/**
	 * word导出到本地
	 * @param img 网络图片路径
	 * @return String[] 本地路径数组
	 * ***/
	public static void exportWordFile( ) {
		CustomXWPFDocument doc = new CustomXWPFDocument();
		XWPFParagraph para = doc.createParagraph(); // 一个XWPFRun代表具有相同属性的一个区域：一段文本
		XWPFRun run = para.createRun();
		/*
		 * run.setBold(true); // 加粗 run.setText("加粗的内容"); run =
		 * para.createRun(); run.setColor("FF0000"); run.addCarriageReturn();
		 * run.setText("红色的字。");
		 */
		for (int i = 0; i < values.length; i++) {
			run.setBold(true);
			for (int j = 0; j < values[i].length; j++) {
				// 将内容按顺序赋给对应的列对象
				run.setText(values[i][j]);
				if(j==0){
					run.setText(":");
				}
				run.setText("     ");
				run = para.createRun();
			}
			run.addCarriageReturn();
		}
		// 创建内容 图片内容
		String[] imgs = BreakImg(img);
		for (int h = 0; h < imgs.length; h++) {
            try {
            	FileInputStream in = new FileInputStream(imgs[h]);
            	byte[] ba = new byte[in.available()];
            	in.read(ba);
            	ByteArrayInputStream byteInputStream = new ByteArrayInputStream(ba);
            	XWPFParagraph picture = doc.createParagraph();
            	//添加图片
            	doc.addPictureData(byteInputStream, CustomXWPFDocument.PICTURE_TYPE_JPEG);
            	//图片大小、位置
            	doc.createPicture(doc.getAllPictures().size() - 1, 300, 200, picture);
            	run.addCarriageReturn();
            } catch (FileNotFoundException e1) {
            	// TODO Auto-generated catch block
            	e1.printStackTrace();
            } catch (IOException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            } catch (InvalidFormatException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            }
		}
		
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("D:/" + fileName + ".docx");
			doc.write(fout);
			String str = "导出" + fileName + "成功！";
			System.out.println(str);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			String str1 = "导出" + fileName + "失败！";
			System.out.println(str1);
		}
		for (int h = 0; h < imgs.length; h++) {
			System.out.println(imgs[h]);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			delFile(new File(imgs[h]));
		}
	}
	/**
	 * pdf导出到本地
	 * @param img 网络图片路径
	 * @return String[] 本地路径数组
	 * ***/
	public static void exportPdfFile() {
		//创建一个h5页面
		StringBuffer html = new StringBuffer();
		//循环插入内容
		for (int i = 0; i < values.length; i++) {
			String str = "";
			for (int j = 0; j < values[i].length; j++) {
				str = str + values[i][j];
				if(j==0){
					str = str + "&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;";
				}
			}
			html.append("<div>" + str + "</div>");
			html.append("<br/>");
		}
		//循环插入图片
		String[] imgs = img.split(",");
		for(int i = 0; i < imgs.length; i++){
			html.append("<img src='"+imgs[i]+"' width='400'/>");
		}
		try {
			//把h5转换成pdf格式的，存储到本地
			Document document = new Document();
			PdfWriter mPdfWriter = PdfWriter.getInstance(document, new FileOutputStream("D:/" + fileName + ".pdf"));
			document.open();
			String s = html.toString();
			ByteArrayInputStream bin = new ByteArrayInputStream(s.getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(mPdfWriter, document, bin, null, new ChinaFontProvide());
			System.out.println("OK");
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//h5转换pdf工具类
	public static final class ChinaFontProvide implements FontProvider {
		@Override
		public Font getFont(String arg0, String arg1, boolean arg2, float arg3, int arg4, BaseColor arg5) {
			BaseFont bfChinese = null;
			try {
				bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				// 也可以使用Windows系统字体(TrueType)
				// bfChinese =
				// BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF",
				// BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Font FontChinese = new Font(bfChinese, 20, Font.NORMAL);
			return FontChinese;
		}
		@Override
		public boolean isRegistered(String arg0) {
			return false;
		}
	}

	public static void main(String[] args) {
		//getHSSFWorkbook();
		//String[] imgs = BreakImg(img);
		//getHSSFWorkbook2();
		//for (int h = 0; h < imgs.length; h++) {
		//	delFile(new File("D:/5d8ffcf1-d98f-4705-a149-e1579322e6e4.jpg"));
		//}
		//getHSSFWorkbook3();
	}
}
