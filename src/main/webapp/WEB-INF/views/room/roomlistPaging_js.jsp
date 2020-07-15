<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container p-3" id="paginationDiv">
<div class="row justify-content-center" id="pagination">
	<ul class="pagination">
		
		<c:if test="${pageMaker.totalCount != 0 }">
		<li class="page-item">
			<a class="page-link" href="javascript:searchList_go(1);">◀</a>
		</li>
		</c:if>
		
		<c:if test="${pageMaker.prev}">
		<li class="page-item">
			<a class="page-link" href="javascript:searchList_go(${pageMaker.prev});">◁</a>
		</li>
		</c:if>
		
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">						
		<li class="page-item 
			<c:out value="${pageMaker.cri.page == pageNum?'active':''}"/>">
			<a class="page-link" href="javascript:searchList_go(${pageNum});">${pageNum}</a>
		</li>
		</c:forEach>
		
		<c:if test="${pageMaker.next}">
		<li class="page-item">
			<a class="page-link" href="javascript:searchList_go(${pageMaker.next});">▷</a>
		</li>
		</c:if>
		
		<c:if test="${pageMaker.totalCount != 0 }">
		<li class="page-item">
			<a class="page-link" href="javascript:searchList_go(${pageMaker.realEndPage});">▶</a>
		</li>
		</c:if>
		
	</ul>
</div> <!-- end row -->
</div> <!-- end container -->

<form id="pageInfo">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type='hidden' name="page" value="${pageMaker.cri.page}" />
	<input type='hidden' name="perPageNum" value="${pageMaker.cri.perPageNum}"/>
	<input type='hidden' name="city" value="${pageMaker.cri.city }" />
	<input type='hidden' name="area" value="${pageMaker.cri.area }" />
	<input type='hidden' name="capacity" value="${pageMaker.cri.capacity }" />
</form>


<script>
function searchList_go(page,url){
	
	var pageInfo=$('#pageInfo');
	pageInfo.find("[name='page']").val(page);
	<%--
 	pageInfo.find("[name='city']").val($('select[name="city"]').val());
	pageInfo.find("[name='area']").val($('select[name="area"]').val());
	pageInfo.find("[name='capacity']").val($('select[name="capacity"]').val());
	--%>
	
	if(url){
		pageInfo.attr("action",url);
	}else{
		pageInfo.attr("action","list");
	}
	pageInfo.attr("method","get");
	
	pageInfo.submit();
}
</script>





