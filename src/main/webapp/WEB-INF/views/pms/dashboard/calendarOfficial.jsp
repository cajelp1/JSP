<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<div class="col-2">
					<button type="button" class="btn btn-block bg-gradient-info" onclick="go_back();">
						<i class="fas fa-long-arrow-alt-left"></i>뒤로가기
					</button>
				</div>
<%-- 			<div class="col-3">
					<button type="button" class="btn btn-block bg-gradient-info" onclick="location.href='schedule?projectId=${projectId}'">
						<i class="fas fa-list-alt"></i>목록 보기
					</button>
				</div>--%>
				<c:if test="${empty userURL}">
				<div class="col-2">
					<button type="button" class="btn btn-block bg-gradient-info" onclick="registOfficialSchedule();">
						<i class="fas fa-edit"></i>등록
					</button>
				</div>
				</c:if>
<%-- 				<div class="col-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="/pms/">Home</a></li>
						<li class="breadcrumb-item active">${projectName }</li>
					</ol>
				</div>
				<div class="col-4">
					<select name="searchType" class="form-control">
						<option value="t" ${cri.searchType == "t" ? 'selected' : ''}>제목</option>
						<option value="d" ${cri.searchType == "d" ? 'selected' : ''}>날짜</option>
						<option value="sd" ${cri.searchType == "sd" ? 'selected' : ''}>시작일</option>
						<option value="ed" ${cri.searchType == "ed" ? 'selected' : ''}>종료일</option>
					</select>
				</div>
				<div class="col-4">
					<div class="input-group input-group">
	                	<input type="text" class="form-control" name="keyword" id="datePicker" autocomplete="off" value="${cri.keyword }">
	                	<span class="input-group-append">
	                    	<button type="button" class="btn btn-info btn-flat" onclick="reloadCalendar();">검색</button>
	                  	</span>
	                </div>
				</div> --%>
			</div>
		</div>

<%-- 		<form action="calendar" method="get">
			<input type="hidden" name="projectId" value="${projectId}">
			<input type="submit" value="검색">
		</form>--%>
<!-- 	Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="card card-primary">
						<div class="card-body p-0">
							<!-- THE CALENDAR -->
							<div id="calendar"></div>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<%@ include file="./official_js.jsp"%>	
</body>