<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%-- <%@ include file="../include/bootstrap.jsp" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="<%=request.getContextPath() %>/site/resources/js/regist_validation.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/site/css/regist_member.css"/>
</head>
<body>
<div class="wrapper">
<div class="container step">
    <div class="row">
    	<div class="col-3 active" id="alrnr">
	    	<div class="row step1">
    			<div class="col">
		    		<span class="tit"><i class="far fa-check-square icon"></i>STEP 01</span>
					<p class="txt">약관동의</p>
				</div>
			</div>	
    	</div>
    	<div class="col-1 active" id="arrow"></div>
    	<div class="col-4">
	    	<div class="row step2">
    			<div class="col">
		    		<span class="tit"><i class="fas fa-pencil-alt icon"></i>STEP 02</span>
					<p class="txt">회원정보 입력</p>
    			</div>
			</div>	
		</div>	
    	<div class="col-4">
    		<div class="row step3">
    			<div class="col">
		    		<span class="tit"><i class="far fa-smile-wink icon"></i>STEP 03</span>
					<p class="txt">회원가입 완료</p>
    			</div>
    		</div>
		</div>
   	</div>
</div>
<div class="container">
	<h3>통합회원가입을 위한 약관을 확인해주세요.</h3>
	<h4>이용약관</h4>
</div>
	<%@ include file="../include/agree_ment.jsp" %>
</div>
</body>
<script>
	function agreeCheck() {
		var chkbox = document.getElementsByName('agree');
		var chk = false;
		for (var i = 0; i < chkbox.length; i++) {
			if (chkbox[i].checked) {
				chk = true;
			} else {
				chk = false;
			}
		}
		if (chk) {
			window.location.href="/commons/member/regist";
		} else {
			alert("모든 약관에 동의해 주세요.")
		}
	}
	document.getElementById('alrnr').style.color=  "white";
	document.getElementById('alrnr').style.backgroundColor="blue";
	
</script>
</html>