package com.dogiant.cms.exception;

public enum ServiceExInfo implements ExceptionInfo {
	
	SUCCEED("1","Successful results"),
	NOT_LOGIN_EXCEPTION("2","Not logged in exception"),
	USER_EXISTS("3","User already exists"),
	NO_AUTH_EXCEPTION("4","No authorization exception"),
	PARAMETER_ERROR_EXCEPTION("5","Parameter error exception"),
	NO_RESULT_ERROR_EXCEPTION("6","No Result error exception"),
	
	
	SYSTEM_ERROR("-1","System error"),
	NO_APPID_APPSECRET("-2","no registration appId or appScret"),
	GET_ACCESSTOKEN_ERROR("-3","get AccessToken return error"),
	USER_PASS_ERROR("-4", "username invalid or password error");
	

	private String code;
	private String message;

	private ServiceExInfo(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}


}
