package com.dogiant.cms.domain.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @param <T>
 */
public class ServiceResponse<T> implements Serializable {

	/*  */
	private static final long serialVersionUID = 2425960131861679422L;
	private String code = BaseResponseCode.SUCCESS.getCode();
	private String msg = BaseResponseCode.SUCCESS.getMsg();
	private String prompt;
	private T data;

	public ServiceResponse(BaseResponseCode success) {

	}

	public ServiceResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public static <K> ServiceResponse<K> successResponse() {
		return new ServiceResponse<K>(BaseResponseCode.SUCCESS);
	}

	public static <K> ServiceResponse<K> failureResponse() {
		return new ServiceResponse<K>(BaseResponseCode.FAILURE);
	}

	public ServiceResponse<T> setResponseCode(ResponseCode ResponseCode) {
		this.code = ResponseCode.getCode();
		this.msg = ResponseCode.getMsg();
		return this;
	}

	public boolean isSuccess() {
		return BaseResponseCode.SUCCESS.getCode().equals(code);
	}

	public String getCode() {
		return code;
	}

	public  ServiceResponse<T>  setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public  ServiceResponse<T>  setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public ServiceResponse<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
