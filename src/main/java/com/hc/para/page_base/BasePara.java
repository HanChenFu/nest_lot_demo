package com.hc.para.page_base;

/**
 * 这个对象主要是用于传参的时候用
 * @author Administrator
 *
 */
public class BasePara extends PageParam{
	private String type;
	private int id;

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
	

}
