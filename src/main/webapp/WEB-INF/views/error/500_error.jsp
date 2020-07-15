<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>현재 요청이 지연되고 있습니다.</h1>
	<h1>잠시 후 다시 시도해주세요.</h1>
	<img alt="" src="<%=request.getContextPath() %>/resources/site/img/error.png" />
</body>
</html>