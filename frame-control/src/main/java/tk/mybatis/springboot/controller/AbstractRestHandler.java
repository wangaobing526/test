package tk.mybatis.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dhcc.ecm.business.exception.BizResponse;
import com.dhcc.ecm.business.exception.EcmException;
import com.dhcc.ecm.business.util.BusinessProperties;

import tk.mybatis.springboot.controller.archivesbox.model.DocumentBean;

/**
 * Created by Administrator on 2016/7/1.
 * 
 * @param <T>
 * 
 */
public class AbstractRestHandler<T> {
	
	private static Logger logger = Logger.getLogger(AbstractRestHandler.class);
	/**
	 * 获取返回错误信息对象
	 * 
	 * @param ex
	 *            需要抛出的异常类
	 * @return
	 */
	public BizResponse getBizResponse(EcmException ex) {
		BizResponse result = new BizResponse();
		result.setErrorCode(ex.getCode());
		result.setErrorDescription(ex.getErrorDescrption());
		return result;
	}
	
	@Autowired
	private BusinessProperties properties;
	
//	@RequestMapping(path = "/getUserName", method = RequestMethod.GET)
//	public  String getUserName() {
//		String userName = SecurityUtils.getCurrentUserLogin();
//        return userName;
//    }
	
	/**
	 * 发送post请求,适用于只传入一个消息头
	 * 
	 * @param hearderKey
	 *            http hearder名称
	 * @param hearderValue
	 *            http hearder内容
	 * @param vo
	 *            请求数据
	 * @param c
	 *            获取数据类型
	 * @param url
	 *            http地址
	 * @return 远程服务返回内容
	 */

	public T sendHttpPost(String hearderKey, String hearderValue, T vo, Class c, String url) {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate template = new RestTemplate();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		if (null != hearderKey && !"".equals(hearderKey) && null != hearderValue && !"".equals(hearderValue)) {
			headers.set(hearderKey, hearderValue);
		}
		HttpEntity<T> entity = new HttpEntity<T>(vo, headers);
		HttpEntity<String> res = template.exchange(url, HttpMethod.POST, entity, String.class);
		ResponseEntity<T> responseEntity = template.postForEntity(url, entity, c);
		T t = responseEntity.getBody();
		return t;
	}

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

