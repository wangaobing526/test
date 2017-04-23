package com.dhcc.ecm.business.archivesbox;

import java.util.List;

import com.dhcc.ecm.business.base.service.IService;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.mybatis.archivesbox.model.InitCabinetNode;
import com.dhcc.ecm.business.mybatis.archivesbox.model.InitCabinetNodeVO;
/**
 * @ClassName InitCabinetNodeService
 * @Description 初始化柜节服务接口
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-13
 */
public interface InitCabinetNodeService extends IService<InitCabinetNode> {
	/**
	 * 初始化柜节操作： 
	 * 1.根据根据页面传入的库区库房柜架柜节信息组成排架编码模糊查询是否已经存在柜架初始化信息
	 * 1.如果是新的柜节初始化，组装MODEL，查询柜节表获得柜节宽度设置到柜节宽度中;将宽度设置到总宽度中，根据页面传入的库区库房柜架柜节盒号等信息生成排架编码
	 * 2.将信息存储到初始化柜节表中 
	 * 3.如果初始化的是已经存在柜节，需要根据传入的内容判断厚度是否超出
	 * 4.如果初始化的是已经存在柜节，查询档案盒记录表，判断输入盒号是否冲突，弹出提示，并告知当前柜节最大盒号是多少
	 * 5.如果初始化的是已经存在柜节，上述判断都没问题，更新已有柜节，更新柜节厚度，更新盒号、排架编码，柜节数据入库
	 * 5.判断是否增加常用排架，此柜节如果已经属于常用排架，则静默更新常用排架编码,如果当前盒号大于常用排架中的的盒号，那么更新常用排架中的排架编码，否则不用更新
	 * 6.调用常用排架服务，增加查用排架 
	 * 7.返回排架编码号
	 * 
	 * @param vo
	 * @throws EcmException
	 */
	public String initCabinetNodeHandle(InitCabinetNodeVO vo)throws EcmException;
	
	/**
	 * 根据查询条件获得初始化柜节列表
	 * @param vo
	 * @return
	 * @throws EcmException
	 */
	public List<InitCabinetNode> getinitCabinetNodeList(InitCabinetNodeVO vo)throws EcmException;
}
