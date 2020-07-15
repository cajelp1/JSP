<%@page import="com.jsp.dto.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ page trimDirectiveWhitespaces="true" %>


<%--

LoginRequest logReq = new LoginRequest(); 
logReq.setId(request.getParameter("id"));
logReq.setPwd(request.getParameter("pwd"));

--%>


<jsp:useBean id="logReq" class="com.jsp.request.LoginRequest">
</jsp:useBean>
<jsp:setProperty property="*" name="logReq"/>

<%-- <jsp:setProperty name="logReq" property="id" 
				 value='<%=request.getParameter("id") %>'/>
<jsp:setProperty name="logReq" property="pwd" 
				 value='<%=request.getParameter("pwd") %>'/> --%>

<%
	MemberVO member = logReq.toMemberVO();
	member.setName("홍길동");
	member.setPhone("010-1111-1111");
	member.setAddress("대전혁신도시 중구 대흥동");
	
	session.setAttribute("loginUser", member);
	session.setMaxInactiveInterval(10);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	아이디 : <%= logReq.getId() %><br/>
	패스워드 : <%= logReq.getPwd() %><br/>
	
	<button type="button" onclick="location.href='main.jsp'">메인가기</button>
	
</body>
</html>










