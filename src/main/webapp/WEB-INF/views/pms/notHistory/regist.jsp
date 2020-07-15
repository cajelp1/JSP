<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쪽지 작성</title>
</head>
<body>
	<form role="form" method="post" action="regist" name="registForm">
		<input type="hidden" name="projectId" value="${projectId}">
		<input type="hidden" name="sender" value="${sessionScope.loginUser.email}">
		<div class="form-group">
			<label for="title">제 목</label> 
			<input type="text" name="title" class="form-control" placeholder="제목을 쓰세요">
		</div>
		<div class="form-group">
			<label for="writer">작성자</label> 
			<input type="text" id="writer" class="form-control" value="${sessionScope.loginUser.nickname}" readonly>
		</div>
		<div class="form-group">
			<label for="content">내용</label> 
			<input type="text" id="content" name="content" class="form-control">
		</div>
		<div class="form-group">
			<label for="receiver">받는사람</label> 
			<input type="text" id="receiver" name="receiver" class="form-control">
		</div>
		<input type="submit" value="등록">
	</form>
</body>
</html>