package com.hc.pojo.base;

public class ReqPageBean {

	private Integer page = 1; // 查询页
	private Integer pageSize = 10; // 每页条数
	private Integer start; // 开始条数

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if (null != page && page > 0) {
			this.page = page;
		} else {
			this.page = 1;
		}
	}

	public Integer getSize() {
		return pageSize;
	}

	public void setSize(Integer size) {
		if (null != size && size > 0) {
			this.pageSize = size;
		} else {
			size = null;
		}

	}

	/**
	 * 获取启始条数
	 * 
	 * @return
	 */
	public Integer getStart() {
		start = null;
		if (null != page && null != pageSize) {
			start = (page - 1) * pageSize;
		}
		return start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
	
}
