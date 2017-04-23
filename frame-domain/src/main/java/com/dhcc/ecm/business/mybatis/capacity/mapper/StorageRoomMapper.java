package com.dhcc.ecm.business.mybatis.capacity.mapper;

import java.util.List;

import com.dhcc.ecm.business.mybatis.capacity.model.StorageRoom;
import tk.mybatis.springboot.util.MyMapper;

public interface StorageRoomMapper extends MyMapper<StorageRoom,StorageRoom> {
	List<StorageRoom> findStorageRoomPage(StorageRoom entity);
	String checkStorageRoomNameisExist(StorageRoom entity);
	String checkStorageRoomHasCabinet(StorageRoom entity);
}
