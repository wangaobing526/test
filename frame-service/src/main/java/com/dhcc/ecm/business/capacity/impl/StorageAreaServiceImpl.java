package com.dhcc.ecm.business.capacity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dhcc.ecm.business.base.service.impl.BaseService;
import com.dhcc.ecm.business.capacity.StorageAreaService;
import com.dhcc.ecm.business.mybatis.capacity.mapper.StorageAreaMapper;
import com.dhcc.ecm.business.mybatis.capacity.model.StorageArea;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.springboot.conf.TransactionService;
@TransactionService
public class StorageAreaServiceImpl extends BaseService<StorageArea>  implements StorageAreaService{
	@Autowired
	private StorageAreaMapper storageAreaMapper;
	
	@Override
	public PageInfo<StorageArea> findStorageAreaPage(StorageArea entity) {
			 Page<StorageArea> page = PageHelper.startPage(entity.getPage(), entity.getRows(),  "createTime DESC");
			 List<StorageArea> list = storageAreaMapper.findStorageAreaPage(entity);
			 return  page.toPageInfo();
	}
	@Override
	public List<StorageArea> findStorageAreaList(StorageArea entity) {
			 List<StorageArea> list = storageAreaMapper.findStorageAreaPage(entity);
			 return  list;
	}
	@Override
	public boolean checkStorageAreaNameisExist(StorageArea entity) {
		String result=storageAreaMapper.checkStorageAreaNameisExist(entity);
		int count=0;
		if(StringUtil.isNotEmpty(result)){
			count=Integer.parseInt(result);
		}
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkStorageAreaHasRoom(StorageArea entity) {
		String result=storageAreaMapper.checkStorageAreaHasRoom(entity);
		int count=0;
		if(StringUtil.isNotEmpty(result)){
			count=Integer.parseInt(result);
		}
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	

	

	
}
