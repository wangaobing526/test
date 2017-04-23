package com.dhcc.ecm.business.mybatis.capacity.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.springboot.model.BaseEntity;
/**
 * 库区信息表
 * @author starsand
 *
 */
@Table(name = "T_DM_STORAGE_AREA")
public class StorageArea extends BaseEntity implements Serializable {
	
    @Id
    @Column(name = "PK_STORAGEAREA_ID")
    @GeneratedValue(generator="UUID")
    private String id;
    //库区代码
    @Column(name = "STORAGEAREA_CODE")
    private String storageAreaCode;
    //库区名称
    @Column(name = "STORAGEAREA_NAME")
    private String storageAreaName;
    //创建时间
    @Column(name = "CREATETIME")
    private Date createTime;
    
    private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStorageAreaCode() {
		return storageAreaCode;
	}
	public void setStorageAreaCode(String storageAreaCode) {
		this.storageAreaCode = storageAreaCode;
	}
	public String getStorageAreaName() {
		return storageAreaName;
	}
	public void setStorageAreaName(String storageAreaName) {
		this.storageAreaName = storageAreaName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((storageAreaCode == null) ? 0 : storageAreaCode.hashCode());
		result = prime * result + ((storageAreaName == null) ? 0 : storageAreaName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StorageArea other = (StorageArea) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (storageAreaCode == null) {
			if (other.storageAreaCode != null)
				return false;
		} else if (!storageAreaCode.equals(other.storageAreaCode))
			return false;
		if (storageAreaName == null) {
			if (other.storageAreaName != null)
				return false;
		} else if (!storageAreaName.equals(other.storageAreaName))
			return false;
		return true;
	}
    
}