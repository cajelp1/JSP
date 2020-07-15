<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	
	function SubmitMemberRegist(formRole){
		var uploadCheck = $('input[name="checkUpload"]').val();
		if(!(uploadCheck>0)){
			alert("사진을 업로드 해주세요.");
			//('input[name=pictureFile]').click();
			return;
		}
		var form = $('form[role="'+formRole+'"]');
		form.submit();
	}
	
	
	
	//아래는 더이상 작동하지 않음.
	$('#registBtn').on('click',function(e){
		
		var uploadCheck = $('input[name="checkUpload"]').val();
		if(!(uploadCheck>0)){
			alert("사진을 업로드 해주세요.");
			//('input[name=pictureFile]').click();
			return;
		}
		var form = $('form[role="form"]');
		form.submit();
	});

</script>

