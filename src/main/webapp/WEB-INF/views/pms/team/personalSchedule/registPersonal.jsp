<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<!-- jQuery UI -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.structure.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.theme.min.css">
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	<!-- default-header.css 스타일 수정할거면 여기서 할것! -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/default-header.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/pms/css/hover-menu.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/personal-style.css">
	
	<!-- Bootstrap -->                                   
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- jQuery UI -->                                   
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- AdminLTE App -->                                
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/resources/pms/js/common.js"></script>
</head>

<!-- Main content -->
<section class="content container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-10" style="max-width:960px;">
			<div class="card card-info">
				<div class="card-header">
					<h2 class="card-title p-1" style="margin-top:4px;">
						<i class="far fa-calendar-alt"></i>
						개인일정 등록
					</h2>
					<div class="card-tools">
						<button type="button" class="btn btn-warning" onclick="registCancle();">취소</button>
					</div>
				</div><!--end card-header  -->
				<div class="card-body pad">
					<form id="registForm" name="registForm" action="regist?${_csrf.parameterName}=${_csrf.token}" role="form" method="post">
						<input type="hidden" name="projectId" value="${projectId}">
						<input type="hidden" name="charger" value="${sessionScope.loginUser.email }">
						
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="title">제목</label>
								<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요">
							</div>
							<div class="form-group col-sm-4">
								<label for="nickname">작성자</label>
								<input type="text" class="form-control" id="nickname" value="${sessionScope.loginUser.nickname}" readonly="readonly">
							</div>
						</div>
						<div class="row">	
							<div class="form-gruop col-sm-12">
								<button type="button" class="btn btn-info" data-toggle="modal" data-target="#modal-lg" id="openModalBtn">
					            	<i class="fas fa-user"></i>
					            	보낼사람 목록
								</button>
								<div class="input-group col-sm-12">
									<span class="selectMemberSpanTag col-sm-12">
										<ul id="sendSelectedMember">
											
										</ul>
									</span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-4">
								<label for="fromDate">시작일</label>
								<input type="text" id="fromDate" class="form-control daterangepicker" name="startDate"  autocomplete="off" readonly><br/>
							</div>
							<div class="form-group col-sm-1 center" style="text-align: center;margin-top: 24px;">
								<div class="container">
									<div class="row align-self-center">
										<div class="col">
											<span class="align-middle" style="font-size: 2.2em;">~</span>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group col-sm-4">
								<label for="toDate">종료일</label>
								<input type="text" id="toDate" class="form-control" name="endDate"  autocomplete="off" readonly><br/>
							</div>
							<div class="col-sm-3">
								<div class="my-colorpicker2 colorpicker-element" data-colorpicker-id="2">
							 		<label for="color">색상 선택</label>
							 		<div class="row">
								 		<div class="col-sm-8">
									 		<input type="text" id="color" class="form-control" name="color" data-original-title="" title="" autocomplete="off">
								 		</div> 
							   			<span class="input-group-text"><i class="fas fa-square" style="color: rgb(0, 231, 243);"></i></span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-4">
								<label>진척률</label>
								<select name="processPercent" class="form-control">
									<option value="0" selected>0%</option>
									<option value="10">10%</option>
									<option value="20">20%</option>
									<option value="30">30%</option>
									<option value="40">40%</option>
									<option value="50">50%</option>
									<option value="60">60%</option>
									<option value="70">70%</option>
									<option value="80">80%</option>
									<option value="90">90%</option>
									<option value="100">100%</option>
								</select><br/>
							</div>	
							<div class="form-group col-sm-4">
								<label>프로세스 상태</label>
								<select name="processStaues" class="form-control">
									<option value="720" selected>신규</option>
									<option value="710">진행</option>
									<option value="700">완료</option>
								</select>
							</div>
							<div class="form-group col-sm-4">
								<label>최종승인여부</label>
								<select name="approvalYn" class="form-control">
									<option value="n" selected>거부</option>
									<option value="y">승인</option>
								</select>
							</div>
						</div>
						<div class="form-group">
<!-- 							<label for="content" >내용</label> -->
							<textarea rows="10" cols="" id="content" name="content"
								class="form-control required" style="display: none;"></textarea>
						</div>
					</form>
				</div><!--end card-body  -->
				
				<div class="card-footer" style="text-align:right;">
					<button type="button" class="btn btn-primary col-sm-3" onclick="javascript:$('form[role=form]').submit();">등록</button>
				</div><!--end card-footer  -->
				
			</div><!-- end card -->				
		</div><!-- end col-md-10 -->
	</div><!-- end row -->
