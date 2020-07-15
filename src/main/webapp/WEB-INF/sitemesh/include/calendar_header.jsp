<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<!-- jQuery UI -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.structure.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.theme.min.css">
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	<!-- default-header.css 스타일 수정할거면 여기서 할것! -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/default-header.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/pms/css/hover-menu.css">
	
	<!-- fullCalendar -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar/main.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar-daygrid/main.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar-timegrid/main.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar-bootstrap/main.min.css">
	
	<!-- bootstrap colorPicker -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
	
<style>
#NotHistoryTitle{
	width : 170px;
	display: inline-block;
	overflow:hidden;
	text-overflow:ellipsis; 
	white-space:nowrap;
}
#NotHistoryWriter{
	width : 80px;
	display: inline-block;
	overflow:hidden;
	text-overflow:ellipsis; 
	white-space:nowrap;
}
.dropdown-menu-lg{
	min-width : 350px;
}
</style>
</head>
<header>
<!-- nav bar 시작 -->

<nav class="main-header navbar navbar-expand navbar-white navbar-light" style="margin-left: 0px;">
	<!-- Left navbar links -->
	<ul class="navbar-nav">
		<li class="nav-item d-none d-sm-inline-block">
			<a href="/pms/" class="nav-link top-menu" style="color: white;">프로젝트 목록</a>
		</li>
		<c:if test="${sessionScope.projectManager eq sessionScope.loginUser.email }">
		<!-- 		drop down 시작 -->
		<li class="nav-item dropdown">
            <a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle" style="color: white;">관리자 Dashboard</a>
            <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow" style="left: 0px; right: inherit;">
              <li><a href="<%=request.getContextPath() %>/pms/admin/main?projectId=${param.projectId}" class="dropdown-item">Dashboard 바로가기</a></li>
              <li><a href="<%=request.getContextPath() %>/pms/admin/team/calendar?projectId=${param.projectId}" class="dropdown-item">팀 일정</a></li>
              <li><a href="<%=request.getContextPath() %>/pms/admin/personalSchedule/list?projectId=${param.projectId}" class="dropdown-item">팀 업무</a></li>
              <li><a href="<%=request.getContextPath() %>/pms/admin/member/list?projectId=${param.projectId}" class="dropdown-item">프로젝트 참여자</a></li>

<!--          <li class="dropdown-divider"></li>
              Level two dropdown
              <li class="dropdown-submenu dropdown-hover">
                <a id="dropdownSubMenu2" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-item dropdown-toggle">Hover for action</a>
                <ul aria-labelledby="dropdownSubMenu2" class="dropdown-menu border-0 shadow">
                  <li><a tabindex="-1" href="#" class="dropdown-item">level 2</a></li>
                  Level three dropdown
                  <li class="dropdown-submenu">
                    <a id="dropdownSubMenu3" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-item dropdown-toggle">level 2</a>
                    <ul aria-labelledby="dropdownSubMenu3" class="dropdown-menu border-0 shadow">
                      <li><a href="#" class="dropdown-item">3rd level</a></li>
                      <li><a href="#" class="dropdown-item">3rd level</a></li>
                    </ul>
                  </li>
                  End Level three
                  <li><a href="#" class="dropdown-item">level 2</a></li>
                  <li><a href="#" class="dropdown-item">level 2</a></li>
                </ul>
              </li>
              End Level two -->
            </ul>
          </li>
<!--           drop down 끝 -->
<!-- 		<li class="nav-item d-none d-sm-inline-block"> -->
<%-- 			<a href="/pms/admin/main?projectId=${param.projectId }" class="nav-link top-menu" style="color: white;">관리자 Dashboard</a> --%>
<!-- 		</li> -->
		</c:if>
		<c:if test="${sessionScope.projectManager ne sessionScope.loginUser.email }">
		<li class="nav-item d-none d-sm-inline-block">
			<a href="/pms/user/main?projectId=${param.projectId }" class="nav-link top-menu" style="color: white;">Dashboard</a>
		</li>
		</c:if>
		<li class="nav-item d-none d-sm-inline-block">
			<a href="/pms/team/notice/list?projectId=${param.projectId }" class="nav-link top-menu" style="color: white;">공지사항</a>
		</li>
		<li class="nav-item d-none d-sm-inline-block">
			<a href="/pms/team/pds/list?projectId=${param.projectId }" class="nav-link top-menu" style="color: white;">자료실</a>
		</li>
		<%-- <%-- <li class="nav-item d-none d-sm-inline-block">
			<a href="/pms/" class="nav-link top-menu" style="color: white;">일정관리</a>
		</li> --%>
	</ul>

	<!-- Right navbar links -->
	<ul class="navbar-nav ml-auto">
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
			  <img src="<%=request.getContextPath() %>/commons/member/getProfile?id=${sessionScope.loginUser.email}" onerror="this.src='https://via.placeholder.com/30?text=IMG'"
				style="height:30px;width:30px;border-radius:100%;">
			</div>
			<div class="info">
			  <p class="d-block" style="color:white;">${sessionScope.loginUser.name }님 환영합니다!</p>
			</div>
		</div>
	<!-- 알림기능 종모양 Dropdown Menu -->
		<li class="nav-item dropdown">
			<a class="nav-link" data-toggle="dropdown" style="cursor:pointer;" aria-expanded="false">
				<i class="far fa-bell" style="color: white; style="font-size: 2.0em;"></i>
				<span class="badge badge-warning navbar-badge" id="NonReadCount"  style="font-size: 1.2em;"></span>
			</a>
			<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right" style="left: inherit; right: 0px;">
				<span class="dropdown-header">
					<i class="fas fa-sticky-note"></i> 읽지않은 메세지
				</span>
				<!-- <i class="far fa-calendar-alt"></i> -->
				<div class="dropdown-divider"></div>
				<a href="<%=request.getContextPath()%>/pms/notHistory/listReadN?projectId=${param.projectId}" target="_blank" class="dropdown-item dropdown-footer">전체 보기</a>          
			</div>
		</li>
	</ul>
</nav>
<!-- nav bar 끝 -->
</header>