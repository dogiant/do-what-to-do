package com.dogiant.cms.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CommException extends Exception {

	private static final long serialVersionUID = -2507016588649356269L;
	
	protected Throwable rootcause = null;
	private List<CommException> exceptions = new ArrayList<CommException>();
	private String code = null;
	private String[] messageArgs = null;
	private String message = null;
	
	public CommException(){
		super();
	}
	
	public CommException( Throwable rootCause ){
		super(rootCause);
		this.rootcause = rootCause;
	}
	
	public CommException(String message, Throwable rootCause) {
		super(message, rootCause);
		this.message = message;
		this.rootcause = rootCause;
	}
	
	public CommException( String messageKey ){
		this(messageKey, "");
	}
	
	public CommException( String code, String[] messageArgs ){
		this.code = code;
		this.messageArgs = messageArgs;
	}
	
	public CommException( String code, String message ){
		super(message);
		this.code = code;
		this.message = message;
	}
	
	public CommException( ExceptionInfo exInfo){
		super(exInfo.getMessage());
		this.code = exInfo.getCode();
		this.message = exInfo.getMessage();
	}
	
	public CommException( ExceptionInfo exInfo, String message ){
		super(message);
		this.code = exInfo.getCode();
		this.message = message;
	}
	
	public CommException( String code, String[] messageArgs, String message ){
		super(message);
		this.code = code;
		this.messageArgs = messageArgs;
		this.message = message;
	}
	
	public List<CommException> getExceptions(){
		return exceptions;
	}
	
	public void addException( CommException ex ){
		exceptions.add(ex);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessageArgs( String [] args ){
		this.messageArgs = args;
	}
	
	public String[] getMessageArgs(){
		return messageArgs;
	}

	public void setRootCause( Throwable anException ){
		this.rootcause = anException;
	}
	
	public Throwable getRootCause(){
		return rootcause;
	}
	
	public void printStackTrace(){
		printStackTrace(System.err);
	}
	
	public void printStackTrace( PrintStream outStream ){
		printStackTrace(new PrintWriter(outStream));
	}
	
	public void printStackTrace( PrintWriter writer ){
		super.printStackTrace( writer );
		if( rootcause!=null ){
			rootcause.printStackTrace( writer );
		}
		writer.flush();
	}
	
	
}
