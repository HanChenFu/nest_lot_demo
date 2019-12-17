package com.hc.para.page_base;

import java.util.List;

/**
 * 这个对象主要是用于传参的时候用
 * @author Administrator
 *
 */
public class BasePara extends PageParam{
	private String type;
	private int id;
	private List<Integer> listid;//这边是作为前台页面传过来的参数定义的
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getListid() {
		return listid;
	}

	public void setListid(List<Integer> listid) {
		this.listid = listid;
	}

}
