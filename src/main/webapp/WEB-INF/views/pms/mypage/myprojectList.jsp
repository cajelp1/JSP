<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>마이페이지</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>AdminLTE 3 | Starter</title>

    <!-- Font Awesome Icons -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
    <!-- Theme style -->
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
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
<body>
<div class="col-12">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">프로젝트 리스트 </h3>

            <div class="card-tools">
                <div class="input-group input-group-sm" style="width: 150px;">
                    <input type="text" name="table_search" class="form-control float-right" placeholder="Search">

                    <div class="input-group-append">
                        <button type="submit" class="btn btn-default"><i class="fas fa-search"></i></button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.card-header -->
        <div class="card-body table-responsive p-0" style="height: 300px;">
            <table class="table table-head-fixed text-nowrap">
                <thead>

                <tr>
                    <th>프로젝트 아이디</th>
                    <th>프로젝트 이름</th>
                    <th>프로젝트 시작일</th>
                    <th>프로젝트 종료일</th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${projectList}" var="pro">
                    <tr>
                        <td>${pro.projectId}</td>
                        <td><a href="/pms/team/notice/list/${pro.projectId}" target="_blank">${pro.projectName}</a></td>
                        <td>${pro.startDate}</td>
                        <td>${pro.endDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- /.card-body -->
    </div>
    <!-- /.card -->
</div>
</body>
</html>
