<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
alert('공지사항이 등록되었습니다.');
if(window.opener){
	window.opener.location="<%=request.getContextPath()%>/dsp/notice/list";	
	window.close();
}
window.location="<%=request.getContextPath()%>/dsp/notice/list";
</script>