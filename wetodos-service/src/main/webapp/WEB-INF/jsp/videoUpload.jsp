<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if lt IE 7]>       <html class="no-js lt-ie9 lt-ie8 lt-ie7">   <![endif]-->
<!--[if IE 7]>          <html class="no-js lt-ie9 lt-ie8">          <![endif]-->
<!--[if IE 8]>          <html class="no-js lt-ie9">                 <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<%@ include file="common/html_head.jsp"%>
</head>
<body>
	<!-- BEGIN WRAP -->
	<div id="wrap">
		<%@ include file="common/top_bar.jsp"%>
		<%@ include file="common/header.jsp"%>
		<%@ include file="common/left.jsp"%>

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
										<div class="icons">
											<i class="icon-edit"></i>
										</div>
										<h5>视频上传</h5>
										<!-- .toolbar -->
										<div class="toolbar">
											<ul class="nav">
												<li><a href="videoList">视频列表</a></li>
												<li class="dropdown"><a data-toggle="dropdown"
													class="dropdown-toggle" href="#"> <i
														class="icon-th-large"></i>
												</a>
													<ul class="dropdown-menu">
														<li><a href="videoList">视频列表</a></li>
														<li><a href="article_cat_input">视频上传</a></li>
													</ul></li>
												<li><a class="accordion-toggle minimize-box"
													data-toggle="collapse" href="#div-1"> <i
														class="icon-chevron-up"></i>
												</a></li>
											</ul>
										</div>
										<!-- /.toolbar -->
									</header>
									<div id="div-1" class="accordion-body collapse in body">

										<div class="form-horizontal">
											<form id="videoForm" action=""
												method="post">
												<div class="control-group">
													<label for="name" class="control-label">提示<br></label>
													<div class="controls">
														<p>
															<br>
															1、点击“添加视频”添加视频文件，点击“添加封面”添加封面文件，然后点击“开始上传”按钮即可上传视频和封面。<br>
															2、取消上传为取消上传中的视频，上传成功的视频不能取消上传。
														</p>
													</div>
												</div>

												<div class="control-group">
													<label for="name" class="control-label">视频名称</label>
													<div class="controls">
														<input type="text" id="videoName" name="videoName"
															class="span6 input-tooltip" data-original-title="请输入视频名称"
															data-placement="bottom" />
													</div>
												</div>
												<div class="control-group">
													<label for="keywords" class="control-label">关键词</label>
													<div class="controls">
														<input type="text" id="keywords" name="keywords"
															class="span6 input-tooltip" data-original-title="请输入关键词"
															data-placement="bottom" />
													</div>
												</div>
												<div class="control-group">
													<label for="catDesc" class="control-label">描述</label>
													<div class="controls">
														<input type="text" id="videoDesc" name="videoDesc"
															class="span6 input-tooltip" data-original-title="请输入描述"
															data-placement="bottom" />
													</div>
												</div>

												<div class="control-group">
													<form id="form2">
														<input id="addVideo-file" type="file"
															style="display: none;" /> <input id="addCover-file"
															type="file" style="display: none;" />
													</form>
													<label class="control-label"></label>
													<div class="row" class="controls">
														<a id="addVideo" href="javascript:void(0);"
															class="btn btn-primary">添加视频</a> <a id="addCover"
															href="javascript:void(0);" class="btn btn-primary">添加封面</a>
														<a id="uploadFile" href="javascript:void(0);"
															class="btn btn-primary">开始上传</a>
													</div>
													<div class="control-group" id="resultBox"></div>
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
	<%@ include file="common/footer.jsp"%>
	<%@ include file="common/help_modal.jsp"%>

	<%@ include file="common/footer_script.jsp"%>

	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="http://imgcache.qq.com/open/qcloud/js/vod/crypto.js"></script>
	<script
		src="http://imgcache.qq.com/open/qcloud/js/vod/sdk/ugcUploader.js"></script>

	<script type="text/javascript">
	var index = 0;
	var cosBox = [];
	/** 
	 * 计算签名
	**/
	var getSignature = function(callback){
		$.ajax({
			url: '/cms/getSignature',
			type: 'POST',
			success: function(res){
				console.log("res",res);
				callback(res);
			}
		});
	};
	
	//持久化视频id、url
	function saveVideoInfo(fileId,url){
		var video = {
				videoName:$("#videoName").val(),
				keywords:$("#keywords").val(),
				videoDesc:$("#videoDesc").val(),
				fileId:fileId,
				url:url,
		};
		console.log("fileId",fileId);
		console.log("url",url);
		$.ajax({
			url: '/cms/saveVideoInfo',
			type: 'POST',
			data: video,
			success: function(res){
				console.log("res",res);
				alert("视频上传成功");
			}
		});
	}

	/**
	 * 添加上传信息模块
	 */
	
	var addUploaderMsgBox = function(type){
		var html = '<div class="uploaderMsgBox" name="box'+index+'">';
		if(!type || type == 'hasVideo') {
			html += '视频名称：<span name="videoname'+index+'"></span>；' + 
				'计算sha进度：<span name="videosha'+index+'">0%</span>；' + 
				'上传进度：<span name="videocurr'+index+'">0%</span>；' + 
				'fileId：<span name="videofileId'+index+'">   </span>；' + 
				'上传结果：<span name="videoresult'+index+'">   </span>；<br>' + 
				'地址：<span name="videourl'+index+'">   </span>；'+
				'<a href="javascript:void(0);" name="cancel'+index+'" cosnum='+index+' act="cancel-upload">取消上传</a><br>';
		}
		
		if(!type || type == 'hasCover') {
			html += '封面名称：<span name="covername'+index+'"></span>；' + 
			'计算sha进度：<span name="coversha'+index+'">0%</span>；' + 
			'上传进度：<span name="covercurr'+index+'">0%</span>；' + 
			'上传结果：<span name="coverresult'+index+'">   </span>；<br>' + 
			'地址：<span name="coverurl'+index+'">   </span>；<br>' + 
			'</div>'
		}
		html += '</div>';
		
		$('#resultBox').append(html);
		return index++;
	};

	/** 
	 * 直接上传视频
	**/
	$('#uploadVideoNow-file').on('change', function (e) {
		var num = addUploaderMsgBox('hasVideo');
		var videoFile = this.files[0];
		$('#result').append(videoFile.name +　'\n');
		var resultMsg = qcVideo.ugcUploader.start({
		videoFile: videoFile,
		getSignature: getSignature,
		allowAudio: 1,
		success: function(result){
			if(result.type == 'video') {
				$('[name=videoresult'+num+']').text('上传成功');
				$('[name=cancel'+num+']').remove();
				cosBox[num] = null;
			} else if (result.type == 'cover') {
				$('[name=coverresult'+num+']').text('上传成功');
			}
		},
		error: function(result){
			if(result.type == 'video') {
				$('[name=videoresult'+num+']').text('上传失败>>'+result.msg);
			} else if (result.type == 'cover') {
				$('[name=coverresult'+num+']').text('上传失败>>'+result.msg);
			}
		},
		progress: function(result){
			if(result.type == 'video') {
				$('[name=videoname'+num+']').text(result.name);
				$('[name=videosha'+num+']').text(Math.floor(result.shacurr*100)+'%');
				$('[name=videocurr'+num+']').text(Math.floor(result.curr*100)+'%');
				$('[name=cancel'+num+']').attr('taskId', result.taskId);
				cosBox[num] = result.cos;
			} else if (result.type == 'cover') {
				$('[name=covername'+num+']').text(result.name);
				$('[name=coversha'+num+']').text(Math.floor(result.shacurr*100)+'%');
				$('[name=covercurr'+num+']').text(Math.floor(result.curr*100)+'%');
			}
			
		},
		finish: function(result){
			$('[name=videofileId'+num+']').text(result.fileId);
			$('[name=videourl'+num+']').text(result.videoUrl);
			if(result.message) {
				$('[name=videofileId'+num+']').text(result.message);
			}
		}
		});
		if(resultMsg){
			$('[name=box'+num+']').text(resultMsg);
		}
		$('#form1')[0].reset();
	});
	$('#uploadVideoNow').on('click', function () {
		$('#uploadVideoNow-file').click();
	});
	/*
	 * 取消上传绑定事件，示例一与示例二通用
	 */
	$('#resultBox').on('click', '[act=cancel-upload]', function() {
		var cancelresult = qcVideo.ugcUploader.cancel({
			cos: cosBox[$(this).attr('cosnum')],
			taskId: $(this).attr('taskId')
		});
		console.log(cancelresult);
	});


	/** 
	 * 上传视频+封面
	**/
	var videoFileList = [];
	var coverFileList = [];
	// 给addVideo添加监听事件
	$('#addVideo-file').on('change', function (e) {
		var videoFile = this.files[0];
		videoFileList[0] = videoFile;
		$('#result').append(videoFile.name +　'\n');

	});
	$('#addVideo').on('click', function () {
		$('#addVideo-file').click();
	});
	// 给addCover添加监听事件
	$('#addCover-file').on('change', function (e) {
		var coverFile = this.files[0];
		coverFileList[0] = coverFile;
		$('#result').append(coverFile.name +　'\n');

	});
	$('#addCover').on('click', function () {
		$('#addCover-file').click();
	});

	var startUploader = function(){
		if(videoFileList.length){
			var num = addUploaderMsgBox();
			if(!coverFileList[0]){
				$('[name=covername'+num+']').text('没有上传封面');
			}
			var resultMsg = qcVideo.ugcUploader.start({
				videoFile: videoFileList[0],
				coverFile: coverFileList[0],
				getSignature: getSignature,
	            isTranscode: 1,//是否转码
				allowAudio: 1,
				success: function(result){
					if(result.type == 'video') {
						$('[name=videoresult'+num+']').text('上传成功');
						$('[name=cancel'+num+']').remove();
						cosBox[num] = null;
					} else if (result.type == 'cover') {
						$('[name=coverresult'+num+']').text('上传成功');
					}
				},
				error: function(result){
					if(result.type == 'video') {
						$('[name=videoresult'+num+']').text('上传失败>>'+result.msg);
					} else if (result.type == 'cover') {
						$('[name=coverresult'+num+']').text('上传失败>>'+result.msg);
					}
				},
				progress: function(result){
					if(result.type == 'video') {
						$('[name=videoname'+num+']').text(result.name);
						$('[name=videosha'+num+']').text(Math.floor(result.shacurr*100)+'%');
						$('[name=videocurr'+num+']').text(Math.floor(result.curr*100)+'%');
						$('[name=cancel'+num+']').attr('taskId', result.taskId);
						cosBox[num] = result.cos;
					} else if (result.type == 'cover') {
						$('[name=covername'+num+']').text(result.name);
						$('[name=coversha'+num+']').text(Math.floor(result.shacurr*100)+'%');
						$('[name=covercurr'+num+']').text(Math.floor(result.curr*100)+'%');
					}
				},
				finish: function(result){
					$('[name=videofileId'+num+']').text(result.fileId);
					$('[name=videourl'+num+']').text(result.videoUrl);
					saveVideoInfo(result.fileId,result.videoUrl);
					if(result.coverUrl) {
						$('[name=coverurl'+num+']').text(result.coverUrl);
					}
					if(result.message) {
						$('[name=videofileId'+num+']').text(result.message);
					}
				}
			});
			if(resultMsg){
				$('[name=box'+num+']').text(resultMsg);
			}
		} else {
			alert("请添加视频");
			$('#result').append('请添加视频！\n');
		}
		
	}

	// 上传按钮点击事件
	$('#uploadFile').on('click', function () {
		var secretId = $('#secretId').val();
		var secretKey = $('#secretKey').val();
		var videoName = $("#videoName").val();
		var keywords = $("#keywords").val();
		var videoDesc = $("#videoDesc").val();
		var msg = "";
		if(videoName==null||videoName==""){
			msg+="视频名称是必填项,";
		}
		if(keywords==null||keywords==""){
			msg+="关键字是必填项,";
		}
		if(videoDesc==null||videoDesc==""){
			msg+="描述是必填项";
		}
		if(msg!=""){
			alert(msg);
			return false;
		}
		$("#resultBox").empty();
		startUploader();
		$('#form2')[0].reset();
	});

	/** 
	 * 直修改封面
	**/
	$('#changeCover-file').on('change', function (e) {
		var num = addUploaderMsgBox('hasCover');
		var changeCoverFile = this.files[0];
		var fileId = $('[name=fileId]').val();
		var resultMsg = qcVideo.ugcUploader.start({
		fileId: fileId,
		coverFile: changeCoverFile,
		getSignature: getSignature,
		success: function(result){
			if(result.type == 'video') {
				$('[name=videoresult'+num+']').text('上传成功');
			} else if (result.type == 'cover') {
				$('[name=coverresult'+num+']').text('上传成功');
			}
		},
		error: function(result){
			if(result.type == 'video') {
				$('[name=videoresult'+num+']').text('上传失败>>'+result.msg);
			} else if (result.type == 'cover') {
				$('[name=coverresult'+num+']').text('上传失败>>'+result.msg);
			}
		},
		progress: function(result){
			if(result.type == 'video') {
				$('[name=videoname'+num+']').text(result.name);
				$('[name=videosha'+num+']').text(Math.floor(result.shacurr*100)+'%');
				$('[name=videocurr'+num+']').text(Math.floor(result.curr*100)+'%');
			} else if (result.type == 'cover') {
				$('[name=covername'+num+']').text(result.name);
				$('[name=coversha'+num+']').text(Math.floor(result.shacurr*100)+'%');
				$('[name=covercurr'+num+']').text(Math.floor(result.curr*100)+'%');
			}
			
		},
		finish: function(result){
			$('[name=coverurl'+num+']').text(result.coverUrl);
			if(result.message) {
				$('[name=coverurl'+num+']').text(result.message);
			}
		}
		});
		if(resultMsg){
			$('[name=box'+num+']').text(resultMsg);
		}
		$('#form1')[0].reset();
	});
	$('#changeCover').on('click', function () {
		$('#changeCover-file').click();
	});
</script>
</body>
</html>