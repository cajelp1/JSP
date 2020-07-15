<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
var registOfficialSchedule = function(){
	if (screen.height < 1000) {
		OpenWindow("regist?projectId=${projectId}", '일정등록', 700, 560);
	} else {
		OpenWindow("regist?projectId=${projectId}", '일정등록', 700, 710);
	}
}
var detailOfficialSchedule = function(no){
	if (screen.height < 1000) {
		OpenWindow("detail?projectId=${projectId}&no="+no, '상세일정', 700, 560);
	} else {
		OpenWindow("detail?projectId=${projectId}&no="+no, '상세일정', 700, 710);
	}
}
</script>