<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="../include/bootstrap.jsp" %> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
<body>
<!-- test -->
<div class="container">
	<table class="table">
	        <tr>
	            <th style="width: 96px;">글번호</th>
	            <td>${board.sno }</td>
	            <th>조회수</th>
	            <td>${board.viewcnt }</td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td>${board.writerNickName }</td>
	            <th>작성일</th>
	            <td><fmt:formatDate value="${board.regDate }" pattern="yyyy.MM.dd" /></td>
	        </tr>
	        <tr>
	            <th>제목</th>
	            <td colspan="3">${board.title }</td>
	        </tr>
	        <tr>
	            <th>글 내용</th>
	            <td colspan="3">${board.content }</td>
	        </tr>
	        <c:if test="${attach.originFilename ne null }">
	        <tr>
	        	<th class="success">첨부파일</th>
	        	<td colspan="3" onclick="download('${attach.no}')"><i class="fas fa-save"></i>${attach.originFilename }</td>
	        </tr>
	        </c:if>
	</table>
	<c:if test="${sessionScope.loginUser.email eq board.writer	}">
		<button type="button" class="btn btn-primary" onclick="modify()">수정</button>
		<button type="button" class="btn btn-primary" onclick="remove()">삭제</button>	
	</c:if>
</div>
<script type="text/javascript">
	function download(fileNo) {
	    if (confirm("파일을 다운로드 하실건가요?")) {
	    	location.href="${pageContext.request.contextPath}/pms/project/downloadFile?no="+fileNo;
	    } else {
			return;
	    }
	}

	function modify(){
		window.location.href="/dsp/notice/modify?sno=${board.sno}";
	}
	
	function remove(){
		if(confirm('이 공지사항을 삭제하시겠습니까?')){
			window.location.href="/dsp/notice/remove?sno=${board.sno}";
		}
	}
	function downloadAttach(){
// 		alert('파일은 미국갔냐?');
		location.href="/download?sno=${board.sno}"
	}
</script>
</body>
</html>