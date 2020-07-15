<jsp:useBean id="proBoardVO" scope="request" type="com.pumsac.vo.ProjectNoticeBoardVO"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>공지사항 상세보기</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body>
<form id="registForm" name="registForm" action="regist" role="form" method="post" enctype="multipart/form-data">
<section class="content container-fluid">
	<div class="border m-3 shadow">
		<!-- <div class="col-md-10" style="max-width:960px;"> -->
		<div class="card card-top">
   			<div class="card-header">
				<h2 class="card-title p-1" style="margin-top:4px;">
				<i class="fas fa-bullhorn"></i>
				공지사항
				</h2>
				<div class="card-tools">
					<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="modify();"><i class="fa fa-edit"></i>&nbsp;&nbsp;수정</button>
					<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="remove();"><i class="fa fa-trash"></i>&nbsp;&nbsp;삭제</button>
					<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="popUpClose();"><i class="far fa-window-close"></i>&nbsp;&nbsp;닫기</button>
				</div>
			</div>
		</div>

   		<div class="card-body">
	    	<div class="row m-0 border-bottom">
				<div class="col border-right p-3 my-auto" id="title"><h4>${proBoardVO.title}</h4></div>
				<div class="col-3 my-auto" >
					<div class="p-1 font-weight-bold" id="writer" style="white-space: nowrap; overflow: hidden; text-overflow:ellipsis;">${proBoardVO.writer}</div>
					<div class="p-1 text-sm" id="viewCnt">
					${proBoardVO.regDate}<br>
					조회 ${proBoardVO.viewCount}
					</div>
				</div>
			</div>
			<div class="row m-0 p-3" style="min-height:250px;">
	            ${proBoardVO.content}
	        </div>
        
			<c:if test="${attachFileList.originFilename ne null }">
	        <div class="form-group">
	            <label for="downloadFileName">첨부파일</label>
	            <div class="row">
	            	<div class="col-3">
			            <button type="button" class="btn btn-primary" onclick="download('${attachFileList.originFilename}')"> 다운로드</button>	            	
	            	</div>
	            	<div class="col-9">
	            		<input type="text" class="form-control"
	                   	id="downloadFileName" name="email" value="${attachFileList.originFilename }" readonly="readonly" style="display: inline-block">
	            	</div>
	            </div>
	        </div>
	        </c:if>
	 	</div>
	</div>
</section>
</form>
<%-- <script        src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script        src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script> --%>
<script type="text/javascript">
    function popUpClose() {
        self.close();
        opener.document.location.reload();
    }
    function download(name) {
        if (confirm("파일을 다운로드 하실건가요?")) {
            var obj = {
                fileName: name
            };
            $.ajax({
                type: 'post',
                url: 'downloadFile',
                data: obj,
                success: function (result) {
                    alert('바탕화면으로 다운로드가 완료되었습니다.');
                },
                error: function () {
                    alert('파일 다운로드 실패 ');
                }


            })

        } else {
            return;
        }

    }
    function modify() {
        window.location.href="<%=request.getContextPath() %>/pms/team/notice/modify?no=${proBoardVO.no}&&projectId=${proBoardVO.projectId}"

    }
    function remove(){
        if(confirm('이 글을 삭제하시겠습니까?')){
            location.href="${pageContext.request.contextPath}/pms/team/notice/remove?no=${proBoardVO.no}&&projectId=${proBoardVO.projectId}";
        }
    }

</script>
</body>
</html>
