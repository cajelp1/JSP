<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<!-- 데코레이터를 쓰기 때문에 해당 데코레이터의 head 태그 안에 들어간다. -->
<head>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css"/>
</head>

<!-- summernote -->
<script src = "<%= request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.min.js">
</script>
<script>

	$(function(){	//ready function. 문서 다 읽어내고 실행하라. 
					//왜냐고? 우리는 js를 footer에 넣으니까. 혹여나 관련 js가 로딩되기 전에 이게 실행될까봐.
		$('#content').summernote({
			/* summernote는 css가 안 먹힘.
			안에는 json 형태로 속성을 추가할 수 있음 */
			placeholder:'여기에 내용을 적으세요',
			height : '300px',
			
			callbacks:{
				onImageUpload : function(files, editor, welEditable) {
					//alert("image selected!");
					for (var i = files.length - 1; i >= 0; i--) {
						if(files[i].size > 1024*1024*5){
    	            		alert("이미지는 5MB 미만입니다.");
    	            		return;
						}
						for (var i = files.length - 1; i >= 0; i--) {
	    	            	sendFile(files[i], this);
	    	            }
					}
				},
				onMediaDelete : function(target) {
					deleteFile(target[0].src);
				}
			}
		});
	});
	
	
	function sendFile(file, el) { /* el은 커서위치..? */
		
		var form_data = new FormData();
		form_data.append("file", file);
		
		$.ajax({
			data : form_data,
			type : "post",
			url : '<%= request.getContextPath() %>/uploadImg.do',
			cache : false,
			contentType : false,
			processData : false,
			success : function(img_url){
				$(el).summernote('editor.insertImage', img_url);
			}
		});
	}
	
	
	function deleteFile(src) {
		
		var splitSrc = src.split("=");
		var fileName = splitSrc[splitSrc.length-1];
		
		var fileData = {
				fileName : fileName
		}
		
		$.ajax({
			url : "<%= request.getContextPath() %>/deleteImg.do",
			data : JSON.stringify(fileData),
			type : "post",
			success : function(res){
				console.log(res);
			}
		});
		
	}
	
	
</script>

