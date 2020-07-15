<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>PMS DASH BOARD</title>

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

    <!-- Main Sidebar Container -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="right col-sm-12">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">PMS</a></li>
                            <li class="breadcrumb-item active">대시보드</li>
                        </ol>
                    </div>
                    <!-- /.col -->

                    <!-- /.col -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-sm-12">
                        <form id="search" action="list" method="post">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-sm-3">

                                        <select id="searchType" name="searchType" class="form-control">
                                            <option value="">검색구분</option>
                                            <option value="t"
                                            ${cri.searchType eq 't' ? 'selected':'' }>프로젝트이름
                                            </option>
                                            <option value="w"
                                            ${cri.searchType eq 'w' ? 'selected':'' }>팀원아이디
                                            </option>
                                        </select>
                                    </div>
                                    <div class="col-sm-3">
                                        <select id="orderByCondition" name="orderByCondition" class="form-control">
                                            <option value="">정렬구분</option>
                                            <option value="projectname"
                                            ${cri.orderByCondition eq 'projectname' ? 'selected':'' }>프로젝트이름순
                                            </option>
                                            <option value="startdate"
                                            ${cri.orderByCondition eq 'startdate' ? 'selected':'' }>프로젝트시작일순
                                            </option>
                                            <option value="enddate"
                                            ${cri.searchType eq 'enddate' ? 'selected':'' }>프로젝트종료일순
                                            </option>
                                        </select>
                                    </div>

                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="keyword" name="keyword"
                                                   placeholder="키워드" value="${pageMaker.cri.keyword }">
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <button id="searchBtn" name="searchBtn" type="button" class="btn btn-block btn-default">검색
                                        </button>
                                    </div>

                                </div>
                            </div>
                    </div>
                    </form>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row">
                    <div class="card-body">
                        <div id="example2_wrapper" class="dataTables_wrapper dt-bootstrap4 project" >
                            <div class="row">
                                <c:forEach items="${projectList}" var="project">
                                    <div class="col-md-4">
                                        <div class="card bg-primary" onclick="project_go(${project.PROJECTID})">
                                            <div class="card-header">
                                                <h3 class="card-title">${project.PROJECTNAME}</h3>
                                                <div class="card-tools">
                                                    <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
                                                    </button>
                                                </div>
                                                <!-- /.card-tools -->
                                            </div>
                                            <!-- /.card-header -->
                                            <div class="card-body">
                                                관리자 : ${project.REGISTER}<br>
                                                프로젝트 설명 ${project.PROJECTEXPLAIN }
                                            </div>
                                            <h6>시작일:${project.STARTDATE}</h6>
                                            <h6>종료일:${project.ENDDATE}</h6>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>
<!-- /.content-header -->
</div>
<!-- Main content -->
<!-- jQuery -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
<script type="text/javascript">



    $('#searchBtn').on('click', function () {
        var form = $('form#search');

        var searchType=$('select#searchType');
        var orderByCondition=$('select#orderByCondition');
        /*if(searchType.val()==""){
            alert("검색구분을 선택하세요.");
            searchType.focus();
            return;
        }*!/*/
        form.submit();
    })

    function project_go(project_id){
        location.href="<%=request.getContextPath()%>/user/main?projectId="+project_id;
    }

</script>

</body>
</html>
