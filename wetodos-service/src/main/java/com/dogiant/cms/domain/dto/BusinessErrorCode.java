package com.dogiant.cms.domain.dto;

/**
 * @description : 错误码枚举类
 */
public enum BusinessErrorCode implements ResponseCode {
	PARAM_NULL("101", "参数[%s]不可为空"), //
	RETURN_RESULT_ERROR("102", "返回结果不正确"), //
	PARAM_FORMAT_ERROR("103", "参数[%s]格式错误"),
	PARAM_RANGE_ERROR("104", "参数[%s]字段长度超出边界值[%s]"),

	RPC_CALL_FAIL("801", "RPC调用失败，CODE不成功"),//
	RPC_CALL_DATA_NULL("802", "RPC调用失败，结果为空"); //
	
	public final String code;
	public final String msg;

	private BusinessErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
