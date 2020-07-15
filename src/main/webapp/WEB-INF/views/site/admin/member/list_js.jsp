<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
/* 
var page = $('#send [name="page"]');
var perPageNum = $('#send [name="perPageNum"]');
var searchType = $('#send [name="searchType"]');
var keyword = $('#send [name="keyword"]');
 */

//detail 화면 보여주기
$('table').on('click','.members',function(){
	openWindow('detail?email='+this.id,'600','610');
});

//검색할 때 화면 보내기
$('#searchBtn').on('click',function(){
	
	$('#searchForm').attr('action','<%request.getContextPath();%>/dsp/admin/member/list');
	var form = $('#searchForm');
	form.submit();
});


</script>