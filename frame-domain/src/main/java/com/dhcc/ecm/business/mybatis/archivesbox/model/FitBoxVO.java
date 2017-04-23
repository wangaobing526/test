package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 装盒信息表VO
 * @author wangaobing
 *
 */
public class FitBoxVO  implements Serializable {

	private static final long serialVersionUID = 1L;
    
    private String id;
    //档案盒ID
    private String archivesBoxId;
    //档案文件ID
    private String[] documentIds;
	//档案分类
    private String documentType;
    //排架编码
    private String bentFrameCode;
    
    
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

	public String getDocumentType() {
			return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getBentFrameCode() {
		return bentFrameCode;
	}
	@Override
	public String toString() {
		return "FitBoxVO [id=" + id + ", archivesBoxId=" + archivesBoxId + ", documentIds="
				+ Arrays.toString(documentIds) + ", documentType=" + documentType + ", bentFrameCode=" + bentFrameCode
				+ "]";
	}
	public void setBentFrameCode(String bentFrameCode) {
		this.bentFrameCode = bentFrameCode;
	}
	
	public String[] getDocumentIds() {
		return documentIds;
	}
	public void setDocumentIds(String[] documentIds) {
		this.documentIds = documentIds;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((archivesBoxId == null) ? 0 : archivesBoxId.hashCode());
		result = prime * result + ((bentFrameCode == null) ? 0 : bentFrameCode.hashCode());
		result = prime * result + Arrays.hashCode(documentIds);
		result = prime * result + ((documentType == null) ? 0 : documentType.hashCode());
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
		FitBoxVO other = (FitBoxVO) obj;
		if (archivesBoxId == null) {
			if (other.archivesBoxId != null)
				return false;
		} else if (!archivesBoxId.equals(other.archivesBoxId))
			return false;
		if (bentFrameCode == null) {
			if (other.bentFrameCode != null)
				return false;
		} else if (!bentFrameCode.equals(other.bentFrameCode))
			return false;
		if (!Arrays.equals(documentIds, other.documentIds))
			return false;
		if (documentType == null) {
			if (other.documentType != null)
				return false;
		} else if (!documentType.equals(other.documentType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
