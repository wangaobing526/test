package com.dhcc.ecm.business.archivesbox.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;

import com.dhcc.ecm.business.archivesbox.ArchivesBoxService;
import com.dhcc.ecm.business.archivesbox.BentFrameOftenUsedService;
import com.dhcc.ecm.business.archivesbox.InitCabinetNodeService;
import com.dhcc.ecm.business.base.service.impl.BaseService;
import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.archivesbox.mapper.ArchivesBoxMapper;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBox;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBoxInfoVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBoxVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsedVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.DocumentInfoVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.InitCabinetNodeVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.QueryConditionInnerVO;
import com.dhcc.ecm.business.mybatis.archivesbox.model.QueryConditionVO;
import com.dhcc.ecm.business.util.BusinessProperties;
import com.dhcc.ecm.business.util.ConvertUtil;
import com.dhcc.ecm.business.util.ExcelUtil;
import com.dhcc.ecm.business.util.HttpUtil;
import com.dhcc.ecm.business.util.NFExcelStyle;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibm.common.docexchange.DocumentBean;

import tk.mybatis.mapper.util.StringUtil;
import tk.mybatis.springboot.conf.TransactionService;

@TransactionService
public class ArchivesBoxServiceImpl extends BaseService<ArchivesBox> implements ArchivesBoxService {
	@Autowired
	private ArchivesBoxMapper archivesBoxMapper;

	/**
	 * 初始化设置服务
	 */
	@Autowired
	private InitCabinetNodeService initCabinetNodeService;
	
	@Autowired
	private BusinessProperties properties;
	/**
	 * 常用排架服务
	 */
	@Autowired
	private BentFrameOftenUsedService bentFrameOftenUsedService;
	@Override
	public PageInfo<ArchivesBox> findArchivesBoxPage(ArchivesBox entity) {
		Page<ArchivesBox> page = PageHelper.startPage(entity.getPage(), entity.getRows(), "createTime DESC");
		List<ArchivesBox> list = archivesBoxMapper.findArchivesBoxPage(entity);
		return page.toPageInfo();
	}

	@Override
	public boolean deleteArchivesBox(ArchivesBox entity) {
		//把当前档案盒的信息拿出来
		ArchivesBox box=archivesBoxMapper.selectOne(entity);
		//删除档案盒和移除档案盒中的档案
		if (archivesBoxMapper.deleteArchives(entity) >= 0 && archivesBoxMapper.deleteArchivesBox(entity) > 0) {
			DocumentBean documentBean=new DocumentBean();
			//如果是最后一个档案盒，删除初始化表和常用排架表
			if(Integer.parseInt(archivesBoxMapper.isLastArchiveBox(entity))==0){
				archivesBoxMapper.deleteOfenUsedBentByBox(box);
				archivesBoxMapper.deleteInitByBox(box);
			}else{
				//不是最后一个档案盒，减少初始化表中的当前档案盒厚度
				archivesBoxMapper.updateInitCabinetNodeAllBoxWide(entity);
			}
			
			return true;
		}
		return false;
	}

	@Override
	public ArchivesBox getArchivesBox(ArchivesBox entity) {
		ArchivesBox archivesBox = archivesBoxMapper.selectOne(entity);
		return archivesBox;
	}

	@Override
	public int deleteArchives(ArchivesBox entity) {
		return archivesBoxMapper.deleteArchives(entity);
	}

