package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;

import com.github.pagehelper.StringUtil;

/**
 * @ClassName InitCabinetNodeVO
 * @Description 初始化柜节VO
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-13
 */
public class InitCabinetNodeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	// 档案分类
	private String documentType;
	// 常用排架，0为设置常用排架、1为不设置常用排架
	private String bentFrameOftenUsedFlag;
	// 柜节模糊查询条件，去掉盒号的部分
	private String bentFrameCodeConditon;

	// **********************************
	private String id;
	// 库区代码
	private String storageAreaCode;
	// 库房代码
	private String storageRoomCode;
	// 柜架代码
	private String cabinetCode;
	// 所属柜节
	private int cabinetNode;
	// 柜节宽度
	private int cabinetNodeWide;
	// 当前档案盒宽度
	private int archivesBoxWide;
	// 当前档案盒号
	private String archivesBoxNum;
	// 当前档案盒总宽度
	private int allBoxWide;
	// 排架编码
	private String bentFrameCode;

	public String getBentFrameCodeConditon() {
		if (!StringUtil.isEmpty(this.getBentFrameCode())) {
			bentFrameCodeConditon = this.getBentFrameCode().substring(0, this.getBentFrameCode().lastIndexOf("-"));
		}
		return bentFrameCodeConditon;
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

	public void setBentFrameCodeConditon(String bentFrameCodeConditon) {
		this.bentFrameCodeConditon = bentFrameCodeConditon;
	}

	public String getBentFrameCode() {
		return bentFrameCode;
	}

	public void setBentFrameCode(String bentFrameCode) {
		this.bentFrameCode = bentFrameCode;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getBentFrameOftenUsedFlag() {
		return bentFrameOftenUsedFlag;
	}

	public void setBentFrameOftenUsedFlag(String bentFrameOftenUsedFlag) {
		this.bentFrameOftenUsedFlag = bentFrameOftenUsedFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setAllBoxWide(int allBoxWide) {
		this.allBoxWide = allBoxWide;
	}

	public int getAllBoxWide() {
		return allBoxWide;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allBoxWide;
		result = prime * result + ((archivesBoxNum == null) ? 0 : archivesBoxNum.hashCode());
		result = prime * result + archivesBoxWide;
		result = prime * result + ((bentFrameCode == null) ? 0 : bentFrameCode.hashCode());
		result = prime * result + ((bentFrameCodeConditon == null) ? 0 : bentFrameCodeConditon.hashCode());
		result = prime * result + ((bentFrameOftenUsedFlag == null) ? 0 : bentFrameOftenUsedFlag.hashCode());
		result = prime * result + ((cabinetCode == null) ? 0 : cabinetCode.hashCode());
		result = prime * result + cabinetNode;
		result = prime * result + cabinetNodeWide;
		result = prime * result + ((documentType == null) ? 0 : documentType.hashCode());
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
		InitCabinetNodeVO other = (InitCabinetNodeVO) obj;
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
		if (bentFrameCodeConditon == null) {
			if (other.bentFrameCodeConditon != null)
				return false;
		} else if (!bentFrameCodeConditon.equals(other.bentFrameCodeConditon))
			return false;
		if (bentFrameOftenUsedFlag == null) {
			if (other.bentFrameOftenUsedFlag != null)
				return false;
		} else if (!bentFrameOftenUsedFlag.equals(other.bentFrameOftenUsedFlag))
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
		return "InitCabinetNodeVO [documentType=" + documentType + ", bentFrameOftenUsedFlag=" + bentFrameOftenUsedFlag
				+ ", bentFrameCodeConditon=" + bentFrameCodeConditon + ", id=" + id + ", storageAreaCode="
				+ storageAreaCode + ", storageRoomCode=" + storageRoomCode + ", cabinetCode=" + cabinetCode
				+ ", cabinetNode=" + cabinetNode + ", cabinetNodeWide=" + cabinetNodeWide + ", archivesBoxWide="
				+ archivesBoxWide + ", archivesBoxNum=" + archivesBoxNum + ", allBoxWide=" + allBoxWide
				+ ", bentFrameCode=" + bentFrameCode + "]";
	}

}
