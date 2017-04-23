package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 档案盒信息表
 * @author starsand
 *
 */
public class ArchivesBoxVO  implements Serializable {
	private static final long serialVersionUID = 1L;
    private String id;
    //盒号
    private String boxNum;
    //所属库区
    private String storageAreaId;
    //所属库房
    private String storageRoomId;
    //所属柜架
    private String cabinetId;
    //所属柜节
    private int cabinetNode;
    //档案盒厚度
    private int archivesBoxThick;
    //排架编码
    private String bentFrameCode;
    //档案盒题名
    private String archivesBoxName;
    //档案分类
    private String archivesBoxType;
    //创建时间
    private Date fitBoxTime;
    //创建人
    private String fitBoxUser;
    //保管年限
    private String storageYear;
    //聚合层次
    private String polymerize;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(String boxNum) {
		this.boxNum = boxNum;
	}
	public String getStorageAreaId() {
		return storageAreaId;
	}
	public void setStorageAreaId(String storageAreaId) {
		this.storageAreaId = storageAreaId;
	}
	public String getStorageRoomId() {
		return storageRoomId;
	}
	public void setStorageRoomId(String storageRoomId) {
		this.storageRoomId = storageRoomId;
	}
	public String getCabinetId() {
		return cabinetId;
	}
	public void setCabinetId(String cabinetId) {
		this.cabinetId = cabinetId;
	}

	public int getArchivesBoxThick() {
		return archivesBoxThick;
	}
	public void setArchivesBoxThick(int archivesBoxThick) {
		this.archivesBoxThick = archivesBoxThick;
	}
	public String getBentFrameCode() {
		return bentFrameCode;
	}
	public void setBentFrameCode(String bentFrameCode) {
		this.bentFrameCode = bentFrameCode;
	}
	public String getArchivesBoxName() {
		return archivesBoxName;
	}
	public void setArchivesBoxName(String archivesBoxName) {
		this.archivesBoxName = archivesBoxName;
	}
	public String getArchivesBoxType() {
		return archivesBoxType;
	}
	public void setArchivesBoxType(String archivesBoxType) {
		this.archivesBoxType = archivesBoxType;
	}
	public Date getFitBoxTime() {
		return fitBoxTime;
	}
	public void setFitBoxTime(Date fitBoxTime) {
		this.fitBoxTime = fitBoxTime;
	}
	public String getFitBoxUser() {
		return fitBoxUser;
	}
	public void setFitBoxUser(String fitBoxUser) {
		this.fitBoxUser = fitBoxUser;
	}
	public String getStorageYear() {
		return storageYear;
	}
	public void setStorageYear(String storageYear) {
		this.storageYear = storageYear;
	}
	public String getPolymerize() {
		return polymerize;
	}
	public void setPolymerize(String polymerize) {
		this.polymerize = polymerize;
	}
	public int getCabinetNode() {
		return cabinetNode;
	}
	public void setCabinetNode(int cabinetNode) {
		this.cabinetNode = cabinetNode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((archivesBoxName == null) ? 0 : archivesBoxName.hashCode());
		result = prime * result + archivesBoxThick;
		result = prime * result + ((archivesBoxType == null) ? 0 : archivesBoxType.hashCode());
		result = prime * result + ((bentFrameCode == null) ? 0 : bentFrameCode.hashCode());
		result = prime * result + ((boxNum == null) ? 0 : boxNum.hashCode());
		result = prime * result + ((cabinetId == null) ? 0 : cabinetId.hashCode());
		result = prime * result + cabinetNode;
		result = prime * result + ((fitBoxTime == null) ? 0 : fitBoxTime.hashCode());
		result = prime * result + ((fitBoxUser == null) ? 0 : fitBoxUser.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((polymerize == null) ? 0 : polymerize.hashCode());
		result = prime * result + ((storageAreaId == null) ? 0 : storageAreaId.hashCode());
		result = prime * result + ((storageRoomId == null) ? 0 : storageRoomId.hashCode());
		result = prime * result + ((storageYear == null) ? 0 : storageYear.hashCode());
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
		ArchivesBoxVO other = (ArchivesBoxVO) obj;
		if (archivesBoxName == null) {
			if (other.archivesBoxName != null)
				return false;
		} else if (!archivesBoxName.equals(other.archivesBoxName))
			return false;
		if (archivesBoxThick != other.archivesBoxThick)
			return false;
		if (archivesBoxType == null) {
			if (other.archivesBoxType != null)
				return false;
		} else if (!archivesBoxType.equals(other.archivesBoxType))
			return false;
		if (bentFrameCode == null) {
			if (other.bentFrameCode != null)
				return false;
		} else if (!bentFrameCode.equals(other.bentFrameCode))
			return false;
		if (boxNum == null) {
			if (other.boxNum != null)
				return false;
		} else if (!boxNum.equals(other.boxNum))
			return false;
		if (cabinetId == null) {
			if (other.cabinetId != null)
				return false;
		} else if (!cabinetId.equals(other.cabinetId))
			return false;
		if (cabinetNode != other.cabinetNode)
			return false;
		if (fitBoxTime == null) {
			if (other.fitBoxTime != null)
				return false;
		} else if (!fitBoxTime.equals(other.fitBoxTime))
			return false;
		if (fitBoxUser == null) {
			if (other.fitBoxUser != null)
				return false;
		} else if (!fitBoxUser.equals(other.fitBoxUser))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (polymerize == null) {
			if (other.polymerize != null)
				return false;
		} else if (!polymerize.equals(other.polymerize))
			return false;
		if (storageAreaId == null) {
			if (other.storageAreaId != null)
				return false;
		} else if (!storageAreaId.equals(other.storageAreaId))
			return false;
		if (storageRoomId == null) {
			if (other.storageRoomId != null)
				return false;
		} else if (!storageRoomId.equals(other.storageRoomId))
			return false;
		if (storageYear == null) {
			if (other.storageYear != null)
				return false;
		} else if (!storageYear.equals(other.storageYear))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ArchivesBoxVO [id=" + id + ", boxNum=" + boxNum + ", storageAreaId=" + storageAreaId
				+ ", storageRoomId=" + storageRoomId + ", cabinetId=" + cabinetId + ", cabinetNode=" + cabinetNode
				+ ", archivesBoxThick=" + archivesBoxThick + ", bentFrameCode=" + bentFrameCode + ", archivesBoxName="
				+ archivesBoxName + ", archivesBoxType=" + archivesBoxType + ", fitBoxTime=" + fitBoxTime
				+ ", fitBoxUser=" + fitBoxUser + ", storageYear=" + storageYear + ", polymerize=" + polymerize + "]";
	}
}