package com.hc.common.result;

/**
 * API 返回，带数据
 * 
 * @ClassName: ResultData
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author DDM
 * @date 2019年1月30日 上午9:50:51
 * 
 * @param <T>
 */
public class ResultData<T> extends ResultBase {

	private T data;// 数据

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}

