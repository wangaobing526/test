package com.dhcc.ecm.business.capacity;


import java.util.List;

import com.dhcc.ecm.business.base.service.IService;
import com.dhcc.ecm.business.mybatis.capacity.model.StorageArea;
import com.github.pagehelper.PageInfo;

public interface StorageAreaService extends IService<StorageArea>{

	public PageInfo<StorageArea> findStorageAreaPage(StorageArea entity);
	public boolean checkStorageAreaNameisExist(StorageArea entity);
	public boolean checkStorageAreaHasRoom(StorageArea entity);
	public List<StorageArea> findStorageAreaList(StorageArea entity);
}
