<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>완료 대기 업무</title>
</head>
<body>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="row">
					<div class="col">
						<button type="button" class="btn btn-block dash-btn" onclick="go_back();">
							<i class="fas fa-long-arrow-alt-left"></i>뒤로가기
						</button>
					</div>
				</div>
				<div class="card-header">
					<h3 class="card-title">완료 대기 업무 목록</h3>
					<div class="card-tools">
						<div class="input-group input-group-sm" style="width: 150px;">
							<input type="text" name="table_search"
								class="form-control float-right" placeholder="Search">

							<div class="input-group-append">
								<button type="submit" class="btn btn-default">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /.card-header -->
				<div class="card-body table-responsive p-0">
					<table class="table table-hover text-nowrap">
						<thead class="table-header">
							<tr>
								<th style="width: 1%;">번호</th>
								<th style="width: 24%;">담당자</th>
								<th style="width: 50%;">담당 업무</th>
								<th style="width: 20%;">완료 일자</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${list }">
							<tr>
								<td>${list.no }</td>
								<td>${list.memberId }</td>
								<td>${list.title }</td>
								<td><span class="tag tag-success"><fmt:formatDate value="${list.updateDate }" pattern="yyyy-MM-dd HH:mm"/></span></td>
								<td>
									<button type="button" class="btn btn-block btn-info" onclick="detailSchedule('${list.no}')">업무 상세 보기</button>
								</td>
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
<script>
// 	var pro_id = document.getElementById('projectId').value;
	function detailSchedule(no){
		OpenWindow('<%=request.getContextPath()%>/pms/admin/perSchedule/detail?no='+no);
	}
</script>
</body>
</html>