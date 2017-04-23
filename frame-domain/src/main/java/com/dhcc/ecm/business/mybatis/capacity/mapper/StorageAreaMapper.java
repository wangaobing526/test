package com.dhcc.ecm.business.mybatis.capacity.mapper;

import java.util.List;

import com.dhcc.ecm.business.mybatis.capacity.model.StorageArea;
import tk.mybatis.springboot.util.MyMapper;

public interface StorageAreaMapper extends MyMapper<StorageArea,StorageArea> {
	String checkStorageAreaNameisExist(StorageArea entity);
	String checkStorageAreaHasRoom(StorageArea entity);
	List<StorageArea> findStorageAreaPage(StorageArea entity);
}
