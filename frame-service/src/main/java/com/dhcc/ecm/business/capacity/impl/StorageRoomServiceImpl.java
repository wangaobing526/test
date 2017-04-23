package com.dhcc.ecm.business.capacity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.util.StringUtils;
import com.dhcc.ecm.business.base.service.impl.BaseService;
import com.dhcc.ecm.business.capacity.StorageRoomService;
import com.dhcc.ecm.business.mybatis.capacity.mapper.StorageRoomMapper;
import com.dhcc.ecm.business.mybatis.capacity.model.StorageRoom;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import tk.mybatis.springboot.conf.TransactionService;
@TransactionService
public class StorageRoomServiceImpl extends BaseService<StorageRoom>  implements StorageRoomService{
	@Autowired
	private StorageRoomMapper storageRoomMapper;
	
	@Override
	public PageInfo<StorageRoom> findStorageRoomPage(StorageRoom entity) {
			 Page<StorageRoom> page = PageHelper.startPage(entity.getPage(), entity.getRows(), "createTime DESC");
			 List<StorageRoom> list = storageRoomMapper.findStorageRoomPage(entity);
			 return  page.toPageInfo();
	}

	@Override
	public boolean checkStorageRoomNameisExist(StorageRoom entity) {
		String result= storageRoomMapper.checkStorageRoomNameisExist(entity);
		int count=StringUtil.isNotEmpty(result)?Integer.parseInt(result):0;
		return count>0?true:false;
	}

	@Override
	public boolean checkStorageRoomHasCabinet(StorageRoom entity) {
		String result=  storageRoomMapper.checkStorageRoomHasCabinet(entity);
		int count=StringUtil.isNotEmpty(result)?Integer.parseInt(result):0;
		return count>0?true:false;
	}

	@Override
	public List<StorageRoom> findStorageRoomList(StorageRoom entity) {
		return storageRoomMapper.findStorageRoomPage(entity);
	}
}
