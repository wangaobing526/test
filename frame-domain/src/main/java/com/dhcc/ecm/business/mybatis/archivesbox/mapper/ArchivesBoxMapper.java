package com.dhcc.ecm.business.mybatis.archivesbox.mapper;


import java.util.List;

import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBox;

import tk.mybatis.mapper.common.Mapper;

public interface ArchivesBoxMapper extends Mapper<ArchivesBox> {
	List<ArchivesBox> findArchivesBoxPage(ArchivesBox entity);
	int deleteArchivesBox(ArchivesBox entity);
	int deleteArchives(ArchivesBox entity);
	int updateInitCabinetNodeAllBoxWide(ArchivesBox entity);
	String isLastArchiveBox(ArchivesBox entity);
	int deleteInitByBox(ArchivesBox box);
	int deleteOfenUsedBentByBox(ArchivesBox box);
}
