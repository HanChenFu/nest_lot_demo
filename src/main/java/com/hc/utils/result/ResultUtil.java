package com.hc.utils.result;

import java.util.List;
import com.hc.common.code.StatusCode;
import com.hc.common.result.Datas;
import com.hc.common.result.ResultBase;
import com.hc.common.result.ResultData;
import com.hc.common.result.ResultQuery;

public class ResultUtil {
	/**
	 * 获取基本结果，不需要 数据。
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static ResultBase getResultBase(boolean success, StatusCode code, String message) {
		ResultBase res = new ResultBase();
		res.setCode(code);
		res.setMessage(message);
		res.setSuccess(success);
		return res;
	}

	/**
	 * 获取基本结果，不需要 数据。
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static ResultBase getResultBase(String message) {
		ResultBase res = new ResultBase();
		res.setCode(StatusCode.SUCCESS);
		res.setMessage(message);
		res.setSuccess(true);
		return res;
	}

	/**
	 * 获取基本结果，不需要 数据。
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static ResultBase getResultBase() {
		ResultBase res = new ResultBase();
		res.setCode(StatusCode.SUCCESS);
		res.setMessage("操作成功！");
		res.setSuccess(true);
		return res;
	}

	/**
	 * 获取基本结果，不需要 数据。
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static ResultBase getResultBase(boolean falg) {
		ResultBase res = new ResultBase();
		res.setCode(falg ? StatusCode.SUCCESS : StatusCode.ERROR);
		res.setMessage(falg ? "操作成功！" : "操作失败！");
		res.setSuccess(falg);
		return res;
	}

	/**
	 * 获取查询结果，带 数据和总条数
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultQuery<T> getResultQuery(boolean success, StatusCode code, String message, List<T> t,
			Integer totalItems) {
		ResultQuery<T> res = new ResultQuery<T>();
		res.setData(new Datas<T>());
		// 设置总条数
		res.getData().setTotalItems(totalItems);
		// 设置数据
		res.getData().setList(t);
		// 调用成功.
		res.setSuccess(success);
		// 设置 标识码
		res.setCode(code);
		// 设置消息
		res.setMessage(message);
		return res;
	}

	/**
	 * 获取查询结果，带 数据和总条数
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultQuery<T> getResultQuery(String message, List<T> t, Integer totalItems) {
		ResultQuery<T> res = new ResultQuery<T>();
		res.setData(new Datas<T>());
		// 设置总条数
		res.getData().setTotalItems(totalItems);
		// 设置数据
		res.getData().setList(t);
		// 调用成功.
		res.setSuccess(true);
		// 设置 标识码
		res.setCode(StatusCode.SUCCESS);
		// 设置消息
		res.setMessage(message);
		return res;
	}
	
	/**
	 * 获取查询结果，带 数据和总条数
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultQuery<T> getResultQuery(String message) {
		ResultQuery<T> res = new ResultQuery<T>();
		res.setData(new Datas<T>());
		// 设置总条数
		res.getData().setTotalItems(null);
		// 设置数据
		res.getData().setList(null);
		// 调用成功.
		res.setSuccess(true);
		// 设置 标识码
		res.setCode(StatusCode.SUCCESS);
		// 设置消息
		res.setMessage(message);
		return res;
	}

	/**
	 * 获取查询结果，带 数据和总条数
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultQuery<T> getResultQuery(List<T> t, Integer totalItems) {
		ResultQuery<T> res = new ResultQuery<T>();
		res.setData(new Datas<T>());
		// 设置总条数
		res.getData().setTotalItems(totalItems);
		// 设置数据
		res.getData().setList(t);
		// 调用成功.
		res.setSuccess(true);
		// 设置 标识码
		res.setCode(StatusCode.SUCCESS);
		// 设置消息
		res.setMessage("操作成功！");
		return res;
	}

	/**
	 * 获取查询结果，带数据
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultData<T> getResultData(boolean success, StatusCode code, String message, T t) {
		// 封装数据
		ResultData<T> res = new ResultData<T>();
		// 设置数据
		res.setData(t);
		// 调用成功.
		res.setSuccess(success);
		// 设置 标识码
		res.setCode(code);
		// 设置消息
		res.setMessage(message);
		return res;
	}

	/**
	 * 获取查询结果，带数据
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultData<T> getResultData(String message, T t) {
		// 封装数据
		ResultData<T> res = new ResultData<T>();
		// 设置数据
		res.setData(t);
		// 调用成功.
		res.setSuccess(true);
		// 设置 标识码
		res.setCode(StatusCode.SUCCESS);
		// 设置消息
		res.setMessage(message);
		return res;
	}
	
	/**
	 * 获取查询结果，带数据
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultData<T> getResultData(T t) {
		// 封装数据
		ResultData<T> res = new ResultData<T>();
		// 设置数据
		res.setData(t);
		// 调用成功.
		res.setSuccess(true);
		// 设置 标识码
		res.setCode(StatusCode.SUCCESS);
		// 设置消息
		res.setMessage("操作成功！");
		return res;
	}
	
	/**
	 * 获取查询结果，带数据
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultData<T> getResultData(String message) {
		// 封装数据
		ResultData<T> res = new ResultData<T>();
		// 设置数据
		res.setData(null);
		// 调用成功.
		res.setSuccess(true);
		// 设置 标识码
		res.setCode(StatusCode.SUCCESS);
		// 设置消息
		res.setMessage(message);
		return res;
	}
	
	/**
	 * 获取查询结果，带数据
	 * 
	 * @author DDM 2018年1月18日
	 */
	public static <T> ResultData<T> getResultData1(T t) {
		// 封装数据
		ResultData<T> res = new ResultData<T>();
		// 设置数据
		res.setData(t);
		// 调用成功.
		res.setSuccess(true);
		// 设置 标识码
		res.setCode(StatusCode.SUCCESS);
		// 设置消息
		res.setMessage("操作成功！");
		return res;
	}
}
