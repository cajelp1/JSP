<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
alert('공지사항이 수정되었습니다.');
if(window.opener){
	window.opener.location="<%=request.getContextPath()%>/dsp/notice/list";	
}
window.location="<%=request.getContextPath()%>/dsp/notice/list";
window.close();
</script>