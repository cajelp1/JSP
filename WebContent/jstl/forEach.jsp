<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	for(int i = 2; i < 10; i++){
		out.println(i+"단 입니다<br/>");
		for(int j = 1; j<10; j++){
			out.println(i +" * "+ j +" = "+ i*j);
		}
		out.println("<br/>");
	}
%>


<c:forEach var="i" begin="2" end="9" step="1">
	${i }단 입니다<br>
	<c:forEach var="j" begin="1" end="9" step="1">
		${i } * ${j } = ${i*j }<br>
	</c:forEach>
	<br>
</c:forEach>


<%
	List<String> strList = new ArrayList<String>();
	strList.add("1");
	strList.add("2");
	strList.add("3");
	strList.add("a");
	strList.add("b");
	strList.add("c");
	
	for(String str : strList){
		out.println(str+"<br/>");
	}
	
%>


<c:forEach var="str" items="<%=strList %>" >
	${str }<br/>
</c:forEach>





















<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
</html>