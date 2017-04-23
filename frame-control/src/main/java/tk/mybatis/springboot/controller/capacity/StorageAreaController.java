package tk.mybatis.springboot.controller.capacity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.springboot.controller.AbstractRestHandler;

import com.dhcc.ecm.business.capacity.StorageAreaService;
import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.capacity.model.StorageArea;
import com.github.pagehelper.PageInfo;

/**
 * 容量配置管理-库区管理
 * @author starsand
 *
 */
@RestController
@RequestMapping(value = "/storageArea")
@Api(value = "business-storageArea-api", description = "库区管理接口服务", tags = "库区管理")
public class StorageAreaController extends AbstractRestHandler {
	@Autowired
	private StorageAreaService storageAreaService;
	/**
	 * 保存库区
	 * @param storageArea
	 * @return
	 */
	@RequestMapping(value = "/saveStorageArea", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "保存库区", notes = "传入库区对象进行保存", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回保存成功") })
	public BizResponse saveStorageArea(
			@ApiParam(value = "容量配置", required = true) @RequestBody StorageArea storageArea) {
		BizResponse returnResponse = null;
		try {
			if(!storageAreaService.checkStorageAreaNameisExist(storageArea)){
				storageArea.setCreateTime(new Date());
				if (storageAreaService.save(storageArea) > 0) {
					returnResponse = new BizResponse("库区保存成功");
				}
			}else{
				returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "当前库区名称或编码已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "保存库区对象失败");
		}
		return returnResponse;
	}
	/**
	 * 删除库区
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteStorageArea", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
			"application/json" })
	@ApiOperation(value = "删除库区", notes = "根据传入参数：库区id", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回删除成功") })
	public BizResponse deleteStorageArea(@ApiParam(value = "删除库区", required = true) @RequestBody String id) {
		BizResponse returnResponse = null;
		try {
			StorageArea storageArea=new StorageArea();
			storageArea.setId(id);
			if(!storageAreaService.checkStorageAreaHasRoom(storageArea)){
				storageAreaService.deleteById(id);
				returnResponse = new BizResponse("删除库区信息成功");
			}else{
				returnResponse = new BizResponse(ErrorCode.DELETE_ERROR,"该库区已有库房，请先删除库房信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.DELETE_ERROR, "删除库区失败");
		}
		return returnResponse;
	}
	/**
	 * 修改库区信息
	 * @param storageArea
	 * @return
	 */
	@RequestMapping(value = "/updateStorageArea", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "修改库区信息", notes = "更新库区信息", position = 5,
	produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "更新库区信息成功") })
	public BizResponse updateStorageArea(
			@ApiParam(value = "库区信息内容", required = true) @RequestBody StorageArea storageArea) {
		BizResponse returnResponse = null;
		try {
			if(!storageAreaService.checkStorageAreaNameisExist(storageArea)){
				if(storageAreaService.updateNotNull(storageArea)>0){
					returnResponse = new BizResponse("更新库区信息成功");
				}
			}else{
				returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "当前库区名称或编码已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "修改库区信息失败");
		}
		return returnResponse;
	}
	/**
	 * 查询库区列表信息
	 * @param storageArea
	 * @return
	 */
	@RequestMapping(value = "/queryStorageArea", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "查询库区列表信息", notes = "查询库区列表", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回列表信息") })
	public BizResponse queryStorageArea(
			@ApiParam(value = "查询库区列表信息", required = true) @RequestBody StorageArea storageArea) {
		BizResponse returnResponse = null;
		try {
			List<StorageArea> list = storageAreaService
					.findStorageAreaList(storageArea);
			returnResponse = new BizResponse(list);
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询库区列表失败");
		}
		return returnResponse;
	}
	/**
	 * 查询库区列表信息（带分页）
	 * @param storageArea
	 * @return
	 */
	@RequestMapping(value = "/queryStorageAreaPage", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "查询库区列表信息（带分页）", notes = "查询库区列表信息（带分页）", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回分页数据") })
	public BizResponse queryStorageAreaPage(
			@ApiParam(value = "库区信息对象", required = true) @RequestBody StorageArea storageArea) {
		BizResponse returnResponse = null;
		try {
			PageInfo<StorageArea> pageInfo = storageAreaService
					.findStorageAreaPage(storageArea);
			returnResponse = new BizResponse(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询库区信息失败");
		}
		return returnResponse;
	}
	
}
