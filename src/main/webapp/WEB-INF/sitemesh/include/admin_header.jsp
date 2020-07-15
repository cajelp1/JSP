<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<script src="<%=request.getContextPath()%>/resources/site/js/common.js"></script>

<title><decorator:title default="DSP Administrator" /></title>

<!-- Our project just needs Font Awesome Solid + Brands -->
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/fontawesome.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/brands.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/solid.css" rel="stylesheet">
  
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/site/css/basicForm.css">
<style>
.navbar a{
	
	font-size: 1.2em;
}
.navbar li{
	list-style: none; 
}

.navbar-toggler {
	color: black;
	border-color: black;
}

.top-bg {
	background-color: #8e8e8e;
}

.navbar-nav ml-auto {
	text-size:8px;
	text-align:right;
}
.nav-link1{
	color : white;
}
.nav-link1:hover{
	color : white;
}

</style>
<script>

$(document).ready(function(){
	$("li a[href='<%=request.getRequestURI()%>']").addClass("active");
});

</script>
</head>
<header class="header">
	
	<nav class="navbar bg-dark">
		<nav class="navbar-expand-lg bg-dark container">
			<ul class="list-group list-group-horizontal mt-n2 mb-n2 ml-auto small">
				<li ><a class="nav-link1" href="<%=request.getContextPath()%>/dsp/main">메인페이지</a></li>&nbsp;&nbsp;&nbsp;
				<li ><a class="nav-link1" href="/commons/logout">로그아웃</a></li>
			</ul>
		</nav>
	</nav>
	
	<nav class="navbar top-bg navbar-dark">
		<nav class="navbar navbar-expand-lg top-bg container ">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarToggler"
				aria-controls="navbarToggler" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<a class="navbar-brand" href="<%=request.getContextPath()%>/dsp/main">
				<img src="<%=request.getContextPath()%>/resources/site/img/dsp-logo.png" style="height:50px;">
			</a>

			<div class="collapse navbar-collapse" id="navbarToggler">
			
				<ul class="navbar-nav m-2 ">
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dsp/admin/member/list">회원관리</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dsp/admin/location/list">사무실관리</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dsp/admin/payment/list">결제관리</a></li>
					<%-- <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dsp/admin/guide/main">이용안내</a></li> --%>
				</ul>
			
				
			
			</div>
		</nav>
	</nav>
	<div class="container">&nbsp;</div>
	
</header>