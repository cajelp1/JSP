<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Content Wrapper. Contains page content -->
    <div class="content">
    	<div class="container">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="right col-sm-12">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">프로젝트</a></li>
                            <li class="breadcrumb-item active">자료실</li>
                        </ol>
                    </div>
                    <!-- /.col -->

                    <!-- /.col -->
                </div>
                <!-- /.row -->
				<div class="row">
	                <div class="right col-sm-2">
		                <button type="button" class="btn btn-block bg-gradient-primary" onclick="OpenWindow('regist?projectId=${projectId}','','850','620')">글등록</button>
	                </div>
                </div>
                    <form id="search" action="list" method="post">
                <div class="row">
                	<div class="right col-sm-4">
	                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                    <input name="page" type="hidden" value="${pageMaker.cri.page }">
	                    <input name="perPageNum" type="hidden" value="${pageMaker.cri.perPageNum }">
	                    <input name="projectId" type="hidden" value="${projectId}">

                      <select id="searchType" name="searchType" class="form-control">
                          <option value="">검색구분</option>
                          <option value="t" ${pageMaker.cri.searchType eq 't' ? 'selected':'' }> 제목 </option>
                          <option value="w" ${pageMaker.cri.searchType eq 'w' ? 'selected':'' }> 작성자 </option>
                          <option value="c" ${pageMaker.cri.searchType eq 'c' ? 'selected':'' }> 본문 </option>
                      </select>
                	</div>
                    <div class="right col-sm-6">
                        <div class="form-group">
                            <input type="text" class="form-control" id="keyword" name="keyword"
                                   placeholder="키워드" value="${pageMaker.cri.keyword }">
                        </div>
                    </div>
                    <div class="right col-sm-2">
                        <button id="searchBtn" name="searchBtn" type="button" class="btn btn-block btn-default">검색
                        </button>
                    </div>
                </div>
				</form>
				</div>

                <div class="row">
                    <div class="card-body">
                        <div id="example2_wrapper" class="dataTables_wrapper dt-bootstrap4">
                            <div class="row">
                                <div class="col-sm-12 col-md-6"></div>
                                <div class="col-sm-12 col-md-6"></div>
                            </div>
                            <div class="row">
                                <div class="left col-sm-12">페이지 정보</div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="example2"
                                           class="table table-bordered table-hover dataTable dtr-inline"
                                           role="grid" aria-describedby="example2_info">
                                        <thead>
                                        <tr role="row">
<!--                                             <th class="sorting_asc" tabindex="0" -->
<!--                                                 aria-controls="example2" rowspan="1" colspan="1" -->
<!--                                                 aria-sort="ascending" -->
<!--                                                 aria-label="Rendering engine: activate to sort column descending">번호 -->
<!--                                             </th> -->
                                            <th class="sorting" tabindex="0" aria-controls="example2"
                                                rowspan="1" colspan="1"
                                                aria-label="Browser: activate to sort column ascending"
                                                >제목
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="example2"
                                                rowspan="1" colspan="1"
                                                aria-label="Platform(s): activate to sort column ascending"
                                                style="">작성자
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="example2"
                                                rowspan="1" colspan="1"
                                                aria-label="Engine version: activate to sort column ascending"
                                                style="">조회수
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="example2"
                                                rowspan="1" colspan="1"
                                                aria-label="CSS grade: activate to sort column ascending"
                                                style="">등록일자
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${list}" var="list">
                                        <c:if test="${list.deleteYn eq 'n' }">
                                            <tr role="row" class="odd">
<%--                                                 <td>${list.no}</td> --%>
                                                <td style="cursor:pointer;" onclick="OpenWindow('detail?projectId=${projectId}&no=${list.no }','','700','910')">${list.title }</td>
                                                <td>${list.wrtierNickName }</td>
                                                <td>${list.viewCnt }</td>
                                                <td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${list.deleteYn eq 'y' }">
	                                        <tr role="row">
	                                        	<td>${list.no }</td>
	                                        	<td colspan="4">삭제된 게시글입니다</td>	
	                                        </tr>
                                        </c:if>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12 col-md-12">
                                    <div class="dataTables_paginate paging_simple_numbers"
                                         id="example2_paginate">
                                        <ul class="pagination justify-content-center">
											<%@ include file="/WEB-INF/views/pms/team/notice/pagenation.jsp" %>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
    	</div>
        </div>

        <!-- /.content-header -->

    </div>
    <!-- Main content -->
</div>
<script type="text/javascript">
window.addEventListener('load', function(){
	 function searchList() {
	        /* alert(1111); */
	            var cri = $("#searchType option:selected").val();
	            var keyword = $("#keyword").val();
	            alert(cri);
	            alert(keyword);
	    }

	    $('#searchBtn').on('click', function () {
	        var form = $('form#search');

	        var searchType=$('select#searchType');
	        if(searchType.val()==""){
	            alert("검색구분을 선택하세요.");
	            searchType.focus();
	            return;
	        }
	        form.submit();
	    })	
});
</script>
