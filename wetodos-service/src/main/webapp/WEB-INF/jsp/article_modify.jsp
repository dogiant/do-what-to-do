<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                            <h5>文章录入</h5>
                                            <!-- .toolbar -->
                                            <div class="toolbar">
                                                <ul class="nav">
                                                    <li><a href="article_list">文章列表</a></li>
                                                    <li class="dropdown">
                                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                                            <i class="icon-th-large"></i>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                            <li><a href="article_list">文章列表</a></li>
                                                            <li><a href="article_input">文章录入</a></li>
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
	  												<form id="articleForm" action="api/article/update" method="post" > 
	  												<input type="hidden" id="id" name="id" value="${articleItem.id}" />
	                                                <div class="control-group">
	                                                    <label for="articleTitle" class="control-label">标题</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="articleTitle" name="title" class="span6 input-tooltip" data-placement="top" value="${articleItem.title }"/>
	                                                    </div>
	                                                </div>
	                                                <c:if test="${articleCats!=null && articleCats.size()>0 }">
		                                                <div class="control-group">
		                                                    <label for="name" class="control-label">文章栏目</label>
		                                                    <div class="controls with-tooltip">
		                                                    <select name="articleCat.catId">
		                                                    	<c:if test="${articleItem.articleCat==null || articleItem.articleCat.catId==null}"><option value="null" selected="selected"> 请选择栏目</option></c:if>
			                                                    <c:forEach items="${articleCats}" var ="obj" varStatus="status">
																	<option value="${obj.catId}" <c:if test="${articleItem.articleCat.catId==obj.catId }"> selected="selected" </c:if>> ${obj.catNameShow }</option>
																</c:forEach>
															</select>
															</div>
	                                                	</div>
                                                	</c:if>
	                                                <div class="control-group">
	                                                    <label for="newsSubtitle" class="control-label">副标题</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="articleSubtitle" name="subtitle" class="span6 input-tooltip" data-placement="top" value="${articleItem.subtitle }"/>
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="newsAuthor" class="control-label">作者</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="articleAuthor" name="author" class="span6 input-tooltip" data-placement="top" value="${articleItem.author }"/>（选填）
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="coverPicUrl" class="control-label">封面</label>
	                                                    <div class="controls with-tooltip">
	                                                            <div id="uploadTips">
									
                                                         		</div>
	                                                             <span class="btn btn-file">
                                                                    <span onclick="uploadPicAjax.click()">选择图片</span>
                                                                    <input id="coverPicUrl" type="hidden" name="coverPicUrl" value="${articleItem.coverPicUrl }"/>
                                                                </span>
		                                                         
		                                                         <p class="js_cover upload_preview" style="display: none;"><img id="cover_preview"  src="">
																	<span><a id="removeCover" href="javascript:void(0);" >删除</a></span>
               													 </p>

               													 <p class="frm_tips">
													                <label for="" class="frm_checkbox_label js_show_cover_pic selected">
													                    <i class="icon_checkbox"></i>
													                    <input type="checkbox" class="frm_checkbox" name="coverIntoContent" value="true" <c:if test="${articleItem.coverIntoContent }"> checked="checked" </c:if>>
													                   封面图片显示在正文中 
													                </label>
													            </p>
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="articleDigest" class="control-label">摘要</label>
	                                                    <div class="controls">
	                                                    	<textarea id="articleDigest" name="digest" class="span6">${articleItem.digest }</textarea>
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="articleContent" class="control-label">正文</label>
	                                                    <div class="controls">
	                                                    	<div id="articleContent" class="summernote">${articleItem.content }</div>
	                                                    	<input type="hidden" id="articleItemContent" name="content"></input>
	                                                    </div> 
	                                                </div>
	                                                <div class="control-group">
														<label class="control-label">类型</label>
														<div class="controls controls-row">
															<input class="uniform" type="radio" name="type" value="0" <c:if test="${articleItem.type==0 }"> checked="checked" </c:if>>文章
	                                                        <input class="uniform" type="radio" name="type" value="1" <c:if test="${articleItem.type==1 }"> checked="checked" </c:if>>链接    
														</div>
	                                                </div>
	                                                
	                                                <div class="control-group">
	                                                    <label for="articleSourceUrl" class="control-label">原文链接</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="articleSourceUrl" name="sourceUrl" class="span6 input-tooltip" data-placement="top"  value="${articleItem.sourceUrl }" />
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="linkUrl" class="control-label">链接地址</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="linkUrl" name="linkUrl" class="span6 input-tooltip" data-placement="top" value="${articleItem.linkUrl }" />
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="fileUrl" class="control-label">文件上传</label>
	                                                    <div class="controls with-tooltip">
	                                                    	<span class="btn btn-file">
                                                                 <span onclick="uploadFileAjax.click()">选择文件</span>
                                                                 <input id="fileUrl" type="hidden" name="fileUrl" value="${articleItem.fileUrl }"/>
                                                            </span>
	                                                    
	                                                    	<span><a href="#" id="fileUrlShow"></a></span>
	                                                        
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
														<label class="control-label">是否推荐首页</label>
														<div class="controls controls-row">
															<input class="uniform" type="radio" name="recommend" value="true" <c:if test="${articleItem.recommend }"> checked="checked" </c:if>>推荐
	                                                        <input class="uniform" type="radio" name="recommend" value="false" <c:if test="${!articleItem.recommend }"> checked="checked" </c:if>>不推荐       
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
			var mCoverPicUrl = $("#coverPicUrl").val();
			if(mCoverPicUrl!=''){
				$("#news_cover").attr("src", STATIC_FILE_HOST + mCoverPicUrl);
				$("#news_cover").css({"display":"block"});
				$("#cover_preview").attr("src", STATIC_FILE_HOST + mCoverPicUrl);
				$(".upload_preview").css({"display":"block"});
			}
			
			var mFileUrl = $("#fileUrl").val();
			if(mFileUrl!=''){
				$("#fileUrlShow").attr("href", STATIC_FILE_HOST + mFileUrl);
			}
			
			$('#articleDigest').autosize();
			$('#articleContent').summernote({
			 	lang: 'zh-CN',
	            height: 200,
	            onImageUpload: function(files, editor, welEditable) {
	                sendFile(files[0], editor, welEditable);
	            },
	            onblur: function(e) {
	                var sHTML = $("#articleContent").code();
	                $("#articleItemContent").val(sHTML);
	            }
		    });
			//summernote图片上传
			function sendFile(file, editor, welEditable) {
				var formData  = new FormData();
				formData.append("uploads", file);
				formData.append("type","json");
				formData.append("channel","news");
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

		$("#articleTitle").keyup(function(){
			$("#news_title").html($(this).val());
		});
		$("#articleDigest").keyup(function(){
			$("#news_digest").html($(this).val());
		});


		$("#removeCover").click(function(){
			$("#coverPicUrl").val("");
			$("#news_cover").attr("src", "");
			$("#news_cover").css({"display":"none"});
			$("#cover_preview").attr("src", "");
			$(".upload_preview").css({"display":"none"});
		});
		  
		$("#articleForm").validate({
		        rules: {
		        	"title":  {
						required: true
					},
					"coverPicUrl":  {
						required: true
					},
					"digest":  {
						required: true
					},
					"sourceUrl": {
						url: true 
					},
					"linkUrl": {
						url: true 
					}
				},
				messages: {
					"title":{
						required:"请输入图文消息标题"
					},
					"coverPicUrl":{
						required:"请上传封面"
					},
					"digest":{
						required:"请输入摘要"
					},
					"sourceUrl": {
						url: "请输入正确的原文链接地址"
					},
					"linkUrl": {
						url: "请输入正确的链接地址"
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
					bootbox.alert('您的文章已成功录入' ,function(){
						message_box.show('将跳转到您的文章列表管理界面!','success');
						var page_list = function(){
							location.href="article_list";
						}
						window.setTimeout(page_list, 1000); 
			 		});
				}else{
					alert('发布失败: '+ '\n\n 状态码: \n' + data.resultInfo.returnCode + '\n\n 提示信息: \n' + data.resultInfo.returnMsg +  '.'); 
				}
		 	}
		});
      </script>
	  <script src="//qzonestyle.gtimg.cn/open/qcloud/video/h5/h5connect.js" charset="utf-8"></script>
    </body>
</html>