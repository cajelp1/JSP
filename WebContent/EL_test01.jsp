<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%
	String str = "아 옆반 시끄러워 ㅡㅡ";
	pageContext.setAttribute("msg", str);
	request.setAttribute("msg", "너는 바보멍청이야아아아");
	session.setAttribute("msg", "세션에 넣은 메시지지롱~");
	application.setAttribute("msg", "이건 어플리케이션 메시지다 이말이야!");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<ul>
		<%-- <li>스크립트릿 : <% out.println(str); %></li> --%>
		<li>스크립트릿 : <% out.println(pageContext.getAttribute("msg")); %></li>
		<%-- <li>표현식 : <%= str %></li> --%>
		<li>표현식 : <%= pageContext.getAttribute("msg") %></li>
		<li>EL문 : ${applicationScope.msg} </li>
	</ul>
	
</body>
</html>








