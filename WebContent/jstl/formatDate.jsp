<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Date today = new Date();
	String todayStr = new SimpleDateFormat("yyyy-mm-dd").format(today);
	out.println(todayStr);
%>


<fmt:formatDate value="<%=today %>" pattern="yyyy-mm-dd" />

<%-- 아래의 var로 선언하면 따로 el문으로 불러온다 --%>
<br><br>

<fmt:formatDate value="<%=today %>" pattern="yyyy-mm-dd" var="today"/>
${today }


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
</html>