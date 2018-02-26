<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="messages.web.name"></spring:message></title>
        <link type="text/css" rel="stylesheet" href="assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/css/login.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="span4 offset4">
                    <div class="signin">
                        <div class="tab-content">
                            <div id="login" class="tab-pane active">
                                <form accept-charset="utf-8" method="post" id="loginForm">
                                    <div class="control-group">
                                        <div class="controls">
                                            <div class="input-prepend">
                                                <span class="add-on"><i class="icon-user"></i></span>
                                            </div>
                                            	<input type="text" name="userName" placeholder="<spring:message code="placeholder.userName"></spring:message>" >
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <div class="input-prepend">
                                                <span class="add-on"><i class="icon-lock"></i></span>
                                            </div>
                                            	<input type="password" name="password" placeholder="<spring:message code="placeholder.password"></spring:message>">
                                        </div>
                                    </div>
                                    <button class="btn btn-inverse btn-block" type="submit"><spring:message code="messages.web.login"></spring:message></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="assets/js/vendor/jquery-1.9.1.min.js"></script>
        <script>window.jQuery || document.write('<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"><\/script>')</script>
        <script type="text/javascript" src="assets/js/vendor/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/js/lib/jquery.validate.min.js"></script>
        <script type="text/javascript" src="assets/js/lib/jquery.form.js"></script>
        <script type="text/javascript">
        $(function () {
    		$("#loginForm").validate({
    			onkeyup: false,
    			rules: {
    				"userName": {
    					required: true
    				},
    				"password": {
    					required: true
    				}
    			},
    			messages: {
    				"userName": {
    					required: "请输入用户名"
    				},
    				"password": {
    					required: "请输入密码"
    				}
    			},
    			debug: true,
    			errorClass: 'help-block',
    	        errorElement: 'span',
    	        highlight: function(element, errorClass, validClass) {
    	            $(element).parents('.control-group').removeClass('success').addClass('error');
    	        },
    	        unhighlight: function(element, errorClass, validClass) {
    	            $(element).parents('.control-group').removeClass('error').addClass('success');
    	        },
    			success: function(label) {
    				label.attr("class","");
    			}, 
    	       	submitHandler: function() {  
    	       		loginSubmit();
    	        	return false;  
    	        }
    		});

        });
        //{"type":"0","code":"4","msg":"No authorization exception","url":"","data":null,"success":false}
        var loginCallBack = function(data) {
        	if(data.success){
        		if(data.url && data.url!=""){
        			window.location.href=data.url;
        		}else{
        			window.location.href="main";
        		}
        	}else{
        		alert("登录失败！提示信息：" + data.msg);
        	}
        };
		var loginSubmit = function (){
		    var queryString = $("#loginForm").fieldSerialize();
		    var options = {  
		        type : "post",  
		        dataType : "json",  
		        url : "api/login",  
		        data : queryString, 
		        cache : "false", 
		        success : loginCallBack
		    };  
		    $("#loginForm").ajaxSubmit(options);  
		};
    	</script>
    </body>
</html>