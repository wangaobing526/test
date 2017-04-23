package com.dhcc.ecm.business.mybatis.capacity.mapper;

import java.util.List;

import com.dhcc.ecm.business.mybatis.capacity.model.CabinetInfo;

import tk.mybatis.springboot.util.MyMapper;

public interface CabinetInfoMapper extends MyMapper<CabinetInfo,CabinetInfo> {
	List<CabinetInfo> findCabinetInfoPage(CabinetInfo entity);
	String checkCabinetNameisExist(CabinetInfo entity);
	String checkCabinetHasInit(CabinetInfo entity);
}
