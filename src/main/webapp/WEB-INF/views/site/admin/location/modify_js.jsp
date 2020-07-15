<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>

//버튼 누르면 해당 fileInput에 파일 넣기
$('#change0').on('click',function(){
	$('#newPic0').click();
})

$('input#newPic0').on('change',function(event){
	
	var fileFormat=
		this.value.substr(this.value.lastIndexOf(".")+1).toUpperCase();
	//이미지 확장자 jpg 확인
	if(fileFormat!="JPG"){
		alert("이미지는 jpg 형식만 가능합니다.");
		return;
	} 
	//이미지 파일 용량 체크
	if(this.files[0].size>1024*1024*1){
		alert("사진 용량은 1MB 이하만 가능합니다.");
		return;
	}
	var filename = this.files[0].name;
	$('#picName0').val(filename);
});
$('#change1').on('click',function(){
	$('#newPic1').click();
})
$('#newPic1').on('change',function(){
	var fileFormat=
		this.value.substr(this.value.lastIndexOf(".")+1).toUpperCase();
	//이미지 확장자 jpg 확인
	if(fileFormat!="JPG"){
		alert("이미지는 jpg 형식만 가능합니다.");
		return;
	} 
	//이미지 파일 용량 체크
	if(this.files[0].size>1024*1024*1){
		alert("사진 용량은 1MB 이하만 가능합니다.");
		return;
	}
	var filename = this.files[0].name;
	$('#picName1').val(filename);
})

$('#change2').on('click',function(){
	$('#newPic2').click();
})
$('#newPic2').on('change',function(){
	var fileFormat=
		this.value.substr(this.value.lastIndexOf(".")+1).toUpperCase();
	//이미지 확장자 jpg 확인
	if(fileFormat!="JPG"){
		alert("이미지는 jpg 형식만 가능합니다.");
		return;
	} 
	//이미지 파일 용량 체크
	if(this.files[0].size>1024*1024*1){
		alert("사진 용량은 1MB 이하만 가능합니다.");
		return;
	}
	var filename = this.files[0].name;
	$('#picName2').val(filename);
})

/* function removeAttach(i){
	var fileNo = ($('#oldPic'+i).val());
	
	if(!fileNo){
		alert('저장한 파일이 없습니다.');
		return;
	}
	
	if(confirm('첨부파일을 삭제하시겠습니까?')){
		$.ajax({
			url:"${pageContext.request.contextPath}/dsp/admin/location/removeAttach",
			type:"post",
			data:{
				no : fileNo
			},
			success:function(data){
				alert('파일 삭제했습니다.');
				$('#picName'+i).val('');			
			},
			error:function(xhr){
				alert('잠시후에 다시 시도해주십시오.');
			}	
		})
	}
	
} */
	function modify(){
		var fileValue = $('#newPic0').val();
// 		alert(fileValue);
		if(fileValue==""){
			$("#newPic0").attr("disabled",true);			
		}
		fileValue= $('#newPic1').val();
		if(fileValue==""){
			$("#newPic1").attr("disabled",true);			
		}
		fileValue= $('#newPic2').val();
		if(fileValue==""){
			$("#newPic2").attr("disabled",true);			
		}
// 		alert(fileValue);
// 		alert('??');
		document.getElementById('modifyForm').submit();
	}

var token = $("meta[name='_csrf']").attr("content");

var header = $("meta[name='_csrf_header']").attr("content");

$('#city').on('change',function(){
	var city = $('option:selected',this).val();
	
	$.ajax({
		url : '<%=request.getContextPath()%>/dsp/room/getJusoArea',
		type : "post",
		data : { 'city' : city },
		beforeSend: function( xhr ) {
		  xhr.setRequestHeader(header, token);
		},
		success : function(res){
			
			code = "";
			
			if(res.length > 0){
				$.each(res, function(i){
					code += "<option value='"+res[i].area+"'>"+res[i].area+" ("+res[i].detail+")"+"</option>"
				})
				$('#area').empty();
			}
			
			$('#area').html(code);
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		}
	})
})



</script>
