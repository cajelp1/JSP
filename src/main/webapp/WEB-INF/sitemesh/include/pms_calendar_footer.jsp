<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script src="<%=request.getContextPath()%>/resources/pms/js/common.js"></script>
<!-- jQuery -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap -->                                   
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- jQuery UI -->                                   
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- AdminLTE App -->                                
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>

<!-- fullCalendar 2.2.5 -->                          
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/moment/moment.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar/main.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar-daygrid/main.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar-timegrid/main.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar-interaction/main.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fullcalendar-bootstrap/main.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- bootstrap colorPicker -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>


<%@ include file="../Alarm_js.jsp"%>
<script>
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

window.addEventListener('load', function(){
	calendar(getCalendarJsonData()).render();
	searchTypeDatepickerAble('select[name=searchType]');
});

var getCalendarJsonData = function(){
	var result;
	$.ajax({
		url : '${pageContext.request.contextPath}/pms/team/officialSchedule/getOfficailData?projectId=${projectId}',
		type : 'get',
// 		beforeSend: function( xhr ) {
// 			  xhr.setRequestHeader(header, token);
// 		},
 		data : {
 			searchType : $('select[name=searchType]').val(),
			keyword : $('input[name=keyword]').val() 
		},
		eventLimit: true,
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
			detailOfficialSchedule(info.event.id);
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