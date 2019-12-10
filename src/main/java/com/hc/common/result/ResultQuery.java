package com.hc.common.result;


/**
 * API 查询，返回结果实体类
 * 
 * @ClassName: ResultQuery
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author DDM
 * @date 2019年1月30日 上午9:51:05
 * 
 * @param <T>
 */
public class ResultQuery<T> extends ResultBase {
	private Datas<T> data;

	public Datas<T> getData() {
		return data;
	}

	public void setData(Datas<T> data) {
		this.data = data;
	}

	/**
	 * 获取起始行
	 * 
	 * @param currPage
	 * @param size
	 * @return
	 */
	public static Integer getStart(Integer currPage, Integer size) {
		Integer start = null;
		if (null != currPage && null != size) {
			start = (currPage - 1) * size;
		}
		return start;
	}
}
