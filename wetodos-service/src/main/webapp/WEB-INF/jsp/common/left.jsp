<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- BEGIN LEFT  -->
<div id="left">
    <!-- .user-media -->
    <div class="media user-media hidden-phone" id="userInfoTablet">
    	<!-- 
            <a href="" class="user-link">
                <img src="assets/img/user.gif" alt="" class="media-object img-polaroid user-img" id="userHeadImg">
                <span class="label user-label"></span>
            </a>
		-->
        <div class="media-body hidden-tablet">
        	<!-- 
            <h5 class="media-heading" id="mp_weChatId">用户头像</h5>
            -->
            <ul class="unstyled user-info">
                <li>welcome,${nickname}</li>
                <li>上次登录时间 : <br/>
                    <small><i class="icon-calendar"></i><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${lastLoginTime}" /></small>
                </li>
            </ul>
        </div>
    </div>
    <!-- /.user-media -->

    <!-- BEGIN MAIN NAVIGATION -->
    <ul id="menu" class="unstyled accordion collapse in">

        <li class="accordion-group ">
            <a data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#article-nav">
                <i class="icon-pencil icon-large"></i> 内容管理 <span
                    class="label label-inverse pull-right">4</span>
            </a>
            <ul class="collapse  <c:if test="${requestScope.menu=='article'}"> in</c:if>"  id="article-nav">
                <li><a href="article_cat_list"><i class="icon-angle-right"></i> 栏目管理</a></li>
                
                <li><a href="article_list"><i class="icon-angle-right"></i> 文章管理</a></li>
               
            	<li><a href="section_list"><i class="icon-angle-right"></i> 版块管理</a></li>
            </ul>
        </li>
        
        <li class="accordion-group ">
            <a data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#todos-nav">
                <i class="icon-book icon-large"></i> 简道学习 <span
                    class="label label-inverse pull-right">2</span>
            </a>
            <ul class="collapse  <c:if test="${requestScope.menu=='todos'}"> in</c:if>"  id="todos-nav">
            	<li><a href="todos_book_list"><i class="icon-angle-right"></i> 学习资源管理</a></li>
            	<li><a href="todos_learning_plan_list"><i class="icon-angle-right"></i> 学习计划管理</a></li>
            </ul>
        </li>  
        
        <!-- 
        <li class="accordion-group ">
            <a data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#video-nav">
                <i class="icon-film icon-large"></i> 视频管理 <span
                    class="label label-inverse pull-right">2</span>
            </a>
            <ul class="collapse  <c:if test="${requestScope.menu=='video'}"> in</c:if>"  id="video-nav">
                <li><a href="uploadVedio"><i class="icon-angle-right"></i> 视频上传</a></li>
                
                <li><a href="videoList"><i class="icon-angle-right"></i> 视频列表</a></li>
            </ul>
        </li>
         -->
        
        <c:if test="${requestScope.userName=='admin'}">
        <li class="accordion-group<c:if test="${requestScope.menu=='admin'}"> active</c:if> ">
            <a data-parent="#menu" data-toggle="collapse" class="accordion-toggle collapsed" data-target="#admin-nav">
                <i class="icon-cog icon-large"></i> 系统管理<span class="label label-inverse pull-right">1</span>
            </a>
            <ul class="collapse <c:if test="${requestScope.menu=='admin'}"> in</c:if>" id="admin-nav">
                <li><a href="admin_list"><i class="icon-angle-right"></i> 管理员账号列表</a></li>
            </ul>
        </li>
        </c:if>
        <li><a href="javascript:logout();"><i class="icon-signin icon-large"></i> 退出登录</a></li>
    </ul>
    <!-- END MAIN NAVIGATION -->

</div>
<!-- END LEFT -->
