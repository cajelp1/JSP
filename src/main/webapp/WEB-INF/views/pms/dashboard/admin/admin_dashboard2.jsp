<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script>
	//팝업창들 띄우기
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
<style>
	.inner{
		overflow: hidden;
		height: 208px;
	}
	.small-box{
		height: 240px;
	}
	.main-content{
		height: 240px
	}
	.chart{
	    height: 300px;
	}
</style>
	<button type="button" onclick="javascript:location.href='${pageContext.request.contextPath}/pms/admin/project/complete?projectId=${param.projectId}'">프로젝트완료버튼</button>
	<div class="content-wrapper" style="min-height: 774px;margin-left: 0px;">
		<!-- Content Header (Page header) -->
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0 text-dark">관리자 Dashboard</h1>
					</div>
					<!-- /.col -->
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="<%=request.getContextPath() %>/pms/">Home</a></li>
							<li class="breadcrumb-item active">${dataMap.project.projectName }</li>
						</ol>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /.content-header -->

		<!-- Main content -->
		<c:if test="${dataMap.percent eq 300 }">
			<div class="row main-content chart">
				<div class="col-lg-12 col-6">
					등록된 업무가 없군요?..						
				</div>
			</div>						
		</c:if>
		<section class="content">
			<div class="container-fluid">
				
				<c:if test="${dataMap.percent ne 300 }">
				<div class="row main-content chart">
					<!-- 전체 업무 상황 -->
					<div class="col-lg-3 col-6">
						<!-- 전체 진행상황 그래프 -->
	                	<div id="piechart"></div>
					</div>
					<div class="col-lg-9 col-6">					
						<div id="bar_chart_div"></div>
					</div>
				</div>
				</c:if>

				
			</div>
		</section>
		<section class="content">
			<div class="container-fluid">
		<div class="row tab tab-menu">
			<button id="defaultOpen" class="tablinks" onclick="openTab(event, 'member')">프로젝트 참여자</button>
			<button class="tablinks" onclick="openTab(event, 'team_schedule')">팀 일정</button>
			<button class="tablinks" onclick="openTab(event, 'recent_team_schedule')">최근 등록 업무</button>
			<button class="tablinks" onclick="openTab(event, 'closing_schedule')">마감 임박 업무</button>
			<button class="tablinks" onclick="openTab(event, 'waiting_completed_schedule')">완료 대기 업무</button>
		</div>
		<div id="member" class="tabcontent">
		  <div class="small-box bg-warning">
				<div class="inner">
					<h3>프로젝트 참여자</h3>
						<table>
							<thead>
								<tr>
									<th>이메일</th>
									<th>역할</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${dataMap.team }" var="team">
								<tr>
									<td>${team.memberId }</td>
									<c:if test="${team.job eq 110 }">
									<td>관리자</td>
									</c:if>
									<c:if test="${team.job ne 110 }">
									<td>팀원</td>
									</c:if>
								</tr>
							</c:forEach>
							</tbody>
						</table>
				</div>
				<div class="icon">
					<i class="ion ion-person-add"></i>
				</div>
				<a onclick="manager_dashboard('member/list');" class="small-box-footer">참여자 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
			</div>
		</div>


				
		<div id="team_schedule" class="tabcontent">
			<div class="small-box bg-danger">
				<div class="inner">
					<h3>팀 일정</h3>
					<table>
						<thead>
							<tr>
								<th>제목</th>
								<th>내용</th>
								<th>등록자</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dataMap.teamSchedule }" var="schedule">
							<tr>
								<th>${schedule.title }</th>
								<th>${schedule.content }</th>
								<th>${schedule.register }</th>
								<th><fmt:formatDate value="${schedule.regDate }" pattern="yyyy-MM-dd"/></th>
							</tr>
							</c:forEach>		
						</tbody>
					</table>
				
					<p>${schedule.title }</p>
				
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<a onclick="manager_dashboard('team/calendar');" class="small-box-footer">팀 일정 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
			</div>
		</div>
				
		<div id="recent_team_schedule" class="tabcontent">
			<div class="small-box bg-success">
				<div class="inner">
					<h3>최근 등록된 업무</h3>
				<c:forEach items="${dataMap.recentSchedule }" var="recentSchedule">
					<p>${recentSchedule.title }  담당자 ${recentSchedule.memberId }</p>
				</c:forEach>
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<a onclick="manager_dashboard('personalSchedule/list');" class="small-box-footer">프로젝트 업무 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
			</div>
		</div>
		
		<div id="closing_schedule" class="tabcontent">
			<!-- small box -->
			<div class="small-box bg-info">
				<div class="inner">
					<h3>마감 임박한 업무</h3>
						<c:forEach items="${dataMap.closingSchedule }" var="closingSchedule">
							<p>${closingSchedule.title } 담당자 ${closingSchedule.memberId }</p>
						</c:forEach>
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<a onclick="manager_dashboard('closingSchedule/list');" class="small-box-footer">마감 임박한 업무 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
			</div>
		</div>
		
		<div id="waiting_completed_schedule" class="tabcontent">
			<div class="small-box bg-info">
				<div class="inner">
					<h3>완료 대기중인 업무 목록</h3>
				<c:forEach items="${dataMap.waitingCompletedScehdule }" var="waitingCompletedScehdule">
					<p>${waitingCompletedScehdule.title } 담당자 ${waitingCompletedScehdule.memberId }</p>
				</c:forEach>
				</div>
				<div class="icon">
					<i class="ion ion-pie-graph"></i>
				</div>
				<a onclick="manager_dashboard('personalSchedule/completed');" class="small-box-footer">완료 대기 업무 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
			</div>
		</div>
	</div>
	</section>
