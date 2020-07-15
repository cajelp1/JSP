<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>업무 목록</title>
</head>
<body>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="row">
					<div class="col-2">
						<button type="button" class="btn btn-block dash-btn" onclick="go_back();">
							<i class="fas fa-long-arrow-alt-left"></i>뒤로가기
						</button>
					</div>
					<div class="col-2">
						<button type="button" class="btn btn-block dash-btn" onclick="addProjectSchedule();">
							<i class="fas fa-plus"></i>업무 추가
						</button>
					</div>
					<div class="col-8">
						<div class="input-group input-group" style="width: 100%;">
							<input type="text" name="table_search" class="form-control float-right" placeholder="Search">
							<div class="input-group-append">
								<button type="submit" class="btn btn-default">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="card-header">
					<h3 class="card-title">업무 목록</h3>
					<div class="card-tools">
					</div>
				</div>
				<!-- /.card-header -->
				<div class="card-body table-responsive p-0">
					<table class="table table-hover text-nowrap">
						<thead class="table-header">
							<tr>
								<th style="width: 1%;">번호</th>
								<th style="width: 24%;">담당자</th>
								<th style="width: 40%;">담당 업무</th>
								<th style="width: 20%;">수정 일자</th>
								<th>진척률</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${dataMap.personalScheduleList }">
							<tr>
								<td>${list.no }</td>
								<td>${list.memberId }</td>
								<td><a onclick="detailSchedule('${list.no}');" style="cursor:pointer;">${list.title }</a></td>
								<td><span class="tag tag-success"><fmt:formatDate value="${list.updateDate }" pattern="yyyy-MM-dd HH:mm"/></span></td>
								<td>${list.processPercent }%</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
			<!-- /.card -->
		</div>
		<form role="role">
			<input id="projectId" type="hidden" value="${projectId }" />
		</form>
	</div>
		<%@include file="/WEB-INF/views/commons/include/pagination.jsp" %>
<script>
	$('#searchBtn').on('click',function(e){
		
		if($('input[name="keyword"]').val() !=""){
			if($('select[name="searchType"]').val()==""){
				alert("검색구분은 필수입니다.");
				$('input[name="searchType"]').focus();
				return;
			}			
		}
		
		var jobForm=$('#jobForm');
		jobForm.find("[name='page']").val(1);
		jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		jobForm.find("[name='keyword']").val($('input[name="keyword"]').val());
		
		/* alert(jobForm.serialize()); */
		
		jobForm.attr("action","list").attr("method","get");
		jobForm.submit();
	});

	var pro_id =document.getElementById('projectId').value;
	function detailSchedule(no){
		OpenWindow('<%=request.getContextPath()%>/pms/admin/personalSchedule/detail?no='+no);
	}
	function addProjectSchedule(){
		OpenWindow('<%=request.getContextPath()%>/pms/admin/personalSchedule/regist?projectId='+pro_id);
	}
</script>
</body>
</html>