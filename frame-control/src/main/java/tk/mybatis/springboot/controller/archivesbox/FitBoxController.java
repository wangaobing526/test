package tk.mybatis.springboot.controller.archivesbox;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
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
import com.dhcc.ecm.business.mybatis.archivesbox.model.FitBoxVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tk.mybatis.springboot.controller.AbstractRestHandler;

/**
 * @ClassName FitBoxController
 * @Description 档案装盒Controller
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-12
 */
@RestController
@RequestMapping(value = "/fitBox")
@Api(value = "archives-fitBox-api", description = "档案装盒服务", tags = "档案装盒")
public class FitBoxController extends AbstractRestHandler {
	@Autowired
	private FitBoxService fitBoxService;
	
	/**
	 * 档案装盒服务
	 */
	@Autowired
	private ArchivesBoxService archivesBoxService;
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
	@RequestMapping(value = "/saveFitBox", method = RequestMethod.POST, produces = { "application/json" }, consumes = {
			"application/json" })
	@ApiOperation(value = "保存档案装盒信息", notes = "传入文档ID集合、档案盒ID、档案分类、排架编码、进行保存", position = 3, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "返回存档成功") })
	public BizResponse saveFitBox(@ApiParam(value = "档案装盒信息", required = true) @RequestBody FitBoxVO vo)
			throws EcmException {
		BizResponse returnResponse = null;
		try {
			if (StringUtils.isEmpty(vo.getDocumentIds()) 
					|| StringUtils.isEmpty(vo.getArchivesBoxId())
					|| StringUtils.isEmpty(vo.getDocumentType())
					|| StringUtils.isEmpty(vo.getBentFrameCode())) {
				throw new EcmException(ErrorCode.DATA_INPUT_NULL_ERROR, "装盒信息不能为空");
			}
			//校验分类是否一致
			if(!verifyDocumentType(vo)){
				throw new EcmException(ErrorCode.DATA_INPUT_NULL_ERROR, "排架编码错误或文档分类与档案盒分类不一致");
			}
			//存储装盒信息
			fitBoxService.savefitBoxInfo(vo);
			
			returnResponse = new BizResponse("档案装盒成功");
		} catch (EcmException e1) {
			e1.printStackTrace();
			returnResponse = new BizResponse(e1.getCode(),e1.getErrorDescrption());
		}catch (Exception e) {
			e.printStackTrace();
			returnResponse = new BizResponse(ErrorCode.SAVE_ERROR, "档案装盒失败");
		}
		return returnResponse;
	}
	
	/**
	 * 校验文档分类是否一致，取出档案盒和装盒入参的档
	 * 案分类第一个字母进行校验，如不相等则档案分类不一致
	 * @param vo
	 * @return
	 * @throws EcmException
	 */
	private boolean verifyDocumentType(FitBoxVO vo)throws EcmException{
		boolean returnValue = false;
		 //根据排架编码取出档案盒
		 ArchivesBox  archivesBox = new ArchivesBox();
		 archivesBox.setBentFrameCode(vo.getBentFrameCode());
		 List<ArchivesBox> list = archivesBoxService.findArchivesBoxPage(archivesBox).getList();
		 if(list!=null && list.size()>0){
			 ArchivesBox a = (ArchivesBox)list.get(0);
			 //传入档案分类第一个字母
			 char fitBoxChar = vo.getDocumentType().charAt(0);
			 //档案盒档案分类第一个字母
			 char archivesBoxChar = a.getArchivesBoxType().charAt(0);

			 if(fitBoxChar == archivesBoxChar){
				 returnValue = true;
			 }
		 }
		 return returnValue;
	}
}
