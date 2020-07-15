<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<head>
	<title>마이페이지</title>
</head>
<body>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/site/css/myPage.css" />
	<h4 class="text-center">마이페이지 메인</h4>
	<div class="container">
		<div class="row">
			<div class="col-6 menu-button left" onclick="go_url('detail')">
				<span><i class="fas fa-user-edit icon"></i></span>
				<br>
				<p class="menu">내 정보 조회</p>
			</div>

			<div class="col-6 menu-button right" onclick="go_url('changePassword')">
				<i class="fas fa-user-shield icon"></i>
				<br>
				<p class="menu">비밀번호 변경</p>
			</div>

			<div class="col-6 menu-button left" onclick="go_url('pay/list')">
				<i class="fas fa-receipt icon"></i>
				<br>
				<p class="menu">결제 내역</p>
			</div>
			<div class="col-6 menu-button right" onclick="go_url('removeCheck')">
				<i class="fas fa-sign-out-alt icon"></i>
				<br>
				<p class="menu">탈퇴</p>
			</div>
		</div>
	</div>


<script>
	function go_url(url){
// 		alert(url);
		location.href="<%=request.getContextPath()%>/dsp/my/"+url;
	}
</script>
</body>
</html>