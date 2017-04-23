package com.dhcc.ecm.business.mybatis.capacity.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.springboot.model.BaseEntity;
/**
 * 柜架信息表
 * @author starsand
 *
 */
@Table(name = "T_DM_CABINET_INFO")
public class CabinetInfo extends BaseEntity implements Serializable {
	
    @Id
    @Column(name = "PK_CABINET_ID")
    @GeneratedValue(generator="UUID")
    private String id;
    //柜架代码
    @Column(name = "CABINET_CODE")
    private String cabinetCode;
    //柜架名称
    @Column(name = "CABINET_NAME")
    private String cabinetName;
    //所属库房
    @Column(name = "STORAGEROOM_ID")
    private String storageRoomId;
    //柜架行数
    @Column(name = "CABINETROWS")
    private int cabinetRows;
    //柜架列数
    @Column(name = "CABINETCOLUMNS")
    private int cabinetColumns;
    //柜节长度
    @Column(name = "CABINETNODELONG")
    private int cabinetNodeLong;
    //柜节宽度
    @Column(name = "CABINETNODEWIDE")
    private int cabinetNodeWide;
    //柜节数
    @Column(name = "CABINETNODENUM")
    private int cabinetNodeNum;
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
	public String getCabinetCode() {
		return cabinetCode;
	}
	public void setCabinetCode(String cabinetCode) {
		this.cabinetCode = cabinetCode;
	}
	public String getCabinetName() {
		return cabinetName;
	}
	public void setCabinetName(String cabinetName) {
		this.cabinetName = cabinetName;
	}
	public String getStorageRoomId() {
		return storageRoomId;
	}
	public void setStorageRoomId(String storageRoomId) {
		this.storageRoomId = storageRoomId;
	}
	public int getCabinetRows() {
		return cabinetRows;
	}
	public void setCabinetRows(int cabinetRows) {
		this.cabinetRows = cabinetRows;
	}
	public int getCabinetColumns() {
		return cabinetColumns;
	}
	public void setCabinetColumns(int cabinetColumns) {
		this.cabinetColumns = cabinetColumns;
	}
	public int getCabinetNodeLong() {
		return cabinetNodeLong;
	}
	public void setCabinetNodeLong(int cabinetNodeLong) {
		this.cabinetNodeLong = cabinetNodeLong;
	}
	public int getCabinetNodeWide() {
		return cabinetNodeWide;
	}
	public void setCabinetNodeWide(int cabinetNodeWide) {
		this.cabinetNodeWide = cabinetNodeWide;
	}
	public int getCabinetNodeNum() {
		return cabinetNodeNum;
	}
	public void setCabinetNodeNum(int cabinetNodeNum) {
		this.cabinetNodeNum = cabinetNodeNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}