<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
function addFile(){
	var uploadFileCount = $('input[name="uploadFile"]').length;
	var attachFileCount = $('p[name=attachedFile]').length;
	if(uploadFileCount+attachFileCount >= 5){
		alert("파일추가는 5개까지만 가능합니다.");
		return;
	}
	var input=$('<input>').attr({"type":"file","name":"uploadFile"})
	  .css("display","inline"); 
	var div=$('<div>').addClass("inputRow");
	div.append(input).append("<button style='border:0;outline:0;' class='badge bg-red' type='button' onclick='removeFile();'>X</button");
	div.appendTo('.fileInput');
}
function removeFile(){
	$('div.fileInput').on('click','div.inputRow > button',function(event){
		$(this).parent('div.inputRow').remove();
	});
}

window.addEventListener('load', function(){
	$('#fileOpenCheck').on('click', function(){
		if($(this).is(":checked")){
			$('input[name=fileOpenYn]').val('n');
		} else {
			$('input[name=fileOpenYn]').val('y');
		}
		
	});
	
	$('.fileInput').on('change','input[type="file"]',function(event){
		if(this.files[0].size>1024*1024*40){
			alert("파일 용량이 40MB를 초과하였습니다.");
			this.value="";
			$(this).focus();
			return false;
		} 
	});
});
</script>