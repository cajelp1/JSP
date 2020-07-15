<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	int price = 100000000;
	DecimalFormat df = new DecimalFormat("#,###");
	out.println(df.format(price));
%>

<span style="color:red;font-weight:bold;">
	<fmt:formatNumber value="<%=price %>" pattern="#,###"/>￦
</span>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
</html>