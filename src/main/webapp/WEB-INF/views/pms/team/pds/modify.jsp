<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="content container-fluid">
	<div class="border m-3 shadow">
			<div class="card card-top">
				<div class="card-header">
					<h2 class="card-title p-1" style="margin-top:4px;">
						<i class="fa fa-file"></i>
						자료실 수정
					</h2>
					<div class="card-tools">
						<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="history.go(-1);"><i class="fas fa-undo-alt"></i>&nbsp;&nbsp;뒤로가기</button>
						<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="doSubmit();"><i class="fa fa-edit"></i>&nbsp;&nbsp;수정</button>
						<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="window.close();"><i class="far fa-window-close"></i>&nbsp;&nbsp;닫기</button>
					</div>
				</div>
			</div>
				<div class="card-body">
					<form id="modifyForm" name="modify" action="modify?${_csrf.parameterName}=${_csrf.token}" role="form" method="post" enctype="multipart/form-data">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<input type="hidden" id="writer" name="writer" value="${sessionScope.loginUser.email}">
						<input type="hidden" id="no" name="no" value="${proBoardVO.no}"/>
						<input type="hidden" id="projectId" name="projectId" value="${projectId}">
						<input type="hidden" id="fileOpenYn" name="fileOpenYn" value="${proBoardVO.fileOpenYn}">
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="title">제목</label>
								<input type="text" class="form-control" id="title" name="title" value="${proBoardVO.title}">
							</div>
							<div class="form-group col-sm-4">
								<label for="nickname">작성자</label>
								<input type="text" class="form-control" id="nickname" value="${sessionScope.loginUser.nickname}" readonly>
							</div>
						</div>
<!-- 						<div class="row"> -->
<!-- 							<div class="form-group col-sm-5"> -->
<!-- 								<label for="fromDate">시작일</label> -->
<%-- 								<input type="text" id="fromDate" class="form-control daterangepicker" name="startDate"  autocomplete="off" readonly value="<fmt:formatDate value="${proBoardVO.startDate}" pattern="yyyy-MM-dd"/>"><br/> --%>
<!-- 							</div> -->
<!-- 							<div class="form-group col-sm-2 center" style="text-align: center;margin-top: 24px;"> -->
<!-- 								<div class="container"> -->
<!-- 									<div class="row align-self-center"> -->
<!-- 										<div class="col"> -->
<!-- 											<span class="align-middle" style="font-size: 2.2em;">~</span> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="form-group col-sm-5"> -->
<!-- 								<label for="toDate">종료일</label> -->
<%-- 								<input type="text" id="toDate" class="form-control" name="endDate"  autocomplete="off" readonly value="<fmt:formatDate value="${proBoardVO.endDate}" pattern="yyyy-MM-dd"/>"><br/> --%>
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="form-group">
							<label for="content" >내용</label>
							<textarea rows="10" cols="" id="content" name="content" class="form-control required"  readonly>
								${proBoardVO.content}
							</textarea>
						</div>
					<c:if test="${boardMaster.fileYn eq 'y'}">
						<div class="form-group" style="vertical-align:middle">
							<h3 style="display:inline;line-height:40px;">파일 비공개 : </h3>
							<input type="checkbox" id="fileOpenCheck" ${proBoardVO.fileOpenYn eq 'n' ? 'checked' : ''} style="width:30px; height:30px;position: relative;top: 4px;">
						</div>
						<div class="form-group">								
							<div class="card card-outline card-success">
								<div class="card-header">
									<h3 style="display:inline;line-height:40px;">첨부파일 : </h3>
									&nbsp;&nbsp;
									<button class="btn btn-primary" type="button" id="addFileBtn" onclick="addFile();">Add File</button>
								</div>
								
								<div class="card-footer fileInput">
									<ul class="mailbox-attachments d-flex align-items-stretch clearfix">
										<c:forEach items="${attachFileList}" var="file" >
										<li class="attach-item">																			
											<div class="mailbox-attachment-info">
												<p class="mailbox-attachment-name" name="attachedFile" 
													attach-originFilename="${file.originFilename }"
													attach-no="${file.no}" 
													href="" >													
													<i class="fas fa-paperclip"></i>
													${file.originFilename }&nbsp;&nbsp;
												</p>
												<button type="button" style="border:0;outline:0;" class="badge bg-red">X</button>
											</div>
										</li>	
										</c:forEach>
									</ul>
									<br/>
								</div>
							</div>
						</div>
					</c:if>
					</form>
				</div> <!-- card body 종료 -->
<%-- 
				</div>
					<button type="button" class="btn btn-primary col-sm-3" onclick="doSubmit();">수정</button>
				<div class="card-footer" style="text-align:right;">
--%>
			</div><!-- end card -->				
</section>
	
	
<%@ include file="/WEB-INF/views/pms/include/summernote_js.jsp" %>
<%@ include file="/WEB-INF/views/pms/include/picker_js.jsp" %>
<%@ include file="file_js.jsp" %>
<script type="text/javascript">
function doSubmit() {
	alert('글이 수정되었습니다.');
	$('#modifyForm').submit();
}
window.addEventListener('load', function(){
	$('div.fileInput').on('click','div.mailbox-attachment-info > button',function(event){
		var parent = $(this).parent('div.mailbox-attachment-info').children('p[name=attachedFile]');
		
		if (!confirm("[ " + parent.attr("attach-originFilename") + " ] 파일을 삭제하시겠습니까?")) {
			return false;
		}
		
		var no = parent.attr("attach-no");
		
		$(this).parents('li.attach-item').remove();
		
		var input=$('<input>').attr({"type":"hidden",
		     "name" : "deleteFile",
		     "value" : no
			 }); 
		$('form[role="form"]').prepend(input);
		
		return false;
	});
});
</script>
