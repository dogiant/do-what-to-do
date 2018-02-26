<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                                            <h5>版块内容录入</h5>
                                            <!-- .toolbar -->
                                            <div class="toolbar">
                                                <ul class="nav">
                                                    <li><a href="section_list">版块内容管理</a></li>
                                                    <li class="dropdown">
                                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                                            <i class="icon-th-large"></i>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                            <li><a href="section_list">版块列表</a></li>
                                                            <li><a href="section_input">版块录入</a></li>
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
                                                <form id="sectionForm" action="api/section/add" method="post" > 
	                                                <div class="control-group" id="keyControl">
	                                                    <label for="key" class="control-label">版块位置代码(英文名)</label>
	                                                    <div class="controls">
	                                                    	<input type="text"  id="code" name="code" class="span6 input-tooltip"
	                                                               data-original-title="请输入版块位置代码(英文名)" data-placement="bottom"/>
	                                                    </div>
	                                                </div>
	                                               
	                                                <div class="control-group">
	                                                    <label for="name" class="control-label">版块位置名称</label>
	                                                    <div class="controls">
	                                                    	<input type="text"  id="name" name="name" class="span6 input-tooltip"
	                                                               data-original-title="请输入版块位置名称" data-placement="bottom"/>
	                                                    </div>
	                                                </div>
	
	                                                <div class="control-group">
															<label class="control-label">类型</label>
															<div class="controls controls-row">
																<input type="radio" name="type" value="0" checked="checked" class="uniform"/> 系统设定
																<input type="radio" name="type" value="1" class="uniform"/> 自由增设
															</div>
	                                                </div>
	                                                
	                                                <div class="control-group">
	                                                    <label for="sectionContent" class="control-label">内容</label>
	                                                    <div class="controls">
	                                                    	<div id="sectionContent" class="summernote"></div>
	                                                    	<input type="hidden" id="icontent" name="content"></input>
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
		<%@ include file="common/help_modal.jsp" %>

		<%@ include file="common/footer_script.jsp" %>
		 
        <script type="text/javascript">
		var STATIC_FILE_HOST = "${fileHost}";
		
		$().ready(function() {
			 $('#sectionContent').summernote({
			 	lang: 'zh-CN',
	            height: 200,
	            onImageUpload: function(files, editor, welEditable) {
	                sendFile(files[0], editor, welEditable);
	            },
	            onblur: function(e) {
	                var sHTML = $("#sectionContent").code();
	                $("#icontent").val(sHTML);
	            }
		    });
			//summernote图片上传
			function sendFile(file, editor, welEditable) {
				var formData  = new FormData();
				formData.append("uploads", file);
				formData.append("type","json");
				formData.append("channel","section");
	            $.ajax({
	                data: formData,
	                type: "post",
	                url: "/upload/api",
	                dataType : "json",
	                async: false,  
	                cache: false,
	                contentType: false,
	                processData: false,
	                success: function(data) {
		                if(data.success){
			               var url = STATIC_FILE_HOST + data.result[0];
			               editor.insertImage(welEditable, url);
			            }
	                },  
	                error: function (returndata) {  
	                    alert(returndata);  
	                }  
	            });
			}
			
			$("#sectionForm").validate({
			        rules: {
			        	"code":  {
							required: true,
							minlength: 3,
		    				maxlength: 36,
		    				remote:{
	    						url : "api/section/checkCodeExists",
	    						cache : false,
	    						type: "post",
	    				        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    				        data: {
	    				        	code: function() {
	    				            	return $("#code").val();
	    				          	}
	    				        }
		    				}
						},
						"name":  {
							required: true,
							minlength: 3,
		    				maxlength: 36,
		    				remote:{
	    						url : "api/section/checkNameExists",
	    						cache : false,
	    						type: "post",
	    				        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    				        data: {
	    				        	name: function() {
	    				            	return $("#name").val();
	    				          	}
	    				        }
		    				}
						}
					},
					messages: {
						"code":{
							required:"请输入版块位置代码(英文)",
							minlength: "请输入至少3位字符",
	    					maxlength: "长度不能超过36字符",
	    					remote: "此版块代码已曾存在"
						},
						"name":{
							required:"请输入版块位置名称",
							minlength: "请输入至少3位字符",
	    					maxlength: "长度不能超过36字符",
	    					remote: "此版块名称已曾存在"
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
		            	   //target: '#showmsg',
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
			 	// post-submit callback 
			 	function showResponse(data)  { 
				 	if(data.success){
						bootbox.alert('版块内容成功录入' ,function(){
							message_box.show('将跳转到版块列表管理界面!','success');
							var  page_list = function(){
								location.href="section_list";
							}
							window.setTimeout(page_list, 1000); 
				 		});
					}else{
						alert('发布失败: '+ '\n\n 状态码: \n' + data.resultInfo.returnCode + '\n\n 提示信息: \n' + data.resultInfo.returnMsg +  '.'); 
					}
			 	}
			});
        </script>

    </body>
</html>