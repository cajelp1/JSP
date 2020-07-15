<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>

	if(${requestScope.error}){
		alert("#{requestScope.error}");
	}else{
		alert("현재 시스템 장애로 글 작성이 불가합니다");
	}
	history.go(-1);
	
</script>