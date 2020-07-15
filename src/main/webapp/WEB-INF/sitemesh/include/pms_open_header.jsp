<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
    <!-- jQuery -->
    <!-- 	<script  src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- jQuery UI -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.structure.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.theme.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    <!-- default-header.css 스타일 수정할거면 여기서 할것! -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/default-header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
    
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
</head>