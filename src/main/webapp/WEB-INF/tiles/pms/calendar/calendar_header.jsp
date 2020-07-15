<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Calendar</title>


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

<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Right navbar links -->
  <ul class="navbar-nav ml-auto">
    <!-- 알림기능 종모양 Dropdown Menu -->
    <li class="nav-item dropdown">
      <a class="nav-link" data-toggle="dropdown" style="cursor:pointer;" aria-expanded="false">
        <i class="far fa-bell"  style="font-size: 2.0em;"></i>
        <span class="badge badge-warning navbar-badge" id="NonReadCount"  style="font-size: 1.2em;"></span>
      </a>
      <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right" style="left: inherit; right: 0px;">
        <span class="dropdown-header">
	<i class="fas fa-sticky-note"></i> 읽지않은 메세지
  </span>
        <!-- <i class="far fa-calendar-alt"></i> -->
        <div class="dropdown-divider"></div>
        <a href="<%=request.getContextPath()%>/pms/notHistory/list" target="_blank" class="dropdown-item dropdown-footer">전체 보기</a>          
      </div>
    </li>
  </ul>
</nav>
