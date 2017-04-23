package com.dhcc.ecm.business.capacity;


import java.util.List;

import com.dhcc.ecm.business.base.service.IService;
import com.dhcc.ecm.business.mybatis.capacity.model.StorageRoom;
import com.github.pagehelper.PageInfo;

public interface StorageRoomService extends IService<StorageRoom>{
	public PageInfo<StorageRoom> findStorageRoomPage(StorageRoom entity);
	public boolean checkStorageRoomNameisExist(StorageRoom entity);
	public boolean checkStorageRoomHasCabinet(StorageRoom entity);
	public List<StorageRoom> findStorageRoomList(StorageRoom entity);
}
