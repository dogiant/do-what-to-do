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
                                                        	<li><a href="todos_banner_input">每日一图录入</a></li>
                                                        	<li><a href="todos_banner_list">每日一图列表</a></li>
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
                                            <table id="bannerDataTable" class="table table-bordered table-condensed table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                    	<th>序号</th>
                                                        <th>每日一图</th>
                                                        <th>文字</th>
                                                        <th>日期</th>
                                                        <th>创建时间</th>
                                                        <th>修改时间</th>
                                                        <th>状态</th>
                                                        <th>操作</th>
                                                    </tr>
                                                </thead>
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
        var STATIC_FILE_HOST = "${fileHost}";
            $(function() {
                /*----------- BEGIN bannerDataTable CODE -------------------------*/
            	$('#bannerDataTable').dataTable({
                	"processing": true,
                    "serverSide": true,
                    "ajax": "api/todos/banner/list",
                    "columns": [
                        { "data": "id" },
                        { "data": "imageUrl"},
                        { "data": "text"},
                        { "data": "date"},
                        { "data": "ctime" },
                        { "data": "mtime" },
                        { "data": "statusDescription" }
                    ],
                    "columnDefs": [  
                        { "bSortable": false, 
                        	"targets": [1],
                        	"sWidth": "256px"
                        },
                        { "bSortable": false, 
                        	"targets": [2],
                        	"sWidth": "256px"
                        },
                        { "targets": [1],
                            "data": "imageUrl" ,
                            "render": function(data, type, full) { return "<img src='"+STATIC_FILE_HOST+data+"'/>"; } 
                        },
                        { "targets": [7],
                        "data": "id" ,
                        "render": function(data, type, full) { return "<button class='btn edit'  dataid='"+data+"'><i class='icon-edit' ></i></button>  <button class='btn btn-danger remove'  dataid='"+data+"'><i class='icon-remove'></i></button>"; } 
                        } 
                    ],
                   
                	"aaSorting": [[ 5, "desc" ]] ,
                    "sPaginationType": "bootstrap",
                   // "dom": '<"top"i>rt<"bottom"flp><"clear">',
                    "oLanguage": {
                    	"sLoadingRecords": "正在加载中......",
                    	"sProcessing": "正在加载中......",
                        "sLengthMenu": "每页显示 _MENU_ 条记录",
                        "sZeroRecords": "没有检索到相关数据",
                        "sEmptyTable": "数据表中没有相关数据",
                        "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                        "sInfoEmpty": "没有相关数据",
                        "sInfoFiltered": "数据表中共 _MAX_ 条记录",
                        "sSearch": "检索 ",
                        "oPaginate": {
                            "sFirst": "首页",
                            "sPrevious": "上一页",
                            "sNext": "下一页",
                            "sLast": "末页"
                        }
                    }
                });

                /*----------- END bannerDataTable CODE -------------------------*/
                $('#bannerDataTable tbody').on( 'click', 'button.view', function () {
                	var sFeatures = "height=480, width=750, scrollbars=yes, resizable=yes";
                	var sUrl="todos_book_preview?id="+$(this).attr("dataid");
                	window.open( sUrl, 'preview', sFeatures );
                	return false;
                }); 
            	
                $('#bannerDataTable tbody').on( 'click', 'button.edit', function () {
                    location.href = "todos_banner_modify?id="+$(this).attr("dataid");
                }); 
                $('#bannerDataTable tbody').on( 'click', 'button.remove', function () {
                    var idsvalue = $(this).attr("dataid");
        			bootbox.confirm("您确定要删除操作吗?", function(result) {
            			if(result){
                        	$.ajax({
                        		type:'post',
                        		url:'api/todos/banner/delete',
                        		data:{ids:idsvalue},
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
                }); 
                
            });
        </script>
    </body>
</html>