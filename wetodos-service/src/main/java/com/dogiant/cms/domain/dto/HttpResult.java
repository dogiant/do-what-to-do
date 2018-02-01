package com.dogiant.cms.domain.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * HTTP结果封装实体类
 */
public class HttpResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	// 区分不同的服务，默认"0"
	private String type = "0";
	private String code;
	private String msg;
	private String url = null;
	private T data;
	private boolean success = true;

	private static class SingletonHolder {
		private static HttpResult<Void> instance = new HttpResult<Void>();
	}

	private HttpResult() {
		this.code = HttpResultEnum.Success.code;
		this.msg = HttpResultEnum.Success.msg;
	}

	private HttpResult(T data) {
		this();
		this.data = data;
	}
	
	private HttpResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
		this.success = HttpResultEnum.Success.code.equals(code);
	}

	private HttpResult(String code, String msg, String url) {
		this.code = code;
		this.msg = msg;
		this.url = url;
		this.success = HttpResultEnum.Success.code.equals(code);
	}

	private HttpResult(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.success = HttpResultEnum.Success.code.equals(code);
	}

	private HttpResult(String code, String msg, T data, String url) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.url = url;
		this.success = HttpResultEnum.Success.code.equals(code);
	}

	private HttpResult(String type, String code, String msg, T data, String url) {
		this.type = type;
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.url = url;
		this.success = HttpResultEnum.Success.code.equals(code);
	}

	public static HttpResult<Void> successResult() {
		return SingletonHolder.instance;
	}

	public static <R> HttpResult<R> newSuccessResult(R result) {
		return new HttpResult<R>(result);
	}

	public static HttpResult<Void> newErrorResult(int code, String msg) {
		return new HttpResult<Void>(String.valueOf(code), msg, "");
	}

	public static HttpResult<Void> newErrorResult(String type, int code, String msg) {
		return new HttpResult<Void>(type, String.valueOf(code), msg, null, "");
	}

	public static HttpResult<Void> newErrorResult(String code, String msg) {
		return new HttpResult<Void>(code, msg, "");
	}

	public static HttpResult<Void> newErrorResult(String code, String msg, String url) {
		return new HttpResult<Void>(code, msg, url);
	}

	public static <R> HttpResult<R> newResult(String code, String msg) {
		return new HttpResult<R>(code, msg, null, null);
	}
	
	public static <R> HttpResult<R> newResult(String code, String msg, R data, String url) {
		return new HttpResult<R>(code, msg, data, url);
	}

	public static <R> HttpResult<R> newResult(String type, String code, String msg, R data, String url) {
		return new HttpResult<R>(type, code, msg, data, url);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@SuppressWarnings("unchecked")
	public T getData() {
		if (data != null) {
			return data;
		}
		if (data instanceof String) {
			return (T) "";
		}
		return null;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getUrl() {
		return StringUtils.trimToEmpty(url);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "HttpResult [type=" + type + ", code=" + code + ", msg=" + msg + ", data=" + data + ", url=" + url
				+ ", success=" + success + "]";
	}

}
