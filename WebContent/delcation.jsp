<%@page import="java.util.Scanner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<%--아래와 같이 <%!로 선언하면 메소드 바깥에 해당 코드가 적힌다  --%>
<%!
	Scanner sc = new Scanner(System.in);
%>

<%!
	public int input(){
	System.out.println("정수를 입력!");
	int i = Integer.parseInt(sc.nextLine());
	return i;
}
%>

<%
	int i = input();
	System.out.println(i);
	for(; i < 10; i ++){
%>
구구단 <%=i%>단<br>
<%
		for(int j = 1; j < 10; j++){
%>
<%=i%> * <%=j%> = <%=i*j%><br>
<%
		}
%>
<br>
<%
	}
%>

</body>
</html>







