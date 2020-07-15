<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<head>
<meta charset="UTF-8">
</head>
<body>
<title>공지사항 등록</title>
<style type="text/css">
span {
	text-align: center;
}
.ui-datepicker .ui-widget-content {
       background: #999 none;
}
.td-input{
	padding-bottom: 0px;
}
</style>
	<div class="container">
		<div class="row">
			<div class="col">
				<form action="regist?${_csrf.parameterName}=${_csrf.token}" method="post" id="registForm" enctype="multipart/form-data">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="writer" value="${sessionScope.loginUser.email}">
					<div class="form-group row">
						<label for="middle-label" class="col-sm-2 col-form-label" style="margin-top: 8px; margin-bottom: 8px;">작성자</label>
						<div class="col-sm-10">
							<input type="text" class="form-control noticeform" id="writer" value="${sessionScope.loginUser.nickname }" readonly>
							<div class="line-box">
								<div class="line"></div>
							</div>	
						</div>
					</div>
					<div class="form-group row">
						<label for="middle-label" class="col-sm-2 col-form-label" 
						style="margin-top: 8px; margin-bottom: 8px;">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control noticeform" id="title" name="title" placeholder="제목">
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
				  			<input type="text" class="datepicker form-control" id="startDate" name="startDate" autocomplete="off">
							<div class="line-box">
								<div class="line"></div>
							</div>
				  		</div>
						<label for="middle-label" class="col-sm-2 col-form-label" 
						style="margin-top: 8px; margin-bottom: 8px;">종료일</label>
						<div class="col-4">
				  			<input type="text" class="datepicker form-control" id="endDate" name="endDate" autocomplete="off">
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
							</textarea>
						</div>
				 	</div>	
					<div class="form-group">
						<label for="file">파일첨부</label>
						<input type="file" class="form-control-file" id="file" name="file">
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<button type="button" class="btn btn-primary" onclick="formRegist()">등록</button>
		</div>
	</div>
<script>
$(function() {
    $(".datepicker").datepicker({dateFormat: 'yy-mm-dd'});
  });

function formRegist(){
	var registForm = document.getElementById('registForm');
	registForm.submit();
}
</script>
<%@ include file="/WEB-INF/views/commons/summernote.jsp" %>
</html>
</body>