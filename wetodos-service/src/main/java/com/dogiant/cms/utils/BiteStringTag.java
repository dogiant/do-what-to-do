package com.dogiant.cms.utils;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class BiteStringTag extends SimpleTagSupport {

	private String source;
	
	private int len;
	
	private String symbol;
	
	public void setSource(String source) {
		this.source = source;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSource() {
		return source;
	}

	public int getLen() {
		return len;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public void doTag() throws JspException, IOException {
		//JspFragment fragment = this.getJspBody();
		super.doTag();
	}


	

}
