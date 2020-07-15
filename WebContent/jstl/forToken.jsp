<%@ page import="java.util.StringTokenizer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	String source = "010-4124-4534";
	StringTokenizer stn = new StringTokenizer(source, "-");
	//한번에 다 자르는게 아니라 달라고 할 때 자른다.
	//다음에 자를게 있으면 T/F로 알려주는게 hasMorToken.
	
	while(stn.hasMoreTokens()){
		out.println(stn.nextToken()+"<br/>");
	}
	
%>

<c:forTokens var="str" items="<%=source %>" delims="-" varStatus="status">	
	<%-- for문은 자르면서 page context안에 넣어준다. 그래서 forEach안에는 el문을 쓸 수 있음. --%>
	<%-- forEach 전에 있는 값들은 자바 소스에만 있고 context attr은 아니기에 가져올 수 없음. --%>
	
	${status.count } : ${str }
	
</c:forTokens>














<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
</html>