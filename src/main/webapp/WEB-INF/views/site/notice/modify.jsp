<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ include file="../include/open_header.jsp"%> --%>
<%-- <%@ include file="../include/bootstrap.jsp" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>

<style type="text/css">
span {
	text-align: center;
}
</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<!-- test -->
<div class="container">
		<form action="modify?${_csrf.parameterName}=${_csrf.token}" method="post" id="modifyForm" enctype="multipart/form-data">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="hidden" name="writer" value="${board.writer }">
			<div class="form-group row">
				<label for="middle-label" class="col-sm-2 col-form-label" 
				style="margin-top: 8px; margin-bottom: 8px;">작성자</label>
				<div class="col-sm-10">
					<input type="text" class="form-control noticeform noticeform" id="writer" value="${board.writerNickName }" readonly>
					<div class="line-box">
						<div class="line"></div>
					</div>	
				</div>
			</div>
			<div class="form-group row">
				<label for="middle-label" class="col-sm-2 col-form-label" 
				style="margin-top: 8px; margin-bottom: 8px;">제목</label>
				<div class="col-sm-10">
					<input type="text" class="form-control noticeform" id="title" name="title" value="${board.title }">
					<div class="line-box">
						<div class="line"></div>
					</div>	
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<span>게시기간</span>
				</div>
			</div>
			<div class="form-group row">
				<label for="middle-label" class="col-sm-2 col-form-label" 
				style="margin-top: 8px; margin-bottom: 8px;">시작일</label>
				<div class="col-4">
		  			<input type="text" class="datepicker form-control noticeform" id="startDate" name="startDate" autocomplete="off"
		  			value="<fmt:formatDate value="${board.startDate }" pattern="yyyy-MM-dd" />">
					<div class="line-box">
						<div class="line"></div>
					</div>
		  		</div>
				<label for="middle-label" class="col-sm-2 col-form-label" 
				style="margin-top: 8px; margin-bottom: 8px;">종료일</label>
				<div class="col-4">
		  			<input type="text" class="datepicker form-control noticeform" id="endDate" name="endDate" autocomplete="off"
		  			value="<fmt:formatDate value="${board.endDate }" pattern="yyyy-MM-dd" />">
					<div class="line-box">
						<div class="line"></div>
					</div>
		  		</div>
			</div>
		 	<div class="row">
	 			<label for="middle-label" class="col-sm-2 col-form-label" 
				style="margin-top: 8px; margin-bottom: 8px;">내용</label>
				<div class="col-10">
		 			<textarea class="textarea" id="summernote" name="content" placeholder="1000자 내외로 작성하세요.">
		 			${board.content }
					</textarea>
				</div>
		 	</div>
			<div class="form-group">
				<label for="file">파일첨부</label>
				<input type="file" class="form-control-file" id="file" name="file">
			</div>
		 	<input type="hidden" id="sno" name="sno" value=${board.sno } />
		</form>
		<button type="button" class="btn btn-primary" onclick="formModify()">수정</button>
	</div>
<!-- test end -->
<script>
$(function() {
    $(".datepicker").datepicker({dateFormat: 'yy-mm-dd'});
//     var file = document.getElementById('oldFile').value;
//     alert(file);
//     alert($("input[name='oldFile']").val());
    
});

function formModify(){
	var file = $('input#file').val();
	if(file==""){
		$('input#file').attr("disabled",true);			
	}
	var modifyForm = document.getElementById('modifyForm');
	modifyForm.submit();
}
var inputFile = document.getElementById('file');

const fileSelector = document.getElementById('file');
  
fileSelector.addEventListener('change', (event) => {
    const fileList = event.target.files;
    var fileName = fileList[0].name;
    document.getElementById('inputFileName').value=fileName;
});

</script>
<%@ include file="/WEB-INF/views/commons/summernote.jsp" %>
</body>
</html>