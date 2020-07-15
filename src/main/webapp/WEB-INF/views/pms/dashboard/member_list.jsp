<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<div class="content">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>${dataMap.project.projectName }</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#">PMS Main</a></li>
							<li class="breadcrumb-item active">${dataMap.project.projectName }</li>
						</ol>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</section>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<div class="container">
						<div class="row">
							<div class="col-md-8">
								<h3 class="card-title">참여자 목록</h3>							
							</div>						
							<div class="col-md-2">
								<button type="button" class="btn btn-block dash-btn" onclick="go_back();">
								<i class="fas fa-long-arrow-alt-left"></i>뒤로가기
								</button>
							</div>
							<div class="col-md-2">
								<button type="button" class="btn btn-block dash-btn" onclick="addProjectMember();">
									<i class="fas fa-plus"></i>초대하기
								</button>
							</div>
							</div>						
						</div>
					</div>
				</div>
			</div>
				<div class="card-body p-0" style="display: block;">
					<table class="table table-striped projects">
						<thead class="table-header">
							<tr>
								<th style="width: 1%">#</th>
								<th style="width: 20%">참여자 이름</th>
								<th style="width: 30%">참여자 Email</th>
								<th style="width: 14%" class="text-center">전화번호</th>
								<th style="width: 25%"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="member" items="${dataMap.projectMember }">
								<tr>
									<td>#</td>
									<td><p>${member.name }</p></td>
									<td class="member"><p id="email">${member.email }</p></td>
									<td class="project-state">${member.phoneNumber	}</td>
									<td class="project-state">
									    <div class="dropdown">
										  	<button class="dropbtn"><i class="fas fa-ellipsis-h" style="color: white;"></i></button>
										  	<div class="dropdown-content">
											    <a onclick="detailMember('${member.email }');">참여자 조회</a>
											    <a onclick="setManager('${member.name}','${member.email }');">관리자로 지정</a>
										  		<a onclick="removeMember('${member.name}','${member.email }');">내보내기</a>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<%@include file="/WEB-INF/views/commons/include/pagination.jsp" %>
				<!-- /.card-body -->
			</div>
			<!-- /.card -->
		</section>
		<!-- /.content -->
	</div>
	<form role="check">
		<input id="proejectId" type="hidden" value="${dataMap.projectId }">
	    <input type="hidden" value="${session.loginUser.email }" id="manager" />
	    <input type="hidden" value="${dataMap.project.register }" id="register" />
	</form>
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
		
		jobForm.attr("action","lsit").attr("method","get");
		jobForm.submit();
	});

	var pro_id = document.getElementById('proejectId').value;
	var register = document.getElementById('register').value;
	function detailMember(email){
		OpenWindow('<%=request.getContextPath()%>/pms/admin/member/detail?id='+email+'&projectId=${param.projectId}');
	}
	
	function setManager(name, email){
		if(confirm(name+'님을 프로젝트의 관리자로 지정하시겠습니까?\n선택시 더이상 프로젝트의 관리자가 되지 않습니다.')){
			
			//관리자가 본인을 관리자로 지정하지 못하도록 막음
			if(register == email){
				alert('다른 사람을 관리자로 지정해주십시오');
				return;
			}
			
			$.ajax({
				url:"<%=request.getContextPath()%>/pms/admin/member/setManager",
				data:{
					"email":email,
					"projectId":pro_id
				},
				success:function(result){
					alert('처리되었습니다.');
					location.href="<%=request.getContextPath()%>/pms/";
				},
				error:function(xhr){
					alert('잠시후에 시도하십시오');
				}
			})
		}
	}
	
	function removeMember(name, email){
		
		if(confirm(name+'님을 ${dataMap.project.projectName }에서 제외시키겠습니까?')){
			
			//관리자가 본인을 관리자로 지정하지 못하도록 막음
			if(register == email){
				alert('다른 사람을 관리자로 먼저 지정해주십시오');
				return;
			}
			
			$.ajax({
				url:"<%=request.getContextPath()%>/pms/admin/member/remove",
				data:{
					"email":email,
					"projectId":pro_id
				},
				success:function(data){
					alert('처리되었습니다.');
					window.location.reload();
				},
				error:function(xhr){
					alert('잠시후에 시도하세요.');
				}
			})			
		}
	}
	
	function addProjectMember(){
		OpenWindow('<%=request.getContextPath()%>/pms/admin/member/regist?projectId='+pro_id);
	}
		
</script>
</body>