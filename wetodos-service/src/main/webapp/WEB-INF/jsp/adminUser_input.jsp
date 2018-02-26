<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!--[if lt IE 7]>       <html class="no-js lt-ie9 lt-ie8 lt-ie7">   <![endif]-->
<!--[if IE 7]>          <html class="no-js lt-ie9 lt-ie8">          <![endif]-->
<!--[if IE 8]>          <html class="no-js lt-ie9">                 <![endif]-->
<!--[if gt IE 8]><!-->  <html class="no-js">                        <!--<![endif]-->
    <head>
		<%@ include file="common/html_head.jsp" %>
    </head>
    <body>
        <!-- BEGIN WRAP -->
        <div id="wrap">
			<%@ include file="common/top_bar.jsp" %>
			<%@ include file="common/header.jsp" %>
			<%@ include file="common/left.jsp" %>

            <!-- BEGIN MAIN CONTENT -->
            <div id="content">
                <!-- .outer -->
                <div class="container-fluid outer">
                    <div class="row-fluid">
                        <!-- .inner -->
                        <div class="span12 inner">
                        
                            <!--BEGIN INPUT TEXT FIELDS-->
                            <div class="row-fluid">
                                <div class="span12">
                                    <div class="box dark">
                                        <header>
                                            <div class="icons"><i class="icon-edit"></i></div>
                                            <h5>管理员账号授权录入</h5>
                                            <!-- .toolbar -->
                                            <div class="toolbar">
                                                <ul class="nav">
                                                    <li><a href="admin_list">管理员账号管理</a></li>
                                                    <li class="dropdown">
                                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                                            <i class="icon-th-large"></i>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                            <li><a href="admin_list">管理员账号管理</a></li>
                                                            <li><a href="admin_input">管理员账号授权录入</a></li>
                                                        </ul>
                                                    </li>
                                                    <li>
                                                        <a class="accordion-toggle minimize-box" data-toggle="collapse" href="#div-1">
                                                            <i class="icon-chevron-up"></i>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <!-- /.toolbar -->
                                        </header>
                                        <div id="div-1" class="accordion-body collapse in body">
                                        	
                                        	<div class="form-horizontal">
                                                <form id="adminUserForm"  method="post" action="api/admin/add"> 
                                                <div class="control-group">
                                                	<input type="hidden" id="userId" name="userId" />
                                                    <label for="userName" class="control-label">用户名</label>
                                                    <div class="controls with-tooltip">
                                                        <input type="text" id="userName" name="userName" class="span6 input-tooltip" 
                                                        		data-original-title="请输入管理员用户名" data-placement="bottom" />
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label for="nickname" class="control-label">昵称</label>
                                                    <div class="controls with-tooltip">
                                                    	<input type="text" id="nickname" name="nickname" class="span6 input-tooltip" 
                                                               data-original-title="请输入管理员昵称" data-placement="top" />
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">初始密码</label>
                                                    <div class="controls">
                                                    	<input type="text" id="password"  name="password" class="span6 input-tooltip"  
                                                        data-original-title="请输入管理员初始密码" data-placement="bottom" />
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">电子邮箱</label>
                                                    <div class="controls">
                                                    	<input type="text" id="email" name="email" class="span6 input-tooltip"  
                                                        data-original-title="请输入管理员电子邮箱" data-placement="bottom" />
                                                    </div>
                                                </div>
                                                <div class="control-group">
													<label class="control-label">联系电话</label>
													<div class="controls">
                                                        <input type="text" id="phone"  name="phone" class="span6 input-tooltip"  
                                                        data-original-title="请输入管理员电子邮箱" data-placement="bottom" />
                                                    </div>
                                               </div>
                                               
												<div class="form-actions">
														<input type="submit" value="提交" class="btn btn-primary">
                                                </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--END TEXT INPUT FIELD-->

                        </div>
                        <!-- /.inner -->
                    </div>
                    <!-- /.row-fluid -->
                </div>
                <!-- /.outer -->
            </div>
            <!-- END CONTENT -->


            <!-- #push do not remove -->
            <div id="push"></div>
            <!-- /#push -->
        </div>
        <!-- END WRAP -->

        <div class="clearfix"></div>
		<%@ include file="common/footer.jsp" %>
		<%@ include file="common/footer_script.jsp" %>
        <script type="text/javascript">
		$().ready(function() {
			 $("#adminUserForm").validate({
			        rules: {
			        	"userName":  {
							required: true,
							userNameCheck: true,
							minlength: 3,
		    				maxlength: 20,
		    				remote:{
	    						url : "api/checkAdminUserNameExists",
	    						cache : false,
	    						type: "post",
	    				        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    				        data: {
	    				        	userName: function() {
	    				            	return $("#userName").val();
	    				          	}
	    				        }
		    				}
						},
						"nickname": {
							required: true,
	    					minlength: 2,
	    					nicknameMaxLength: true,
	    					nicknameCheck: true,
	    					remote: {
	    				        url: "api/checkAdminNicknameExists",
	    				        cache : false,
	    				        type: "post",
	    				        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    				        data: {
	    				        	nickname: function() {
	    				            	return $("#nickname").val();
	    				        	}
	    				    	}
	    					}
					    		
						},
						"password": {
					    	required: true,
					    	regexPassword: true,
	    					rangelength:[6,18]
						}
					},
					messages: {
						"userName":{
							required: "请输入用户名",
	    					userNameCheck: "用户名只允许包括小写英文字母、数字和下划线",
	    					minlength: "用户名至少3位字符",
	    					maxlength: "用户名不能超过20字符",
	    					remote: "用户名已存在"
						},
						"nickname": {
							required: "请输入昵称",
	    					minlength: "昵称至少2个字",
	    					maxlength: "昵称不能超过16个字符",
	    					nicknameCheck: "昵称只能包括中文字、英文字母、数字和下划线",
	    					remote: "昵称已使用"
						},
						"password": {
							required: "请输入密码",
	    					regexPassword:"6~18个字符(字母，数字，符号均可),区分大小写",
	    					rangelength:$.validator.format("密码长度必须在{0}-{1}个字符之间")
						}
					},
			        errorClass: 'help-block',
			        errorElement: 'span',
			        highlight: function(element, errorClass, validClass) {
			            $(element).parents('.control-group').removeClass('success').addClass('error');
			        },
			        unhighlight: function(element, errorClass, validClass) {
			            $(element).parents('.control-group').removeClass('error').addClass('success');
			        },
		            submitHandler: function (form) {
		            	var options = {
		            	   beforeSubmit:showStart,
		            	   success:showResponse,
		            	   dataType:  'json'
		            	};
			            
		            	$(form).ajaxSubmit(options);
	                  	return false;
		            }
				});
			 
			 	function showStart(){
				    return true;
				}

			 	function showResponse(data) { 
				 	if(data.success){
						bootbox.alert('管理员账号授权录入成功!' ,function(){
							message_box.show('将跳转到管理员账号列表页!','success');
							var  page_list = function(){
								location.href="admin_list";
							}
							window.setTimeout(page_list, 3000); 
				 		});
					}else{
						alert('管理员账号授权录入失败: '+ '\n\n 状态码: \n' + data.resultInfo.returnCode + '\n\n 提示信息: \n' + data.resultInfo.returnMsg +  '.'); 
					}
			 	}
		});
		// 用户名字符验证       
        jQuery.validator.addMethod("userNameCheck", function(value, element) {       
           return this.optional(element) || /^[a-z0-9_]+$/.test(value);       
        }, "用户名只允许包括小写英文字母、数字和下划线");
        jQuery.validator.addMethod("nicknameMaxLength", function(value, element) { 
        	var b = false;
            var len=0;   
            for(var i=0;i<value.length;i++){
            	var c = value.charCodeAt(i) > 255 ? 2 : 1;
                len += c;   
            }
            if(len<=16){
            	b = true;
            }
            return this.optional(element) || b;       
         }, "昵称不能超过16个字符");
     	// 昵称字符验证       
        jQuery.validator.addMethod("nicknameCheck", function(value, element) {
           return this.optional(element) || /^[\u3007\u3400-\u4DB5\u4E00-\u9FCB\uE815-\uE864.@\w]+$/gi.test(value);       
        }, "昵称只能包括中文字、英文字母、数字和下划线");
		jQuery.validator.addMethod("regexPassword", function(value, element) {  
            return this.optional(element) || /^[\~\`\!\@#$\%\^\&\*\(\)\-\+\=\{\}\[\]\:\;\"\'\<\>\,\.\?\/\w]{6,18}$/.test(value);  
        }, "6~18个字符(字母，数字，符号均可),区分大小写");

        </script>

    </body>
</html>