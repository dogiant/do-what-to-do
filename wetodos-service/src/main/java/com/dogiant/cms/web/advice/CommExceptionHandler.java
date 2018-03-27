package com.dogiant.cms.web.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dogiant.cms.domain.dto.HttpResult;
import com.dogiant.cms.exception.CommException;

@RestControllerAdvice
public class CommExceptionHandler {

	@ExceptionHandler(value = CommException.class)
	@ResponseBody
	public HttpResult<Void> jsonErrorHandler(HttpServletRequest req, CommException e) throws Exception {
		HttpResult<Void> result = HttpResult.newErrorResult(e.getCode(), e.getMessage());
		return result;
	}
}
