<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>공지사항</title>

    <!-- Font Awesome Icons -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
    <!-- Theme style -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
            rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resources/pms/js/common.js"></script>
    <script type="text/javascript">


        //팝업창들 뛰우기
        //새로운 Window창을 Open할 경우 사용되는 함수 ( arg : 주소 , 창타이틀 , 넓이 , 길이 )
        function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
            winleft = (screen.width - WinWidth) / 2;
            wintop = (screen.height - WinHeight) / 2;
            var win = window.open(UrlStr, WinTitle, "scrollbars=yes,width=" + WinWidth + ", "
                + "height=" + WinHeight + ", top=" + wintop + ", left="
                + winleft + ", resizable=yes, status=yes");
            win.focus();
        }

    </script>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
	<div class="container">
    <!-- Content Wrapper. Contains page content -->
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="right col-sm-12">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">${projectName}</a></li>
                            <li class="breadcrumb-item active">공지사항</li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
					<div class="col-sm-2">
					<c:if test="${job eq 110 }"  >
					<button type="button" class="btn btn-block bg-gradient-primary" onclick="OpenWindow('<%=request.getContextPath()%>/pms/team/notice/regist?projectId=${projectId}','','850','620')">
					    글등록
					</button>
					</c:if>
					</div>
				</div>
                <div class="row">
                    <div class="right col-sm-4">
                        <form id="search" action="list" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <input name="page" type="hidden" value="${pageMaker.cri.page }">
                            <input name="perPageNum" type="hidden" value="${pageMaker.cri.perPageNum }">
                            <input name="projectId" type="hidden" value="${projectId}"/>
                            <select id="searchType" name="searchType" class="form-control">
                                <option value="">검색구분</option>
                                <option value="t"
                                ${pageMaker.cri.searchType eq 't' ? 'selected':'' }>제목
                                </option>
                                <option value="w"
                                ${pageMaker.cri.searchType eq 'w' ? 'selected':'' }>작성자
                                </option>
                                <option value="c"
                                ${pageMaker.cri.searchType eq 'c' ? 'selected':'' }>본문
                                </option>
                                <option value="tc"
                                ${pageMaker.cri.searchType eq 'tc' ? 'selected':'' }>제목+본문
                                </option>
                                <option value="cw"
                                ${pageMaker.cri.searchType eq 'cw' ? 'selected':'' }>본문+작성자
                                </option>
                                <option value="tcw"
                                ${pageMaker.cri.searchType eq 'tcw' ? 'selected':'' }>제목+본문+작성자
                                </option>
                            </select>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <input type="text" class="form-control" id="keyword" name="keyword"
                                   placeholder="키워드" value="${pageMaker.cri.keyword }">
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <button id="searchBtn" name="searchBtn" type="button" class="btn btn-block btn-default">검색
                        </button>
                    </div>
                    </form>
                </div>

                <div class="row">
                    <div class="card-body">
                        <div id="example2_wrapper"
                             class="dataTables_wrapper dt-bootstrap4">
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
                                            <th class="sorting" tabindex="0" aria-controls="example2"
                                                rowspan="1" colspan="1"
                                                aria-label="Browser: activate to sort column ascending">제목
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
                                        <c:forEach items="${noticeList}" var="list">
                                            <tr role="row" class="odd">
                                                <td style="cursor:pointer;" onclick="OpenWindow('<%=request.getContextPath()%>/pms/team/notice/detail?no=${list.no }&projectId=${projectId}','','850','620')">
                                                ${list.title }
                                                </td>
                                                <td>${list.writer }</td>
                                                <td>${list.viewCount }</td>
                                                <td>${list.regDate.substring(0,10)}</td>
                                            </tr>
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
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
<script type="text/javascript">

    $('#searchBtn').on('click', function () {
        var form = $('form#search');

        var searchType = $('select#searchType');
        if (searchType.val() == "") {
            alert("검색구분을 선택하세요.");
            searchType.focus();
            return;
        }
        form.submit();
    });
</script>
</body>
</html>
