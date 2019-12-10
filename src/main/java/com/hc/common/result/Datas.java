package com.hc.common.result;

import java.util.List;

/**
 * API 查询实体
 * 
 * @ClassName: Datas
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author DDM
 * @date 2019年1月30日 上午9:51:28
 * 
 * @param <T>
 */
public class Datas<T> {
	private List<T> list;
	private Integer totalItems; // 总条数

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

}
