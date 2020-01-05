package com.hc.controller.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hc.common.result.ResultBase;
import com.hc.service.TechnologyDocumentService;

@Controller
@RequestMapping("/technology/document")
@ResponseBody
public class TechnologyDocumentCotroller {
	@Autowired
	private TechnologyDocumentService technologyDocumentService; 
	/**
	 * 获取技术文档
	 * @param tbCaseTypeId 案件类型ID
	 * @param time 时间
	 * @param tbNumber  编号(档案号)
	 * @param tbAddress  案件地址
	 * @param tbSize  事件大小
	 * @param tbStar  关注星级
	 * @param tbCaseSaveCategory  案件保存类别（0：联网保存，1：草稿箱保存）
	 * @return ResultData
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUrl", method = RequestMethod.POST,produces="application/json")
	public ResultBase getUrl() throws Exception {
		return technologyDocumentService.getUrl();
	}
}
