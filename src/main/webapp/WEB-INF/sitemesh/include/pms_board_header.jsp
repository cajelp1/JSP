<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.structure.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.theme.min.css">
    <script  src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
    <script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/default-header.css">
</head>
<header>
    <nav class="main-header navbar navbar-expand navbar-white navbar-light" style="margin-left: 0px;">
        <ul class="navbar-nav">
            <li class="nav-item d-none d-sm-inline-block">
                <a href="/pms" class="nav-link top-menu" style="color: white;">대시보드</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="/pms/team/notice/" class="nav-link top-menu" style="color: white;">공지사항</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="/pms/team/pds/" class="nav-link top-menu" style="color: white;">자료실</a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="/pms/" class="nav-link top-menu" style="color: white;">일정관리</a>
            </li>
        </ul>
    </nav>
</header>

