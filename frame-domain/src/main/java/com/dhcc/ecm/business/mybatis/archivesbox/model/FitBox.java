package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.springboot.model.BaseEntity;

/**
 * 装盒信息表
 * @author wangaobing
 *
 */
@Table(name = "T_DM_FITBOX")
public class FitBox extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "PK_FITBOX_ID")
    @GeneratedValue(generator="UUID")
    private String id;
    //档案盒ID
    @Column(name = "ARCHIVESBOX_ID")
    private String archivesBoxId;
    //文档ID
    @Column(name = "DOCUMENT_ID")
    private String documentId;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArchivesBoxId() {
		return archivesBoxId;
	}
	public void setArchivesBoxId(String archivesBoxId) {
		this.archivesBoxId = archivesBoxId;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((archivesBoxId == null) ? 0 : archivesBoxId.hashCode());
		result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FitBox other = (FitBox) obj;
		if (archivesBoxId == null) {
			if (other.archivesBoxId != null)
				return false;
		} else if (!archivesBoxId.equals(other.archivesBoxId))
			return false;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
