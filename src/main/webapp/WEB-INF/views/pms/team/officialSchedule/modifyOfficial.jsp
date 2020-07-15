<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Main content -->
<section class="content container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-10" style="max-width:960px;">
			<div class="card card-info">
				<div class="card-header" style="margin-top:5px;">
					<h2 class="card-title p-1">
						<i class="far fa-calendar-alt"></i>
						공식일정 수정
					</h2>
					<div class="card-tools">
						<button type="button" class="btn btn-warning" onclick="window.history.go(-1);">뒤로가기</button>
					</div>
				</div><!--end card-header  -->
				<div class="card-body pad">
					<form id="modifyForm" name="modifyForm" action="modify?${_csrf.parameterName}=${_csrf.token}" role="form" method="post"  enctype="multipart/form-data">
						<input type="hidden" name="no" value="${detailOfficial.no}">
						<input type="hidden" name="projectId" value="${detailOfficial.projectId}">
						<input type="hidden" name="register" value="${sessionScope.loginUser.email}">
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="title">제목</label>
								<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요" value="${detailOfficial.title}">
							</div>
							<div class="form-group col-sm-4">
								<label for="nickname">작성자</label>
								<input type="text" class="form-control" id="nickname" value="${sessionScope.loginUser.nickname}" readonly="readonly">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-4">
								<label for="fromDate">시작일</label>
								<input type="text" name="startDate" id="fromDate" autocomplete="off" readonly
								value='<fmt:formatDate value="${detailOfficial.startDate}" pattern="yyyy-MM-dd"/>'><br/>
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
								<input type="text" name="endDate" id="toDate" autocomplete="off" readonly
								value='<fmt:formatDate value="${detailOfficial.endDate}" pattern="yyyy-MM-dd"/>'><br/>
							</div>
							<div class="col-sm-3">
								<div class="my-colorpicker2 colorpicker-element" data-colorpicker-id="2">
							 		<label for="color">색상 선택</label>
							 		<div class="row">
								 		<div class="col-sm-8">
									 		<input type="text" id="color" class="form-control" name="color" value="${detailOfficial.color}" data-original-title="" title="" autocomplete="off">
								 		</div> 
							   			<span class="input-group-text"><i class="fas fa-square" style="color: rgb(0, 231, 243);"></i></span>
									</div>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="content" >내용</label>
							<textarea rows="10" cols="" id="content" name="content"
								class="form-control required" style="display: none;">
								${detailOfficial.content}
							</textarea>
						</div>
					</form>
				</div><!--end card-body  -->
				<div class="card-footer" style="text-align:right;">
					<button type="button" class="btn btn-primary col-sm-3" onclick="sendSubmit();">등록</button>
				</div><!--end card-footer  -->
			</div><!-- end card -->				
		</div><!-- end col-md-10 -->
	</div><!-- end row -->
</section>
<script>
	function sendSubmit(){
		$('form[role=form]').submit();
	}
</script>
<%@ include file="/WEB-INF/views/pms/include/picker_js.jsp" %>
<%@ include file="/WEB-INF/views/pms/include/summernote_js.jsp" %>