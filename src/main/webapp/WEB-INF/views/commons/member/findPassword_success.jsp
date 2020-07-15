<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("임시 비밀번호가 해당 전화번호로 발송되었습니다.");
	if (window.opener && !window.opener.closed) {
		window.close();
	} else {
		location.href='${pageContext.request.contextPath}/dsp/main';
	}
</script>