package com.dhcc.ecm.business.archivesbox.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.dhcc.ecm.business.archivesbox.ArchivesBoxService;
import com.dhcc.ecm.business.archivesbox.BentFrameOftenUsedService;
import com.dhcc.ecm.business.archivesbox.InitCabinetNodeService;
import com.dhcc.ecm.business.base.service.impl.BaseService;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.archivesbox.mapper.InitCabinetNodeMapper;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBox;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsed;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsedVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.InitCabinetNode;
import com.dhcc.ecm.business.mybatis.archivesbox.model.InitCabinetNodeVO;
import com.dhcc.ecm.business.mybatis.capacity.mapper.CabinetInfoMapper;
import com.dhcc.ecm.business.mybatis.capacity.model.CabinetInfo;
import com.dhcc.ecm.business.util.BusBeanUtils;
import com.dhcc.ecm.business.util.BusinessProperties;
import com.dhcc.ecm.business.util.StringBusinessUtil;

import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.springboot.conf.TransactionService;

/**
 * @ClassName InitCabinetNodeServiceImpl
 * @Description 初始化柜节服务接口实现类
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-13
 */
@TransactionService
public class InitCabinetNodeServiceImpl extends BaseService<InitCabinetNode> implements InitCabinetNodeService {
	@Autowired
	private BusinessProperties properties;
    /**
     * 初始化柜节mapper
     */
	@Autowired
	private InitCabinetNodeMapper initMapper;
	
	/**
	 * 柜架mapper
	 */
	@Autowired
	private CabinetInfoMapper cabinetInfoMapper;
	
	@Autowired
	private ArchivesBoxService archivesBoxService;
	
	/**
	 * 常用排架服务
	 */
	@Autowired
	private BentFrameOftenUsedService bentFrameOftenUsedService;


	@Override
	public String initCabinetNodeHandle(InitCabinetNodeVO vo) throws EcmException {
		InitCabinetNode node = null;
		// 根据组装的柜节查询初始化表
		List<InitCabinetNode> list = this.getinitCabinetNodeList(vo);
		// 初始化的是已经存在的柜节
		if (list != null && list.size() > 0) {
			node = (InitCabinetNode) list.get(0);
			//构造排架编码
//			String bentFrameCode = buildBentFrameCode(vo);
			// 判断盒号是否冲突，去档案盒中查询,按照排架编码
			ArchivesBox box = new ArchivesBox();
			box.setBentFrameCode(vo.getBentFrameCode());
			List<ArchivesBox> archivesBoxList = archivesBoxService.findArchivesBoxPage(box).getList();
			
			if (archivesBoxList!=null && archivesBoxList.size()>0) {
				throw new EcmException(ErrorCode.DATA_INPUT_ERROR, "排架编码已经存在，此柜节当前盒号为" + node.getArchivesBoxNum());
			}
			// 判断是否超出总厚度:档案盒宽度+总厚度是否大于柜节宽度
			int nowWide = vo.getArchivesBoxWide() + node.getAllBoxWide();
			if (nowWide > node.getCabinetNodeWide()) {
				throw new EcmException(ErrorCode.DATA_INPUT_ERROR, "档案盒的总厚度已经超过柜节宽度，请重新初始化柜节，或调整档案盒厚度");
			}
			//判断盒号小于当前初始化中的内容，不更新排架编码和盒号**********************************
			// 已有初始化中的盒号
			String archivesBoxNum = node.getBentFrameCode().substring(
					node.getBentFrameCode().lastIndexOf("-")+1);
			Integer[] existArchivesBoxNumArr = StringBusinessUtil.getarchivesBoxNumAndLength(archivesBoxNum);
			// 获得新增的盒号
			String initArchivesBoxNum = vo.getBentFrameCode().substring(vo.getBentFrameCode().lastIndexOf("-")+1);
			Integer[] initArchivesBoxNumArr = StringBusinessUtil.getarchivesBoxNumAndLength(initArchivesBoxNum);
			// 当初始化的盒号大于常用排架的盒号，更新常用排架的爬架编码
			if (initArchivesBoxNumArr[0] > existArchivesBoxNumArr[0]) {
				node.setArchivesBoxNum(vo.getArchivesBoxNum());
				node.setBentFrameCode(vo.getBentFrameCode());
			}
			//***********************************************************************
			// 设置总厚度
			node.setAllBoxWide(nowWide);
			// 更新初始化柜节
			this.updateNotNull(node);

		} else {// 初始化的是不存在的柜节
			// 设置MODEL值
			node = new InitCabinetNode();
			BusBeanUtils.copyProperties(vo,node);
			//获得柜架中的柜节厚度
			CabinetInfo cabinetInfo = new CabinetInfo();
			cabinetInfo.setCabinetCode(node.getCabinetCode());
			List<CabinetInfo> cabinetInfoList = cabinetInfoMapper.findCabinetInfoPage(cabinetInfo);
			if(cabinetInfoList!=null && cabinetInfoList.size() > 0){
				//设置初始化中的柜节宽度
				node.setCabinetNodeWide(cabinetInfoList.get(0).getCabinetNodeWide());
				// 设置总宽度为初始化的宽度
				node.setAllBoxWide(node.getArchivesBoxWide());
				// 保存初始化柜节
				this.save(node);
			}else{
				throw new EcmException(ErrorCode.SYSTEM_ERROR,"系统异常，柜架数据异常");
			}
		}
		//新初始化柜节时，如果在常用排架中已经有了，虽然没勾常用排架，也需要同步常用排架的排架编码
		//根据初始化的库房库区柜节信息等查询常用排架
		BentFrameOftenUsedVO bentFrameOftenUsedVO = setBentFrameOftenUsedVO(vo);
		List<BentFrameOftenUsed> bentList = bentFrameOftenUsedService.getBentFrameOftenUsedList(bentFrameOftenUsedVO);
		
		// 初始化设置为0为设置常用排架；初始化的排架编码，在常用排架中有，需要更新常用排架
		if ((!StringUtil.isEmpty(vo.getBentFrameOftenUsedFlag()) 
				&& vo.getBentFrameOftenUsedFlag().equals(properties.getBentFrameFlag()))
				||(bentList!=null && bentList.size()>0)) {
			//保存常用排架信息
			bentFrameOftenUsedService.initBentFrameOftenUsed(bentFrameOftenUsedVO);
		}
		return node.getBentFrameCode();
	}
	
