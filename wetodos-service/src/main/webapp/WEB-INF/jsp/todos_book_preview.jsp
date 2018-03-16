<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="author" content="dogiant">
	<title>${book.title}</title>
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<style>
		.content {padding:0 10px}
		
		 .chapter {
            position: relative;
        }
 
        .chapter .delete {
            position: absolute;
            top: 20px;
            right: -13px;
            width: 50px;
            height: 50px;
            display: none;
        }
        
        .chapter .edit {
            position: absolute;
            top: 20px;
            right: 40px;
            width: 50px;
            height: 50px;
            display: none;
        }
        
        .phase-container .delete {
            position: absolute;
            top: 5px;
            right: -30px;
            width: 50px;
            height: 50px;
            display: none;
        }
        
        .phase-container p{
        	padding-top:10px;
        	word-wrap: break-word;
        	word-break: break-all;
        }
        
        .buttonDiv {
        	padding-top: 10px;
        	padding-bottom: 10px;
        }
	</style>
	</head>
	<body>
	
    <!-- Start: Main content -->
    <div class="content">     
      <div class="container">
        <!-- Start: article  -->
        <article class="article"> 
          <div class="row bottom-space">
          
            <div class="span12">
              <div class="page-header">
                <h4>${book.title }</h4>
                <p><small>${book.author }</small></p>
              </div>
            </div>
            
          </div>
          
          <div class="row bottom-space">
            <div class="span10 offset1">
	            <c:if test="${book.coverPicUrl!='' }">
	            	<div class="span10 center-align">
	              		<img src="${fileHost }${book.coverPicUrl }" class="thumbnail cover-snap">            
	            	</div>
	            </c:if>
				
				<div class="panel panel-default">
				  <div class="panel-heading">摘要信息</div>
				  <div class="panel-body">
				    ${book.digest }
				  </div>
				</div>
            </div>
            <!-- 章节管理开始 -->
            <div class="chapter-container list-group">
            	<div class="buttonDiv">
            		<button id="addChapterButton1" type="button" class="btn btn-primary" data-toggle="modal" data-target="#chapterModal" data-operation="新建">
            		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加章节</button>
            	</div>
            	<c:forEach items="${chapterList}" var="chapter" varStatus="status">
            		<div class="chapter list-group-item" data-id="${chapter.id }">
	            		<h4>${chapter.title }</h4>
	            		<c:if test="${not empty chapter.subTitle}">
	            			<h5>${chapter.subTitle }</h5>
	            		</c:if>
	            		<div class="phase-container list-group">
	            			<c:forEach items="${chapter.phases }" var="phase" varStatus="st">
	            				<c:if test="${phase.contentType eq 'text' }">
	            					<div class="phase text list-group-item">
			            				<p>${phase.content }</p>
			            				<span class="glyphicon glyphicon-remove delete phaseDelBtn" aria-hidden="true" data-id="${phase.id }"></span>
			            			</div>
	            				</c:if>
	            				<c:if test="${phase.contentType eq 'image' }">
	            					<div class="phase image list-group-item">
			            				<img src="${fileHost }${phase.uri }" width="640"/>
			            				<span class="glyphicon glyphicon-remove delete phaseDelBtn" aria-hidden="true" data-id="${phase.id }"></span>
			            			</div>
	            				</c:if>
	            			</c:forEach>
	            			<div class="buttonDiv">
		            			<button type="button" class="btn btn-primary addPhaseButton" data-chapter="${chapter.id }">
		            			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 增加段落
		            			</button>
	            			</div>
	            		</div>
	            		<span class="glyphicon glyphicon-edit edit chapterEditBtn" aria-hidden="true" data-id="${chapter.id }" chapter-type="${chapter.contentType }" chapter-taskTypes="${chapter.taskTypes }"></span>
	            		<span class="glyphicon glyphicon-remove delete chapterDelBtn" aria-hidden="true"  data-id="${chapter.id }"></span>
            		</div>
            	</c:forEach>
            	<div class="buttonDiv">
	            	<button id="addChapterButton2" type="button" class="btn btn-primary addChapterButton">
	            	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加章节</button>
	            </div>
            </div>
          </div>
        </article>
        <!-- End: Article show -->
      </div>
    </div>
    
    <div class="modal fade" id="chapterModal" tabindex="-1" role="dialog" aria-labelledby="chapterModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
          <form id="chapterForm" action="api/todos/chapter/save" method="post">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel"><span id="operation"></span>章节</h4>
	      </div>
	      <div class="modal-body">
	          <input type="hidden" id="id" name="id">
	          <input type="hidden" id="bookId" name="bookId" value="${book.id }">
	          <div class="form-group">
	            <label for="title" class="control-label">标题:</label>
	            <input type="text" class="form-control" id="title" name="title">
	          </div>
	          <div class="form-group">
	            <label for="subTitle" class="control-label">副标题:</label>
	            <input type="text" class="form-control" id="subTitle" name="subTitle">
	          </div>
	          <div class="form-group">
	          	<label for="contentType" class="control-label">内容类型:</label>
	          	<select class="form-control" name="contentType" id="contentType">
				  <option value="article" selected="selected">文章</option>
				  <option value="poem">古诗词</option>
				  <option value="speech">演讲</option>
				</select>
	          </div>
	          <div class="form-group">
	          	<label for="taskTypes" class="control-label">阅后任务类型:</label>
	          	<div>
					<label class="checkbox-inline">
					  <input type="checkbox" id="inlineCheckbox1" name="taskTypes" value="choice" checked="checked"> 选择 
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="inlineCheckbox2" name="taskTypes" value="image" checked="checked"> 图片
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="inlineCheckbox3" name="taskTypes" value="audio" checked="checked"> 语音 
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="inlineCheckbox3" name="taskTypes" value="video" checked="checked"> 视频
					</label>
	          	</div>
	          </div>
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="submit" class="btn btn-primary">保存提交</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>
	
    <div class="modal fade" id="phaseModal" tabindex="-1" role="dialog" aria-labelledby="phaseModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
          <form id="phaseForm" action="api/todos/phase/save" method="post">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">增加段落</h4>
	      </div>
	      <div class="modal-body">
	          <input type="hidden" id="id" name="id">
	          <input type="hidden" id="bookId" name="bookId" value="${book.id }">
	          <input type="hidden" id="chapterId" name="chapterId" >
	          <div class="form-group">
	          	<label for="contentType" class="control-label">内容类型:</label>
	          	<div>
					<label class="checkbox-inline">
					  <input type="radio" id="phaseTextRadio" name="contentType" value="text" checked="checked"> 文字 
					</label>
					<label class="checkbox-inline">
					  <input type="radio" id="phaseImageRadio" name="contentType" value="image" > 图片
					</label>
	          	</div>
	          </div>
	          
	          <div class="form-group" id="phaseTextForm">
	            <label for="content" class="control-label">内容:</label>
	            <textarea class="form-control" id="content" name="content"></textarea>
	          </div>
	          <div class="form-group" id="phaseImageForm" style="display: none">
				  <label for="uri" class="control-label">图片</label>
                  <div class="controls with-tooltip">

                      <div id="uploadTips">

                      </div>
                      
                      
                      <span class="btn btn-file">
							<span onclick="uploadPicAjax.click()">选择图片</span>
			 			  
                          	<input id="uri" type="hidden" name="uri" />
                      </span>
                        
                      <p class="js_cover upload_preview" style="display: none;">
                          <img id="pic_preview"  src="">
						  <span><a id="removePic" href="javascript:void(0);" >删除</a></span>
					  </p>
                 </div>
	          </div>

	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="submit" class="btn btn-primary">保存提交</button>
	      </div>
	      </form>
	    </div>
	  </div>
	</div>
    
    <div style="display: none;">
		<form id="uploadPicAjaxForm" action="/upload/api"  enctype="multipart/form-data"  method="post" >
			<input id="uploadPicAjax" name="uploads" type="file" onchange="uploadPicAjaxSubmit(this);"/>
		</form>
	</div>

    
    <!-- End: Main content -->
	<%@ include file="common/footer_script_preview.jsp" %>
	
	<script type="text/javascript">
    $(function() {
    	
    	 $(".chapter").mouseenter(function () {
             $(this).find(".delete").show();
             $(this).find(".edit").show();
         });

         $(".chapter").mouseleave(function () {
             $(this).find(".delete").hide();
             $(this).find(".edit").hide();
         });
         
    	 $(".phase").mouseenter(function () {
             $(this).find(".delete").show();
         });
    	 
         $(".phase").mouseleave(function () {
             $(this).find(".delete").hide();
         });
         
//          $('#chapterModal').on('show.bs.modal', function (event) {
//         	  var button = $(event.relatedTarget) // Button that triggered the modal
//         	  var id = button.data('whatever') // Extract info from data-* attributes
//         	  var modal = $(this)
//         	  if(id){
//             	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
//             	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            	  
//             	  //modal.find('.modal-title').text('New message to ' + recipient)
//             	  modal.find('#id').val(id);
//         	  }
//         	  var operation =  button.data('operation');
//         	  alert(operation);
//         	  if(operation!=undefined && operation!=''){
//         		  modal.find('#operation').text(operation);
//         	  }
//          });
         
		 $("#chapterForm").validate({
		        rules: {
		        	"title":  {
						required: true
					}
				},
				messages: {
					"title":{
						required:"请输入章节标题"
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
	            	   dataType:'json'
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
				alert("录入成功");
				window.location.reload();
			}else{
				alert('发布失败: '+ '\n\n 状态码: \n' + data.resultInfo.returnCode + '\n\n 提示信息: \n' + data.resultInfo.returnMsg +  '.'); 
			}
	 	}
		
	 	
       $('#phaseModal').on('show.bs.modal', function (event) {
//       	  var button = $(event.relatedTarget) // Button that triggered the modal
      	 
//       	  var modal = $(this);

//       	  var chapterId =  button.data('chapter');

//       	  if(chapterId!=undefined && chapterId!=''){
//       		  modal.find('#chapterId').val(chapterId);
//       	  }
      	  
      	  $("#phaseImageRadio").click(function(){
      		$("#phaseImageForm").show();
  		  	$("#phaseTextForm").hide();
      	  });
      	  
      	  $("#phaseTextRadio").click(function(){
    		  $("#phaseImageForm").hide();
    		  $("#phaseTextForm").show();
    	  });
      	  
  		  $("#removePic").click(function(){
  			$("#uri").val("");
  			$("#pic_preview").attr("src", "");
  			$("#pic_preview").css({"display":"none"});
  			$(".upload_preview").css({"display":"none"});
  		  });
      	  
       });
       
	   $("#phaseForm").validate({
		        rules: {
		        	"contentType":  {
						required: true
					}
				},
				messages: {
					"contentType":{
						required:"请选择内容类型"
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
	            	   dataType:'json'
	            	};
		            
	            	$(form).ajaxSubmit(options);
               	return false;
	            }
		});


	   $(".addChapterButton").click(function(){
		   $('#chapterModal').modal('show');
		   $('#operation').text("新建");
	   });
	   
	   $(".addPhaseButton").click(function(){
		   $('#phaseModal').modal('show');
		   var chapterId = $(this).attr("data-chapter");
		   $('#chapterId').val(chapterId);
	   });

	   $(".phaseDelBtn").click(function(){
		   var idsValue = $(this).attr("data-id");
		   if (confirm("确定要删除这个段落吗？")){
			   $.ajax({
	           		type:'post',
	           		url:'api/todos/phase/delete',
	           		data:{ids:idsValue},
	           		dataType:'json',
	           		beforeSend: function(){
	           		},
	           		success:function(data){
	           			if(data.success){
	               			alert("删除成功");
	           				window.location.reload(); 
	               		}
	           		},
	           		error:function(){
	           			alert("删除出错!");
	           		}
           		});
		   }
	   });
	   
	   
	   $(".chapterDelBtn").click(function(){
		   var idsValue = $(this).attr("data-id");
		   if (confirm("确定要删除这个章节吗？")){
			   $.ajax({
	           		type:'post',
	           		url:'api/todos/chapter/delete',
	           		data:{ids:idsValue},
	           		dataType:'json',
	           		beforeSend: function(){
	           		},
	           		success:function(data){
	           			if(data.success){
	               			alert("删除成功");
	           				window.location.reload(); 
	               		}
	           		},
	           		error:function(){
	           			alert("删除出错!");
	           		}
           		});
		   }
	   });
	   
	   $(".chapterEditBtn").click(function(){
		   var id = $(this).attr("data-id");
		   
		   var title = $(this).parent().find("h4").text();
		   
		   var subTitle = $(this).parent().find("h5").text();
		   
		   var contentType = $(this).attr("chapter-type");
		   var taskTypes = $(this).attr("chapter-taskTypes");
		   
		   $('#chapterModal').modal('show');
		   
		   $('#operation').text("修改");
		   
		   $("#chapterForm").find("#id").val(id);
		   $("#chapterForm").find("#title").val(title);
		   $("#chapterForm").find("#subTitle").val(subTitle);
		   $("#chapterForm").find("#contentType").val(contentType);
		   
		   //var types = new Array(); //定义一数组 
		   if(taskTypes!=''){
			   var types = taskTypes.split(","); //字符分割 
			   
			   $("#chapterForm").find(("[name='taskTypes']")).each(function () {  
	                if (types.contains($(this).val())) {  
	                	$(this).attr("checked",true); 
	                }else{
	                	$(this).attr("checked",false); 
	                } 
	           });  
				
			}
		   
	   });
	   
    });
    
    Array.prototype.contains = function ( needle ) {
    	  for (i in this) {
    	    if (this[i] == needle) return true;
    	  }
    	  return false;
    	}
    
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
					$("#uploadTips").html("正在上传图片，请稍候……");
				},
				success : function(data) {
					if (data.success) {
						$("#uploadTips").hide();
						$("#uri").val(data.result[0]);
						$("#pic_preview").attr("src", STATIC_FILE_HOST + data.result[0]);
						$("#pic_preview").css({"display":"block"});
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

	</script>
  </body>
</html>
