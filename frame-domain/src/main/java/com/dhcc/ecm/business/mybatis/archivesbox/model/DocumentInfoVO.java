package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;
/**
 * @ClassName DocumentInfoVO
 * @Description 文档信息VO，远程获得文档信息赋值到此VO中
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-20
 */
public class DocumentInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	//文件中文题名
	private String RecordChineseTitle;
	//案卷分类
	private String FileClassificationNum;
	//档号
	private String ArchiveCode;
	//聚合层次
	private String AggregationLevel;
	
	public String getRecordChineseTitle() {
		return RecordChineseTitle;
	}
	public void setRecordChineseTitle(String recordChineseTitle) {
		RecordChineseTitle = recordChineseTitle;
	}
	public String getAggregationLevel() {
		return AggregationLevel;
	}
	public void setAggregationLevel(String aggregationLevel) {
		AggregationLevel = aggregationLevel;
	}
	public String getFileClassificationNum() {
		return FileClassificationNum;
	}
	public void setFileClassificationNum(String fileClassificationNum) {
		FileClassificationNum = fileClassificationNum;
	}
	public String getArchiveCode() {
		return ArchiveCode;
	}
	public void setArchiveCode(String archiveCode) {
		ArchiveCode = archiveCode;
	}
	@Override
	public String toString() {
		return "DocumentInfoVO [RecordChineseTitle=" + RecordChineseTitle + ", FileClassificationNum="
				+ FileClassificationNum + ", ArchiveCode=" + ArchiveCode + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ArchiveCode == null) ? 0 : ArchiveCode.hashCode());
		result = prime * result + ((FileClassificationNum == null) ? 0 : FileClassificationNum.hashCode());
		result = prime * result + ((RecordChineseTitle == null) ? 0 : RecordChineseTitle.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentInfoVO other = (DocumentInfoVO) obj;
		if (ArchiveCode == null) {
			if (other.ArchiveCode != null)
				return false;
		} else if (!ArchiveCode.equals(other.ArchiveCode))
			return false;
		if (FileClassificationNum == null) {
			if (other.FileClassificationNum != null)
				return false;
		} else if (!FileClassificationNum.equals(other.FileClassificationNum))
			return false;
		if (RecordChineseTitle == null) {
			if (other.RecordChineseTitle != null)
				return false;
		} else if (!RecordChineseTitle.equals(other.RecordChineseTitle))
			return false;
		return true;
	}
}
