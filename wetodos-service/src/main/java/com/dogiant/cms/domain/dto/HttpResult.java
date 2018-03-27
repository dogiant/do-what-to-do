package com.dogiant.cms.domain.dto;

import java.io.Serializable;

/**
 * HTTP结果封装实体类
 */
public class HttpResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;
	private String msg;
	private T result;
	private boolean success = true;

	private static class SingletonHolder {
		private static HttpResult<Void> instance = new HttpResult<Void>();
	}

	private HttpResult() {
		this.code = HttpResultEnum.Success.code;
		this.msg = HttpResultEnum.Success.msg;
	}

	private HttpResult(T result) {
		this();
		this.result = result;
	}
	
	private HttpResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
		this.success = HttpResultEnum.Success.code.equals(code);
	}

	private HttpResult(String code, String msg, T result) {
		this.code = code;
		this.msg = msg;
		this.result = result;
		this.success = HttpResultEnum.Success.code.equals(code);
	}

	public static HttpResult<Void> successResult() {
		return SingletonHolder.instance;
	}

	public static <R> HttpResult<R> newSuccessResult(R result) {
		return new HttpResult<R>(result);
	}

	public static HttpResult<Void> newErrorResult(int code, String msg) {
		return new HttpResult<Void>(String.valueOf(code), msg);
	}

	public static HttpResult<Void> newErrorResult(String code, String msg) {
		return new HttpResult<Void>(code, msg);
	}

	public static <R> HttpResult<R> newResult(String code, String msg, R result) {
		return new HttpResult<R>(code, msg, result);
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
	public T getResult() {
		if (result != null) {
			return result;
		}
		if (result instanceof String) {
			return (T) "";
		}
		return null;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	@Override
	public String toString() {
		return "HttpResult [code=" + code + ", msg=" + msg + ", result=" + result + ", success=" + success + "]";
	}

}
