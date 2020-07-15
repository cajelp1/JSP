<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	.small-box{
		background-color: #F7E7D6;
	}
	.table-title{
		width: 40%;
	}
	.table-start{
		width: 60%;	
	}
</style>
<!-- 나중에 주석 해제할 것 -->
<%-- 	<button type="button" onclick="javascript:location.href='${pageContext.request.contextPath}/pms/admin/project/complete?projectId=${param.projectId}'">프로젝트완료버튼</button> --%>
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
		<div class="content-body">
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-4">
					<div class="small-box">
				<div class="inner">
				<div class="card-body table-responsive p-0">
					<h4>팀 일정</h4>
					<c:if test="${dataMap.teamSchedule eq null }">
					<span>아직 등록한 팀 일정이 없습니다</span>
					</c:if>
					<c:if test="${dataMap.teamSchedule ne null }">
					<table class="table table-hover text-nowrap table-sm">
						<colgroup>
				            <col width="60%" />
				            <col width="40%" />
						</colgroup>
						<thead>
							<tr>
								<th>제목</th>
								<th>시작일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dataMap.teamSchedule }" var="schedule">
							<tr>
								<td><a onclick="planDetail('${schedule.no}');">${schedule.title }</a></td>
								<td><fmt:formatDate value="${schedule.startDate}" pattern="yyyy-MM-dd" /></td>
							</tr>
							</c:forEach>		
						</tbody>
					</table>
				
					</c:if>
					<p>${schedule.title }</p>
				
				</div>
				</div>
				<a onclick="manager_dashboard('team/calendar');" class="small-box-footer">팀 일정 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
			</div>
			<!-- 카드 끝 -->
					</div>
					
					<div class="col-sm-4">
						<div class="small-box">
							<div class="inner">
								<div class="card-body table-responsive p-0">
									<h4>프로젝트 업무</h4>
									<c:if test="${dataMap.recentSchedule eq null }">
										<span>아직 등록한 업무가 없습니다</span>
									</c:if>
									<c:if test="${dataMap.recentSchedule ne null }">
										<table class="table table-hover text-nowrap table-sm">
											<colgroup>
									            <col width="60%" />
									            <col width="40%" />
											</colgroup>
											<thead>
												<tr>
													<th>업무</th>
													<th>담당자</th>
												</tr>
											</thead>
											<c:forEach items="${dataMap.recentSchedule }" var="recentSchedule">
												<tr>
													<td><a onclick="taskDetail('${recentSchedule.no}')">${recentSchedule.title }</a></td>
													<td>${recentSchedule.memberId }</td>
												</tr>
											</c:forEach>
										</table>
									</c:if>
								</div>
							</div>
							<a onclick="manager_dashboard('personalSchedule/list');" class="small-box-footer">프로젝트 업무 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
						</div>
					</div>
					
					<div class="col-sm-4">
						<div class="small-box">
							<div class="inner">
								<div class="card-body table-responsive p-0">
									<h4>프로젝트 참여자</h4>
									<table class="table table-hover text-nowrap table-sm">
										<colgroup>
								            <col width="60%" />
								            <col width="40%" />
										</colgroup>
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
							</div>
							<a onclick="manager_dashboard('member/list');" class="small-box-footer">참여자 목록 보기<i class="fas fa-arrow-circle-right"></i></a>
						</div>		
					</div>
					
				</div>
			</div>
		</div>
		<!-- Main content -->
		<c:if test="${dataMap.percent eq 300 }">
			<div class="row main-content chart">
				<div class="col-lg-12 col-6">
					등록된 업무가 없군요?..						
				</div>
			</div>						
		</c:if>

		<div class="container-fluid">
			
			<c:if test="${dataMap.percent ne 300 }">
			<div class="row main-content chart" style="margin-left: 0px;margin-right: 0px;">
				<!-- 전체 업무 상황 -->
					<!-- 전체 진행상황 그래프 -->
                	<div id="piechart" style="width: 33%;"></div>
					<div id="bar_chart_div" style="width: 66%;"></div>
			</div>
			</c:if>

			
		</div>

<script>
	function taskDetail(no){
		OpenWindow('<%=request.getContextPath()%>/pms/admin/personalSchedule/detail?no='+no,720,640);
	}
	function planDetail(no){
		OpenWindow('<%=request.getContextPath()%>/pms/admin/team/detail?no='+no,720,640);
	}

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
	               width :600,
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