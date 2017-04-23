package com.dhcc.ecm.business.archivesbox;

import com.dhcc.ecm.business.base.service.IService;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.mybatis.archivesbox.model.FitBox;
import com.dhcc.ecm.business.mybatis.archivesbox.model.FitBoxVO;
/**
 * @ClassName FitBoxService
 * @Description 档案装盒服务接口
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-13
 */
public interface FitBoxService extends IService<FitBox> {
	/**
	 * 1.循环list，进行保存操作，在循环过程中
	 *   判断各值不能为空，空则抛出异常，方法支持事务
	 * @param list
	 */
	public void savefitBoxInfo(FitBoxVO vo) throws EcmException;
	public String[] getDocumentByArchivesBox(String id);
}
