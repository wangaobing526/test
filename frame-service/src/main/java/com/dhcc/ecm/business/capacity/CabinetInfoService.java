package com.dhcc.ecm.business.capacity;


import java.util.List;

import com.dhcc.ecm.business.base.service.IService;
import com.dhcc.ecm.business.mybatis.capacity.model.CabinetInfo;
import com.github.pagehelper.PageInfo;

public interface CabinetInfoService extends IService<CabinetInfo>{
	public PageInfo<CabinetInfo> findCabinetInfoPage(CabinetInfo entity);
	public List<CabinetInfo> findCabinetInfoList(CabinetInfo entity);
	public boolean checkCabinetNameisExist(CabinetInfo entity);
	public boolean checkCabinetHasInit(String id);
}
