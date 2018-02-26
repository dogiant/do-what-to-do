<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                            <h5>文章栏目修改</h5>
                                            <!-- .toolbar -->
                                            <div class="toolbar">
                                                <ul class="nav">
                                                    <li><a href="article_cat_list">文章栏目管理</a></li>
                                                    <li class="dropdown">
                                                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                                            <i class="icon-th-large"></i>
                                                        </a>
                                                        <ul class="dropdown-menu">
                                                            <li><a href="article_cat_list">文章栏目列表</a></li>
                                                            <li><a href="artcile_cat_input">文章栏目录入</a></li>
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
                                        				
                                        	<div class="form-horizontal">
                                                <form id="articleCatForm"  action="api/article/cat/update" method="post" > 
                                                <input type="hidden" id="catId" name="catId" value="${articleCat.catId}" />
                                                <div class="control-group">
                                                    <label for="name" class="control-label">上级栏目</label>
                                                    <div class="controls">
                                                    	<select name="parent.catId" id="parentCatId">
                                                    		<option value="" <c:if test="${articleCat.parent.catId==null}"> selected="selected" </c:if>>顶级栏目</option>
                                                    		<c:forEach items="${articleCats}" var ="obj" varStatus="status">
																<option value="${obj.catId}" <c:if test="${articleCat.parent.catId==obj.catId}"> selected="selected" </c:if>>${obj.catNameShow }</option>
															</c:forEach>
                                                    	</select>
                                                    </div>
                                                </div>
                                                
                                                <div class="control-group">
                                                    <label for="name" class="control-label">栏目名称</label>
                                                    <div class="controls">
                                                    	<input type="text"  id="catName" name="catName" value="${articleCat.catName }" class="span6 input-tooltip"
                                                               data-original-title="请输入栏目名称" data-placement="bottom"/>
                                                    </div>
                                                </div>
                                                <div class="control-group" id="keyControl">
                                                    <label for="key" class="control-label">栏目代码(英文名)</label>
                                                    <div class="controls">
                                                    	<input type="text"  id="catCode" name="catCode" value="${articleCat.catCode }" class="span6 input-tooltip"
                                                               data-original-title="请输入栏目代码(英文名)" data-placement="bottom"/>
                                                    </div>
                                                </div>
                                                <div class="control-group">
														<label class="control-label">栏目类型</label>
														<div class="controls controls-row">
															<input type="radio" name="catType" value="1" <c:if test="${articleCat.catType==0}"> checked="checked" </c:if> class="uniform" readonly="readonly"/> 自由增设 
															<input type="radio" name="catType" value="0" <c:if test="${articleCat.catType==1}"> checked="checked" </c:if> class="uniform" readonly="readonly"/> 系统设定
														</div>
                                                </div>
                                                <div class="control-group">
														<label class="control-label">是否正文页栏目(非列表)</label>
														<div class="controls controls-row">
															<input type="radio" name="isTextCat" value="true" <c:if test="${articleCat.isTextCat}"> checked="checked" </c:if> class="uniform"/> 是
															<input type="radio" name="isTextCat" value="false" <c:if test="${!articleCat.isTextCat}"> checked="checked" </c:if> class="uniform"/> 否
														</div>
                                                </div>
                                                <div class="control-group">
														<label class="control-label">是否显示到导航</label>
														<div class="controls controls-row">
															<input type="radio" name="showInNav" value="true" <c:if test="${articleCat.showInNav}"> checked="checked" </c:if> class="uniform"/> 是
															<input type="radio" name="showInNav" value="false" <c:if test="${!articleCat.showInNav}"> checked="checked" </c:if> class="uniform"/> 否
														</div>
                                                </div>
                                                
                                                <div class="control-group">
                                                    <label for="sortOrder" class="control-label">排序字段</label>
                                                    <div class="controls">
                                                    	<input type="text"  id="sortOrder" name="sortOrder" value="${articleCat.sortOrder }" class="span6 input-tooltip"
                                                               data-original-title="请输入排序字段" data-placement="bottom"/>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label for="sortOrder" class="control-label">关键词</label>
                                                    <div class="controls">
                                                    	<input type="text"  id="keywords" name="keywords" value="${articleCat.keywords }" class="span6 input-tooltip"
                                                               data-original-title="请输入关键词" data-placement="bottom"/>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label for="sortOrder" class="control-label">描述</label>
                                                    <div class="controls">
                                                    	<input type="text"  id="catDesc" name="catDesc" value="${articleCat.catDesc }" class="span6 input-tooltip"
                                                               data-original-title="请输入描述" data-placement="bottom"/>
                                                    </div>
                                                </div>

												<div class="form-actions">
														<input type="submit" value="提交" class="btn btn-primary">
                                                </div>
                                                </form>
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
			 $("#articleCatForm").validate({
			        rules: {
			        	"catName":  {
							required: true,
							minlength: 3,
		    				maxlength: 36,
		    				remote:{
	    						url : "api/article/cat/checkSameLevelCatNameExists",
	    						cache : false,
	    						type: "post",
	    				        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    				        data: {
	    				        	parentCatId: function() {
	    				            	return $("#parentCatId").val();
	    				          	},
	    				          	catId: function(){
	    				          		return $("#catId").val();
	    				          	},
	    				          	catName: function() {
	    				          		return $("#catName").val();
	    				          	}
	    				        }
		    				}
						},
						"catCode":  {
							required: true,
							minlength: 3,
		    				maxlength: 128,
		    				remote:{
	    						url : "api/article/cat/checkSameLevelCatCodeExists",
	    						cache : false,
	    						type: "post",
	    				        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	    				        data: {
	    				        	parentCatId: function() {
	    				            	return $("#parentCatId").val();
	    				          	},
	    				          	catId: function(){
	    				          		return $("#catId").val();
	    				          	},
	    				          	catCode: function() {
	    				          		return $("#catCode").val();
	    				          	}
	    				        }
		    				}
						},
						"sortOrder":  {
							number: true
						}
					},
					messages: {
						"catName":{
							required:"请输入栏目名称",
							minlength: "请输入至少3位字符",
	    					maxlength: "长度不能超过36字符"
						},
						"catCode":  {
							required: "请输入栏目代码(英文名)",
							minlength: "请输入至少3位字符",
	    					maxlength: "长度不能超过128字符"
						},
						"sortOrder":  {
							number: "请输入一个数字排序值"
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
						bootbox.alert('普通栏目成功录入' ,function(){
							message_box.show('将跳转到普通栏目列表管理界面!','success');
							var page_list = function(){
								location.href="article_cat_list";
							}
							window.setTimeout(page_list, 500); 
				 		});
					}else{
						alert('发布失败: '+ '\n\n 状态码: \n' + data.resultInfo.returnCode + '\n\n 提示信息: \n' + data.resultInfo.returnMsg +  '.'); 
					}
			 	}
			});
        </script>

    </body>
</html>