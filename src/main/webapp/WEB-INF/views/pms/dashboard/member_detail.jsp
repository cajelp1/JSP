<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 참여자 상세조회</title>
</head>
<body>
<div class="container">
	<div class="card card-primary card-outline">
		<div class="card-body box-profile">
			<div class="text-center">
				<img class="profile-user-img img-fluid" src="<%=request.getContextPath() %>/commons/member/getProfile?id=${member.email}" alt="User profile picture">
			</div>

			<h3 class="profile-username text-center">${member.name }</h3>

<!-- 			<p class="text-muted text-center">Software Engineer</p> -->

			<ul class="list-group list-group-unbordered mb-3">
				<li class="list-group-item"><b>Email</b> <a
					class="float-right">${member.email }</a></li>
				<li class="list-group-item"><b>이름</b> <a
					class="float-right">${member.name }</a></li>
				<li class="list-group-item"><b>전화번호</b> <a
					class="float-right">${member.phoneNumber }</a></li>
			</ul>

           	<h4>프로젝트 업무 진척도</h4>
			<div class="card-body p-0">
                <table class="table">
                  <thead>
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>담당 업무</th>
                      <th>진척률</th>
                      <th style="width: 40px">Label</th>
                    </tr>
                  </thead>
                  <tbody>
				<c:forEach var="task" items="${task }">
                    <tr>
                      <td>#</td>
                      <td>${task.title }</td>
                      <td>
                        <div class="progress progress-xs">
                        <c:if test="${task.processPercent eq 100 }">
                          <div class="progress-bar progress-bar-striped bg-success" style="width: ${task.processPercent}%"></div>
                        </c:if>
                        <c:if test="${task.processPercent < 100}">
	                        <c:if test="${task.processPercent < 50}">
                        		<div class="progress-bar progress-bar-striped bg-warning" style="width: ${task.processPercent}%"></div>
	                        </c:if>
	                        <c:if test="${task.processPercent > 50}">
                          		<div class="progress-bar progress-bar-striped progress-bar-danger" style="width: ${task.processPercent}%"></div>
	                        </c:if>
                        </c:if>
                        </div>
                      </td>
                      <td><span class="badge bg-danger">${task.processPercent}%</span></td>
                    </tr>
			</c:forEach>
                    </tbody>
                </table>
              </div>
<!-- 			<a href="#" class="btn btn-primary btn-block"><b>Follow</b></a> -->
		</div>
		<!-- /.card-body -->
	</div>
</div>
</body>
</html>