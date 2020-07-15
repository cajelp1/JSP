<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	
<%-- 	$('#registBtn').on('click',function(e){
		var registForm = $('form[role="form"]');
		
		console.log($('#title').val());
		
		if($('#title').val().trim().length <= 0){
			alert("제목을 입력하세요");
			return;
		}
		if($('#content').val().trim().length <= 0){
			alert("내용을 입력하세요")
			return;
		}
		
		registForm.submit();
	});
 --%>	
 
	$('#registBtn').on('click',function(e){
		var form = document.registForm;
		
		var files = $('input[name="uploadFile"]');
		for(var file of files){
			console.log(file.name+" : "+file.value);
			if(file.value==""){
				alert("파일을 선택하세요.");
				file.focus();
				file.click();
				return;
			}
		}
		
		if($('input[name="title"]').val()==""){ //form.title.value
			alert("제목은 필수입니다.");
			$("input[name='title']").focus();
			return;
		}
		
		//이러면 value만 가져오나?
		if(form.content.value.length>1000){
			alert("글자수가 1000자를 초과할 수 없습니다.");
			return;
		}
		
		form.submit();
	});
	
	
</script>