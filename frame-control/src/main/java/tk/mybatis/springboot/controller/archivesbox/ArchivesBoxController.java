package tk.mybatis.springboot.controller.archivesbox;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ecm.business.archivesbox.ArchivesBoxService;
import com.dhcc.ecm.business.archivesbox.FitBoxService;
import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBox;
import com.dhcc.ecm.business.mybatis.archivesbox.model.ArchivesBoxInfoVO;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tk.mybatis.springboot.controller.AbstractRestHandler;
import tk.mybatis.springboot.controller.archivesbox.model.DocumentBean;
import tk.mybatis.springboot.controller.archivesbox.model.MetaItems;

/**
 * 排架管理-档案盒管理
 * 
 *
 */
@RestController
@RequestMapping(value = "/archivesBox")
@Api(value = "business-archivesBox-api", description = "档案盒接口服务", tags = "档案盒管理")
public class ArchivesBoxController extends AbstractRestHandler {
	@Autowired
	private ArchivesBoxService archivesBoxService;
	@Autowired
	private FitBoxService fitBoxService;
	/**
	 * 查询档案盒列表分页信息
	 * 
	 * @param archivesBox
	 * @return
	 */
	@RequestMapping(value = "/queryArchivesBoxPage", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "查询档案盒分页信息", notes = "查询档案盒分页信息", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回分页数据") })
	public BizResponse queryArchivesBoxPage(
			@ApiParam(value = "档案盒信息对象", required = true) @RequestBody ArchivesBox archivesBox) {
		BizResponse returnResponse = null;
		try {
			PageInfo<ArchivesBox> pageInfo = archivesBoxService.findArchivesBoxPage(archivesBox);
			returnResponse = new BizResponse(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询档案盒列表信息失败");
		}
		return returnResponse;
	}

	/**
	 * 删除档案盒
	 * 
	 * @param archivesBox
	 * @return
	 */
	@RequestMapping(value = "/deleteArchivesBox", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "删除档案盒", notes = "根据传入参数：档案盒id", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回删除成功") })
	public BizResponse deleteArchivesBox(
			@ApiParam(value = "删除档案盒信息", required = true) @RequestBody HttpServletRequest request,ArchivesBox archivesBox) {
		BizResponse returnResponse = null;
		try {
				String [] ids=fitBoxService.getDocumentByArchivesBox(archivesBox.getId());
			// TODO 加入删除档案元数据物理位置信息
			if (archivesBoxService.deleteArchivesBox(archivesBox)) {
				DocumentBean documentBean=new DocumentBean();
				List<MetaItems> items=new ArrayList<MetaItems>();
				MetaItems meta=new MetaItems();
				meta.setKey("PhysicalLocation");
				meta.setValue("");;
				items.add(meta);
				documentBean.setMetaItems(items);
				for(int i=0;i<ids.length;i++){
					documentBean.setId(ids[i]);
					updatePhy(request, documentBean);
				}
				returnResponse = new BizResponse("删除档案盒信息成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.DELETE_ERROR, "删除档案盒失败");
		}
		return returnResponse;
	}

	/**
	 * 移除档案
	 * 
	 * @param archives
	 * @return
	 */
	@RequestMapping(value = "/deleteArchives", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "移除档案", notes = "根据传入参数：档案盒id", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回移除成功") })
	public BizResponse deleteArchives(
			@ApiParam(value = "移除档案信息", required = true) @RequestBody HttpServletRequest request, ArchivesBox archivesBox) {
		BizResponse returnResponse = null;
		try {
				
			// TODO 加入删除档案元数据物理位置信息
			if (archivesBoxService.deleteArchives(archivesBox) > 0) {
				DocumentBean documentBean=new DocumentBean();
				List<MetaItems> items=new ArrayList<MetaItems>();
				MetaItems meta=new MetaItems();
				meta.setKey("PhysicalLocation");
				meta.setValue("");;
				items.add(meta);
				documentBean.setMetaItems(items);
				for(int i=0;i<archivesBox.getArchivesIds().length;i++){
					documentBean.setId(archivesBox.getArchivesIds()[i]);
					updatePhy(request, documentBean);
				}
				returnResponse = new BizResponse("移除档案成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.DELETE_ERROR, "移除档案失败");
		}
		return returnResponse;
	}

	/**
	 * 导出档案盒信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/exportArchivesBox", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "导出档案盒信息", notes = "根据传入参数：档案盒ids", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回导出成功") })
	public BizResponse exportArchivesBox(@ApiParam(value = "导出档案信息", required = true) @RequestBody  ArchivesBox archivesBox) {
		BizResponse bizResponse = null;
		FileOutputStream fileOut = null;

		Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS"); 
		String myTime = sdFormat.format(date);
		String fileName = "档案盒导出"+myTime + ".xls";
		String dir=archivesBox.getExportDir()+File.separator+fileName;
		// String filePath = systemPath + File.separator + fileName;
		try {
			HSSFWorkbook wb = archivesBoxService.exportExcelArchivesBox(archivesBox);

			// 写入文件，关闭流
			fileOut = new FileOutputStream(dir);
			wb.write(fileOut);
			if(new File(dir).exists()){
				fileOut.close();
				bizResponse = new BizResponse("导出成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bizResponse = new BizResponse(ErrorCode.QUERY_ERROR, "导出档案盒失败");
		} 
		return bizResponse;
	}
	
	
	/**
	 * 创建\修改档案盒信息
	 * @param tInstanceFile
	 * @return
	 */
	@RequestMapping(value = "/saveArchivesBox", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "保存档案盒信息", notes = "传入档案盒信息对象进行保存", position
	= 3, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回保存成功") })
	public BizResponse saveArchivesBox(@ApiParam(value = "档案盒信息内容", required = true) @RequestBody ArchivesBoxInfoVO vo) {
		BizResponse returnResponse = null;
		try {
			//校验输入内容,查询档案盒，校验排架编码是否重复
			this.verifyArchiveBox(vo);
			//保存档案盒信息
			archivesBoxService.saveArchivesBox(vo);
			returnResponse = new BizResponse("保存信息成功");
		}catch (EcmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnResponse = new BizResponse(e.getCode(),e.getErrorDescrption());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "保存信息失败");
		}
		return returnResponse;
	}
	
	/**
	 * 根据文档id集合获得
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/getRemoteDocumentInfoByIds", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "获得文档列表", notes = "获得文档列表", position
	= 3, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "获得文档数据") })
	public BizResponse getRemoteDocumentInfoByIds(@ApiParam(value = "文档ID集合", required = true) @RequestBody String[] ids) {
		BizResponse returnResponse = null;
		try {
			//获得文档数据
			List list = archivesBoxService.getRemoteDocumentInfoByIds(ids);
			returnResponse = new BizResponse(list);
		}catch (EcmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnResponse = new BizResponse(e.getCode(),e.getErrorDescrption());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询信息失败");
		}
		return returnResponse;
	}
	
	private void verifyArchiveBox(ArchivesBoxInfoVO vo)throws EcmException{
		if(vo.getInitCabinetNodeVO()==null && vo.getBentFrameOftenUsedVO()==null){
			throw new EcmException(ErrorCode.DATA_INPUT_NULL_ERROR,"初始化设置与常用排架内容不能都为空");
		}
		if(vo.getInitCabinetNodeVO()!=null && vo.getBentFrameOftenUsedVO()!=null){
			throw new EcmException(ErrorCode.DATA_INPUT_NULL_ERROR,"不能同时设置初始化设置与常用排架内容");
		}
		ArchivesBox box = new ArchivesBox();
		box.setBentFrameCode(vo.getArchivesBoxVO().getBentFrameCode());
		List<ArchivesBox> list =  archivesBoxService.findArchivesBoxPage(box).getList();
		if(list!=null && list.size()>0){
			throw new EcmException(ErrorCode.DATA_INPUT_NULL_ERROR,"输入的排架编码重复");
		}
	}
	
	/**
	 * 根据档案盒id获得档案
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/getRemoteDocumentInfoByBox", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "根据档案盒获得档案", notes = "根据档案盒获得档案", position
	= 3, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "获得文档数据") })
	public BizResponse getRemoteDocumentInfoByBox(@ApiParam(value = "档案盒id", required = true) @RequestBody String id) {
		BizResponse returnResponse = null;
		try {
			String[] ids=fitBoxService.getDocumentByArchivesBox(id);
			//获得文档数据
			List list = archivesBoxService.getRemoteDocumentInfoByIds(ids);
			returnResponse = new BizResponse(list);
		}catch (EcmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnResponse = new BizResponse(e.getCode(),e.getErrorDescrption());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询信息失败");
		}
		return returnResponse;
	}
}
