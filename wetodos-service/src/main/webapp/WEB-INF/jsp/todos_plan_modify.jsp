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
                        
                            <!--BEGIN INPUT TEXT FIELDS-->
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
                                        <div id="div-1" class="accordion-body collapse in body">
                                        	<div class="span12">
                                        	
  												<div class="span7">
	  												<div class="form-horizontal">
	  												<form id="todosPlanForm" action="api/todos/plan/update" method="post" >
	  												<input type="hidden" id="id" name="id" value="${learningPlan.id}" />
	  												<input type="hidden" id="bookIds" name="bookIds" value="${learningPlan.bookIds}"/>
	                                                <div class="control-group">
	                                                    <label for="name" class="control-label">计划名称</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="name" name="name" class="span6 input-tooltip" data-placement="top" value="${learningPlan.name }"/>
	                                                    </div>
	                                                </div>
	                                                <div class="control-group">
	                                                    <label for="name" class="control-label">计划类型</label>
	                                                    <div class="controls controls-row">
															<input id="actTermRadio" class="uniform" type="radio" name="type" value="0" <c:if test="${learningPlan.type==0}"> checked="checked" </c:if>>自由学期
	                                                        <input id="fixTermRadio" class="uniform" type="radio" name="type" value="1" <c:if test="${learningPlan.type==1}"> checked="checked" </c:if>>固定学期  
														</div>
	                                                </div>
	                                                <div class="control-group" id="datePanel" style="display: none;">
	                                                    <label for="startDate" class="control-label">开始时间</label>
	                                                    <div class="controls with-tooltip">
	                                                        <input type="text" id="startDate" name="startDate" value="${learningPlan.startDate }" class="span6 input-tooltip" 
	                                                        		data-original-title="请输入开始时间" data-placement="bottom" />
	                                                    </div>
	                                                </div>
	                                                
													<div class="form-actions">
														<input type="submit" value="提交" class="btn btn-primary">
	                                                </div>
	                                                </form>
	                                            </div>
                                            
                                            </div>
                                        	
                                        	<div class="span5">
												<c:forEach items="${learningPlan.books }" var="book" varStatus="st">					            				
													<div class="thumbnail" id="news_thumbnail">
		                                        	  <h4 id="news_title">${book.title }</h4>
		                                        	  <div id="cover_wrapper">
									                  	<img src="${fileHost }${book.coverPicUrl }" id="news_cover" >
									                  	<i>封面图片</i>                           	  
		                                        	  </div>
									                  <div class="caption">
									                    <p id="news_digest" >
									                    	${book.digest }
									                    </p>
									                  </div>
									                </div>
						            			</c:forEach>
											</div>

            							</div>
                                        	
                                     </div>
                                   </div>
                                </div>
                            </div>
                            <!--END TEXT INPUT FIELD-->

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
	
		
		$().ready(function() {

			  
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
			    return true;
			}
		 	// post-submit callback 
		 	function showResponse(data)  { 
			 	if(data.success){
					bootbox.alert('学习计划已成功修改' ,function(){
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
            
            var a = $("input[name='type']:checked").val();
            if(a==1){
            	$("#datePanel").show();
            }
            
            $('#startDate').datepicker({
                //maxDate: 0  // 当前日期之后的 0 天，就是当天
                minDate: 0, // 当前日期之前的 5 天
                hideIfNoPrevNext: true   
           })
		});
      </script>
    </body>
</html>