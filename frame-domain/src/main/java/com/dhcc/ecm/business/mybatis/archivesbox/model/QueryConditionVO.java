package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class QueryConditionVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="查询条件集合",required=true)
	public List<QueryConditionInnerVO> condition;
	
	@ApiModelProperty(value="返回列属性",required=true)
	public String[] columns;
	
	public List<QueryConditionInnerVO> getCondition() {
		return condition;
	}
	public void setCondition(List<QueryConditionInnerVO> condition) {
		this.condition = condition;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
}

