<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<!-- jQuery UI -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.structure.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.theme.min.css">
	<script  src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	<!-- bootstrap colorPicker -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- default-header.css 스타일 수정할거면 여기서 할것! -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/default-header.css">
</head>

<header>
<!-- nav bar 시작 -->
<nav class="main-header navbar navbar-expand navbar-white navbar-light" style="margin-left: 0px;">
	<!-- Left navbar links -->
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

		
		
		<!-- drop down 시작 -->
		<li class="nav-item dropdown">
            <a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle" style="color: white;">Dropdown</a>
            <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow" style="left: 0px; right: inherit;">
              <li><a href="" class="dropdown-item"></a></li>
              <li><a href="" class="dropdown-item"></a></li>

              <li class="dropdown-divider"></li>
              <!-- Level two dropdown-->
              <li class="dropdown-submenu dropdown-hover">
                <a id="dropdownSubMenu2" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-item dropdown-toggle">Hover for action</a>
                <ul aria-labelledby="dropdownSubMenu2" class="dropdown-menu border-0 shadow">
                  <li><a tabindex="-1" href="#" class="dropdown-item">level 2</a></li>
                  <!-- Level three dropdown-->
                  <li class="dropdown-submenu">
                    <a id="dropdownSubMenu3" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-item dropdown-toggle">level 2</a>
                    <ul aria-labelledby="dropdownSubMenu3" class="dropdown-menu border-0 shadow">
                      <li><a href="#" class="dropdown-item">3rd level</a></li>
                      <li><a href="#" class="dropdown-item">3rd level</a></li>
                    </ul>
                  </li>
                  <!-- End Level three -->
                  <li><a href="#" class="dropdown-item">level 2</a></li>
                  <li><a href="#" class="dropdown-item">level 2</a></li>
                </ul>
              </li>
              <!-- End Level two -->
            </ul>
          </li>
          <!-- drop down 끝 -->
	</ul>

	<!-- Right navbar links -->
	<ul class="navbar-nav ml-auto">
		<!-- Notifications Dropdown Menu -->
		<li class="nav-item dropdown">
		<a class="nav-link" data-toggle="dropdown" href="#"> <i class="far fa-bell" style="color: white; font-size: 20px;"></i> <span class="badge badge-warning navbar-badge">15</span>
		</a>
			<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
				<span class="dropdown-item dropdown-header">15 Notifications</span>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item"> <i
					class="fas fa-envelope mr-2"></i> 4 new messages <span
					class="float-right text-muted text-sm">3 mins</span>
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item"> <i class="fas fa-users mr-2"></i>
					8 friend requests <span class="float-right text-muted text-sm">12
						hours</span>
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i>
					3 new reports <span class="float-right text-muted text-sm">2
						days</span>
				</a>
				<div class="dropdown-divider"></div>
				<a href="#" class="dropdown-item dropdown-footer">See All
					Notifications</a>
			</div></li>
	</ul>
</nav>
<!-- nav bar 끝 -->
</header>

