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

import com.dhcc.ecm.business.capacity.CabinetInfoService;
import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.capacity.model.CabinetInfo;
import com.github.pagehelper.PageInfo;

/**
 * 容量配置管理-排架管理
 * 
 *
 */
@RestController
@RequestMapping(value = "/cabinetInfo")
@Api(value = "business-cabinetInfo-api", description = "排架管理接口服务", tags = "排架管理")
public class CabinetInfoController extends AbstractRestHandler {
	@Autowired
	private CabinetInfoService cabinetInfoService;
	/**
	 * 保存柜架信息
	 * 
	 * @param cabinetInfo
	 * @return
	 */
	@RequestMapping(value = "/saveCabinetInfo", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "保存柜架信息", notes = "传入柜架信息进行保存", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回保存成功") })
	public BizResponse saveCabinetInfo(
			@ApiParam(value = "容量配置", required = true) @RequestBody CabinetInfo cabinetInfo) {
		BizResponse returnResponse = null;
		try {
			if (!cabinetInfoService.checkCabinetNameisExist(cabinetInfo)) {
				cabinetInfo.setCreateTime(new Date());
				if (cabinetInfoService.save(cabinetInfo) > 0) {
					returnResponse = new BizResponse("柜架信息保存成功");
				}
			} else {
				returnResponse = new BizResponse("柜架名称或编码已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "保存柜架信息失败");
		}
		return returnResponse;
	}

	/**
	 * 删除柜架信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteCabinetInfo", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "删除柜架信息", notes = "根据传入参数：柜架id", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回删除成功") })
	public BizResponse deleteCabinetInfo(@ApiParam(value = "删除柜架信息", required = true) @RequestBody String id) {
		BizResponse returnResponse = null;
		try {
			if(!cabinetInfoService.checkCabinetHasInit(id)){
				cabinetInfoService.deleteById(id);
				returnResponse = new BizResponse("删除柜架信息成功");
			}else{
				returnResponse = new BizResponse(ErrorCode.DELETE_ERROR,"柜架信息已被初始化");
			}
		} catch (Exception e) {
		
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.DELETE_ERROR, "删除柜架失败");
		}
		return returnResponse;
	}

	/**
	 * 修改柜架信息
	 * 
	 * @param cabinetInfo
	 * @return
	 */
	@RequestMapping(value = "/updateCabinetInfo", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "修改柜架信息", notes = "更新柜架信息", position = 5, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "更新柜架信息成功") })
	public BizResponse updateCabinetInfo(
			@ApiParam(value = "柜架信息内容", required = true) @RequestBody CabinetInfo cabinetInfo) {
		BizResponse returnResponse = null;
		try {
			if (!cabinetInfoService.checkCabinetNameisExist(cabinetInfo)) {
				if (cabinetInfoService.updateNotNull(cabinetInfo) > 0) {
					returnResponse = new BizResponse("更新柜架信息成功");
				}
			} else {
				returnResponse = new BizResponse("柜架名称或编码已存在");
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "修改柜架信息失败");
		}
		return returnResponse;
	}

	/**
	 * 查询柜架列表信息
	 * 
	 * @param cabinetInfo
	 * @return
	 */
	@RequestMapping(value = "/queryCabinetInfo", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "查询柜架列表信息", notes = "查询柜架列表", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回柜架列表信息") })
	public BizResponse queryCabinetInfo(
			@ApiParam(value = "查询柜架列表信息", required = true) @RequestBody CabinetInfo cabinetInfo) {
		BizResponse returnResponse = null;
		try {
			List<CabinetInfo> list = cabinetInfoService.findCabinetInfoList(cabinetInfo);
			returnResponse = new BizResponse(list);
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询柜架列表失败");
		}
		return returnResponse;
	}

	/**
	 * 查询柜架分页信息
	 * 
	 * @param cabinetInfo
	 * @return
	 */
	@RequestMapping(value = "/queryCabinetInfoPage", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "查询柜架分页信息", notes = "查询柜架分页信息", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回分页数据") })
	public BizResponse queryCabinetInfoPage(
			@ApiParam(value = "柜架信息对象", required = true) @RequestBody CabinetInfo cabinetInfo) {
		BizResponse returnResponse = null;
		try {
			PageInfo<CabinetInfo> pageInfo = cabinetInfoService.findCabinetInfoPage(cabinetInfo);
			returnResponse = new BizResponse(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询柜架信息失败");
		}
		return returnResponse;
	}
}
