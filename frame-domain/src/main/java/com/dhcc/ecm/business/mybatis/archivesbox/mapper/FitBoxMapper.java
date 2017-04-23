package com.dhcc.ecm.business.mybatis.archivesbox.mapper;

import java.util.List;

import com.dhcc.ecm.business.mybatis.archivesbox.model.FitBox;

import tk.mybatis.mapper.common.Mapper;

public interface FitBoxMapper extends Mapper<FitBox> {
	public List<String> getDocumentByArchivesBox(String id);
}
