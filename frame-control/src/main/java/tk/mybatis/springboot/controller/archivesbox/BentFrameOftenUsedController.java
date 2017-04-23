package tk.mybatis.springboot.controller.archivesbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ecm.business.archivesbox.BentFrameOftenUsedService;
import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.ErrorCode;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsed;
import com.dhcc.ecm.business.mybatis.archivesbox.model.BentFrameOftenUsedVO;
import com.github.pagehelper.PageInfo;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tk.mybatis.springboot.controller.AbstractRestHandler;
import tk.mybatis.springboot.controller.archivesbox.model.DocumentBean;

/**
 * @ClassName BentFrameOftenUsedController
 * @Description 常用排架Controller
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-17
 */
@RestController
@RequestMapping(value = "/bentFrameOftenUsed")
@Api(value = "archives-bentFrameOftenUsed-api", description = "常用排架服务", tags = "常用排架")
public class BentFrameOftenUsedController extends AbstractRestHandler {
	@Autowired
	private BentFrameOftenUsedService bentFrameOftenUsedService;
	
	@RequestMapping(value = "/delBentFrameOftenUsed", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
		"application/json" })
	@ApiOperation(value = "取消常用排架", notes = "传入常用排架ID数组，进行删除操作", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回删除成功") })
	public BizResponse saveFitBox(@ApiParam(value = "档案装盒信息", required = true) @RequestBody String[] ids){
		BizResponse returnResponse = null;
		try{
			bentFrameOftenUsedService.deleteByIds(ids);
			returnResponse = new BizResponse("取消常用排架成功");
		}catch(Exception e){
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.DELETE_ERROR, "取消常用排架失败");
		}
		return returnResponse;
	}
	
	
	@RequestMapping(path = "/getDictionaryList/{dNames}", method = RequestMethod.POST)
    @ApiOperation(value = "获取数据字典", notes = "获取数据字典", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "返回保存成功") })
    public BizResponse getDictionaryList(HttpServletRequest request, @PathVariable String[] dNames){
        BizResponse bizResponse=null;
        if(!StringUtils.isEmpty(dNames)){
            Map<String,String[]> map=new HashMap();
            map.put("dNames",dNames);
            bizResponse = (getDictionaryByName(request,map));
        }
        return bizResponse;
    }
	
	
	@RequestMapping(value = "/navDomain/{action}", method = RequestMethod.GET,headers = "userid")
	@ApiOperation(value = "获取档案一级分类", notes = "获取档案一级分类", position = 7, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回所有一级档案分类") })
	protected BizResponse navDomain(HttpServletRequest request,@PathVariable String[] action) {
		BizResponse bizResponse = null;
		if(!StringUtils.isEmpty(action)){
            Map<String,String[]> map=new HashMap();
            map.put("action",action);
		bizResponse = navDomain(request,map);
		}
		return bizResponse;
	}
	
	@RequestMapping(value = "/updatePhysiLocation", method = RequestMethod.POST,headers = "userid")
	@ApiOperation(value = "修改物理位置", notes = "修改物理位置", position = 7, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "修改成功") })
	protected BizResponse updatePhysiLocation(HttpServletRequest request,
			@RequestBody DocumentBean documentBean) {
		BizResponse bizResponse = updatePhy(request, documentBean);
		return bizResponse;
	}

	
	/**
	 * 查询排架分页信息
	 * 
	 * @param archivesBox
	 * @return
	 */
	@RequestMapping(value = "/queryBentFrameOftenUsedPage", method = RequestMethod.POST, produces = {
			"application/json" }, consumes = { "application/json" })
	@ApiOperation(value = "查询排架分页信息", notes = "查询排架分页信息", position = 1, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回分页数据") })
	public BizResponse queryArchivesBoxPage(
			@ApiParam(value = "常用排架对象", required = true) @RequestBody BentFrameOftenUsedVO vo) {
		BizResponse returnResponse = null;
		try {
			PageInfo<BentFrameOftenUsed> pageInfo = bentFrameOftenUsedService.queryBentFrameOftenUsedPage(vo);
			returnResponse = new BizResponse(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.QUERY_ERROR, "查询常用排架列表信息失败");
		}
		return returnResponse;
	}
	
	
	

}