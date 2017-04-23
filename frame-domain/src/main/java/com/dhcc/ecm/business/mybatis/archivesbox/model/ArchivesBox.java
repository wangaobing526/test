package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import tk.mybatis.springboot.model.BaseEntity;

/**
 * 档案盒信息表
 * 
 * @author starsand
 *
 */
@Table(name = "T_DM_ARCHIVES_BOX")
public class ArchivesBox extends BaseEntity implements Serializable {

	@Id
	@Column(name = "PK_ARCHIVESBOX_ID")
	@GeneratedValue(generator = "UUID")
	private String id;
	// 盒号
	@Column(name = "BOXNUM")
	private String boxNum;
	// 所属库区
	@Column(name = "STORAGEAREA_ID")
	private String storageAreaId;
	// 所属库房
	@Column(name = "STORAGEROOM_ID")
	private String storageRoomId;
	// 所属柜架
	@Column(name = "CABINET_ID")
	private String cabinetId;
	// 所属柜节
	@Column(name = "CABINETNODE")
	private int cabinetNode;
	// 档案盒厚度
	@Column(name = "ARCHIVESBOXTHICK")
	private int archivesBoxThick;
	// 排架编码
	@Column(name = "BENTFRAMECODE")
	private String bentFrameCode;
	// 档案盒提名
	@Column(name = "ARCHIVESBOXNAME")
	private String archivesBoxName;
	// 档案盒分类
	@Column(name = "ARCHIVESBOXTYPE")
	private String archivesBoxType;
	// 装盒时间
	@Column(name = "FITBOXTIME")
	private Date fitBoxTime;
	// 创建时间
	@Column(name = "CREATETIME")
	private Date createTime;
	// 装盒人
	@Column(name = "FITBOXUSER")
	private String fitBoxUser;
	// 保管年限
	@Column(name = "STORAGEYEAR")
	private String storageYear;
	// 聚合层次
	@Column(name = "POLYMERIZE")
	private String polymerize;
	// 档案id
	@Transient
	private String[] archivesIds;
	@Transient
	private Date beginTime;
	@Transient
	private Date endTime;
	@Transient
	private String exportDir;
	
	
	public String getExportDir() {
		return exportDir;
	}

	public void setExportDir(String exportDir) {
		this.exportDir = exportDir;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

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

	public int getCabinetNode() {
		return cabinetNode;
	}

	public void setCabinetNode(int cabinetNode) {
		this.cabinetNode = cabinetNode;
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

	public String[] getArchivesIds() {
		return archivesIds;
	}

	public void setArchivesIds(String[] archivesIds) {
		this.archivesIds = archivesIds;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}