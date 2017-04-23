package com.dhcc.ecm.business.mybatis.archivesbox.mapper;

import java.util.List;

import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsed;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsedVO;

import tk.mybatis.mapper.common.Mapper;

public interface BentframeOftenusedMapper extends Mapper<BentFrameOftenUsed> {
	public List<BentFrameOftenUsed> getBentFrameOftenUsedList(BentFrameOftenUsedVO vo);
//	public List<BentFrameOftenUsed> queryBentFrameOftenUsedPage(BentFrameOftenUsedVO vo);
}