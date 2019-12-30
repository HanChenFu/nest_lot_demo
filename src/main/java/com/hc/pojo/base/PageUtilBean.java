package com.hc.pojo.base;


import java.util.ArrayList;


public class PageUtilBean {
	private Integer pagesize = 10;//每页多少条
	private Integer pagetotal;//总页数
	private Integer totalCount;//总条数
	private Integer page = 1;//当前页
	private Object results = new ArrayList<Object>();
	
	public PageUtilBean(){
		
	}
	
	public PageUtilBean(Integer pagesize, Integer pagetotal, Integer totalCount, Integer page, Object results) {
		super();
		this.pagesize = pagesize;
		this.pagetotal = pagetotal;
		this.totalCount = totalCount;
		this.page = page;
		this.results = results;
	}

	public PageUtilBean(Integer pagesize, Integer totalCount, Integer page) {
		super();
		this.pagesize = pagesize == null ? 10 : (pagesize <= 0 ? 10 : pagesize );
		this.totalCount = totalCount == null ? 0 : totalCount;
		this.pagetotal = this.totalCount % this.pagesize == 0 ? (this.totalCount / this.pagesize):(this.totalCount / this.pagesize + 1);
		this.page = page == null ? 1 : (page < 1 ? 1 : page );
	}
	public void setResults(Object results) {
		this.results = results;
	}

	public Integer getPagesize() {
		return pagesize;
	}
	public Integer getPagetotal() {
		return pagetotal;
	}
	public Integer getPage() {
		return page;
	}
	public Object getResults() {
		return results;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	
	
	public int limitsTart() {
		return (page-1)*pagesize;
	}
	public int limitsEnd() {
		return pagesize;
	}
}
