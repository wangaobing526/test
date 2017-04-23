package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.springboot.model.BaseEntity;

/**
 * 初始化柜节信息
 * 
 * @author starsand
 *
 */
@Table(name = "T_DM_INITCABINETNODE")
public class InitCabinetNode extends BaseEntity implements Serializable {

	@Id
	@Column(name = "PK_INITCABINETNODE_ID")
	@GeneratedValue(generator = "UUID")
	private String id;

	// 库区代码
	@Column(name = "STORAGEAREA_CODE")
	private String storageAreaCode;
	// 库房代码
	@Column(name = "STORAGEROOM_CODE")
	private String storageRoomCode;
	// 柜架代码
	@Column(name = "CABINET_CODE")
	private String cabinetCode;
	// 所属柜节
	@Column(name = "CABINETNODE")
	private int cabinetNode;
	// 柜节宽度
	@Column(name = "CABINETNODEWIDE")
	private int cabinetNodeWide;
	// 当前档案盒宽度
	@Column(name = "ARCHIVESBOXWIDE")
	private int archivesBoxWide;
	// 当前档案盒号
	@Column(name = "ARCHIVESBOXNUM")
	private String archivesBoxNum;
	// 排架编码
	@Column(name = "BENTFRAMECODE")
	private String bentFrameCode;
	// 当前档案盒总宽度
	@Column(name = "ALLBOXWIDE")
	private int allBoxWide;

	public int getAllBoxWide() {
		return allBoxWide;
	}

	public void setAllBoxWide(int allBoxWide) {
		this.allBoxWide = allBoxWide;
	}

	public String getBentFrameCode() {
		return bentFrameCode;
	}

	public void setBentFrameCode(String bentFrameCode) {
		this.bentFrameCode = bentFrameCode;
	}

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

	public int getCabinetNode() {
		return cabinetNode;
	}

	public void setCabinetNode(int cabinetNode) {
		this.cabinetNode = cabinetNode;
	}

	public int getCabinetNodeWide() {
		return cabinetNodeWide;
	}

	public void setCabinetNodeWide(int cabinetNodeWide) {
		this.cabinetNodeWide = cabinetNodeWide;
	}

	public int getArchivesBoxWide() {
		return archivesBoxWide;
	}

	public void setArchivesBoxWide(int archivesBoxWide) {
		this.archivesBoxWide = archivesBoxWide;
	}

	public String getArchivesBoxNum() {
		return archivesBoxNum;
	}

	public void setArchivesBoxNum(String archivesBoxNum) {
		this.archivesBoxNum = archivesBoxNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allBoxWide;
		result = prime * result + ((archivesBoxNum == null) ? 0 : archivesBoxNum.hashCode());
		result = prime * result + archivesBoxWide;
		result = prime * result + ((bentFrameCode == null) ? 0 : bentFrameCode.hashCode());
		result = prime * result + ((cabinetCode == null) ? 0 : cabinetCode.hashCode());
		result = prime * result + cabinetNode;
		result = prime * result + cabinetNodeWide;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((storageAreaCode == null) ? 0 : storageAreaCode.hashCode());
		result = prime * result + ((storageRoomCode == null) ? 0 : storageRoomCode.hashCode());
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
		InitCabinetNode other = (InitCabinetNode) obj;
		if (allBoxWide != other.allBoxWide)
			return false;
		if (archivesBoxNum == null) {
			if (other.archivesBoxNum != null)
				return false;
		} else if (!archivesBoxNum.equals(other.archivesBoxNum))
			return false;
		if (archivesBoxWide != other.archivesBoxWide)
			return false;
		if (bentFrameCode == null) {
			if (other.bentFrameCode != null)
				return false;
		} else if (!bentFrameCode.equals(other.bentFrameCode))
			return false;
		if (cabinetCode == null) {
			if (other.cabinetCode != null)
				return false;
		} else if (!cabinetCode.equals(other.cabinetCode))
			return false;
		if (cabinetNode != other.cabinetNode)
			return false;
		if (cabinetNodeWide != other.cabinetNodeWide)
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
		if (storageRoomCode == null) {
			if (other.storageRoomCode != null)
				return false;
		} else if (!storageRoomCode.equals(other.storageRoomCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InitCabinetNode [id=" + id + ", storageAreaCode=" + storageAreaCode + ", storageRoomCode="
				+ storageRoomCode + ", cabinetCode=" + cabinetCode + ", cabinetNode=" + cabinetNode
				+ ", cabinetNodeWide=" + cabinetNodeWide + ", archivesBoxWide=" + archivesBoxWide + ", archivesBoxNum="
				+ archivesBoxNum + ", bentFrameCode=" + bentFrameCode + ", allBoxWide=" + allBoxWide + "]";
	}
}