<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="../include/bootstrap.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href='//fonts.googleapis.com/css?family=Roboto:100,400,300' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- DatePicker -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- form css -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/site/css/basicForm.css">
</head>
<body>
<div class="container">
	<form id="projectForm" action="regist" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<fieldset>
			<legend>프로젝트 생성하기</legend>
			<label> <!--     <p class="label-txt">프로젝트 이름</p> -->
				<input type="text" class="input" name="projectName" placeholder="프로젝트 이름">
				<div class="line-box">
					<div class="line"></div>
				</div>
			</label>
			<label> <!--     <p class="label-txt">ENTER YOUR NAME</p> -->
				<input type="text" class="input" name="projectExplain" placeholder="프로젝트 설명">
				<div class="line-box">
					<div class="line"></div>
				</div>
			</label>
			<div class="container">
			<div class="row">
			    <div class="col-sm">
			    <p>시작 일자 <input type="text" class="input datepicker" id="startDate" name="startDate" autocomplete="off"></p>
			    <div class="line-box">
					<div class="line"></div>
				</div>
			    </div>
			    <div class="col-sm">
			    <p>종료 일자 <input type="text" class="input datepicker" name="endDate" id="endDate" autocomplete="off"></p>
			    <div class="line-box">
					<div class="line"></div>
				</div>
			    </div>
			</div>
        </div>
			<button type="button" onclick="registProject()" style="margin-top: 20px;">프로젝트 생성하기</button>
		</fieldset>
		<input type="hidden" name="register" value="${loginUser.email }"/>
	</form>
</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('.input').focus(function() {
			$(this).parent().find(".label-txt").addClass('label-active');
		});

		$(".input").focusout(function() {
			if ($(this).val() == '') {
				$(this).parent().find(".label-txt").removeClass('label-active');
			};
		});

	});
	
	$(function() {
		$(".datepicker").datepicker({dateFormat: 'yy-mm-dd'});
	});
	
	function registProject(){
		var form = document.getElementById('projectForm');
		form.submit();		
	}
	
</script>
</html>