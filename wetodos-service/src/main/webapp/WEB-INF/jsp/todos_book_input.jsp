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
                                            <h5>学习资源录入</h5>
                                            <!-- .toolbar -->
                                            <div class="toolbar">
                                                <ul class="nav">
                                                    <li><a href="todos_book_list">学习资源列表</a></li>
                                                    <li class="dropdown">
                                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                                            <i class="icon-th-large"></i>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                            <li><a href="todos_book_list">学习资源列表</a></li>
                                                            <li><a href="todos_book_input">学习资源录入</a></li>
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
		                                                <form id="uploadFileAjaxForm" action="/upload/api"  enctype="multipart/form-data"  method="post" >
		                                                	<input type="hidden" name="isPic" value="false" />
	                                                    	<input id="uploadFileAjax" name="uploads" type="file" onchange="uploadFileAjaxSubmit(this);"/>
		                                                </form>
	                                                </div>
	  												<div class="form-horizontal">
	  												<form id="todosBookForm" action="api/todos/book/add" method="post" > 
	                                                <div class="control-group">
	                                                    <label for="bookTitle" class="control-label">标题</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="bookTitle" name="title" class="span6 input-tooltip" data-placement="top" />
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="newsAuthor" class="control-label">作者</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="bookAuthor" name="author" class="span6 input-tooltip" data-placement="top" />（选填）
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="coverPicUrl" class="control-label">封面</label>
	                                                    <div class="controls with-tooltip">
	                                                            <div id="uploadTips">
									
                                                         		</div>
	                                                             <span class="btn btn-file">
                                                                    <span onclick="uploadPicAjax.click()">选择图片</span>
                                                                    <input id="coverImageUrl" type="hidden" name="coverImageUrl" />
                                                                </span>
		                                                         
		                                                         <p class="js_cover upload_preview" style="display: none;"><img id="cover_preview"  src="">
																	<span><a id="removeCover" href="javascript:void(0);" >删除</a></span>
               													 </p>
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="bookDigest" class="control-label">摘要</label>
	                                                    <div class="controls">
	                                                    	<textarea id="bookDigest" name="digest" class="span6" ></textarea>
	                                                    </div>
	                                                </div>

	                                                <div class="control-group">
	                                                    <label for="tags" class="control-label">标签</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="bookTags" name="tags" class="span6 input-tooltip" data-placement="top"  />
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
	                                        	  <h4 id="news_title" >标题</h4>
	                                        	  <div id="cover_wrapper">
								                  	<img src="" id="news_cover" style="display:none;">
								                  	<i>封面图片</i>                           	  
	                                        	  </div>
								                  <div class="caption">
								                    <p id="news_digest" >
								                    	摘要文字
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
				data : {type:"json","channel":"news",genThumbnails:true,"sizes":"360,null,_360;200,null,_200",uploads:$file.val()},
				beforeSubmit : function() {
					$("#uploadTips").show();
					$("#uploadTips").html("正在上传封面，请稍候……");
				},
				success : function(data) {
					if (data.success) {
						$("#uploadTips").hide();
						$("#coverPicUrl").val(data.result[0]);
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
		
		function uploadFileAjaxSubmit(o) {
			var ajaxForm = $('#uploadFileAjaxForm'), $file = $(o).clone();
			
			var byteSize = o.files[0].size;
			if(byteSize>maxsize){
				return alert(errMsg);
			}
			
			var options = {
				dataType : "json",
				data : {returnType:"json","channel":"news","isPic":false,uploads:$file.val()},
				beforeSubmit : function() {
					alert("开始上传文件");
				},
				success : function(data) {
					if (data.success) {
						$("#fileUrl").val(data.result[0]);
						$("#fileUrlShow").attr("href", STATIC_FILE_HOST + data.result[0]);
						$("#fileUrlShow").text(data.result[0]);
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
			$('#bookDigest').autosize();

			$("#bookTitle").keyup(function(){
				$("#news_title").html($(this).val());
			});
			$("#bookDigest").keyup(function(){
				$("#news_digest").html($(this).val());
			});

			$("#removeCover").click(function(){
				$("#coverImageUrl").val("");
				$("#news_cover").attr("src", "");
				$("#news_cover").css({"display":"none"});
				$("#cover_preview").attr("src", "");
				$(".upload_preview").css({"display":"none"});
			});
			  
			$("#todosBookForm").validate({
		       rules: {
		        	"title":  {
						required: true
					},
					"coverImageUrl":  {
						required: true
					},
					"digest":  {
						required: true
					},
					"tags": {
						required: true 
					}
				},
				messages: {
					"title":{
						required:"请输入学习资源标题"
					},
					"coverImageUrl":{
						required:"请上传封面"
					},
					"digest":{
						required:"请输入摘要"
					},
					"tags": {
						required: "请输入学习资源标签"
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
					bootbox.alert('学习资源已成功录入' ,function(){
						message_box.show('将跳转到学习资源管理界面!','success');
						var page_list = function(){
							location.href="todos_book_list";
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