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
            right: 0px;
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
            	<button id="addChapterButton1" type="button" class="btn btn-primary" data-toggle="modal" data-target="#chapterModal" data-operation="新建">
            	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加章节</button>
            	<c:forEach items="${chapters}" var="chapter" varStatus="status">
            	
            	</c:forEach>
            	<div class="chapter list-group-item" data-id="1">
            		<h4>chapter 1</h4>
            		<h5>副标题</h5>
            		<div class="phase-container list-group">
            			<div class="phase text list-group-item">
            				<p>文字段落</p>
            				<span class="glyphicon glyphicon-remove delete" aria-hidden="true"></span>
            			</div>
            			<div class="phase image list-group-item">
            				<img src="${fileHost }${book.coverPicUrl }"/>
            				<span class="glyphicon glyphicon-remove delete" aria-hidden="true"></span>
            			</div>
            			<button type="button" class="btn btn-primary" class="addPhaseButton"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 增加段落</button>
            		</div>
            		<span class="glyphicon glyphicon-edit edit" aria-hidden="true"></span>
            		<span class="glyphicon glyphicon-remove delete" aria-hidden="true"></span>
            	</div>
            	
            	<div class="chapter list-group-item" data-id="2">
            		<h4>chapter 2</h4>
            		<h5>副标题</h5>
            		<div class="phase-container list-group">
            			<div class="phase text list-group-item">
            				<p>文字段落</p>
            				<span class="glyphicon glyphicon-remove delete" aria-hidden="true"></span>
            			</div>
            			<div class="phase image list-group-item">
            				<img src="${fileHost }${book.coverPicUrl }"/>
            				<span class="glyphicon glyphicon-remove delete" aria-hidden="true"></span>
            			</div>
            			<button type="button" class="btn btn-primary" class="addPhaseButton">增加段落</button>
            		</div>
            		<span class="glyphicon glyphicon-edit edit" aria-hidden="true"></span>
            		<span class="glyphicon glyphicon-remove delete" aria-hidden="true"></span>
            	</div>
            	
            	<button id="addChapterButton2" type="button" class="btn btn-primary" data-toggle="modal" data-target="#chapterModal" data-operation="新建">
            	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加章节</button>
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
	          	<select class="form-control" name="contentType">
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
					  <input type="checkbox" id="inlineCheckbox3" name="taskTypes" value="vedio" checked="checked"> 视频
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
    

    
    <!-- End: Main content -->
	<script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="assets/js/lib/jquery.form.js"></script>
	<script type="text/javascript" src="assets/js/lib/jquery.validate.min.js"></script>
	<script type="text/javascript" src="assets/js/lib/jquery.form.wizard-min.js"></script>
	
	<script type="text/javascript" src="assets/js/lib/bootbox.js"></script>
	
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
         
         $('#chapterModal').on('show.bs.modal', function (event) {
        	  var button = $(event.relatedTarget) // Button that triggered the modal
        	  var id = button.data('whatever') // Extract info from data-* attributes
        	  var modal = $(this)
        	  if(id){
            	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
            	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            	  
            	  //modal.find('.modal-title').text('New message to ' + recipient)
            	  modal.find('#id').val(id);
        	  }
        	  var operation =  button.data('operation');
        	  if(operation!=undefined && operation!=''){
        		  modal.find('#operation').text(operation);
        	  }
         });
         
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
				}else{
					alert('发布失败: '+ '\n\n 状态码: \n' + data.resultInfo.returnCode + '\n\n 提示信息: \n' + data.resultInfo.returnMsg +  '.'); 
				}
		 	}
    	
    });

	</script>
  </body>
</html>
