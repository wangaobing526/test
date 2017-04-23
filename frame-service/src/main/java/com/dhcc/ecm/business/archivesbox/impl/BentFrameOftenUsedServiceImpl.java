package com.dhcc.ecm.business.archivesbox.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dhcc.ecm.business.archivesbox.BentFrameOftenUsedService;
import com.dhcc.ecm.business.base.service.impl.BaseService;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.mybatis.archivesbox.mapper.BentframeOftenusedMapper;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsed;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsedVO;
import com.dhcc.ecm.business.util.BusBeanUtils;
import com.dhcc.ecm.business.util.StringBusinessUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.springboot.conf.TransactionService;

@TransactionService
public class BentFrameOftenUsedServiceImpl extends BaseService<BentFrameOftenUsed>
		implements BentFrameOftenUsedService {
	@Autowired
	private BentframeOftenusedMapper mapper;

	/**
	 * 保存常用排架信息
	 * 1.此柜节如果已经属于常用排架，则静默更新常用排架编码,如果当前盒号大于常用排架中的的盒号，那么更新常用排架中的排架编码，否则不用更新
	 * 2.此柜节不属于常用排架，直接增加常用排架
	 * 
	 * @param vo
	 * @throws EcmException
	 */
	@Override
	public void initBentFrameOftenUsed(BentFrameOftenUsedVO vo) throws EcmException {
		List<BentFrameOftenUsed> list = this.getBentFrameOftenUsedList(vo);
		if (list != null && list.size() > 0) {
			BentFrameOftenUsed bentFrameOftenUsed = list.get(0);
			// 获得常用排架中的当前盒号
			String archivesBoxNum = bentFrameOftenUsed.getBentFrameCode().substring(
					bentFrameOftenUsed.getBentFrameCode().lastIndexOf("-")+1);
			Integer[] archivesBoxNumArr = StringBusinessUtil.getarchivesBoxNumAndLength(archivesBoxNum);
			// 获得新增的盒号
			String initArchivesBoxNum = vo.getBentFrameCode().substring(vo.getBentFrameCode().lastIndexOf("-")+1);
			Integer[] initArchivesBoxNumArr = StringBusinessUtil.getarchivesBoxNumAndLength(initArchivesBoxNum);
			// 当初始化的盒号大于常用排架的盒号，更新常用排架的爬架编码
			if (initArchivesBoxNumArr[0] > archivesBoxNumArr[0]) {
				this.updateBentFrameOftenUsed(bentFrameOftenUsed,vo);
			}
		} else {
			this.saveBentFrameOftenUsed(vo);
		}

	}
	
	@Override
	public void saveBentFrameOftenUsed(BentFrameOftenUsedVO vo)throws EcmException{
		BentFrameOftenUsed bentFrameOftenUsed = new BentFrameOftenUsed();
		BusBeanUtils.copyProperties(vo,bentFrameOftenUsed);
		bentFrameOftenUsed.setCreateTime(new Date());
		this.save(bentFrameOftenUsed);
	}
	
	@Override
	public void updateBentFrameOftenUsed(BentFrameOftenUsed bentFrameOftenUsed,BentFrameOftenUsedVO vo)throws EcmException{
		BusBeanUtils.copyProperties(vo,bentFrameOftenUsed);
		bentFrameOftenUsed.setCreateTime(new Date());
		this.updateNotNull(bentFrameOftenUsed);
	}
	
	@Override
	public PageInfo<BentFrameOftenUsed> queryBentFrameOftenUsedPage(BentFrameOftenUsedVO vo)throws EcmException{
		Page<BentFrameOftenUsed> page = PageHelper.startPage(vo.getPage(), vo.getRows(), "createTime DESC");
		List<BentFrameOftenUsed> list = mapper.getBentFrameOftenUsedList(vo);
		return page.toPageInfo();
	}

	public List<BentFrameOftenUsed> getBentFrameOftenUsedList(BentFrameOftenUsedVO vo) throws EcmException {
		List<BentFrameOftenUsed> bentFrameOftenUsedList = mapper.getBentFrameOftenUsedList(vo);
		return bentFrameOftenUsedList;
	}
	

	
//	@Override
//	public void updateBentFrameCode(BentFrameOftenUsedVO vo)throws EcmException{
//		String newBentFrameCode = addBentFrameCode(vo.getBentFrameCode());
//		BentFrameOftenUsed bentFrameOftenUsed = this.getById(vo.getId());
//		bentFrameOftenUsed.setBentFrameCode(newBentFrameCode);
//		this.updateNotNull(bentFrameOftenUsed);
//	}
	/**
	 * 将排架编码加1
	 * @param bentFrameCode
	 * @return
	 */
//	private String addBentFrameCode(String bentFrameCode){
//		String archivesBoxNum = bentFrameCode.substring(
//				bentFrameCode.lastIndexOf("-"),
//				bentFrameCode.length());
//		 Integer[] arr = StringBusinessUtil.getarchivesBoxNumAndLength(archivesBoxNum);
//		 String nowArchivesBoxNum = String.valueOf(arr[0]+1);
//		 String  returnValue =StringBusinessUtil.setArchivesBoxNumZero(nowArchivesBoxNum, arr[1]);
//		 return returnValue;
//	}
}
