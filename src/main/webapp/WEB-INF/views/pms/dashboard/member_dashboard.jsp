<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>

<title>대시보드</title>

<!-- Font Awesome -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<!-- jQuery UI -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.structure.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.theme.min.css">
<!-- 	<script  src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	<!-- bootstrap colorPicker -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- default-header.css 스타일 수정할거면 여기서 할것! -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/default-header.css">

</head>
<body>
	
	<div class="content-wrapper" style="min-height: 774px;margin-left: 0px;">
		<!-- Content Header (Page header) -->
		<!-- Main content -->
		<section class="content">
			<div class="container">
				<c:if test="${dataMap.percent eq 300 }">
					<div class="row main-content chart">
						<div class="col-12" style="min-height:200px;text-align:center;">
							<br><br>
							<h3>${dataMap.project.projectName }에 등록된 업무가 없습니다.</h3>						
						</div>
					</div>
				</c:if>
				
				<c:if test="${dataMap.percent ne 300 }">
				<div class="row main-content chart">
					<div class="container row m-4">
						<div class="col-lg-6 col-md-12 col-sm-12">
							<canvas id="donutChart"  class="chartjs-render-monitor"></canvas>
						</div>
						<div class="col-lg-6 col-md-12 col-sm-12">
							<canvas id="barChart" class="chartjs-render-monitor"></canvas>
						</div>
					</div>
				</div>
				</c:if>
			<%-- 
				<div class="row tab tab-menu">
				  <button class="tablinks" onclick="openTab(event, 'member')">프로젝트 참여자</button>
				  <button class="tablinks" onclick="openTab(event, 'team_schedule')">팀 일정</button>
				  <button class="tablinks" onclick="openTab(event, 'recent_team_schedule')">최근 등록 업무</button>
				  <button class="tablinks" onclick="openTab(event, 'closing_schedule')">마감 임박 업무</button>
				  <button class="tablinks" onclick="openTab(event, 'waiting_completed_schedule')">완료 대기 업무</button>
				</div>
			 --%>
			 <div class="row content-row">
				<div id="member" class="col-lg-4 col-md-12 col-sm-12">
				  <div class="small-box bg-warning h-100">
						<div class="inner">
							<h3>프로젝트 참여자</h3>
							<c:forEach items="${member }" var="team" varStatus="status">
								<c:if test="${status.index < 5 }">
									<p>${team.nickname }
									</p>
								</c:if>
							</c:forEach>
						</div>
						<div class="icon">
							<i class="ion ion-person-add"></i>
						</div>
						<a onclick="manager_dashboard('user/list');" class="small-box-footer">참여자 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
					</div>
				</div>
				
				<div id="team_schedule" class="col-lg-4 col-md-12 col-sm-12">
					<div class="small-box bg-danger h-100">
						<div class="inner">
							<h3>팀 일정</h3>
						<c:forEach items="${dataMap.teamSchedule }" var="schedule" varStatus="status">
							<c:if test="${status.index < 5 }">
							<p>${schedule.title }</p>
							</c:if>
						</c:forEach>
						</div>
						<div class="icon">
							<i class="ion ion-pie-graph"></i>
						</div>
						<a onclick="manager_dashboard('team/officialSchedule/calendar');" class="small-box-footer" style="bottom:0;">팀 일정 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
					</div>
				</div>
				
				<div id="recent_team_schedule" class="col-lg-4 col-md-12 col-sm-12">
					<div class="small-box bg-success h-100">
						<div class="inner">
							<h3>내 업무</h3>
						<c:forEach items="${task }" var="task" varStatus="status">
							<c:if test="${status.index < 5 }">
							<p>${task.title }</p>
							</c:if>
						</c:forEach>
						</div>
						<div class="icon">
							<i class="ion ion-stats-bars"></i>
						</div>
						<a onclick="manager_dashboard('team/personalSchedule/list');" class="small-box-footer">업무 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
					</div>
				</div>
				</div><!-- .row -->	
			</div>
		</section>
	</div>

<script src="<%=request.getContextPath()%>/resources/pms/js/Chart.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">
<script>
	var ctx = $('#donutChart');
	var dChart = new Chart(ctx, {
		type: 'doughnut',
		data: {
			labels: ['완료', '미완료'],
			datasets: [{
				label: '${dataMap.project.projectName}',
				data: [${dataMap.percent}, ${100-dataMap.percent}],
				backgroundColor: [
                    'blue',
                    'red'
				],
                borderColor: [
                    'blue',
                    'red'
				],
				borderWidth: 1
			}]
		}
	});
	
	var ctx2 = $('#barChart');
	//넣을 데이터 정리
	var jsonArr = ${dataMap.memberPercent};
	var arr = [];
	var avgArr = [];
	for(var i = 0; i < jsonArr.length; i++){
		arr.push(jsonArr[i].memberId);
		avgArr.push(jsonArr[i].avg);
	}
	
	var bChart = new Chart(ctx2, {
		type: 'bar',
		data: {
			labels: arr,
			datasets: [{
				label: '프로젝트 진행상황',
				data: avgArr,
				fillcolor:'green'
			}]
		},
		options: {
			scales: {
		        yAxes: [{
		            ticks: {
		            	 max: 100,
		                 min: 0,
		            }
		        }]
		    }
		}
	});
	
</script>
<!-- 탭 만들기 -->
<script>
	/* function openTab(evt, option) {
	  var i, tabcontent, tablinks;
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }
	  tablinks = document.getElementsByClassName("tablinks");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }
	  document.getElementById(option).style.display = "block";
	  evt.currentTarget.className += " active";

	}
 */
	function manager_dashboard(url){
		location.href="<%=request.getContextPath()%>/pms/"+url+"?projectId=${dataMap.project.projectId}";			
	}
</script>
</body>

</html>