<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>에러가 났어요!</h1>
	<%-- <p><%=exception.getMessage() %></p> --이렇게하면 에러내용 출력--%>
	<%-- <p><%=exception %></p> --이 경우는 어떤 에려인지와 내용 모두 출력--%>
	<p><%=exception.printStackTrace(new java.io.PrintWriter(out)) %></p>
	
</body>
</html>

<%-- 
	페이지 디렉토리에서 isErrorPage="true"로 하면 
	전단계의 에러를 이 페이지까지 가져온다.
--%>