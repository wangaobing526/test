package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.springboot.model.BaseEntity;
/**
 * 常用排架信息表
 * @author starsand
 *
 */
@Table(name = "T_DM_BENTFRAME_OFTENUSED")
public class BentFrameOftenUsed extends BaseEntity implements Serializable {
	
    @Id
    @Column(name = "OFTENUSED_ID")
    @GeneratedValue(generator="UUID")
    private String id;
    //排架编码
    @Column(name = "BENTFRAMECODE")
    private String bentFrameCode;
    // 库区代码
 	@Column(name = "STORAGEAREA_CODE")
 	private String storageAreaCode;
 	// 库房代码
 	@Column(name = "STORAGEROOM_CODE")
 	private String storageRoomCode;
 	// 柜架代码
 	@Column(name = "CABINET_CODE")
 	private String cabinetCode;
    //所属柜节
    @Column(name = "CABINETNODE")
    private int cabinetNode;
    //档案盒厚度
    @Column(name = "ARCHIVESBOXWIDE")
    private int archivesBoxWide;
    //所属用户
    @Column(name = "BELONGUSER")
    private String belongUser;
    @Column(name = "CREATETIME")
    private Date createTime;
	public Date getCreateTime() {
		return createTime;
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
	public int getCabinetNode() {
		return cabinetNode;
	}
	public void setCabinetNode(int cabinetNode) {
		this.cabinetNode = cabinetNode;
	}
	public String getBelongUser() {
		return belongUser;
	}
	public void setBelongUser(String belongUser) {
		this.belongUser = belongUser;
	}
	public int getArchivesBoxWide() {
		return archivesBoxWide;
	}
	public void setArchivesBoxWide(int archivesBoxWide) {
		this.archivesBoxWide = archivesBoxWide;
	}
	@Override
	public String toString() {
		return "BentFrameOftenUsed [id=" + id + ", bentFrameCode=" + bentFrameCode + ", storageAreaCode="
				+ storageAreaCode + ", storageRoomCode=" + storageRoomCode + ", cabinetCode=" + cabinetCode
				+ ", cabinetNode=" + cabinetNode + ", archivesBoxWide=" + archivesBoxWide + ", belongUser=" + belongUser
				+ ", createTime=" + createTime + "]";
	}

    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + archivesBoxWide;
		result = prime * result + ((belongUser == null) ? 0 : belongUser.hashCode());
		result = prime * result + ((bentFrameCode == null) ? 0 : bentFrameCode.hashCode());
		result = prime * result + ((cabinetCode == null) ? 0 : cabinetCode.hashCode());
		result = prime * result + cabinetNode;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
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
		BentFrameOftenUsed other = (BentFrameOftenUsed) obj;
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
}