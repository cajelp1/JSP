<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<section class="content container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-10" style="max-width:960px;">
			<div class="card card-top">
				<div class="card-header">
					<h2 class="card-title p-1" style="margin-top:4px;">
						<i class="fa fa-file"></i>
						자료실 등록
					</h2>
					<div class="card-tools">
						<button type="button" class="btn btn-blue" onclick="doSubmit();">등록</button>
						<button type="button" class="btn btn-blue" onclick="cancel();">취소</button>
					</div>
				</div>
			</div>
				<div class="card-body">
					<form id="registForm" name="registForm" action="regist?${_csrf.parameterName}=${_csrf.token}" role="form" method="post"  enctype="multipart/form-data">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<input type="hidden" id="writer" name="writer" value="${sessionScope.loginUser.email}">
						<input type="hidden" id="projectId" name="projectId" value="${projectId}">
						<input type="hidden" id="fileOpenYn" name="fileOpenYn" value="y">
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="title">제목</label>
								<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요">
							</div>
							<div class="form-group col-sm-4">
								<label for="nickname">작성자</label>
								<input type="text" class="form-control" id="nickname" value="${sessionScope.loginUser.nickname}" readonly="readonly">
							</div>
						</div>
						<!-- <div class="row">
							<div class="form-group col-sm-5">
								<label for="fromDate">시작일</label>
								<input type="text" id="fromDate" class="form-control daterangepicker" name="startDate"  autocomplete="off" readonly><br/>
							</div>
							<div class="form-group col-sm-2 center" style="text-align: center;margin-top: 24px;">
								<div class="container">
									<div class="row align-self-center">
										<div class="col">
											<span class="align-middle" style="font-size: 2.2em;">~</span>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group col-sm-5">
								<label for="toDate">종료일</label>
								<input type="text" id="toDate" class="form-control" name="endDate"  autocomplete="off" readonly><br/>
							</div>
						</div> -->
						<div class="form-group">
							<label for="content" >내용</label>
							<textarea rows="10" cols="" id="content" name="content"
								class="form-control required" style="display: none;"></textarea>
						</div>
					<c:if test="${boardMaster.fileYn eq 'y'}">	
						<div class="form-group">
							<h3 style="display:inline;line-height:40px;">파일 비공개 : </h3>
							<input type="checkbox" id="fileOpenCheck" style="width:30px; height:30px;position: relative;top: 4px;">
						</div>
				        <div class="form-group">								
							<div class="card card-outline card-success">
								<div class="card-header">
									<h5 style="display:inline; line-height:40px;">첨부파일 : </h5>
									&nbsp;&nbsp;
									<button class="btn btn-xs btn-primary" type="button" id="addFileBtn" onclick="addFile();">파일추가</button>
								</div>									
								<div class="card-footer fileInput">
								</div>
							</div>
						</div>
					</c:if>
					</form>
				</div> <!-- card body 종료 -->
				<!-- <div class="card-footer" style="text-align:right;">
					<button type="button" class="btn btn-blue col-sm-3" onclick="doSubmit();">등록</button>
				</div> -->
			</div><!-- end card -->				
		</div><!-- end col-md-12 -->
	</div><!-- end row -->
</section>
<%@ include file="/WEB-INF/views/pms/include/summernote_js.jsp" %>
<%@ include file="/WEB-INF/views/pms/include/picker_js.jsp" %>
<%@ include file="/WEB-INF/views/pms/team/pds/file_js.jsp" %>
<script type="text/javascript">

function doSubmit() {
	alert('글이 등록되었습니다.');
	$('#registForm').submit();
}
function cancel(){
	if(confirm('글 등록을 취소하겠습니까?')){
		window.close();
	}
}
</script>
</body>