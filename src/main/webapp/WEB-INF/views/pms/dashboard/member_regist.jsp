<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 참여자 초대</title>
</head>
<body>
<div class="content">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>${dataMap.project.projectName }</h1>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</section>

		<!-- Main content -->
		<section class="content">
		<c:set var="pageMaker" value="${dataMap.pageMaker }" />
			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">참여자 목록</h3>
				</div>
				<div class="card-header">
					<div class="row">
                    <div class="col-sm-12">
                        <form id="search" action="regist" method="get" class="form-inline">
                        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <select id="searchType" name="searchType" class="form-control">
                                <option value="en" ${pageMaker.cri.searchType eq 'en' ? 'selected' : '' }>전체</option>
                                <option value="e" ${pageMaker.cri.searchType eq 'e' ? 'selected' : '' }>이메일</option>
                                <option value="n" ${pageMaker.cri.searchType eq 'n' ? 'selected' : '' }>이름</option>
                            </select>
                           	<input type="text" class="form-control" id="keyword" name="keyword" placeholder="키워드" value="${pageMaker.cri.keyword }">
	                        <button id="searchBtn" name="searchBtn" type="button" class="btn btn-primary" onclick="searchList_go(1);">
	                        	<i class="fas fa-search"></i>검색
	                        </button>
                    	</form>
                    </div>
                </div>
				</div>

				<div class="card-body p-0" style="display: block;">
					<table class="table table-striped projects">
						<thead class="table-header">
							<tr>
								<th style="width: 10%">프로필</th>
								<th style="width: 30%">참여자 Email</th>
								<th style="width: 20%">참여자 이름</th>
								<th style="width: 14%" class="text-center">닉네임</th>
								<th style="width: 14%" class="text-center">전화번호</th>
								<th style="width: 25%"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="member" items="${dataMap.member}">
								<tr>
									<td>
										<div class="text-center">
											<img class="profile-user-img img-fluid" src="<%=request.getContextPath() %>/commons/member/getProfile?id=${member.email}" alt="User profile picture">
										</div>
									</td>
									<td class="member"><p id="email">${member.email }</p></td>
									<td><p>${member.name }</p></td>
									<td class="member"><p id="email">${member.nickname }</p></td>
									<td class="project-state">
										${member.phoneNumber}
									</td>
									<td class="project-state">
									    <%-- <div class="dropdown">
										  	<button class="dropbtn"><i class="fas fa-ellipsis-h" style="color: white;"></i></button>
										  	<div class="dropdown-content">
											    <a onclick="setManager('${member.name}','${member.email }');">관리자로 지정</a>
											    <a onclick="OpenWindow('detail?id=${member.email }');">참여자 조회</a>
											    <a onclick="removeMember('${member.name}','${member.email }');">내보내기</a>
											</div>
										</div> --%>
										<button class="btn btn-block btn-primary" onclick="addMember('${member.email }','${projectId }');"><i class="far fa-plus-square"></i>추가</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
			<!-- /.card -->
		</section>
		<!-- /.content -->
	</div>
		<%@include file="./pagination.jsp" %>
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
		
		jobForm.attr("action","regist").attr("method","get");
		jobForm.submit();
	});

	function addMember(email,projectId){
		if(confirm(email+'님을 프로젝트에 초대하시겠습니까?')){
			$.ajax({
				url:"<%=request.getContextPath()%>/pms/admin/member/regist",
				type:"post",
				beforeSend : function(xhr) {
					xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
                },
				data:{
					"memberId":email,
					"projectId":projectId
				},
				success:function(result){
					alert(email+'님을 초대하였습니다.');
					window.location.reload();
					window.opener.location.reload();
				},
				error:function(xhr){
					alert('관리자에게 문의하십시오.');
				}
			})
			
		}
	}
</script>
</body>
</html>