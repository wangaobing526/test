package com.dhcc.ecm.business.archivesbox.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dhcc.ecm.business.archivesbox.FitBoxService;
import com.dhcc.ecm.business.base.service.impl.BaseService;
import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.archivesbox.mapper.FitBoxMapper;
import com.dhcc.ecm.business.mybatis.archivesbox.model.DocumentBean;
import com.dhcc.ecm.business.mybatis.archivesbox.model.FitBox;
import com.dhcc.ecm.business.mybatis.archivesbox.model.FitBoxVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.MetaItems;
import com.dhcc.ecm.business.util.BusBeanUtils;
import com.dhcc.ecm.business.util.BusinessProperties;
import com.dhcc.ecm.business.util.HttpUtil;

import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.springboot.conf.TransactionService;
/**
 * @ClassName FitBoxServiceImpl
 * @Description 档案装盒服务接口实现类
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-13
 */
@TransactionService
public class FitBoxServiceImpl extends BaseService<FitBox> implements FitBoxService {
	
	@Autowired
	private FitBoxMapper fitBoxMapper;
	
	@Autowired
	private BusinessProperties properties;
	
	@Override
	public void savefitBoxInfo(FitBoxVO vo) throws EcmException {
		for(String documentId:vo.getDocumentIds()){
			FitBox fitBox = new FitBox(); 
			BusBeanUtils.copyProperties(vo,fitBox);
			fitBox.setDocumentId(documentId);
			this.save(fitBox);
			remoteUpdatePhysicalLocation(documentId);
		}
	}
	private void remoteUpdatePhysicalLocation(String documentId)throws EcmException{
		//调用DM，更新物理位置
		DocumentBean bean = new DocumentBean();
		List<MetaItems> metaItems = new ArrayList();
		bean.setId(documentId);
		MetaItems item = new MetaItems("PhysicalLocation",documentId);
		metaItems.add(item);
		bean.setMetaItems(metaItems);
		
    	String url = properties.getMasterServiceUrl()+properties.getUpdatePhyUrl();
    	Map<String, String> hearderMap = new HashMap();
    	//需要从单点登录获得
    	hearderMap.put("userid", properties.getUserId());
    	hearderMap.put("systemId", properties.getSystemId());
    	
		HttpUtil httpUtil = new HttpUtil();
		BizResponse res = (BizResponse)httpUtil.sendHttpPost(hearderMap, bean, BizResponse.class, url);
		if(StringUtil.isEmpty(res.getErrorCode())){
			throw new EcmException(ErrorCode.REMOTE_ERROR, "调用标准接口修改物理位置失败");
		}
	}
	@Override
	public String[] getDocumentByArchivesBox(String id){
		List<String> list=new ArrayList<String>();
		list=fitBoxMapper.getDocumentByArchivesBox(id);
		String[] strings = new String[list.size()];
		list.toArray(strings);
		
		return strings;
	}
}
