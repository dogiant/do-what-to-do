<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
            <!-- BEGIN TOP BAR -->
            <div id="top">
                <!-- .navbar -->
                <div class="navbar navbar-inverse navbar-static-top">
                    <div class="navbar-inner">
                        <div class="container-fluid">
                            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </a>
                            <a class="brand" href="index.html"><spring:message code="messages.web.name"></spring:message></a>
                            <!-- .topnav -->
                            <div class="btn-toolbar topnav">
                                <div class="btn-group">
                                    <a id="changeSidebarPos" class="btn btn-success" rel="tooltip"
                                    data-original-title="显示/ 隐藏 侧边栏" data-placement="bottom">
                                        <i class="icon-resize-horizontal"></i>
                                    </a>
                                </div>
                                <div class="btn-group">
                                    <a class="btn btn-inverse" rel="tooltip" href="#" data-original-title="消息中心"
                                       data-placement="bottom">
                                        <i class="icon-comments"></i>
                                        <span class="label label-important"></span>
                                    </a>
                                </div>
                                <div class="btn-group">
                                	<!-- 
                                    <a class="btn btn-inverse" rel="tooltip" href="#" data-original-title="帮助文档"
                                       data-placement="bottom">
                                        <i class="icon-file"></i>
                                    </a>
                                     -->
                                    <a href="#helpModal" class="btn btn-inverse" rel="tooltip" data-placement="bottom"
                                       data-original-title="帮助" data-toggle="modal">
                                        <i class="icon-question-sign"></i>
                                    </a>
                                </div>
                                <div class="btn-group">
                                    <a class="btn btn-inverse" data-placement="bottom" data-original-title="退出" rel="tooltip"
                                       href="javascript:logout();"><i class="icon-off"></i></a></div>
                            </div>
                            <!-- /.topnav -->
                            <div class="nav-collapse collapse">
                                <!-- .nav -->
                                <ul class="nav">
                                    <li class="active"><a href="index.html">Console</a></li>
                                    <!-- 
                                    <li class="dropdown">
                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#" >
                                            我的公众号切换<b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu" id="mp-list">
                                        </ul>
                                    </li>
                                    <li class="dropdown">
                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                            消息素材管理<b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a href="text_list.do">文本消息</a></li>
                                            <li><a href="news_list.do">图文消息</a></li>
                                            <li><a href="#">图片消息</a></li>
                                            <li><a href="#">语音消息</a></li>
                                            <li><a href="#">视频消息</a></li>
                                        </ul>
                                    </li>
                                     -->
                                </ul>
                                <!-- /.nav -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.navbar -->
            </div>
            <!-- END TOP BAR -->
