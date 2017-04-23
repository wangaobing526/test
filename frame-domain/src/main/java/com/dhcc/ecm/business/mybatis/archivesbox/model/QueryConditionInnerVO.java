package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class QueryConditionInnerVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="操作符",required=true)
	public String handle;
	
	@ApiModelProperty(value="where条件值",required=true)
	public String[] whereValue;
	
	@ApiModelProperty(value="where条件名",required=true)
	public String whereParam;
	
	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}


	public String getWhereParam() {
		return whereParam;
	}

	public void setWhereParam(String whereParam) {
		this.whereParam = whereParam;
	}

	public String[] getWhereValue() {
		return whereValue;
	}

	public void setWhereValue(String[] whereValue) {
		this.whereValue = whereValue;
	}


}
