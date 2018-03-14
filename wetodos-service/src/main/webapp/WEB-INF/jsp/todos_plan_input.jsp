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
                                            <h5>学习计划管理</h5>
                                            <!-- .toolbar -->
                                            <div class="toolbar">
                                                <ul class="nav">
                                                    <li><a href="todos_plan_input">学习计划创建</a></li>
                                                    <li class="dropdown">
                                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                                            <i class="icon-th-large"></i>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                        	<li><a href="todos_plan_list">学习计划列表</a></li>
                                                            <li><a href="todos_plan_input">学习计划录入</a></li>
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
                                        
                                        
                                        <div id="div-1"  class="accordion-body collapse in body">
	                                        <div class="span12">
	                                        	<div class="span8">
		                                            <table id="bookDataTable" class="table table-bordered table-condensed table-hover table-striped">
		                                                <thead>
		                                                    <tr>
		                                                    	<th>序号</th>
		                                                        <th>学习资源条目</th>
		                                                        <th>类型</th>
		                                                        <th>创建时间</th>
		                                                        <th>修改时间</th>
		                                                        <th>状态</th>
		                                                        <th>操作</th>
		                                                    </tr>
		                                                </thead>
		                                            </table>
	                                        	</div>
	                                        	<div class="span4">
	                                        		
									                <div class="media_preview_area">
												        <div class="appmsg multi editing">
												            <div id="js_appmsg_preview" class="appmsg_content">
												                <div id="appmsgItem1" data-fileid="" data-id="1" class="js_appmsg_item ">
															        <div class="appmsg_info">
															            <em class="appmsg_date"></em>
															        </div>
															        <div class="cover_appmsg_item">
															            <h4 class="appmsg_title"><a href="javascript:void(0);" onclick="return false;" target="_blank">书名</a></h4>
															            <div class="appmsg_thumb_wrp">
															                <img class="js_appmsg_thumb appmsg_thumb" src="">
															                <i class="appmsg_thumb default">封面图片</i>
															            </div>
															            <div class="appmsg_edit_mask">
															                <a onclick="return false;" class="icon18_common edit_gray js_del" data-id="1" href="javascript:;">删除</a>
															            </div>
															        </div>
																</div>
																
																<div id="appmsgItem2" data-fileid="" data-id="2" class="appmsg_item js_appmsg_item ">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <a class="icon18_common del_gray js_del" data-id="2" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>
																<div id="appmsgItem3" data-fileid="" data-id="3" class="appmsg_item js_appmsg_item " style="display:none;">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <!--  <a class="icon18_common edit_gray js_edit" data-id="3" onclick="return false;" href="javascript:void(0);">编辑</a>-->
																        <a class="icon18_common del_gray js_del" data-id="3" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>
																<div id="appmsgItem4" data-fileid="" data-id="4" class="appmsg_item js_appmsg_item " style="display:none;">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <!--  <a class="icon18_common edit_gray js_edit" data-id="3" onclick="return false;" href="javascript:void(0);">编辑</a>-->
																        <a class="icon18_common del_gray js_del" data-id="3" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>																
																<div id="appmsgItem5" data-fileid="" data-id="5" class="appmsg_item js_appmsg_item " style="display:none;">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <!--  <a class="icon18_common edit_gray js_edit" data-id="3" onclick="return false;" href="javascript:void(0);">编辑</a>-->
																        <a class="icon18_common del_gray js_del" data-id="3" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>
																<div id="appmsgItem6" data-fileid="" data-id="6" class="appmsg_item js_appmsg_item " style="display:none;">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <!--  <a class="icon18_common edit_gray js_edit" data-id="3" onclick="return false;" href="javascript:void(0);">编辑</a>-->
																        <a class="icon18_common del_gray js_del" data-id="3" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>
																<div id="appmsgItem7" data-fileid="" data-id="7" class="appmsg_item js_appmsg_item " style="display:none;">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <!--  <a class="icon18_common edit_gray js_edit" data-id="3" onclick="return false;" href="javascript:void(0);">编辑</a>-->
																        <a class="icon18_common del_gray js_del" data-id="3" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>
																<div id="appmsgItem8" data-fileid="" data-id="8" class="appmsg_item js_appmsg_item " style="display:none;">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <!--  <a class="icon18_common edit_gray js_edit" data-id="3" onclick="return false;" href="javascript:void(0);">编辑</a>-->
																        <a class="icon18_common del_gray js_del" data-id="3" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>
																<div id="appmsgItem9" data-fileid="" data-id="9" class="appmsg_item js_appmsg_item " style="display:none;">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <!--  <a class="icon18_common edit_gray js_edit" data-id="3" onclick="return false;" href="javascript:void(0);">编辑</a>-->
																        <a class="icon18_common del_gray js_del" data-id="3" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>
																<div id="appmsgItem10" data-fileid="" data-id="10" class="appmsg_item js_appmsg_item " style="display:none;">
																    <img class="js_appmsg_thumb appmsg_thumb" src="">
																    <i class="appmsg_thumb default">缩略图</i>
																    <h4 class="appmsg_title"><a onclick="return false;" href="javascript:void(0);" target="_blank">书名</a></h4>
																    <div class="appmsg_edit_mask">
																        <!--  <a class="icon18_common edit_gray js_edit" data-id="3" onclick="return false;" href="javascript:void(0);">编辑</a>-->
																        <a class="icon18_common del_gray js_del" data-id="3" onclick="return false;" href="javascript:void(0);">删除</a>
																    </div>
																</div>																																
																																												
															</div>
													        <div class="appmsg_add icon24_common">
													          	<form id="multiNewsForm">
													                <a onclick="return false;" id="js_add_appmsg" href="javascript:void(0);">
													                		<span id="news_ids">
													                		</span>
													                    	<button class="btn btn-large" type="submit"  id="multiNewsAdd">完成学习计划（组合）提交</button>
													                </a>
													           	</form>
													        </div>
												        </div>
												    </div>
	                                        		<!-- 左侧 -->
	                                        	
	                                        	
	                                        	</div>
	                                        	
	                                        </div>

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
                /*----------- BEGIN bookDataTable CODE -------------------------*/
            	$('#bookDataTable').dataTable({
                	"processing": true,
                    "serverSide": true,
                    "ajax": "api/todos/book/list",
                    "columns": [
                        { "data": "id" },
                        { "data": "bookShow"},
                        { "data": "typeDesc"},
                        { "data": "ctime" },
                        { "data": "mtime" },
                        { "data": "statusDescription" }
                    ],
                    "columnDefs": [  
                        { "bSortable": false, 
                        	"targets": [1],
                        	"sWidth": "256px"
                        },
                        //{ "visible": false,  "targets": [2] },
                        { "targets": [6],
                        "data": "id" ,
                        "render": function(data, type, full) { return "<button class='btn view'  dataid='"+data+"'><i class='icon-search' ></i></button>  <button class='btn edit'  dataid='"+data+"'><i class='icon-edit' ></i></button>  <button class='btn btn-danger remove'  dataid='"+data+"'><i class='icon-remove'></i></button> <button class='btn set'  dataid='"+data+"'><i class='icon-check' title='增加此学习资源（图书）至学习计划'></i></button>"; } 
                        } 
                    ],
                   
                	"aaSorting": [[ 3, "desc" ]] ,
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

                /*----------- END bookDataTable CODE -------------------------*/
                $('#bookDataTable tbody').on( 'click', 'button.view', function () {
                	var sFeatures = "height=480, width=750, scrollbars=yes, resizable=yes";
                	var sUrl="todos_book_preview?id="+$(this).attr("dataid");
                	window.open( sUrl, 'preview', sFeatures );
                	return false;
                }); 
            	
                $('#bookDataTable tbody').on( 'click', 'button.edit', function () {
                    location.href = "todos_book_modify?id="+$(this).attr("dataid");
                }); 
                $('#bookDataTable tbody').on( 'click', 'button.remove', function () {
                    var idsvalue = $(this).attr("dataid");
        			bootbox.confirm("你确定要删除操作吗?", function(result) {
            			if(result){
                        	$.ajax({
                        		type:'post',
                        		url:'api/todos/book/delete',
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
                $('#bookDataTable tbody').on( 'click', 'button.set', function () {
                    var idsvalue = $(this).attr("dataid");
        			bootbox.confirm("你确定要将此学习资源（图书）添加到学习计划中吗?", function(result) {
            			if(result){
                        	$.ajax({
                        		type:'post',
                        		url:'api/todos/book/preview',
                        		data:{id:idsvalue},
                        		dataType:'json',
                        		beforeSend: function(){
                        		},
                        		success:function(data){
                        			if(data.success){
                            			if(data.code==0){

                            				var repeat = false;
                            				//判断id是否已经添加到多图文消息里
                            				$("#news_ids input").each(function(){
    									    	if($(this).val()==data.data.id){
    									    		repeat = true;
    									    		return false;
    										    }
    									 	});
    									 	if(repeat){
    										 	alert("您已经添加了此学习资源（图书），不能重复添加。");
    										 	return false;
    										}
    									 	var len = $("#news_ids").children("input").length;
    										if(len>4){
    											alert("学习资源（图书）项添加，不能超过4项。");
    											return false;
    										}
                            				var input=$("<input type='hidden' name='articleItemIds'></input>").val(data.data.id);
                            				input.appendTo($("#news_ids"));
                            				len = $("#news_ids").children("input").length;
                            				//增加消息到旁边的多图文消息预览里
                            				if(len==1){
                            					$("#appmsgItem1 .appmsg_title a").text(data.articleItem.title);
                                				$("#appmsgItem1 .js_appmsg_thumb").attr("src",STATIC_FILE_HOST+data.data.coverPicUrl);
                                				$("#appmsgItem1 .js_appmsg_thumb").show();
                                			}else if(len==2){
                                				$("#appmsgItem2 .appmsg_title a").text(data.articleItem.title);
                                				$("#appmsgItem2 .js_appmsg_thumb").attr("src",STATIC_FILE_HOST+data.data.coverPicUrl);
                                				$("#appmsgItem2 .js_appmsg_thumb").show();
                                				$("#appmsgItem2 .default").hide();
                                    		}else{
                                    			$("#appmsgItem"+len+" .appmsg_title a").text(data.articleItem.title);
                                				$("#appmsgItem"+len+" .js_appmsg_thumb").attr("src",STATIC_FILE_HOST+data.data.coverPicUrl);
                                				$("#appmsgItem"+len+" .js_appmsg_thumb").show();
                                				$("#appmsgItem"+len+" .default").hide();
                                				$("#appmsgItem"+len).show();
                                        	}
                            				
                                		}else{
                                    		alert("访问出错！");
                                    	}
                            		}else{
                            			alert("访问出错！");
                                	}
                        		},
                        		error:function(){
                        			alert("出错了！");
                        		}
                        	});
                		}
        			});
                });
                //多图文已添加项删除动作
    			$(".js_del").click(function(){
    				var dataid= $(this).attr("data-id");
    				$("#news_ids input").each(function(index, item){
    			    	if((index+1)==dataid){
    			    		$(this).remove();
    			    		return false;
    				    }
    			 	});
    	    		if(dataid<3){
    					$("#appmsgItem"+dataid+" .appmsg_title a").text("书名");
        				$("#appmsgItem"+dataid+" .js_appmsg_thumb").attr("src","");
        				$("#appmsgItem"+dataid+" .js_appmsg_thumb").hide();
        				$("#appmsgItem"+dataid+" .default").show();
    				}else{
    					$(this).parent().parent().hide();
    				}
    			});
    			
                $('#multiNewsAdd').click(function () {
                	//var data = JSON.stringify($("#multiNewsForm").serializeArray());
                	var data = $("#multiNewsForm").serialize();
                	
    				//判断提交数据的长度
    				if(data.length==0){
    					alert("您需要选择至少一个学习资源（图书），请点击勾选操作按钮添加!");
    					return false;
    				}
        			bootbox.confirm("您确定要提交此学习计划吗?", function(result) {
            			if(result){
                        	$.ajax({
                        		type:'post',
                        		url:'api/news_add_multi.do',
                        		data:data,
                        		dataType:'json',
                        		beforeSend: function(){
                        		},
                        		success:function(data){
                        			if(data.success){
                        				bootbox.alert('成功添加学习计划' ,function(){
                    						message_box.show('将跳转到您的学习计划列表管理界面!','success');
                    						var  page_list = function(){
                    							location.href="todos_plan_list";
                    						}
                    						window.setTimeout(page_list, 1000); 
                    			 		});
                            		}
                        		},
                        		error:function(){
                        			alert("出错了!");
                        		}
                        	});
                		}
        			});
                }); 
                
                
                
            });
        </script>
    </body>
</html>