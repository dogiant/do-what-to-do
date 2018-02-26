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
                        	<!--Begin Datatables-->
							<div class="row-fluid">
                                <div class="span12">
                                    <div class="box dark">
                                        <header>
                                            <div class="icons"><i class="icon-edit"></i></div>
                                            <h5>文章栏目列表</h5>
                                            <!-- .toolbar -->
                                            <div class="toolbar">
                                                <ul class="nav">
                                                    <li><a href="article_cat_input.do">文章栏目录入</a></li>
                                                    <li class="dropdown">
                                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                                            <i class="icon-th-large"></i>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                        	<li><a href="article_cat_input.do">文章栏目录入</a></li>
                                                            <li><a href="article_cat_list.do">文章栏目列表</a></li>
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
                                        <div  id="div-1"  class="accordion-body collapse in body">
                                            <table id="articleCatDataTable" class="table table-bordered table-condensed table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                    	<th>栏目名称</th>
                                                        <th>栏目代码</th>
                                                        <th>类型</th>
                                                        <th>是否显示</th>
                                                        <th>排序值</th>
                                                        <th>创建时间</th>
                                                        <th>修改时间</th>
                                                        <th>操作</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${articleCats}" var="obj" varStatus="status">
                                                	<tr dataid="${obj.catId}">
												    	<td><img src="assets/img/menu_arrow.gif" width="9" height="9" border="0" style="margin-left:${obj.level}em" />&nbsp;${obj.catName}</td>
                                                    	<td>${obj.catCode}</td>
                                                    	<td>${obj.catTypeDesc}</td>
                                                    	<td>${obj.showInNav}</td>
                                                    	<td>${obj.sortOrder}</td>
                                                        <td width="200"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${obj.ctime}" /></td>
                                                        <td width="200"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${obj.mtime}" /></td>
                                                        <td>
                                                        	<button class="btn edit"  dataid="${obj.catId}"><i class="icon-edit" ></i></button>
                                                            <button class="btn btn-danger remove" dataid="${obj.catId}"><i class="icon-remove" ></i></button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--End Datatables-->

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
            $(function() {

                /*----------- END menuDataTable CODE -------------------------*/
                $('#articleCatDataTable tbody').on( 'click', 'button.view', function () {
                	$.ajax({
                		type:'post',
                		url:'api/article_cat_get.do',
                		data:{id:$(this).attr("dataid")},
                		dataType:'json',
                		success:function(data){
                			if(data.success){
                    			//alert(data.text.content);
                    			bootbox.alert(data.text.content);                   			
                    		}
                		},
                		error:function(){
                			alert("读取出错!");
                		}
                	});
                }); 
            	
                $('#articleCatDataTable tbody').on( 'click', 'button.edit', function () {
                    location.href = "article_cat_modify.do?catId="+$(this).attr("dataid");
                }); 
                $('#articleCatDataTable tbody').on( 'click', 'button.remove', function () {
                    var idsvalue = $(this).attr("dataid");
        			bootbox.confirm("您确定要删除操作吗?", function(result) {
            			if(result){
                        	$.ajax({
                        		type:'post',
                        		url:'api/article/cat/delete',
                        		data:{ids:idsvalue},
                        		dataType:'json',
                        		beforeSend: function(){
                        		},
                        		success:function(data){
                        			if(data.success){
                            			alert("删除成功");
                        				window.location.reload(); 
                            		}else{
                            			if(data.code==4){
                            				alert("系统设定的栏目不允许删除");
                            			}else{
                            				alert(data.msg);
                            			}
                            		}
                        		},
                        		error:function(){
                        			alert("删除出错!");
                        		}
                        	});
                		}
        			});
                }); 

                
            });
        </script>
    </body>
</html>