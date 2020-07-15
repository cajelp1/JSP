<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%-- 이 부분은 target으로 가서 해야 우리 예상대로 alert가 뜨고 그 후 페이지로 이동한다
<script>
	alert("target.jsp 페이지로 이동합니다");
</script>
 --%>

<%-- 야매
<jsp:forward page="/action_forward/target.jsp?name=홍길동&age=12">
--%>

<jsp:forward page="/action_forward/target.jsp">
	<jsp:param value='<%=URLEncoder.encode("홍길동","utf-8") %>' name="name"/>
	<jsp:param value="12" name="age"/>
</jsp:forward>
