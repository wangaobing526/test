package com.dhcc.ecm.business.mybatis.archivesbox.model;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;




/**
 * @ClassName DocumentBean
 * @Description 文档属性实体类
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2016-06-02
 */
public class DocumentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 文种类型
	 */
	@ApiModelProperty(value="文种类型",required=true)
	private String recordClassification;
	
//	/**
//	 * 附件列表
//	 */
//	@ApiModelProperty(value="附件列表",required=false)
//    private List<AttachedLists> attachedLists;  
	
	/**
	 * 元数据列表
	 */
	@ApiModelProperty(value="元数据列表",required=false)
    private List<MetaItems> metaItems;          
	
	/**
	 * 是否只修改附件
	 */
	@ApiModelProperty(value="元数据列表",required=false)
    private boolean attachModified;      
	
	/**
	 * 电厂ID
	 */
	@ApiModelProperty(value="电厂ID",required=true)
    private String planId;   
	
	/**
	 * 文档ID
	 */
	@ApiModelProperty(value="文档ID",required=false)
    private String id;   
	
	/**
	 * 1公司、2部门、3承包商
	 */
	@ApiModelProperty(value="内容库类型",required=true)
	private String corpOrDept;
    
    public String getCorpOrDept() {
		return corpOrDept;
	}
	public void setCorpOrDept(String corpOrDept) {
		this.corpOrDept = corpOrDept;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

//	public List<AttachedLists> getAttachedLists() {
//		return attachedLists;
//	}
//	public void setAttachedLists(List<AttachedLists> attachedLists) {
//		this.attachedLists = attachedLists;
//	}
	public boolean isAttachModified() {
		return attachModified;
	}
	public void setAttachModified(boolean attachModified) {
		this.attachModified = attachModified;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
    public List<MetaItems> getMetaItems() {
        return metaItems;
    }
    public void setMetaItems(List<MetaItems> metaItems) {
        this.metaItems = metaItems;
    }
    public String getRecordClassification() {
        return recordClassification;
    }
    public void setRecordClassification(String recordClassification) {
        this.recordClassification = recordClassification;
    }


}