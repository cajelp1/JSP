<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	if(window.location.href.search("modify")!=-1){
		alert('일정이 수정되었습니다.');
	}
	if(window.location.href.search("regist")!=-1){
		alert('일정이 등록되었습니다.');
	}
	if(window.location.href.search("remove")!=-1){
		alert('일정이 삭제되었습니다.');
	}
	
	if (typeof(window.opener.reloadCalendar) == 'function') {
		window.opener.reloadCalendar();
	} else {
		window.opener.location.reload(true);
	}
	window.close();
</script>