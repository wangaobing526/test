package com.dhcc.ecm.business.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @ClassName BusinessProperties
 * @Description 配置文件模型类
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2016-07-08
 */
@ConfigurationProperties(prefix = "bus",locations = "classpath:/business.properties")  
public class BusinessProperties {
	private String bentFrameFlag;
	private String masterServiceUrl;
	private String systemId;
	private String filePath;
	private String propertiesByRequestParamUrl;
	private String userId;
	private String dictionaryBynameUrl;//数据字典查询url
	private String navDomainUrl;//档案一级分类url
	private String updatePhyUrl;//修改物理位置url
	
	public String getUpdatePhyUrl() {
		return updatePhyUrl;
	}
	public void setUpdatePhyUrl(String updatePhyUrl) {
		this.updatePhyUrl = updatePhyUrl;
	}
	public String getNavDomainUrl() {
		return navDomainUrl;
	}
	public void setNavDomainUrl(String navDomainUrl) {
		this.navDomainUrl = navDomainUrl;
	}
	public String getDictionaryBynameUrl() {
		return dictionaryBynameUrl;
	}
	public void setDictionaryBynameUrl(String dictionaryBynameUrl) {
		this.dictionaryBynameUrl = dictionaryBynameUrl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPropertiesByRequestParamUrl() {
		return propertiesByRequestParamUrl;
	}
	public void setPropertiesByRequestParamUrl(String propertiesByRequestParamUrl) {
		this.propertiesByRequestParamUrl = propertiesByRequestParamUrl;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getBentFrameFlag() {
		return bentFrameFlag;
	}
	public void setBentFrameFlag(String bentFrameFlag) {
		this.bentFrameFlag = bentFrameFlag;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getMasterServiceUrl() {
		return masterServiceUrl;
	}
	public void setMasterServiceUrl(String masterServiceUrl) {
		this.masterServiceUrl = masterServiceUrl;
	}
}
