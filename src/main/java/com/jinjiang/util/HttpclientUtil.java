package com.jinjiang.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 * http请求工具类
 * @author xiangchao
 */
public class HttpclientUtil {

	private static PoolingHttpClientConnectionManager cm;
	private static String EMPTY_STR = "";
	private static String UTF_8 = "UTF-8";

	private static void init() {
		if (cm == null) {
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(50);// 整个连接池最大连接数
			cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
		}
	}

	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 */
	private static CloseableHttpClient getHttpClient() {
		init();
		return HttpClients.custom().setConnectionManager(cm).build();
	}

	/**
	 * 处理get请求
	 * @param url
	 * @return
	 */
	public static String httpGetRequest(String url) {
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}

	/**
	 * 处理post请求
	 * @param url
	 * @param param
	 * @return
	 */
	public static String httpPostRequest(String url, Map<String, Object> param) {
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(param);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
			return getResult(httpPost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "请求失败";
		}
	}

	private static ArrayList<NameValuePair> covertParams2NVPS(
			Map<String, Object> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		if(params != null){
			for (Map.Entry<String, Object> param : params.entrySet()) {
				pairs.add(new BasicNameValuePair(param.getKey(), String
						.valueOf(param.getValue())));
			}
		}
		return pairs;
	}
	
	/**
	 * 处理Http请求
	 * 
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request) {
		CloseableHttpClient httpClient = getHttpClient();
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity);
				response.close();
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		return EMPTY_STR;
	}
	
	/**
	 * 请求接口参数json/application类型
	 * @param url
	 * @param json
	 * @return
	 */
	public static String postJsonParams(String url, String json) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost(url);
			StringEntity se = new StringEntity(json);
			httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
			httpPost.setEntity(se);
			HttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity, "UTF-8");
			return content;
		} catch (Exception e) {
			if(e.getMessage().indexOf("Connection refused: connect") > -1){
				return "网络连接超时!";
			}
			return "error";
		}
	}
	
}
