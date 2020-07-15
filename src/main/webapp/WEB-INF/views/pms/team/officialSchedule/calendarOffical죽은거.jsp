<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<body>
	<div class="wrapper">
		<div class="container">
				<div class="row">
					<div class="col-2">
						팀 일정
					</div>
					<div class="col-2">
						<a href="list?projectId=${projectId}">리스트 가기</a>
					</div>
					<div class="col-4">
						<select name="searchType">
							<option value="t" ${cri.searchType == "t" ? 'selected' : ''}>제목</option>
							<option value="d" ${cri.searchType == "d" ? 'selected' : ''}>날짜</option>
							<option value="sd" ${cri.searchType == "sd" ? 'selected' : ''}>시작일</option>
							<option value="ed" ${cri.searchType == "ed" ? 'selected' : ''}>종료일</option>
						</select>
					</div>
					<div class="col-4">
						<div class="input-group input-group-sm">
		                	<input type="text" class="form-control" name="keyword" id="datePicker" autocomplete="off" value="${cri.keyword }">
		                	<span class="input-group-append">
		                    	<button type="button" class="btn btn-info btn-flat" onclick="reloadCalendar();">검색</button>
		                  	</span>
		                </div>
					</div>
				</div>
			</div>

		<!-- 	<form action="calendar" method="get"> -->
		<%-- 		<input type="hidden" name="projectId" value="${projectId}"> --%>
		<!-- 		<input type="submit" value="검색"> -->
		<!-- 	</form> -->
		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="card">
						<a class="btn btn-app" onclick="registOfficialSchedule();">
							<i class="fas fa-edit"></i> 등록
						</a>
					</div>
				</div>
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
	<!-- ./wrapper -->
	<!-- timepicki -->
<script	src="${pageContext.request.contextPath}/resources/pms/js/common.js"></script>
<%@ include file="./official_js.jsp"%>