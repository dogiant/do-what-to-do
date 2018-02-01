package com.dogiant.cms.exception;

public class ExcelHandleException extends RuntimeException {
	private static final long serialVersionUID = 7539170419458220518L;

	public ExcelHandleException() {
		super();
	}

	public ExcelHandleException(String message) {
		super(message);
	}

	public ExcelHandleException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelHandleException(Throwable cause) {
		super(cause);
	}
}
