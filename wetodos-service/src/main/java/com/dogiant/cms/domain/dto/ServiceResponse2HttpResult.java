package com.dogiant.cms.domain.dto;

/**
 * 转换ServiceResponse to HttpResult
 */
public class ServiceResponse2HttpResult {
	public static HttpResult<?> transfer(ServiceResponse<?> response) {
		if (response.isSuccess()) {
			return HttpResult.newSuccessResult(response.getData());
		} else {
			return HttpResult.newResult(response.getCode(), response.getMsg(), response.getData(), response.getPrompt());
		}
	}

}
