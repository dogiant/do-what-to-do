/*
	author:zsb1987@foxmail.com
	date:2013-08-22
	version:v1.0.0
	
	@description:

	
*/

/*
	dialog弹出层
*/
(function($,w){
	w.gDialog = w.gDialog || {};
	var dialog = w.gDialog;
	var modalHtml='<div class="modal hide fade" data-backdrop="static" id="#{modalId}">'+
			  '<div class="modal-header">'+
				'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'+
				'<h3 class="_JHeadContent">#{title}</h3>'+
			  '</div> <div class="_JDialogContent"></div> </div>';
	$.extend(dialog,{
		fCreate:function(config){
			if(!config) return;
			if(!config.modalId){
				config.modalId='modal-'+new Date().getTime();
			}

			var modalContent=gUtils.fRenderTemplate(modalHtml,config);
			var $modalContent=$(modalContent).appendTo('body');
			
			$.ajax({
					url : config.url,
					dataType : 'html',
					cache:false,
					async:false,
					data:config.param || "",
					success : function(data,textStatus){
						$modalContent.find('._JDialogContent').html(data);
					}
				});
			var modalConfig={
					keyboard: false,
					show:false
				};
			var width=config['width'];
			if(width){
				modalConfig['width']=width;
			}
			$modalContent.modal(modalConfig);	
				
			var modal= $modalContent.data('modal');
			//modal can be reuse
			if(!config['reuse']){
				modal['removeDomWhenHide']=true;
			}
			return modal;
		},
		fClose:function(id){
		 var modalManager=$('body').modalmanager().data('modalmanager');
			if(modalManager!=null){
				if(modalManager.hasOpenModal()){
					modalManager.getTopModal().hide();
				}
			}
		},
		fAlert:function(msg){
			bootbox.alert(msg);
		},
		fConfirm:function(title,content,callback){
			bootbox.dialog(content,[{
						label: "确定",
						'class': "btn-success",
						callback: callback
				},{
						label: "取消",
						'class': "btn"
				}
			],{'header':title});
		}
	});
})(jQuery,window);

