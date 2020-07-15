<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form action="login" method="post">
		아이디 : <input type = "text" name = "id" value="${param.id}"/><br>	
		패스워드 : <input type = "password" name = "pwd" /><br>
		<input type = "submit" value = "로그인" /><br>
	</form>
</body>

<script>
	if("${param.id}"!=""){
		alert("아이디 아니면 비밀번호가 틀렸서")
	}
</script>
</html>