<script>

/* 	미국간 한번 클릭 Dashboard 지진나버림
*/	window.addEventListener('load', function(){
		document.getElementById("defaultOpen").click();		
	})
	
	function openTab(evt, option) {
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

	function manager_dashboard(url){
		if(url === 'officialSchedule/calendar'){
			location.href="<%=request.getContextPath()%>/pms/"+url+"?projectId=${dataMap.project.projectId}";
			return;
		}
		location.href="<%=request.getContextPath()%>/pms/admin/"+url+"?projectId=${dataMap.project.projectId}";
	}
</script>

<script src="https://www.gstatic.com/charts/loader.js"></script>
	<script type = "text/javascript">
		google.charts.load('current', {packages: ['corechart']});     
	</script>
	<script>
		function drawChart() {
			// Define the chart to be drawn.
			var data = new google.visualization.DataTable();
			data.addColumn('string', '업무진행여부');
			data.addColumn('number', 'Percentage');
			data.addRows([
				['완료', ${dataMap.percent }],
				['미완료', ${100-dataMap.percent }]
			]);
			
			// Set chart options
			var options = {
				'title':'팀 업무 진행 상황',
				'width':300,
				'height':300,
				pieHole: 0.4,
				tooltip:{textStyle : {fontSize:20}, showColorCode : true}
			};
			
			// Instantiate and draw the chart.
			var chart = new google.visualization.PieChart(document.getElementById('piechart'));
			chart.draw(data, options);
		}
		google.charts.setOnLoadCallback(drawChart);
		
	    function schedulerSuccessAndFailChart() {
			
	    	// jsondata 처리
	    	var jsonArr = ${dataMap.memberPercent};
	    	var arr=[];
	    	arr.push("Member별 업무 진행 상황");
			
			var avgArr = [];
			avgArr.push("");
			for(var i = 0; i < jsonArr.length; i++){
				arr.push(jsonArr[i].memberId);
				arr.push({role:'annonation'});
				avgArr.push(jsonArr[i].avg);
				avgArr.push(jsonArr[i].avg);
			}
			var obj = [];
			obj.push(arr);
			obj.push(avgArr);
			
	       var data = google.visualization.arrayToDataTable(obj);
	    	
	       var barChartOption = {
	               bars: 'vertical',
	               height :300,
	               width : 600,
	               legend: { position: "top" },
	               isStacked: false,
	               tooltip:{textStyle : {fontSize:20}, showColorCode : true},
	               animation: { //차트가 뿌려질때 실행될 애니메이션 효과
	                 startup: true,
	                 duration: 1000,
	                 easing: 'linear' },
	               annotations: {
	                   textStyle: {
	                     fontSize: 20,
	                     bold: true,
	                     italic: true,
	                     color: '#871b47',
	                     auraColor: '#d799ae',
	                     opacity: 0.8
	                   }
	              }
	        };
	 
	       var chart = new google.visualization.BarChart(document.getElementById('bar_chart_div'));
	 
	       chart.draw(data, barChartOption);

	       window.addEventListener('resize', function() { chart.draw(data, barChartOption); }, false);
	    }
	 
	    google.charts.setOnLoadCallback(schedulerSuccessAndFailChart);

// 	    $('#my-card').CardWidget('collapseTrigger');
	</script>
</body>
</html>