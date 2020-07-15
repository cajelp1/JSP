<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("등록이 완료되었습니다");
	window.opener.location="<%=request.getContextPath()%>/dsp/admin/location/list";
	window.close();
</script>