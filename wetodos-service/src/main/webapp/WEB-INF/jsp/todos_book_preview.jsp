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
            
            	<div class="chapter list-group-item" data-id="1">
            		<h4>chapter 1</h4>
            		<h5>副标题</h5>
            		<div class="phase-container list-group">
            			<div class="text list-group-item">文字段落</div>
            			<div class="image list-group-item"><img src="${fileHost }${book.coverPicUrl }"></div>
            			<button type="button" class="btn btn-primary" class="addPhaseButton">增加段落</button>
            		</div>
            	</div>
            	
            	<div class="chapter list-group-item" data-id="2">
            		<h4>chapter 2</h4>
            		<h5>副标题</h5>
            		<div class="phase-container list-group">
            			<div class="text list-group-item">文字段落</div>
            			<div class="image list-group-item"><img src="${fileHost }${book.coverPicUrl }"></div>
            			<div class="text list-group-item">文字段落</div>
            			<button type="button" class="btn btn-primary" class="addPhaseButton">增加段落</button>
            		</div>
            	</div>
            	
            	<button id="addChapterButton" type="button" class="btn btn-primary" data-toggle="modal" data-target="#chapterModal">添加章节</button>
            </div>
          </div>
        </article>
        <!-- End: Article show -->
      </div>
    </div>
    
    <div class="modal fade" id="chapterModal" tabindex="-1" role="dialog" aria-labelledby="chapterModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">新建章节</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="title" class="control-label">标题:</label>
	            <input type="text" class="form-control" id="title">
	          </div>
	          <div class="form-group">
	            <label for="subTitle" class="control-label">副标题:</label>
	            <input type="text" class="form-control" id="subTitle">
	          </div>
	          <div class="form-group">
	          	<label for="contentType" class="control-label">内容类型:</label>
	          	<select class="form-control" name="contentType">
				  <option value="article">文章</option>
				  <option value="poem">古诗词</option>
				  <option value="speech">演讲</option>
				</select>
	          </div>
	          <div class="form-group">
	          	<label for="taskTypes" class="control-label">阅后任务类型:</label>
	          	<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox1" name="taskTypes" value="choice"> 选择 
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox2" name="taskTypes" value="image"> 图片
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox3" name="taskTypes" value="audio"> 语音 
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" id="inlineCheckbox3" name="taskTypes" value="vedio"> 视频
				</label>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary">保存提交</button>
	      </div>
	    </div>
	  </div>
	</div>
    

    
    <!-- End: Main content -->
	<script src="//cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
    $(function() {
    	
    	
    	
    });
	
	
	</script>
  </body>
</html>
