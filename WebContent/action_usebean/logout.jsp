<%@page import="com.jsp.dto.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<% session.invalidate(); %>

<script>
	alert("로그아웃 되었습니다.\n메인페이지로 이동합니다.");
	location.href="loginForm.jsp";
</script>
