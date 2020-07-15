<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	if(window.opener){
		alert("로그인이 종료되었습니다.");
		window.opener.location.href="<%= request.getContextPath()%>/commons/loginForm.do";
		window.close();
	}else{
		location.href="<%= request.getContextPath() %>/commons/loginForm.do";
	}
	
</script>