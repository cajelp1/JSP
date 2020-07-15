<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>공지사항 수정 </title>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
    <!-- Theme style -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
            rel="stylesheet">
</head>
<body>
<section class="content container-fluid">
	<div class="border m-3 shadow">
		<!-- <div class="col-md-10" style="max-width:960px;"> -->
			<div class="card card-top">
				<div class="card-header">
					<h2 class="card-title p-1" style="margin-top:4px;">
						<i class="fas fa-bullhorn"></i>
						공지사항 수정
					</h2>
					<div class="card-tools">
						<!-- <button type="button" class="btn btn-warning" onclick="history.go(-1);">뒤로가기</button> -->
				        <!-- <button type="button" class="btn btn-primary" onclick="doSubmit();">수정</button> -->
				        <button type="button" class="btn btn-sm bg-white shadow-sm" onclick="history.go(-1);"><i class="fas fa-undo-alt"></i>&nbsp;&nbsp;뒤로가기</button>
				        <button type="button" class="btn btn-sm bg-white shadow-sm" onclick="doSubmit();"><i class="fa fa-edit"></i>&nbsp;&nbsp;수정</button>
				        <button type="button" class="btn btn-sm bg-white shadow-sm" onclick="window.close();"><i class="far fa-window-close"></i>&nbsp;&nbsp;닫기</button>
				        <!-- <button type="button" class="btn btn-primary" onclick="window.close()">닫기</button> -->
					</div>
				</div>
			</div>
			<form id="modifyForm" name="modifyForm" action="modify?${_csrf.parameterName}=${_csrf.token}" role="form" method="post"
      			enctype="multipart/form-data">
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    <input type="hidden" name="no" id="no" value="${proBoardVO.no}"/>
			    <input type="hidden" name="fileDeleteYn" id="fileDeleteYn" value="n"/>
			    <input type="hidden" name="projectId" value="${proBoardVO.projectId}"/>
			    <div class="card-body">
			    	<div class="row">
				        <div class="form-group col-sm-8">
				            <label for="title">제목</label>
				            <input type="text" class="form-control" id="title" name="title" value="${proBoardVO.title}">
				        </div>
				        <div class="form-group col-sm-4">
				            <label for="title">작성자</label>
				            <input type="text" class="form-control" id="email" name="email" value="${proBoardVO.writer }"
							placeholder="제목을 입력하세요" readonly="readonly">
				        </div>
			        </div>

			        <div class="form-group">
			            <label for="content">내용</label>
			            <textarea rows="10" cols="" id="content" name="content"
			                      class="form-control required">${proBoardVO.content}</textarea>
			        </div>
			
			        <div class="form-group">
			            <label for="file">파일 첨부</label>
			            <div class="input-group">
			                    <input type="file" id="file" name="file" onchange="fileValChange(this)" value="${ProjectNoticeAttachFileVO.originFilename}">
			            </div>
			            <c:if test="${attachFileList ne null}">
			                <div id="fileInfoDiv" class="mailbox-attachment-info">
			                    <a class="mailbox-attachment-name" name="attachedFile"
			                       attach-originFilename="${attachFileList.originFilename }"
			                       attach-no="${attachFileList.no}"
			                       href="">
			                        <i class="fas fa-paperclip"></i>
			                            ${attachFileList.originFilename }&nbsp;&nbsp;
			                    </a>
			                    <button id="deleteFileButton" type="button" style="border:0;outline:0;" class="badge bg-red"
			                            onclick="deleteAttach();">X
			                    </button>
			                </div>
			            </c:if>
			        </div>
			    </div>
			</form>
	    </div>
</section>
<script
        src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script
        src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript">
    function fileValChange() {
        $('#fileDeleteYn').val('y');
        $('#fileInfoDiv').remove();
    }

    function deleteAttach() {
        $('#fileInfoDiv').remove();
    }

    function doSubmit() {
        alert('글이 수정되었습니다.');
        $('#modifyForm').submit();
    }
</script>
<%@ include file="summernote_js.jsp" %>
</body>
</html>
