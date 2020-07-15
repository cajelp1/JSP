<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	if (typeof(window.opener.reloadCalendar) == 'function') {
		window.opener.reloadCalendar();
	} else {
		window.opener.location.reload(true);
	}
	window.close();
</script>