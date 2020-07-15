<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<body>
<%-- <!-- Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script> --%>

<%--@elvariable id="projetNoticeBoardVO" type=""--%>
<section class="content container-fluid">
    <div class="row justify-content-center">
        <div class="border m-3 shadow">
            <div class="card card-top">
                <div class="card-header">
                    <h2 class="card-title p-1" style="margin-top:4px;">
                        <i class="fas fa-bullhorn"></i>
                        공지사항 등록
                    </h2>
                    <div class="card-tools">
                        <!-- 						<button type="button" class="btn btn-warning" onclick="cancel();">취소</button> -->
                        <button type="button" class="btn btn-blue" onclick="doSubmit();">등록</button>
                        <button type="button" class="btn btn-blue" onclick="close();">닫기</button>
                    </div>

                </div>
            </div>
            <div class="card-body">
                <form:form commandName="projectNoticeBoardVO" id="registForm" name="registForm"
                           action="regist?${_csrf.parameterName}=${_csrf.token}" role="form" method="post"
                           enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <form:hidden name="projectId" value="${projectId}" path="projectId"/>
                <div class="form-group">
                    <label for="title">제목</label>
                    <form:input class="form-control" placeholder="제목을 입력하세요" path="title"/>
                    <form:errors path="title">제목은 1글자 이상입니다.</form:errors>
                </div>
                <div class="form-group">
                    <label for="title">작성자</label>
                    <form:input class="form-control" value="${id}" path="writer" readonly="true"/>

                </div>
                    <%-- <div class="form-group">
                        <label for="">게시기간</label>
                        <form:errors path="postStartDate" htmlEscape="true">게시기간은 필수 입니다.</form:errors>
                        <form:errors path="postEndDate" htmlEscape="true">게시기간은 필수 입니다.</form:errors>
                        <div class="input-group">
                            <form:input type="text" id="startDate" class="form-control" name="postStartDate" placeholder="게시 시작일" path="postStartDate"/>
                            <h5 style="display: inline-block">~</h5>
                            <form:input type="text" class="form-control" id="endDate" name="postEndDate" placeholder="게시 종료일" path="postEndDate"/>
                        </div>
                    </div> --%>
                <div class="form-group">
                    <label for="content">내용</label>
                    <form:textarea rows="10" cols="30" id="content" name="content"
                                   onkeydown="CheckTextCount(this, 1000);" class="form-control required"
                                   style="display: none;" path="content"></form:textarea>
                    <form:errors path="content">내용이 있어야합니다.</form:errors>
                </div>
                <div class="form-group">
                    <label for="file">파일첨부</label>
                    <div class="input-group">
                        <input type="file" id="file" name="file"/>
                    </div>

                </div>
            </div>

        </div>
        </form:form>
    </div>
    </div><!-- end card -->
    </div><!-- end col-md-12 -->
    </div><!-- end row -->
</section>

<%-- 	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script> --%>

<script type="text/javascript">
    $(function () {
        $("#startDate").datepicker({
            dateFormat: 'yy-mm-dd'
        });
        $("#endDate").datepicker({
            dateFormat: 'yy-mm-dd'
        });

    });

    function doSubmit() {
        alert('글이 등록되었습니다.');
        $('#registForm').submit();


    }

    function close() {
        alert("111");


    }

    function fileUpload() {
        var fileInfo = document.getElementById('inputFile');

        if (!filInfo.value) {
            alert('파일이 선택되지 않았습니다. 선택해주세요');
            return;
        }

        /*   $.ajax({
              type:'post',
              url:'fileUpalod',
              data:{fileinfo:fileInfo},
              success:function(xml){
                  alert("성공");
              },
              error:function(){
                  alert('실패');
              }


          }); */

    }
</script>
<%@ include file="summernote_js.jsp" %>
<%-- <%@ include file="/WEB-INF/views/pms/include/picker_js.jsp" %> --%>
</body>
