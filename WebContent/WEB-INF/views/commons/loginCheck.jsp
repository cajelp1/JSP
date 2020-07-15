<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	// 세션이 만료됐을 때만 메시지를 뿌릴 것이다!
	// 그리고 세션만료는 세션리스너에서 설정할 것이다! 
	if(window.opener){
		alert("로그인 세션이 종료되었습니다.\n현재 창이 종료됩니다.")
		window.opener.location.href="<%= request.getContextPath() %>/index.jsp";
		window.close();
	}else{
		alert("로그인 세션이 종료되었습니다.\n페이지를 전환합니다.")
		location.href="<%= request.getContextPath() %>/index.jsp";
	}
</script>

