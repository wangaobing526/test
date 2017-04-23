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

import com.dhcc.ecm.business.capacity.StorageRoomService;
import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.capacity.model.StorageRoom;
import com.github.pagehelper.PageInfo;

/**
 * 容量配置管理-库房管理
 * 
 * @author starsand
 *
 */
@RestController
@RequestMapping(value = "/storageRoom")
@Api(value = "business-storageRoom-api", description = "库房管理接口服务", tags = "库房管理")
public class StorageRoomController extends AbstractRestHandler {
	@Autowired
	private StorageRoomService storageRoomService;

	/**
	 * 保存库房
	 * 
	 * @param storageRoom
	 * @return
	 */
	@RequestMapping(value = "/saveStorageRoom", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "保存库房", notes = "传入库房对象进行保存", position = 2, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回保存成功") })
	public BizResponse saveStorageRoom(
			@ApiParam(value = "容量配置", required = true) @RequestBody StorageRoom storageRoom) {
		BizResponse returnResponse = null;
		try {
			if (!storageRoomService.checkStorageRoomNameisExist(storageRoom)) {
				storageRoom.setCreateTime(new Date());
				if (storageRoomService.save(storageRoom) > 0) {
					returnResponse = new BizResponse("库房保存成功");
				}
			} else {
				returnResponse = new BizResponse(ErrorCode.SAVE_ERROR,"库房名称或编码已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "保存库房对象失败");
		}
		return returnResponse;
	}

	/**
	 * 删除库房
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteStorageRoom", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "删除库房", notes = "根据传入参数：库房id", position = 4, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回删除成功") })
	public BizResponse deleteStorageRoom(@ApiParam(value = "删除库房", required = true) @RequestBody String id) {
		BizResponse returnResponse = null;
		try {
			StorageRoom storageRoom = new StorageRoom();
			storageRoom.setId(id);
			if (!storageRoomService.checkStorageRoomHasCabinet(storageRoom)) {
				storageRoomService.deleteById(id);
				returnResponse = new BizResponse("删除库房信息成功");
			} else {
				returnResponse = new BizResponse(ErrorCode.DELETE_ERROR,"该库房已有柜架，请删除柜架后删除库房");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.DELETE_ERROR, "删除库房失败");
		}
		return returnResponse;
	}

	/**
	 * 修改库房信息
	 * 
	 * @param storageArea
	 * @return
	 */
	@RequestMapping(value = "/updateStorageRoom", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "修改库房信息", notes = "更新库房信息", position = 5, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "更新库房信息成功") })
	public BizResponse updateStorageRoom(
			@ApiParam(value = "库房信息内容", required = true) @RequestBody StorageRoom storageRoom) {
		BizResponse returnResponse = null;
		try {
			if (!storageRoomService.checkStorageRoomNameisExist(storageRoom)) {
				if (storageRoomService.updateNotNull(storageRoom) > 0) {
					returnResponse = new BizResponse(ErrorCode.SAVE_ERROR,"更新库房信息成功");
				}
			} else {
				returnResponse = new BizResponse(ErrorCode.SAVE_ERROR,"库房名称或编码已存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "修改库房信息失败");
		}
		return returnResponse;
	}

	/**
	 * 查询库房列表信息
	 * 
	 * @param storageRoom
	 * @return
	 */
	@RequestMapping(value = "/queryStorageRoom", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "查询库房列表信息", notes = "查询库房列表", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回列表信息") })
	public BizResponse queryStorageRoom(
			@ApiParam(value = "查询库房列表信息", required = true) @RequestBody StorageRoom storageRoom) {
		BizResponse returnResponse = null;
		try {
			List<StorageRoom> list = storageRoomService.findStorageRoomList(storageRoom);
			returnResponse = new BizResponse(list);
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询库房列表失败");
		}
		return returnResponse;
	}

	/**
	 * 查询库房分页信息
	 * 
	 * @param storageRoom
	 * @return
	 */
	@RequestMapping(value = "/queryStorageRoomPage", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "查询库房分页信息", notes = "查询库房信息", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回分页数据") })
	public BizResponse queryStorageRoomPage(
			@ApiParam(value = "库房信息对象", required = true) @RequestBody StorageRoom storageRoom) {
		BizResponse returnResponse = null;
		try {
			PageInfo<StorageRoom> pageInfo = storageRoomService.findStorageRoomPage(storageRoom);
			returnResponse = new BizResponse(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询库房信息失败");
		}
		return returnResponse;
	}
}