	/**
	 * 初始化或更新常用排架
	 * @param vo
	 * @throws EcmException
	 */
	@Deprecated
	private void initBentFrameOftenUsed(InitCabinetNodeVO vo)throws EcmException{
		BentFrameOftenUsedVO bentFrameOftenUsedVO = setBentFrameOftenUsedVO(vo);
		//保存常用排架信息
		bentFrameOftenUsedService.initBentFrameOftenUsed(bentFrameOftenUsedVO);
	}
	
    @Override
	public List<InitCabinetNode> getinitCabinetNodeList(InitCabinetNodeVO vo) throws EcmException {
		List<InitCabinetNode> nodeList = initMapper.getinitCabinetNodeList(vo);
		return nodeList;
	}

	
	/**
	 * 构造常用排架VO对象
	 * 从VO向model赋值
	 * 
	 * @param vo
	 * @return
	 */
	private BentFrameOftenUsedVO setBentFrameOftenUsedVO(InitCabinetNodeVO vo) throws EcmException {
		BentFrameOftenUsedVO bentFrameOftenUsedVO = new BentFrameOftenUsedVO();
		try {
			BeanUtils.copyProperties(bentFrameOftenUsedVO, vo);
			// 构造排架编码
//			bentFrameOftenUsedVO.setBentFrameCode(buildBentFrameCode(vo));
			//设置当前登录用户（需重构）
			bentFrameOftenUsedVO.setBelongUser(properties.getUserId());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EcmException(ErrorCode.SETVALUE_ERROR, "赋值操作失败");
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EcmException(ErrorCode.SETVALUE_ERROR, "赋值操作失败");
		}
		return bentFrameOftenUsedVO;
	}

	/**
	 * 构造排架编码：库区代码-库房代码-柜架代码-柜节编码-盒号
	 * 
	 * @param vo
	 * @return
	 */
//	private String buildBentFrameCode(InitCabinetNodeVO vo) {
//		String bentFrameCode = vo.getStorageAreaCode() + "-" + vo.getStorageRoomCode() + "-" + vo.getCabinetCode() + "-"
//				+ vo.getCabinetNode() + "-" + vo.getArchivesBoxNum();
//		return bentFrameCode;
//	}
}
