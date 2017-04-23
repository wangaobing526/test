/**
 * Copyright 2016 aTool.org 
 */
package com.dhcc.ecm.business.mybatis.archivesbox.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;




/**
 * @ClassName MetaItems
 * @Description 元数据属性类
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2016-06-02 10:8:9
 */
public class MetaItems<K, V> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 元数据名称
	 */
	@ApiModelProperty(value="元数据名称",required=true)
	public K key;
	/**
	 * 元数据内容
	 */
	@ApiModelProperty(value="元数据内容",required=true)
	public V value;

	public MetaItems() {
	}

	public MetaItems(K key) {
		this(key, null);
	}

	public MetaItems(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}