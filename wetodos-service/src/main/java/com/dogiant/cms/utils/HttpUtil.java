/**
 *  Copyright(C) 2013 Software(Shanghai) Co., Ltd.
 *  All Right Reserved.
 */
package com.dogiant.cms.utils;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Http request
 *
 * @version 2014-4-21
 * @author SUNTEC
 * @since JDK1.6
 *
 */
public class HttpUtil {

	private static final Log log = LogFactory.getLog(HttpUtil.class);

	/**
	 * Http request ：GET
	 *
	 * @param url
	 * @param params
	 * @param heads
	 * @return String of request result
	 */
	public static String get(String url, List params, Map heads) throws Exception {
		return get(url, params, heads, 60*1000);
	}

	/**
	 * Http request ：GET
	 *
	 * @param url
	 * @param params
	 * @param heads
	 * @param timeOut (Millisecond)
	 * @return String of request result
	 */
	public static String get(String url, List params, Map<String,String> heads, Integer timeOut)
			throws Exception {
		String reStr = "";

		HttpClient httpClient = HttpClients.createDefault();
		URIBuilder uri = new URIBuilder();
		uri.setPath(url);
		// パラメータ設定
		uri.addParameters(params);

		try {
			HttpGet httpget = new HttpGet(uri.build());
			if (heads != null) {
				// 一般要求プロパティを設定します
				for (Entry<String,String> e : heads.entrySet()) {
					httpget.addHeader(e.getKey(), e.getValue());
				}
			}

			// set Timeout
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut)
					.setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
			httpget.setConfig(requestConfig);
			// get responce
			HttpResponse responce = httpClient.execute(httpget);
			// get http status code
			int resStatu = responce.getStatusLine().getStatusCode();
			if (resStatu == HttpStatus.SC_OK) {
				// get result data
				HttpEntity entity = responce.getEntity();
				reStr = EntityUtils.toString(entity);
			}
			else {
				log.error(uri.toString() + ": resStatu is " + resStatu);
			}
		}

		catch (ConnectionPoolTimeoutException e) {
			log.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);
		}
		catch (ConnectTimeoutException e) {
			log.error("http get throw ConnectTimeoutException", e);
		}
		catch (SocketTimeoutException e) {
			log.error("http get throw SocketTimeoutException", e);
		}
		catch (Exception e) {
			log.error("http get throw Exception", e);
		}
		return reStr;
	}

	/**
	 * Http request ：Post
	 *
	 * @param url
	 * @param params
	 * @param heads
	 * @return String of request result
	 */
	public static String post(String url, List params, Map heads) throws Exception {
		return post(url, params, heads, 60*1000);
	}

	/**
	 * Http request ：Post
	 *
	 * @param url
	 * @param params
	 * @param heads
	 * @param timeOut (Millisecond)
	 * @return String of request result
	 */
	public static String post(String url, List params, Map<String,String> heads, Integer timeOut)
			throws Exception {
		// default time out
		String reStr = "";
		try {
			HttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);

			httppost.setEntity(new UrlEncodedFormEntity(params));
			if (heads != null) {
				// 一般要求プロパティを設定します
				for (Entry<String, String> e : heads.entrySet()) {
					httppost.addHeader(e.getKey(), e.getValue());
				}
			}

			// set Timeout
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut)
					.setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
			httppost.setConfig(requestConfig);
			// get responce
			HttpResponse responce = httpClient.execute(httppost);
			// get http status code
			int resStatu = responce.getStatusLine().getStatusCode();
			if (resStatu == HttpStatus.SC_OK) {
				// get result data
				HttpEntity entity = responce.getEntity();
				reStr = EntityUtils.toString(entity);
			}
			else {
				log.error(url + ": resStatu is " + resStatu);
			}
		}
		catch (ConnectionPoolTimeoutException e) {
			log.error("http post throw ConnectionPoolTimeoutException", e);
		}
		catch (ConnectTimeoutException e) {
			log.error("http post throw ConnectTimeoutException", e);
		}
		catch (SocketTimeoutException e) {
			log.error("http post throw SocketTimeoutException", e);
		}
		catch (Exception e) {
			log.error("http post throw Exception", e);
		}
		return reStr;
	}
}