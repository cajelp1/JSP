<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="../include/bootstrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 생성 완료</title>
<style type="text/css">
body{
    text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="alert alert-info">
		<div class="notify successbox">
        <h1><strong>Info:</strong>프로젝트 생성을 완료했습니다!</h1>
        <span class="alerticon"><img src="http://s22.postimg.org/i5iji9hv1/check.png" alt="checkmark" /></span>
        <p>프로젝트 생성을 완료했습니다.</p>
        <p>프로젝트실을 찾아서 예약하실 수 있습니다.</p>
      </div>
			
			<button type="button" class="btn btn-xs btn-primary pull-right" onclick="successGo('<%=request.getContextPath() %>/dsp/room/list')">프로젝트실 찾기</button>
			<button type="button" class="btn btn-xs btn-primary pull-right" onclick="successGo('<%=request.getContextPath() %>/pms/')">PMS 바로가기</button>
		</div>
	</div>
<script>
	function successGo(url){
		window.close();
		window.opener.location.href=url;
	}
</script>	
</body>
</html>