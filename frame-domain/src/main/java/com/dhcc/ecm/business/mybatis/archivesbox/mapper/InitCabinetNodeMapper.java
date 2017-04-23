package com.dhcc.ecm.business.mybatis.archivesbox.mapper;

import java.util.List;

import com.dhcc.ecm.business.mybatis.archivesbox.model.InitCabinetNode;
import com.dhcc.ecm.business.mybatis.archivesbox.model.InitCabinetNodeVO;

import tk.mybatis.springboot.util.MyMapper;

public interface InitCabinetNodeMapper extends MyMapper<InitCabinetNode,InitCabinetNode> {
	public List<InitCabinetNode> getinitCabinetNodeList(InitCabinetNodeVO vo);
}