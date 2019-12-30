package com.hc.pojo.base;

public class ReqPageBean {
	private Integer pageSize; //每页多少条
	private Integer page; //当前页
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
