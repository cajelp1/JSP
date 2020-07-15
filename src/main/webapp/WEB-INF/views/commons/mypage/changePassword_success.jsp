<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("비밀번호 변경이 완료되었습니다.\n다시 로그인 해 주세요.")
	location.href = "<%=request.getContextPath()%>/";
</script>