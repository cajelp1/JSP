<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert('회원 정보 수정이 정상적으로 처리되었습니다.\r\n보안을 위해 다시 로그인해주세요!');
	window.close();
	window.opener.location.href="<%=request.getContextPath()%>/"
</script>
