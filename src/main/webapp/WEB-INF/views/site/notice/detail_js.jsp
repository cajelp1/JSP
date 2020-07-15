<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	function modify(){
		window.location.href="/notice/modify?sno=${board.sno}";
	}
	
	function remove(){
		if(confirm('이 공지사항을 삭제하시겠습니까?')){
			window.location.href="/notice/remove?sno=${board.sno}";			
		}
	}
</script>