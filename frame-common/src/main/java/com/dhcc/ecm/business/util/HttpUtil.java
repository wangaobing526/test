package com.dhcc.ecm.business.util;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dhcc.ecm.business.exception.EcmException;

public class HttpUtil<T> {
	
	private static Logger logger = Logger.getLogger(HttpUtil.class);
	/**
	 * 发送post请求，适用于多个消息头利用map传入
	 * 
	 * @param hearderMap
	 *            消息头数组：KEY:消息头名称;VALUE:消息头内容
	 * @param vo
	 *            请求数据
	 * @param c
	 *            获取数据类型
	 * @param url
	 *            http地址
	 * @return 远程服务返回内容
	 */

	public   T sendHttpPost(Map<String, String> hearderMap, T vo, Class c, String url)throws EcmException {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate template = new RestTemplate();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		// 循环设置传入的hearders数组
		for (Map.Entry<String, String> entry : hearderMap.entrySet()) {
			String hearderKey = entry.getKey().toString();
			String hearderValue = entry.getValue().toString();
			logger.debug("key=" + hearderKey + " value=" + hearderValue);
			if (null != hearderKey && !"".equals(hearderKey) && null != hearderValue && !"".equals(hearderValue)) {
				headers.set(hearderKey, hearderValue);
			}
		}
		HttpEntity<T> entity = new HttpEntity<T>(vo, headers);
		HttpEntity<String> res = template.exchange(url, HttpMethod.POST, entity, String.class);
		ResponseEntity<T> responseEntity = template.postForEntity(url, entity, c);
		T t = responseEntity.getBody();
		return t;
	}
}
