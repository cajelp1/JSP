<%@page import="java.net.URLEncoder"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	Date birth = new SimpleDateFormat("yyyy-MM-dd")
			.parse(request.getParameter("birth"));
	String[] hobby = request.getParameterValues("hobby");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	
	이름 : <%=name %><br>
	나이 : <%=age %><br>
	생일 : <%=new SimpleDateFormat("yyyy.MM.dd").format(birth) %><br>
	취미 : 
	<%
		for(String hobbies : hobby){
			out.print(hobbies+" ");
		}
	%>
	
	<%--아래는 form 태그를 이용한 get 방식 --%>
	<br>
	<button type=button onclick="regist_go();">등록하기</button>
	
	<form name="frm" action="regist.jsp" method="get">
		<input type="hidden" name="name" value="<%=name %>">
		<input type="hidden" name="age" value="<%= age %>">
	</form>
	
	<script
  src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
		function regist_go(){
 			/* document.frm.submit(); */
			$('form[name="frm"]').submit();
		}
	</script>
	
	
	<%-- <script> 	//원초적 스크립트. 무조건 get 방식
		var answer = confirm("등록하시겠습니까?");
		if(answer){
			location.href="regist.jsp?name=<%=name%>&age=<%=age%>";
		}else{
			location.href="http://ddit.or.kr"
		}
	</script> --%>
	
	
	<%-- <%			//원초적 send
		//으애애ㅐ... 여기의 response는 encoding을 또 해줘야함...
		
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		if(Integer.parseInt(age)>=20){
			response.sendRedirect("regist.jsp?name="+
			URLEncoder.encode(name, "utf-8") //헐..여기도....
			+"&age="+age);
		}else{
			response.sendRedirect("http://www.ddit.or.kr");
		}
	%> --%>
	
	
</body>
</html>















