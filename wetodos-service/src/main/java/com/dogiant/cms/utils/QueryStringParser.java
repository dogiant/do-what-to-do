package com.dogiant.cms.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author dogiant
 *
 */
public class QueryStringParser {

	public static TreeMap<String, String> queryStringParser(String queryString,
			String encode) {
		TreeMap<String, String> paramMap = new TreeMap<String, String>();
		if (StringUtils.isEmpty(queryString)) {
			return paramMap;
		}
		StringTokenizer st = new StringTokenizer(queryString, "&");
		while (st.hasMoreTokens()) {
			String pairs = st.nextToken();
			String key = pairs.substring(0, pairs.indexOf('='));
			String value = pairs.substring(pairs.indexOf('=') + 1);
			try {
				key = URLDecoder.decode(key, encode);
				value = URLDecoder.decode(value, encode);
			} catch (UnsupportedEncodingException e) {
				throw new java.lang.RuntimeException("URLDecoder error !");
			}
			paramMap.put(key, value);
		}
		return paramMap;
	}

}
