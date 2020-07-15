<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<script src="<%=request.getContextPath()%>/resources/site/js/common.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/site/css/regist_member.css"/>
<!-- Our project just needs Font Awesome Solid + Brands -->
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/fontawesome.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/brands.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/site/css/fontawesome/solid.css" rel="stylesheet">
</head>
<body>
	<style>
img {
	background: blue;
}
</style>
<div class="container step">
    <div class="row">
    	<div class="col-4" id="alrnr">
	    	<div class="row step1">
    			<div class="col">
		    		<span class="tit"><i class="far fa-check-square icon"></i>STEP 01</span>
					<p class="txt">약관동의</p>
				</div>
			</div>	
    	</div>
    	<div class="col-4" id="alrnr">
	    	<div class="row step2">
    			<div class="col">
		    		<span class="tit"><i class="fas fa-pencil-alt icon"></i>STEP 02</span>
					<p class="txt">회원정보 입력</p>
    			</div>
			</div>	
		</div>	
    	<div class="col-3" id="alrnr">
    		<div class="row step3">
    			<div class="col">
		    		<span class="tit"><i class="far fa-smile-wink icon"></i>STEP 03</span>
					<p class="txt">회원가입 완료</p>
    			</div>
    		</div>
		</div>
		<div class="col-1 active" id="arrow"></div>
   	</div>
</div>
<div class="container-fluid">
	<div class="row justify-content-center">
		<div class="col-6">
			<div class="card-deck" style="height: 100%;">
				<div class="card">
					<img src="<%=request.getContextPath()%>/resources/site/img/dsp-logo.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h4 class="card-title">회원가입 성공</h4>
						<p class="card-text">회원가입이 정상적으로 완료되었습니다.</p>
						<p class="card-text">
							<small class="text-muted">DSP의 기능을 사용하실 수 있습니다.</small>
						</p>
					</div>
				</div>
			</div>
		<div class="container-fluid">
			<button type="button" class="btn btn-primary btn-lg" onclick="javascript:window.close();">닫기</button>		
		</div>
		<!-- main footer -->
		<footer class="main-footer">
			<div class="container">
				<!-- <div class="main-footer row">
					<br>
					<br>
					<br>
				</div> -->
				<div class="main-footer ">
					<div class="main-footer row">
						<div class="col-sm-4 m-auto">
							<img src="<%=request.getContextPath() %>/resources/site/img/dsp-logo.png" style="height:50px;">
						</div>
						<div class="main-footer col-sm-8 text-right small">
							<p class="footer-context m-2">
							<a href="#">이용안내</a>&nbsp;/&nbsp;
							<a href="#">정보처리약관</a>&nbsp;/&nbsp;
								㈜디에스프로젝트 
								<br> 사업자등록번호 : 306 82 05291 / 대표이사 김령환 <br> 대전광역시 중구 중앙로
								76 영민빌딩 205호 <br> ©2020. DSPROJECT Corp
							</p>
						</div>
					</div>
				</div>
			</div>
		</footer>
		</div>
	</div>
</div>
</body>
</html>