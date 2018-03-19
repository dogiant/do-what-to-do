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
                                            <h5>每日一图管理</h5>
                                            <!-- .toolbar -->
                                            <div class="toolbar">
                                                <ul class="nav">
                                                    <li><a href="todos_banner_input">每日一图创建</a></li>
                                                    <li class="dropdown">
                                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                                            <i class="icon-th-large"></i>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                        	<li><a href="todos_banner_list">每日一图列表</a></li>
                                                            <li><a href="todos_banner_input">每日一图录入</a></li>
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
                                        	<div class="span12">
                                        	
  												<div class="span7">
  													<div style="display: none;">
	  												    <form id="uploadPicAjaxForm" action="/upload/api"  enctype="multipart/form-data"  method="post" >
	                                                    	<input id="uploadPicAjax" name="uploads" type="file" onchange="uploadPicAjaxSubmit(this);"/>
		                                                </form>
	                                                </div>
	  												<div class="form-horizontal">
	  												<form id="todosBannerForm" action="api/todos/banner/update" method="post" >
	  												<input id="id" type="hidden" name="id" value="${dailyBanner.id}"/>
	                                                <div class="control-group">
	                                                    <label for="coverPicUrl" class="control-label">每日一图</label>
	                                                    <div class="controls with-tooltip">
	                                                            <div id="uploadTips">
									
                                                         		</div>
	                                                            <span class="btn btn-file">
                                                                    <span onclick="uploadPicAjax.click()">选择图片</span>
                                                                    <input id="imageUrl" type="hidden" name="imageUrl"  value="${dailyBanner.imageUrl}"/>
                                                                </span>
		                                                         
		                                                         <p class="js_cover upload_preview" style="display: none;">
		                                                         	<img id="cover_preview"  src="">
																	<span><a id="removeCover" href="javascript:void(0);" >删除</a></span>
               													 </p>
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="text" class="control-label">文字</label>
	                                                    <div class="controls">
	                                                    	<textarea id="text" name="text" class="span6" style="height:260px">${dailyBanner.text }</textarea>
	                                                    </div>
	                                                </div>

	                                                <div class="control-group">
	                                                    <label for="date" class="control-label">日期</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="date" name="date" class="span3 input-tooltip" data-placement="top" value="${dailyBanner.date}"/>
	                                                    </div>
	                                                </div>

													<div class="form-actions">
														<input type="submit" value="提交" class="btn btn-primary">
	                                                </div>
	                                                </form>
	                                            </div>
                                            
                                            </div>
                                        	
                                        	<div class="span5">
	                                        	<div class="thumbnail" id="news_thumbnail">
	                                        	  <div id="banner_wrapper">
								                  	<img src="" id="news_cover" style="display:none;">
								                  	<i>每日一图</i>                           	  
	                                        	  </div>
								                  <div class="caption">
								                    <p id="banner_text" >
								                    	文字
								                    </p>
								                  </div>
								                </div>  
											</div>

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
        var maxsize = 2*1024*1024;//2M  
        var errMsg = "上传的文件不能超过2M！！！";  

		var STATIC_FILE_HOST = "${fileHost}";
		function uploadPicAjaxSubmit(o) {
			var ajaxForm = $('#uploadPicAjaxForm'), $file = $(o).clone();
			
			var byteSize = o.files[0].size;
			if(byteSize>maxsize){
				return alert(errMsg);
			}

			var options = {
				dataType : "json",
				data : {type:"json","channel":"todos",genThumbnails:true,"sizes":"360,null,_360;200,null,_200",uploads:$file.val()},
				beforeSubmit : function() {
					$("#uploadTips").show();
					$("#uploadTips").html("正在上传封面，请稍候……");
				},
				success : function(data) {
					if (data.success) {
						$("#uploadTips").hide();
						$("#imageUrl").val(data.result[0]);
						$("#news_cover").attr("src", STATIC_FILE_HOST + data.result[0]);
						$("#news_cover").css({"display":"block"});
						$("#cover_preview").attr("src", STATIC_FILE_HOST + data.result[0]);
						$(".upload_preview").css({"display":"block"});
					}else{
						alert(data);
					}
				},
				error : function(data) {

				}
			};
			ajaxForm.ajaxSubmit(options);
			return false;
		}
		
		
		$().ready(function() {

			$("#text").keyup(function(){
				$("#banner_text").html($(this).val());
			});

			$("#removeCover").click(function(){
				$("#imageUrl").val("");
				$("#news_cover").attr("src", "");
				$("#news_cover").css({"display":"none"});
				$("#cover_preview").attr("src", "");
				$(".upload_preview").css({"display":"none"});
			});
			  
			$("#todosBannerForm").validate({
			   ignore: "",
		       rules: {
		        	"date":  {
						required: true
					},
					"imageUrl":  {
						required: true
					},
					"text":  {
						required: true
					}
				},
				messages: {
					"date":{
						required:"请输入日期"
					},
					"imageUrl":{
						required:"请上传每日一图"
					},
					"text":{
						required:"请输入文字说明"
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
					bootbox.alert('每日一图已成功录入' ,function(){
						message_box.show('将跳转到每日一图管理界面!','success');
						var page_list = function(){
							location.href="todos_banner_list";
						}
						window.setTimeout(page_list, 1000); 
			 		});
				}else{
					alert('发布失败: '+ '\n\n 状态码: \n' + data.resultInfo.returnCode + '\n\n 提示信息: \n' + data.resultInfo.returnMsg +  '.'); 
				}
		 	}
		 	
            $('#date').datepicker({
                //maxDate: 0  // 当前日期之后的 0 天，就是当天
                //minDate: 0, // 当前日期之前的 5 天
                hideIfNoPrevNext: true   
           })
		});
      </script>
    </body>
</html>