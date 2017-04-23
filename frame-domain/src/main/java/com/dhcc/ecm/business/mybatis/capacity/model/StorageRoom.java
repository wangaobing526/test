package com.dhcc.ecm.business.mybatis.capacity.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.springboot.model.BaseEntity;
/**
 * 库房信息表
 * @author starsand
 *
 */
@Table(name = "T_DM_STORAGE_ROOM")
public class StorageRoom extends BaseEntity implements Serializable {
	
    @Id
    @Column(name = "PK_STORAGEROOM_ID")
    @GeneratedValue(generator="UUID")
    private String id;
    //库房代码
    @Column(name = "STORAGEROOM_CODE")
    private String storageRoomCode;
    //库房名称
    @Column(name = "STORAGEROOM_NAME")
    private String storageRoomName;
    //所属库区
    @Column(name = "STORAGEAREA_ID")
    private String storageAreaId;
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
	public String getStorageRoomCode() {
		return storageRoomCode;
	}
	public void setStorageRoomCode(String storageRoomCode) {
		this.storageRoomCode = storageRoomCode;
	}
	public String getStorageRoomName() {
		return storageRoomName;
	}
	public void setStorageRoomName(String storageRoomName) {
		this.storageRoomName = storageRoomName;
	}
	public String getStorageAreaId() {
		return storageAreaId;
	}
	public void setStorageAreaId(String storageAreaId) {
		this.storageAreaId = storageAreaId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}