</section>


<!-- 모달 영역 -->
<div class="modal fade" id="modal-lg">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">회원 선택</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-5" id="teamMemberlist">
						<h3>회원목록</h3>
					</div>
					<div class="col-sm-2 btn-group-vertical">
						<button id="rightAll"> >> </button>
						<button id="leftAll"> << </button>
					</div>
					<div class="col-sm-5" id="selectTeamMember">
						<h3>선택회원</h3>
					</div>
				</div>
			</div>
			
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" id="modalSelectComplete">선택완료</button>
			</div>
		</div> <!-- modal-content 끝 -->
	</div>
</div> <!-- 모달 영역 끝 --> 
<%@ include file="/WEB-INF/views/pms/include/picker_js.jsp" %>
<%@ include file="/WEB-INF/views/pms/include/summernote_js.jsp" %>
<script>
	function registCancle(){
		if (confirm("작성한내용이 저장되지 않습니다.\r\n등록을 취소하시겠습니까?")) {
			window.close();
		}
	}

	function removeSelectedMember(tag){
		var liT = $(tag).parents('li.liMember');
		var memberInput = $('input[memId="'+ liT.attr('memId') +'"]');
		memberInput.remove();
		liT.remove();
		$(tag).parents('li.liMember').remove();
	}
</script>
<script>
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
window.addEventListener('load', function() {
	
	$('#openModalBtn').on('click', function(){
		$.ajax({
			url : '${pageContext.request.contextPath}/pms/team/personalSchedule/getProjectMemberList?projectId=${projectId}',
			type : 'get',
			beforeSend: function( xhr ) {
			  xhr.setRequestHeader(header, token);
			},
			success : function(data){
				var Source = $('#projectTeamMember-template').html();
				var template=Handlebars.compile(Source);
				$('#teamMemberlist').append(template(data));
			},
			error : function(){
				alert("잠시후 다시 요청해주세요");
			}
		})
	});
	
	/* 모달이 닫히는 순간 일어나는 이벤트*/
	$('#modal-lg').on('hide.bs.modal', function (evnet) {
		$('.modal-body div[data-memberId]').remove();
	});

	/* 왼쪽내용 클릭시 오른쪽으로 이동 */
	$('#teamMemberlist').on('click', 'div[data-memberId]', function(){
		$('#selectTeamMember').append(this);
	});
	$('#rightAll').on('click', function(){
		var teamChild = $('#teamMemberlist').children('div[data-memberId]');
		for (var value of teamChild) {
			$('#selectTeamMember').append(value);
		}
	});
	
	/* 오른쪽내용 클릭시 왼쪽으로 이동 */
	$('#selectTeamMember').on('click', 'div[data-memberId]', function(){
		$('#teamMemberlist').append(this);
	});
	$('#leftAll').on('click', function(){
		var teamChild = $('#selectTeamMember').children('div[data-memberId]');
		for (var value of teamChild) {
			$('#teamMemberlist').append(value);
		}
	});
	
	/* 선택완료 클릭시 이벤트 */
	$('#modalSelectComplete').on('click', function(){
		$('#sendSelectedMember').empty();
		var teamChild = $('#selectTeamMember').children('div[data-memberId]');
		for (var value of teamChild) {
			
			var Name = $($(value).children('div')[0]).children().text();

			var liTag = $('<li>');
			liTag.addClass("liMember");
			liTag.attr('memId', $(value).attr('data-memberId'));
			liTag.text(Name);
			liTag.append($('<span>').attr("onclick", "removeSelectedMember(this);").text('X'));
			$('#sendSelectedMember').append(liTag);
		}
		for (var value of teamChild) {
			var input = $('<input>');
			input.attr('type', 'hidden');
			input.attr('name', 'memberId');
			input.attr('memId', $(value).attr('data-memberId'));
			input.attr('value', $(value).attr('data-memberId'));
			$('#sendSelectedMember').append(input);
		}
		
	});
});
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.min.js" ></script>
<script id="projectTeamMember-template" type="text/x-handlebars-template">
{{#each .}}
	<div class="row" data-memberId={{memberId}} style="cursor: pointer; border-style: solid;">
		<div class="col-sm-8 input-group-text">
			<span>{{memberNickName}}</span>
		</div>
		<div class="col-sm-4 input-group-text">
			<span>{{positionName}}</span>
		</div>
	</div>
{{/each}}
</script>
