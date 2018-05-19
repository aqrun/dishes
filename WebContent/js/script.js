(function($){
	function foodDeleteHandle(){
		$('.btn_food_delete').on('click', function(e){
			e.preventDefault();
			var $s = $(this);
			var name = $s.data('name');
			var id = $s.data('id');
			if(!window.confirm("确定删除 [" + name + "]?")){
				return;
			}
			$.post(g.basePath + 'foods-handle?action=delete', {id: id}, function(data){
				console.log(data);
				alert("删除成功！");
				location.reload();
			})
		})
	}
	
	function foodFormHandle(){
		$('.btn_food_submit').on("click", function(e){
			e.preventDefault();
			var hasError = false;
			if($('.ipt_name').val().trim()==""){
				hasError = true;
			}
			if($('.ipt_price').val().trim()==''){
				hasError = true;
			}
			if(hasError){
				alert("name, price is required!")
				return;
			}
			$(this).parents('form').submit();
		})
	}
	
	function fileupload(){
		var url = g.basePath + "file-upload";
	    $('#fileupload').fileupload({
	        url: url,
	        dataType: 'json',
	        success:function(data){
	        	console.log(data)
	        	$('#progress .progress-bar').css('width','0%' );
	            if(data.code == 0){
	            	$('.ipt_thumb').val(data.uri);
	            	$('.img_preview').attr('src', g.basePath + data.uri);
	            }else{
	            	alert(data.msg);
	            	console.log(data);
	            }
	        },
	        done: function (e, data) {
	        	$('#progress .progress-bar').css('width','0%' );
	        },
	        progressall: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress .progress-bar').css(
	                'width',
	                progress + '%'
	            );
	        }
	    }).prop('disabled', !$.support.fileInput)
	        .parent().addClass($.support.fileInput ? undefined : 'disabled');
	}
	
	function filterHandle(){
		$('.btn_filter').on("click", function(e){
			e.preventDefault();
			var href = $(this).data('url');
			var name = $('.ipt_filter_name').val();
			if(name.trim() == '') return;
			location.href = href + '?name=' + name;
		})
	}
	
	$(function(){
		foodDeleteHandle();
		foodFormHandle();
		fileupload();
		filterHandle();
	})
})(jQuery)