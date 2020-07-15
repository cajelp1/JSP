<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator"  uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<script src="<%=request.getContextPath()%>/resources/site/js/common.js"></script>

<title><decorator:title default="DSP" /></title>

<!-- Our project just needs Font Awesome Solid + Brands -->
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/fontawesome.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/brands.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/solid.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/site/css/basicForm.css">
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
	background-color: #1E90FF;
}

.navbar-nav ml-auto {
	text-size:8px;
	text-align:right;
}
.nav-link1{
	color : white;
	padding-right: 1.5em;
}
.nav-link1:hover{
	color : white;
}
.navbar,.bg-secondary,.list-group,.navbar-expand-lg container{
	background-color: #1E90FF;
}
.navbar-expand-lg.container{
	text-align:right;
	background-color: #1E90FF;
}
.right{
	margin-left: 10em;
}
.login-container{
	justify-content: flex-end;
}
.member-name{
	color: white;
	font-size: medium;
	font-weight: bold;
	margin-right: 2em;
}

</style>
<script>

$(document).ready(function(){
	$("li a[href='<%=request.getRequestURI()%>']").addClass("active");
});

</script>
</head>
<header class="header">
	<nav class="navbar">
		<nav class="navbar-expand-lg container">
			<ul class="list-group list-group-horizontal mt-n2 mb-n2 ml-auto small">
				<c:if test="${loginUser != null && loginUser.role != 'ROLE_ADMIN'}">
					<span class="member-name">${loginUser.name }님 환영합니다!</span>
					<li ><a class="nav-link1" href="/dsp/my">마이페이지</a></li>&nbsp;&nbsp;&nbsp;
					<li ><a class="nav-link1" href="/commons/logout">로그아웃</a></li>
				</c:if>
				<c:if test="${loginUser != null && loginUser.role == 'ROLE_ADMIN'}">
					<li ><a class="nav-link1" href="/dsp/admin/member/list">관리자페이지</a></li>&nbsp;&nbsp;&nbsp;
					<li ><a class="nav-link1" href="/dsp/my">마이페이지</a></li>&nbsp;&nbsp;&nbsp;
					<li ><a class="nav-link1" href="/commons/logout">로그아웃</a></li>
				</c:if>
				<c:if test="${loginUser == null}">
					<li ><a class="nav-link1" href="/commons/member/agree"
						onclick="window.open(this.href,'_blank', 'width=800, height=640'); return false;">회원가입   </a></li>
					<li ><a class="nav-link1" href="/commons/member/findEmail"
						onclick="window.open(this.href,'_blank', 'width=800, height=640'); return false;">Email찾기   </a></li>
					<li ><a class="nav-link1" href="/commons/member/findPassword"
						onclick="window.open(this.href,'_blank', 'width=800, height=640'); return false;">비밀번호 찾기   </a></li>
				</c:if>
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
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dsp/main">메인</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dsp/guide">사이트소개</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dsp/notice/list">공지사항</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/dsp/room/list">장소찾기</a></li>
					<c:if test="${loginUser != null}">
						<li class="nav-item"><a class="nav-link" onclick="openWindow('<%=request.getContextPath()%>/pms/project/regist',720,640)">프로젝트 생성</a></li>
						<li class="nav-item">
							<a class="nav-link right" target="_blank" href='/pms/'>PMS 바로가기</a>
						</li>
					</c:if>
				</ul>
			</div>
			<c:if test="${loginUser eq null}">
			<div class="login-container">
			   <form action="<%=request.getContextPath() %>/commons/login" method="post">
			   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				   	<div class="row">
				   		<div class="col-5">
				   			<input class="form-control" type="text" placeholder="Email" name="email">
				   		</div>
				   		<div class="col-5">
						   	<input class="form-control" type="password" placeholder="Password" name="password">
						</div>
				   		<div class="col-2">
						   	<button type="submit" class="btn btn-light">Login</button>
						</div>
					</div>
			   </form>
			   </div>
			</c:if>
		</nav>
	</nav>
	<div class="container">&nbsp;</div>

</header>