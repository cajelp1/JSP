<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scale=no">

    <link href="<%request.getContextPath();%>/resources/mobile/css/bootstrap.min.css">


    <script src="<%request.getContextPath();%>/resources/mobile/js/bootstrap.min.js"></script>

    <script src="<%request.getContextPath();%>/resources/mobile/js/popper.min.js"></script>
    <script src="<%request.getContextPath();%>/resources/mobile/js/jquery-3.5.1.min.js"></script>
    <style>

    </style>
    <title>dsp에 오신걸 환영합니다. </title>
    <script>
        $(document).ready(function(){
            $("li a[href='<%=request.getRequestURI()%>']").addClass("active");
        });
    </script>
</head>
<header class="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/mobile/main">Home </a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/mobile/login">로그인</a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/mobile/logout">로그아웃</a>
                <a class="nav-item nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </div>
        </div>
    </nav>

</header>