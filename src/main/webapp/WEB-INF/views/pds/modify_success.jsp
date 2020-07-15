<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>


<script>
	alert("글 수정이 완료되었습니다.")
	<%-- window.opener.location.href.reload(true); --%>
	window.opener.location.href="list.do${pageMaker.makeQuery()}";
	location.href=("detail.do${pageMaker.makeQuery()}&pno=${pds.pno}&from=modify");
</script>

