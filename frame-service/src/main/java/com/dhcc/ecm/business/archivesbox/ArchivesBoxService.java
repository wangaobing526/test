package com.dhcc.ecm.business.archivesbox;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.dhcc.ecm.business.base.service.IService;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBox;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBoxInfoVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.DocumentInfoVO;
import com.github.pagehelper.PageInfo;

public interface ArchivesBoxService extends IService<ArchivesBox> {
	PageInfo<ArchivesBox> findArchivesBoxPage(ArchivesBox entity);

	boolean deleteArchivesBox(ArchivesBox entity);
	int deleteArchives(ArchivesBox entity);

	HSSFWorkbook exportExcelArchivesBox(ArchivesBox entity);

	/**
	 * 保存档案盒信息 
	 * 1.判断是保存或者修改档案盒信息
	 * 2.修改则直接调用修改方法
	 * 3.保存判断VO中使用的是初始化设置还是常用排架，如果两个都不为空则抛出异常 
	 * 4.如是初始化操作，则保存初始化信息内容
	 * 5.如是常用排架，调用常用排架服务，更新常用排架编码 
	 * 6.保存档案盒信息
	 * 
	 * @param vo
	 *            档案盒信息、初始化信息、常用排架信息
	 * @throws EcmException
	 */
	public void saveArchivesBox(ArchivesBoxInfoVO vo) throws EcmException;
	
	/**
	 * 根据文档ID查询文档相关信息，调用标准接口查询方法
	 * @param ids 文档ID集合
	 * @return
	 * @throws EcmException
	 */
	public List<DocumentInfoVO> getRemoteDocumentInfoByIds(String[] ids)throws EcmException;

	/**
	 * 获得档案装盒对象
	 * @param entity
	 * @return
     */
	public ArchivesBox getArchivesBox(ArchivesBox entity);
}
