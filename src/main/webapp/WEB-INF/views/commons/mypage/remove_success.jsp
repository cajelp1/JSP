<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert('회원 탈퇴가 정상적으로 처리되었습니다.');
	location.href = "<%=request.getContextPath()%>/";
</script>