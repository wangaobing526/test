package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import tk.mybatis.springboot.model.BaseEntity;

public class BentFrameOftenUsedVO  implements Serializable {
	private static final long serialVersionUID = 1L;
   
	//柜节模糊查询条件，去掉盒号的部分
	private String bentFrameCodeConditon;
	//****************************
	private String id;
	// 排架编码
	private String bentFrameCode;
    // 库区代码
 	private String storageAreaCode;
 	// 库房代码
 	private String storageRoomCode;
 	// 柜架代码
 	private String cabinetCode;
	// 所属柜节
	private int cabinetNode;
	// 档案盒厚度
	private int archivesBoxWide;
	// 所属用户
	private String belongUser;
	
	//创建时间
	private Date createTime;
	
	
    private Integer page = 1;

    private Integer rows = 10;
	
	public Date getCreateTime() {
		return createTime;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBentFrameCode() {
		return bentFrameCode;
	}
	public void setBentFrameCode(String bentFrameCode) {
		this.bentFrameCode = bentFrameCode;
	}

	
	public String getStorageAreaCode() {
		return storageAreaCode;
	}
	public void setStorageAreaCode(String storageAreaCode) {
		this.storageAreaCode = storageAreaCode;
	}
	public String getStorageRoomCode() {
		return storageRoomCode;
	}
	public void setStorageRoomCode(String storageRoomCode) {
		this.storageRoomCode = storageRoomCode;
	}
	public String getCabinetCode() {
		return cabinetCode;
	}
	public void setCabinetCode(String cabinetCode) {
		this.cabinetCode = cabinetCode;
	}
	
	public int getArchivesBoxWide() {
		return archivesBoxWide;
	}
	public void setArchivesBoxWide(int archivesBoxWide) {
		this.archivesBoxWide = archivesBoxWide;
	}
	public String getBelongUser() {
		return belongUser;
	}
	public void setBelongUser(String belongUser) {
		this.belongUser = belongUser;
	}
	public String getBentFrameCodeConditon() {
		return bentFrameCodeConditon;
	}
	public void setBentFrameCodeConditon(String bentFrameCodeConditon) {
		this.bentFrameCodeConditon = bentFrameCodeConditon;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + archivesBoxWide;
		result = prime * result + ((belongUser == null) ? 0 : belongUser.hashCode());
		result = prime * result + ((bentFrameCode == null) ? 0 : bentFrameCode.hashCode());
		result = prime * result + ((bentFrameCodeConditon == null) ? 0 : bentFrameCodeConditon.hashCode());
		result = prime * result + ((cabinetCode == null) ? 0 : cabinetCode.hashCode());
		result = prime * result + cabinetNode;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((page == null) ? 0 : page.hashCode());
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
		result = prime * result + ((storageAreaCode == null) ? 0 : storageAreaCode.hashCode());
		result = prime * result + ((storageRoomCode == null) ? 0 : storageRoomCode.hashCode());
		return result;
	}
	public int getCabinetNode() {
		return cabinetNode;
	}
	public void setCabinetNode(int cabinetNode) {
		this.cabinetNode = cabinetNode;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BentFrameOftenUsedVO other = (BentFrameOftenUsedVO) obj;
		if (archivesBoxWide != other.archivesBoxWide)
			return false;
		if (belongUser == null) {
			if (other.belongUser != null)
				return false;
		} else if (!belongUser.equals(other.belongUser))
			return false;
		if (bentFrameCode == null) {
			if (other.bentFrameCode != null)
				return false;
		} else if (!bentFrameCode.equals(other.bentFrameCode))
			return false;
		if (bentFrameCodeConditon == null) {
			if (other.bentFrameCodeConditon != null)
				return false;
		} else if (!bentFrameCodeConditon.equals(other.bentFrameCodeConditon))
			return false;
		if (cabinetCode == null) {
			if (other.cabinetCode != null)
				return false;
		} else if (!cabinetCode.equals(other.cabinetCode))
			return false;
		if (cabinetNode != other.cabinetNode)
			return false;
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
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		if (storageAreaCode == null) {
			if (other.storageAreaCode != null)
				return false;
		} else if (!storageAreaCode.equals(other.storageAreaCode))
			return false;
		if (storageRoomCode == null) {
			if (other.storageRoomCode != null)
				return false;
		} else if (!storageRoomCode.equals(other.storageRoomCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BentFrameOftenUsedVO [bentFrameCodeConditon=" + bentFrameCodeConditon + ", id=" + id
				+ ", bentFrameCode=" + bentFrameCode + ", storageAreaCode=" + storageAreaCode + ", storageRoomCode="
				+ storageRoomCode + ", cabinetCode=" + cabinetCode + ", cabinetNode=" + cabinetNode
				+ ", archivesBoxWide=" + archivesBoxWide + ", belongUser=" + belongUser + ", createTime=" + createTime
				+ ", page=" + page + ", rows=" + rows + "]";
	}

}
