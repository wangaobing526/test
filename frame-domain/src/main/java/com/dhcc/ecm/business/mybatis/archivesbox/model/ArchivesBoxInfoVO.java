package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;
/**
 * 档案盒信息表
 * @author starsand
 *
 */
public class ArchivesBoxInfoVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	//初始化柜节信息
    private InitCabinetNodeVO initCabinetNodeVO;
    //常用排架
    private BentFrameOftenUsedVO bentFrameOftenUsedVO;
    //档案盒信息
    private ArchivesBoxVO archivesBoxVO;
    
    public ArchivesBoxVO getArchivesBoxVO() {
		return archivesBoxVO;
	}
	public void setArchivesBoxVO(ArchivesBoxVO archivesBoxVO) {
		this.archivesBoxVO = archivesBoxVO;
	}
	public InitCabinetNodeVO getInitCabinetNodeVO() {
		return initCabinetNodeVO;
	}
	public void setInitCabinetNodeVO(InitCabinetNodeVO initCabinetNodeVO) {
		this.initCabinetNodeVO = initCabinetNodeVO;
	}
	public BentFrameOftenUsedVO getBentFrameOftenUsedVO() {
		return bentFrameOftenUsedVO;
	}
	public void setBentFrameOftenUsedVO(BentFrameOftenUsedVO bentFrameOftenUsedVO) {
		this.bentFrameOftenUsedVO = bentFrameOftenUsedVO;
	}
	
}