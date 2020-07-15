<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert('공지사항이 삭제되었습니다.');
	if(window.opener){
		window.close();
	}
	location.href="<%=request.getContextPath()%>/dsp/notice/list";
</script>