	/**
	 * 导出Excel
	 */
	@Override
	public HSSFWorkbook exportExcelArchivesBox(ArchivesBox entity) {
		String sheetName = "档案盒信息";
		List<ArchivesBox> list = archivesBoxMapper.findArchivesBoxPage(entity);
		HSSFWorkbook wb = null;
		try {
			wb = ExcelUtil.createWorkbook(null, 3, sheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 生成导入记录信息 XLS
		createExcelImportRecordInfo(wb, wb.getSheet(sheetName), 5, 0, sheetName, list); // 写数据到XLS
		return wb;
	}

	public static void createExcelImportRecordInfo(HSSFWorkbook wb, HSSFSheet sheet, int startRow, int startCol,
			String title, List<ArchivesBox> workList) {
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 2, startCol + 2, startCol + 53, false,
				NFExcelStyle.getTitle(wb), title);
		startRow += 3;
		///////////// **********/////////////
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 1, startCol + 2, startCol + 3, false,
				NFExcelStyle.getTableColorHeader(wb), "序号");
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 1, startCol + 4, startCol + 12, false,
				NFExcelStyle.getTableColorHeader(wb), "排架代码");
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 1, startCol + 13, startCol + 33, false,
				NFExcelStyle.getTableColorHeader(wb), "档案盒题名");
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 1, startCol + 34, startCol + 37, false,
				NFExcelStyle.getTableColorHeader(wb), "档案分类");
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 1, startCol + 38, startCol + 43, false,
				NFExcelStyle.getTableColorHeader(wb), "装盒人");
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 1, startCol + 44, startCol + 47, false,
				NFExcelStyle.getTableColorHeader(wb), "装盒时间");
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 1, startCol + 48, startCol + 50, false,
				NFExcelStyle.getTableColorHeader(wb), "档案盒厚度");
		NFExcelStyle.createMergedCell(wb, sheet, startRow, startRow + 1, startCol + 51, startCol + 53, false,
				NFExcelStyle.getTableColorHeader(wb), "聚合层次");
		startRow += 2;
		///////////// **********/////////////
		int newRow = startRow;
		int num = 0;
		HSSFCellStyle cellStyle = NFExcelStyle.getTableColorRow(wb, "");
		for (ArchivesBox model : workList) {
			num++;
			NFExcelStyle.createMergedCell(wb, sheet, newRow, newRow + 1, startCol + 2, startCol + 3, false, cellStyle,
					String.valueOf(num));
			NFExcelStyle.createMergedCell(wb, sheet, newRow, newRow + 1, startCol + 4, startCol + 12, false, cellStyle,
					model.getBentFrameCode());
			NFExcelStyle.createMergedCell(wb, sheet, newRow, newRow + 1, startCol + 13, startCol + 33, false, cellStyle,
					model.getArchivesBoxName());
			NFExcelStyle.createMergedCell(wb, sheet, newRow, newRow + 1, startCol + 34, startCol + 37, false, cellStyle,
					model.getArchivesBoxType());
			NFExcelStyle.createMergedCell(wb, sheet, newRow, newRow + 1, startCol + 38, startCol + 43, false, cellStyle,
					model.getFitBoxUser(), false);
			NFExcelStyle.createMergedCell(wb, sheet, newRow, newRow + 1, startCol + 44, startCol + 47, false, cellStyle,
					model.getFitBoxTime().toString());
			NFExcelStyle.createMergedCell(wb, sheet, newRow, newRow + 1, startCol + 48, startCol + 50, false, cellStyle,
					String.valueOf(model.getArchivesBoxThick()));
			NFExcelStyle.createMergedCell(wb, sheet, newRow, newRow + 1, startCol + 51, startCol + 53, false, cellStyle,
					model.getPolymerize());
			newRow = newRow + 2;
		}
	}

	public static void createMergedCell(HSSFWorkbook wb, HSSFSheet sheet, int startRow, int endRow, int startCol,
			int endCol, boolean isNewRow, HSSFCellStyle hssfcellstyle, String cellValue, boolean num) {
		CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
		sheet.addMergedRegion(region);
		if (isNewRow) {
			for (int i = startRow; i < endRow + 1; i++) {
				HSSFRow row = sheet.createRow(i);
				row.setHeight((short) 280);
				for (int j = startCol; j < endCol + 1; j++) {
					row.createCell(j).setCellStyle(hssfcellstyle);
				}
			}
		} else {
			for (int i = startRow; i < endRow + 1; i++) {
				HSSFRow row = sheet.getRow(i);
				if (row == null) {
					row = sheet.createRow(i);
				}
				row.setHeight((short) 280);
				for (int j = startCol; j < endCol + 1; j++) {
					row.createCell(j).setCellStyle(hssfcellstyle);
				}
			}
		}
		if (StringUtil.isNotEmpty(cellValue)) {
			if (isNumeric(cellValue) && num) {
				try {
					sheet.getRow(startRow).getCell(startCol).setCellValue(Integer.parseInt(cellValue));
				} catch (NumberFormatException e) {
					sheet.getRow(startRow).getCell(startCol).setCellValue(cellValue);
				}
			} else {
				sheet.getRow(startRow).getCell(startCol).setCellValue(cellValue);
			}
		}
	}

	public static boolean isNumeric(String s) {
		if ((s != null) && (s != ""))
			return s.matches("^[0-9]*$");
		else
			return false;
	}
	
    @Override
	public void saveArchivesBox(ArchivesBoxInfoVO vo) throws EcmException {
    	//初始化创建档案盒
    	//不管增加的时候是通过初始化设置，还是常用排架最终都需要更新初始化设置（柜节信息），因此当初始化信息为空时，需要通过常用排架在设置一次初始化VO
    	if(vo.getInitCabinetNodeVO() == null){
    		InitCabinetNodeVO initCabinetNodeVO = this.copyToInitCabinetNodeVO(vo.getBentFrameOftenUsedVO());
    		//将盒号设置到初始化配置中
    		String archivesBoxNum = vo.getArchivesBoxVO().getBentFrameCode().
    				substring(vo.getArchivesBoxVO().getBentFrameCode().lastIndexOf("-")+1);
    		initCabinetNodeVO.setArchivesBoxNum(archivesBoxNum);
    		vo.setInitCabinetNodeVO(initCabinetNodeVO);
		}
    	initCabinetNodeService.initCabinetNodeHandle(vo.getInitCabinetNodeVO());
    	
    	//常用排架不为空
    	if(vo.getBentFrameOftenUsedVO()!=null){
    		bentFrameOftenUsedService.initBentFrameOftenUsed(vo.getBentFrameOftenUsedVO());
		}
		//档案盒赋值
		ArchivesBox archivesBox = this.setArchivesBox(vo.getArchivesBoxVO());
		//档案盒的厚度每次都从初始化信息中拿
		archivesBox.setArchivesBoxThick(vo.getInitCabinetNodeVO().getArchivesBoxWide());
		if(StringUtil.isEmpty(vo.getArchivesBoxVO().getId())){
			this.save(archivesBox);
		}else{
			this.updateNotNull(archivesBox);
		}
		
	}
    @Override
    public List<DocumentInfoVO> getRemoteDocumentInfoByIds(String[] ids)throws EcmException{
    	List<DocumentInfoVO> returnList = new ArrayList();
    	List<QueryConditionInnerVO> condition = new ArrayList();
    	
    	QueryConditionVO queryConditionVO = new QueryConditionVO();
		String[] columns = {"RecordChineseTitle","FileClassificationNum","ArchiveCode","AggregationLevel"};
    	queryConditionVO.setColumns(columns);
    	
    	QueryConditionInnerVO queryConditionInnerVO = new QueryConditionInnerVO();
    	queryConditionInnerVO.setHandle("in");
    	queryConditionInnerVO.setWhereParam("id");
    	queryConditionInnerVO.setWhereValue(ids);
    	condition.add(queryConditionInnerVO);
    	queryConditionVO.setCondition(condition);
    	
    	String url = properties.getMasterServiceUrl()+properties.getPropertiesByRequestParamUrl();
    	
    	Map<String, String> hearderMap = new HashMap();
    	//需要从单点登录获得
    	hearderMap.put("userid", properties.getUserId());
    	hearderMap.put("systemId", properties.getSystemId());
    	
    	
		HttpUtil httpUtil = new HttpUtil();
		try{
			BizResponse bizResponse = (BizResponse) httpUtil.sendHttpPost(
	    			hearderMap, queryConditionVO, BizResponse.class,url);
	    	if(StringUtil.isEmpty(bizResponse.getErrorCode())){
	    		Map item = (LinkedHashMap)bizResponse.getItem();
	    		List list = (ArrayList)item.get("items");
	    		if(null != list && list.size()>0){
	    			for(int i=0;i<list.size();i++){
	    				Map map = (LinkedHashMap)list.get(i);
	    				DocumentInfoVO documentInfoVO = (DocumentInfoVO)ConvertUtil.mapToBean(map, DocumentInfoVO.class); 
	    				returnList.add(documentInfoVO);
	    			}
	    		}
	    	}else{
	    		throw new EcmException(bizResponse.getErrorCode(),bizResponse.getErrorDescription());
	    	}
		}catch(Exception e){
			e.printStackTrace();
			throw new EcmException(e);
		}
    	
    	return returnList;
    }
	/**
	 * 从VO向model赋值
	 * 
	 * @param vo
	 * @return
	 */
	private ArchivesBox setArchivesBox(ArchivesBoxVO vo) throws EcmException {
		ArchivesBox archivesBox = new ArchivesBox();
		try {
			BeanUtils.copyProperties(archivesBox, vo);
			archivesBox.setCreateTime(new Date());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EcmException(ErrorCode.SETVALUE_ERROR, "赋值操作失败");
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EcmException(ErrorCode.SETVALUE_ERROR, "赋值操作失败");
		}
		return archivesBox;
	}
	/**
	 * 从VO向model赋值，将排架的数据赋值给初始化VO
	 * 
	 * @param vo
	 * @return
	 */
	private InitCabinetNodeVO copyToInitCabinetNodeVO(BentFrameOftenUsedVO vo) throws EcmException {
		InitCabinetNodeVO initCabinetNodeVO = new InitCabinetNodeVO();
		try {
			BeanUtils.copyProperties(initCabinetNodeVO, vo);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EcmException(ErrorCode.SETVALUE_ERROR, "赋值操作失败");
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new EcmException(ErrorCode.SETVALUE_ERROR, "赋值操作失败");
		}
		return initCabinetNodeVO;
	}


}
