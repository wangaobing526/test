package com.dhcc.ecm.business.archivesbox;

import java.util.List;

import com.dhcc.ecm.business.base.service.IService;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBox;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsed;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsedVO;
import com.github.pagehelper.PageInfo;
/**
 * @ClassName BentFrameOftenUsedService
 * @Description 排架管理服务接口
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-13
 */
public interface BentFrameOftenUsedService extends IService<BentFrameOftenUsed> {
	/**
	 * 使用常用排架后更新排架编码
	 * @param vo
	 * @throws EcmException
	 */
//	public void updateBentFrameCode(BentFrameOftenUsedVO vo)throws EcmException;
	
	/**
	 * 保存常用排架
	 * @param vo
	 * @throws EcmException
	 */
	public void saveBentFrameOftenUsed(BentFrameOftenUsedVO vo)throws EcmException;
	
	/**
	 * 更新常用排架
	 * @param vo
	 * @throws EcmException
	 */
	public void updateBentFrameOftenUsed(BentFrameOftenUsed bentFrameOftenUsed,BentFrameOftenUsedVO vo)throws EcmException;
	
	/**
	 * 根据常用排架条件，获得常用排架列表内容
	 * @param vo
	 * @return
	 * @throws EcmException
	 */
	public List<BentFrameOftenUsed> getBentFrameOftenUsedList(BentFrameOftenUsedVO vo) throws EcmException;
	
	/**
	 * 初始化常用排架信息
	 * 1.此柜节如果已经属于常用排架，则静默更新常用排架编码,如果当前盒号大于常用排架中的的盒号，那么更新常用排架中的排架编码，否则不用更新
	 * 2.此柜节不属于常用排架，直接增加常用排架
	 * @param vo
	 * @throws EcmException
	 */
	public void initBentFrameOftenUsed(BentFrameOftenUsedVO vo) throws EcmException;
	
	
	/**
	 * 查询常用排架分页信息列表
	 * @param vo
	 * @return
	 * @throws EcmException
	 */
	public PageInfo<BentFrameOftenUsed> queryBentFrameOftenUsedPage(BentFrameOftenUsedVO vo)throws EcmException;
	
	
	
	
}
