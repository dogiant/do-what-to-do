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
		<link type="text/css" rel="stylesheet" href="assets/css/style_singlenews.css">
        <link type="text/css" rel="stylesheet" href="assets/css/style_multinews.css">
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
													          	<form id="todosPlanForm" action="api/todos/plan/add" method="post">
													          		<div class="control-group">
					                                                    <label for="name" class="control-label">计划名称</label>
					                                                    <div class="controls with-tooltip">
					                                                        <input type="text" id="name" name="name" class="span12 input-tooltip" 
					                                                        		data-original-title="请输入计划名称" data-placement="bottom" />
					                                                    </div>
					                                                </div>
					                                                <div class="control-group">
					                                                    <label for="name" class="control-label">计划类型</label>
					                                                    <div class="controls controls-row">
																			<input id="actTermRadio" class="uniform" type="radio" name="type" value="0" checked="checked">自由学期
					                                                        <input id="fixTermRadio" class="uniform" type="radio" name="type" value="1" >固定学期  
																		</div>
					                                                </div>
																	<div class="control-group">
					                                                    <label for="coverPicUrl" class="control-label">封面</label>
					                                                    <div class="controls with-tooltip">
					                                                            <div id="uploadTips">
													
				                                                         		</div>
					                                                            <span class="btn btn-file">
				                                                                    <span onclick="uploadPicAjax.click()">选择图片</span>
				                                                                    <input id="coverPicUrl" type="hidden" name="coverPicUrl" />
				                                                                </span>
						                                                         
						                                                         <p class="js_cover upload_preview" style="display: none;">
						                                                         	<img id="cover_preview"  src="">
																					<button id="removeCover">删除</button>
				               													 </p>
					                                                    </div>
					                                                </div>
					                                                <div class="control-group">
					                                                    <label for="planDigest" class="control-label">摘要</label>
					                                                    <div class="controls">
					                                                    	<textarea id="planDigest" name="digest" class="span6" style="height:260px"></textarea>
					                                                    </div>
					                                                </div>
					                                                
													          		<div class="control-group" id="datePanel" style="display: none;">
					                                                    <label for="startDate" class="control-label">开始时间</label>
					                                                    <div class="controls with-tooltip">
					                                                        <input type="text" id="startDate" name="startDate" class="span6 input-tooltip" 
					                                                        		data-original-title="请输入开始时间" data-placement="bottom" />
					                                                    </div>
					                                                </div>
													                <span id="news_ids">
													                	<input type="hidden" id="bookIds" name="bookIds" />
													                </span>
													                <button class="btn btn-large" type="submit"  id="multiNewsAdd">完成学习计划（组合）提交</button>
													           	</form>
													           	
													           	<div style="display: none;">
				  												    <form id="uploadPicAjaxForm" action="/upload/api"  enctype="multipart/form-data"  method="post" >
				                                                    	<input id="uploadPicAjax" name="uploads" type="file" onchange="uploadPicAjaxSubmit(this);"/>
					                                                </form>
				                                                </div>
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
    				data : {type:"json","channel":"todos",genThumbnails:true,"sizes":"360,null,_360;200,null,_200",uploads:$file.val()},
    				beforeSubmit : function() {
    					$("#uploadTips").show();
    					$("#uploadTips").html("正在上传封面，请稍候……");
    				},
    				success : function(data) {
    					if (data.success) {
    						$("#uploadTips").hide();
    						$("#coverPicUrl").val(data.result[0]);
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
                            				var bookIds = new Array(); //定义一数组 
                            				
                            				if($("#bookIds").val()!=''){
                                				bookIds = $("#bookIds").val().split(","); //字符分割 
                                				for (i=0; i<bookIds.length; i++ ) { 
                                					if(bookIds[i]==data.data.id){
                                						repeat = true;
        									    		return false;
                                					} 
                                				} 
        									 	if(repeat){
        										 	alert("您已经添加了此学习资源（图书），不能重复添加。");
        										 	return false;
        										}
                            				}
                            				
    									 	var len = bookIds.length;
    										if(len>4){
    											alert("学习资源（图书）项添加，不能超过4项。");
    											return false;
    										}
                            				bookIds.push(data.data.id);
                            				$("#bookIds").val(bookIds.join(","));
                            				len = bookIds.length;
                            				//增加消息到旁边的多图文消息预览里
                            				if(len==1){
                            					$("#appmsgItem1 .appmsg_title a").text(data.data.title);
                                				$("#appmsgItem1 .js_appmsg_thumb").attr("src",STATIC_FILE_HOST+data.data.coverPicUrl);
                                				$("#appmsgItem1 .js_appmsg_thumb").show();
                                			}else if(len==2){
                                				$("#appmsgItem2 .appmsg_title a").text(data.data.title);
                                				$("#appmsgItem2 .js_appmsg_thumb").attr("src",STATIC_FILE_HOST+data.data.coverPicUrl);
                                				$("#appmsgItem2 .js_appmsg_thumb").show();
                                				$("#appmsgItem2 .default").hide();
                                    		}else{
                                    			$("#appmsgItem"+len+" .appmsg_title a").text(data.data.title);
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
    				
    				var bookIds = $("#bookIds").val().split(","); //字符分割 
    				var bookIdsNew = new Array(); 
    				for (i=0;i<bookIds.length;i++ ) { 
    					if(bookIds[i]==dataid){
    						//
    					}else{
    						bookIdsNew.push(bookIds[i]);
    					}
    				} 
    				
    				$("#bookIds").val(bookIdsNew.join(","))
    				
    	    		if(dataid<3){
    					$("#appmsgItem"+dataid+" .appmsg_title a").text("书名");
        				$("#appmsgItem"+dataid+" .js_appmsg_thumb").attr("src","");
        				$("#appmsgItem"+dataid+" .js_appmsg_thumb").hide();
        				$("#appmsgItem"+dataid+" .default").show();
    				}else{
    					$(this).parent().parent().hide();
    				}
    			});
    			
                
    			$("#todosPlanForm").validate({
    			       rules: {
    			        	"name":  {
    							required: true
    						}
    					},
    					messages: {
    						"name":{
    							required:"请输入学习计划名称"
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
    			 		var len = $("#bookIds").val().split(",").length;
        				//判断提交数据的长度
        				if($("#bookIds").val()=='' || len==0){
        					alert("您需要选择至少一个学习资源（图书），请点击勾选操作按钮添加!");
        					return false;
        				}
    			 		
    				    return true;
    				}
    			 	// post-submit callback 
    			 	function showResponse(data)  { 
    				 	if(data.success){
    						bootbox.alert('学习计划已成功录入' ,function(){
    							message_box.show('将跳转到学习计划管理界面!','success');
    							var page_list = function(){
    								location.href="todos_plan_list";
    							}
    							window.setTimeout(page_list, 1000); 
    				 		});
    					}else{
    						alert('发布失败: '+ '\n\n 状态码: \n' + data.resultInfo.returnCode + '\n\n 提示信息: \n' + data.resultInfo.returnMsg +  '.'); 
    					}
    			 	}
                
                
                
                
                $("#fixTermRadio").click(function(){
                	$("#datePanel").show();
                });
                
                $("#actTermRadio").click(function(){
                	$("#datePanel").hide();
                });
                
                $('#startDate').datepicker({
                    //maxDate: 0  // 当前日期之后的 0 天，就是当天
                    minDate: 0, // 当前日期之前的 5 天
                    hideIfNoPrevNext: true   
               })
                
               
	   			$("#removeCover").click(function(){
					$("#coverPicUrl").val("");
					$("#cover_preview").attr("src", "");
					$(".upload_preview").css({"display":"none"});
				});
            });
        </script>
    </body>
</html>