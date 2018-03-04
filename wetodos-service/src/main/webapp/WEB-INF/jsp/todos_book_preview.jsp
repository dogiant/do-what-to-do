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
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
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
                <small>${book.author }</small>
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
	
				<div>
					${book.digest }
				</div>
            </div>
            <!-- 章节开始 -->
            <div >
            
            </div>
          </div>
        </article>
        <!-- End: Article show -->
      </div>
    </div>
    <!-- End: Main content -->
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="//qzonestyle.gtimg.cn/open/qcloud/video/h5/h5connect.js" charset="utf-8"></script>
  </body>
</html>