	public T sendHttpPost(Map<String, String> hearderMap, T vo, Class c, String url) {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate template = new RestTemplate();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		// 循环设置传入的hearders数组
		for (Map.Entry<String, String> entry : hearderMap.entrySet()) {
			String hearderKey = entry.getKey().toString();
			String hearderValue = entry.getValue().toString();
			System.out.println("key=" + hearderKey + " value=" + hearderValue);
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

	/**
	 * 
	 * @param hearderMap
	 *            消息头数组：KEY:消息头名称;VALUE:消息头内容
	 * @param vo
	 *            入参为VO
	 * @param url
	 *            调用URL
	 * @return 远程返回内容字符串
	 */
	public String sendHttpPostStr(Map<String, String> hearderMap, T vo, String url) {
		String returnValue = "";
		HttpHeaders headers = new HttpHeaders();
		RestTemplate template = new RestTemplate();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		// 循环设置传入的hearders数组
		for (Map.Entry<String, String> entry : hearderMap.entrySet()) {
			String hearderKey = entry.getKey().toString();
			String hearderValue = entry.getValue().toString();
			System.out.println("key=" + hearderKey + " value=" + hearderValue);
			logger.debug("key=" + hearderKey + " value=" + hearderValue);
			if (null != hearderKey && !"".equals(hearderKey) && null != hearderValue && !"".equals(hearderValue)) {
				headers.set(hearderKey, hearderValue);
			}
		}
		HttpEntity<T> entity = new HttpEntity<T>(vo, headers);
		HttpEntity<String> res = template.exchange(url, HttpMethod.POST, entity, String.class);
		ResponseEntity<String> responseEntity = template.postForEntity(url, entity, String.class);
		returnValue = responseEntity.getBody();
		return returnValue;
	}

	/**
	 * 
	 * @param hearderMap 消息头数组：KEY:消息头名称;VALUE:消息头内容
	 * @param bodyParams 入参为字符串
	 * @param url 调用URL
	 * @return 远程返回内容字符串
	 */
	public String sendHttpPost(Map<String, String> hearderMap, String bodyParams, String url) {
		String s = "";

		HttpHeaders headers = new HttpHeaders();
		RestTemplate template = new RestTemplate();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");

		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		// 循环设置传入的hearders数组
		for (Map.Entry<String, String> entry : hearderMap.entrySet()) {
			String hearderKey = entry.getKey().toString();
			String hearderValue = entry.getValue().toString();
			System.out.println("key=" + hearderKey + " value=" + hearderValue);
			logger.debug("key=" + hearderKey + " value=" + hearderValue);
			if (null != hearderKey && !"".equals(hearderKey) && null != hearderValue && !"".equals(hearderValue)) {
				headers.set(hearderKey, hearderValue);
			}
		}
		// JSONObject jsonObject = JSONObject.fromObject(bodyParams);

		HttpEntity<String> entity = new HttpEntity<String>(bodyParams, headers);

		HttpEntity<String> res = template.exchange(url, HttpMethod.POST, entity, String.class);
		ResponseEntity<String> responseEntity = template.postForEntity(url, entity, String.class);
		s = responseEntity.getBody();

		// s= template.postForObject(url, responseEntity, String.class);

		return s;
	}

	/**
	 * 历史的可不用
	 * @param hearderKey
	 * @param hearderValue
	 * @param bodyParams
	 * @param url
	 * @return
	 */
	public String sendHttpPost(String hearderKey, String hearderValue, String bodyParams, String url) {
		String s = "";

		HttpHeaders headers = new HttpHeaders();
		RestTemplate template = new RestTemplate();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");

		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		if (null != hearderKey && !"".equals(hearderKey) && null != hearderValue && !"".equals(hearderValue)) {
			headers.set(hearderKey, hearderValue);
		}

		HttpEntity<String> entity = new HttpEntity<String>(bodyParams, headers);

		HttpEntity<String> res = template.exchange(url, HttpMethod.POST, entity, String.class);
		ResponseEntity<String> responseEntity = template.postForEntity(url, entity, String.class);
		s = responseEntity.getBody();

		return s;

	}
	
//	public  String getUserId() {
//		return properties.getUserId();
//    }
	
	public BizResponse getDictionaryByName(HttpServletRequest request,/*String dName,*/ Map<String,String[]> dNamesmap) {

        try {
			long startTime=System.currentTimeMillis();
            String url=properties.getMasterServiceUrl() + properties.getDictionaryBynameUrl();
            System.out.println(url);
            Map<String, String> hearderMap = new HashMap<String, String>();
            hearderMap.put("userid", properties.getUserId());
            hearderMap.put("systemId", properties.getSystemId());
           // hearderMap.put("dName",dName );
            BizResponse br = (BizResponse) this.sendHttpPost(hearderMap, (T) dNamesmap, BizResponse.class, url);
			long endtTime=System.currentTimeMillis();
			System.out.println("调用"+url+"时间为:"+(endtTime-startTime)+"毫秒");
            return br;
        } catch (Exception e) {
            e.printStackTrace();
            return new BizResponse(e.getMessage());
        }
    }
	
	
	public BizResponse navDomain(HttpServletRequest request, Map<String,String[]> actionMap){
		try{
			long startTime=System.currentTimeMillis();
			String url=properties.getMasterServiceUrl() + properties.getNavDomainUrl();
	        System.out.println(url);
	        Map<String, String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("userid", properties.getUserId());
	        hearderMap.put("systemId", properties.getSystemId());
	        BizResponse br = (BizResponse) this.sendHttpPost(hearderMap, (T) actionMap, BizResponse.class, url);
	        long endtTime=System.currentTimeMillis();
	        return br;
		} catch (Exception e) {
            e.printStackTrace();
            return new BizResponse(e.getMessage());
        }
		
	}
	
	
	public BizResponse updatePhy(HttpServletRequest request, DocumentBean documentBean){
		try{
			long startTime=System.currentTimeMillis();
			String url=properties.getMasterServiceUrl() + properties.getUpdatePhyUrl();
	        System.out.println(url);
	        Map<String, String> hearderMap = new HashMap<String, String>();
	        hearderMap.put("userid", properties.getUserId());
	        hearderMap.put("systemId", properties.getSystemId());
	        BizResponse br = (BizResponse) this.sendHttpPost(hearderMap, (T) documentBean, BizResponse.class, url);
	        long endtTime=System.currentTimeMillis();
	        return br;
		} catch (Exception e) {
            e.printStackTrace();
            return new BizResponse(e.getMessage());
        }
		
	}

}
