<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/personal-style.css">
<!-- Main content -->
<section class="content container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-10" style="max-width:960px;">
			<div class="card card-info">
				<div class="card-header">
					<h2 class="card-title p-1" style="margin-top:4px;">
						<i class="far fa-calendar-alt"></i>
						개인일정 수정
					</h2>
					<div class="card-tools">
						<button type="button" class="btn btn-warning" onclick="HistoryBack();">뒤로가기</button>
					</div>
				</div><!--end card-header  -->
				<div class="card-body pad">
					<form id="modifyForm" name="modifyForm" action="modify?${_csrf.parameterName}=${_csrf.token}" role="form" method="post">
						<input type="hidden" name="no" value="${detailPersonal.no}">
						<input type="hidden" name="projectId" value="${detailPersonal.projectId}">
						<input type="hidden" name="memberId" value="${detailPersonal.memberId}">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="row">
							<div class="form-group col-sm-12">
								<label for="title">제목</label>
								<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요"
								value="${detailPersonal.title}" ${sessionScope.loginUser.role eq 'ROLE_ADMIN' ? '' : 'readonly'} >
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label for="nickname">보낸사람</label>
								<c:choose>
									<c:when test="${sessionScope.loginUser.role eq 'ROLE_ADMIN'}">
										<input type="text" class="form-control"  value="${sessionScope.loginUser.nickname}"  readonly>
										<input type="hidden"  name="charger" value="${sessionScope.loginUser.email}">
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control"  value="${detailPersonal.chargerNickName}" readonly>
										<input type="hidden" name="charger" value="${detailPersonal.charger}">
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group col-sm-6">
								<label for="nickname">받은사람</label>
								<input type="text" class="form-control" id="nickname" value="${detailPersonal.memberNickName}" readonly="readonly">
							</div>	
						</div>
						<div class="row">
							<div class="form-group col-sm-4">
								<label for="fromDate">시작일</label>
								<input type="text" id="${sessionScope.loginUser.role eq 'ROLE_ADMIN' ? 'fromDate' : ''}" class="form-control daterangepicker" name="startDate"  autocomplete="off" 
								readonly value='<fmt:formatDate value="${detailPersonal.startDate}" pattern="yyyy-MM-dd"/>'><br/>
							</div>
							<div class="form-group col-sm-1 center" style="text-align: center;margin-top: 24px;">
								<div class="container">
									<div class="row align-self-center">
										<div class="col">
											<span class="align-middle" style="font-size: 2.2em;">~</span>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group col-sm-4">
								<label for="toDate">종료일</label>
								<input type="text" id="${sessionScope.loginUser.role eq 'ROLE_ADMIN' ? 'toDate' : ''}" class="form-control" name="endDate"  autocomplete="off" 
								readonly value='<fmt:formatDate value="${detailPersonal.endDate}" pattern="yyyy-MM-dd"/>'><br/>
							</div>
						<c:if test="${sessionScope.loginUser.role eq 'ROLE_ADMIN'}">
							<div class="col-sm-3">
								<div class="my-colorpicker2 colorpicker-element" data-colorpicker-id="2">
							 		<label for="color">색상 선택</label>
							 		<div class="row">
								 		<div class="col-sm-8">
									 		<input type="text" id="color" class="form-control" name="color" data-original-title="" title="" autocomplete="off" value="${detailPersonal.color}">
								 		</div> 
							   			<span class="input-group-text"><i class="fas fa-square" style="color: ${detailPersonal.color};"></i></span>
									</div>
								</div>
							</div>
						</c:if>
						</div>
						<div class="row">
							<div class="form-group col-sm-4">
								<label>진척률</label>
								<select name="processPercent" class="form-control">
									<option value="0" ${detailPersonal.processPercent eq '0' ? 'selected' : ''}>0%</option>
									<option value="10" ${detailPersonal.processPercent eq '10' ? 'selected' : ''}>10%</option>
									<option value="20" ${detailPersonal.processPercent eq '20' ? 'selected' : ''}>20%</option>
									<option value="30" ${detailPersonal.processPercent eq '30' ? 'selected' : ''}>30%</option>
									<option value="40" ${detailPersonal.processPercent eq '40' ? 'selected' : ''}>40%</option>
									<option value="50" ${detailPersonal.processPercent eq '50' ? 'selected' : ''}>50%</option>
									<option value="60" ${detailPersonal.processPercent eq '60' ? 'selected' : ''}>60%</option>
									<option value="70" ${detailPersonal.processPercent eq '70' ? 'selected' : ''}>70%</option>
									<option value="80" ${detailPersonal.processPercent eq '80' ? 'selected' : ''}>80%</option>
									<option value="90" ${detailPersonal.processPercent eq '90' ? 'selected' : ''}>90%</option>
									<option value="100" ${detailPersonal.processPercent eq '100' ? 'selected' : ''}>100%</option>
								</select><br/>
							</div>	
							<div class="form-group col-sm-4">
								<label>프로세스 상태 : </label>
								<select name="processStaues" class="form-control" ${sessionScope.loginUser.role eq 'ROLE_ADMIN' ? '' : 'disabled'}>
									<option value="720" ${detailPersonal.processStaues eq '720' ? 'selected' : ''}>신규</option>
									<option value="710" ${detailPersonal.processStaues eq '710' ? 'selected' : ''}>진행</option>
									<option value="700" ${detailPersonal.processStaues eq '700' ? 'selected' : ''}>완료</option>
								</select>
							</div>
							<input type="hidden" name="approvalYn" value="${detailPersonal.approvalYn}"/>
							
						</div>
						<div>
						${detailPersonal.content}
						</div>
						<input type="hidden" name="content" value="${detailPersonal.content}">
						<%-- <div class="form-group">
							<textarea rows="10" cols="" id="content" name="content"
								class="form-control required" style="display: none;">${detailPersonal.content}</textarea>
						</div> --%>
					</form>
				</div><!--end card-body  -->
				
				<div class="card-footer" style="text-align:right;">
					<button type="button" class="btn btn-primary col-sm-3" onclick="javascript:$('form[role=form]').submit();">수정</button>
				</div><!--end card-footer  -->
				
			</div><!-- end card -->				
		</div><!-- end col-md-10 -->
	</div><!-- end row -->
</section>
<script>
function HistoryBack(){
	if (confirm("글이 저장되지 않습니다.\r\n돌아가시겠습니까?")) {
		history.go(-1);
	}
}
</script>
<%@ include file="/WEB-INF/views/pms/include/picker_js.jsp" %>
<%@ include file="/WEB-INF/views/pms/include/summernote_js.jsp" %>
