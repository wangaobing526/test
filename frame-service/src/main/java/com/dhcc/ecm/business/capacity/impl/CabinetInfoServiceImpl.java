package com.dhcc.ecm.business.capacity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dhcc.ecm.business.base.service.impl.BaseService;
import com.dhcc.ecm.business.capacity.CabinetInfoService;
import com.dhcc.ecm.business.mybatis.capacity.mapper.CabinetInfoMapper;
import com.dhcc.ecm.business.mybatis.capacity.model.CabinetInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import tk.mybatis.springboot.conf.TransactionService;
@TransactionService
public class CabinetInfoServiceImpl extends BaseService<CabinetInfo>  implements CabinetInfoService{
	@Autowired
	private CabinetInfoMapper cabinetInfoMapper;
	
	@Override
	public PageInfo<CabinetInfo> findCabinetInfoPage(CabinetInfo entity) {
			 Page<CabinetInfo> page = PageHelper.startPage(entity.getPage(), entity.getRows(),  "createTime DESC");
			 List<CabinetInfo> list = cabinetInfoMapper.findCabinetInfoPage(entity);
			 return  page.toPageInfo();
	}

	@Override
	public List<CabinetInfo> findCabinetInfoList(CabinetInfo entity) {
		return cabinetInfoMapper.findCabinetInfoPage(entity);
	}

	@Override
	public boolean checkCabinetNameisExist(CabinetInfo entity) {
		int count=0;
		if(StringUtil.isNotEmpty(cabinetInfoMapper.checkCabinetNameisExist(entity))){
			count=Integer.parseInt(cabinetInfoMapper.checkCabinetNameisExist(entity));
		}
		if(count>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkCabinetHasInit(String id) {
		CabinetInfo entity=new CabinetInfo();
		entity.setId(id);
		int count=0;
		String result=cabinetInfoMapper.checkCabinetHasInit(entity);
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
