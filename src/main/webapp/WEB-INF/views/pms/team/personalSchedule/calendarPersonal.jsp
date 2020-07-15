<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
<!-- 	<form action="calendar" method="get"> -->
<%-- 		<input type="hidden" name="projectId" value="${projectId}"> --%>
		<select name="searchType">
			<option value="t" ${cri.searchType == "t" ? 'selected' : ''}>제목</option>
			<option value="d" ${cri.searchType == "d" ? 'selected' : ''}>날짜</option>
			<option value="sd" ${cri.searchType == "sd" ? 'selected' : ''}>시작일</option>
			<option value="ed" ${cri.searchType == "ed" ? 'selected' : ''}>종료일</option>
		</select>
		<input type="text" name="keyword" id="datePicker" autocomplete="off" value="${cri.keyword }">
		<button type="button" onclick="reloadCalendar();">검색</button>
<!-- 		<input type="submit" value="검색"> -->
<!-- 	</form> -->
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
      	<div class="row">
      		<div class="card">
      			<a class="btn btn-app" onclick="registPersonalSchedule();">
				    <i class="fas fa-edit"></i> 등록
				</a>
      		</div>
      	</div>
        <div class="row">
          <div class="col-md-12">
            <div class="card card-primary">
              <div class="card-body p-0">
                <!-- THE CALENDAR -->
                <div id="calendar"></div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->

<script src="${pageContext.request.contextPath}/resources/pms/js/common.js"></script>
<script>

window.addEventListener('load', function(){
	calendar(getCalendarJsonData()).render();
	searchTypeDatepickerAble('select[name=searchType]');
});

var getCalendarJsonData = function(){
	var result;
	$.ajax({
		url : '${pageContext.request.contextPath}/pms/team/personalSchedule/getPersonalData?projectId=${projectId}',
		type : 'get',
		data : {
			searchType : $('select[name=searchType]').val(),
			keyword : $('input[name=keyword]').val(),
		},
		async : false,
		success : function(data){
			result = data;
		},
		error : function(xhr){
			alert("에러");
		}
	})
	return result;
}

var calendar = function(data){
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	var calendarEl = document.getElementById('calendar');
	var calendarResult = new FullCalendar.Calendar(calendarEl, {
		plugins: [ 'bootstrap', 'interaction', 'dayGrid', 'timeGrid' ],
		header: {
			left: "",
			center: "title",
			//right: "month,basicWeek,basicDay"
			right: "today prev,next"
		},
		displayEventTime: false,
		eventLimit: 5,
		events : data,
		eventClick: function(info) {
			detailPersonalSchedule(info.event.id);
		},
 		buttonText: {today: '오늘', month: '월', week: '주', day: '일'},
		editable  : false
	});
    return calendarResult;
}
var reloadCalendar = function(){
	if ($('select[name=searchType]').val() == 'd' && $('#datePicker').val() == '') {
		alert("날짜는 필수 입력사항입니다.");
		$('#datePicker').focus();
		return;
	}
	$('#calendar').empty();
	calendar(getCalendarJsonData()).render();
}
</script>
<%@ include file="./personal_js.jsp" %>
</body>
