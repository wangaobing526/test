package tk.mybatis.springboot.controller.archivesbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ecm.business.archivesbox.InitCabinetNodeService;
import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.archivesbox.model.InitCabinetNodeVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tk.mybatis.springboot.controller.AbstractRestHandler;
/**
 * @ClassName InitCabinetNodeController
 * @Description 初始化柜节Controller
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-13
 */
@RestController
@RequestMapping(value = "/initCabinetNode")
@Api(value = "archives-initCabinetNode-api", description = "初始化柜节服务", tags = "初始化柜节")
public class InitCabinetNodeController extends AbstractRestHandler {
	@Autowired
	private InitCabinetNodeService initCabinetNodeService;
	/**
	 * 装盒完成操作
	 * 1.判断输入是否为空
	 * 2.判断档案分类是否唯一
	 * 3.存储：将档案盒与文档ID关联
	 * 4.调用标准接口传递文档ID、排架编码更新文档物理位置
	 * 
	 * @param FitBoxVO
	 *            listList 档案装盒信息列表
	 * @return
	 */
	@RequestMapping(value = "/InitCabinetNode", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
			"application/json" })
	@ApiOperation(value = "初始化柜节", notes = "输入柜节相关信息，进行初始化操作", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回排架编码") })
	public BizResponse InitCabinetNode(@ApiParam(value = "初始化柜节内容", required = true) @RequestBody InitCabinetNodeVO vo)
			throws EcmException {
		BizResponse returnResponse = null;
		try {
			if(!StringUtils.isEmpty(vo)){
				initCabinetNodeService.initCabinetNodeHandle(vo);
			}else{
				throw new EcmException(ErrorCode.DATA_INPUT_NULL_ERROR,"初始化信息不能为空");
			}
			returnResponse = new BizResponse("初始化柜节成功");
		}catch(EcmException e1) {
			e1.printStackTrace();
			returnResponse = new BizResponse(e1.getCode(),e1.getErrorDescrption());
		}catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "初始化柜节失败");
		}
		return returnResponse;
	}
}
