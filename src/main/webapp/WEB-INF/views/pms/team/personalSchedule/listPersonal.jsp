<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="container">
	<div class="row p-2" style="position:relative; margin-bottom: 40px;">
<!-- 		<button type="button" class="btn bg-green" onclick="registPersonalSchedule();"> 등록 </button> -->
		<form action="list" method="get" class="form" style="position:absolute;right:10px;">
			<div class="input-group">
				<input type="hidden" name="projectId" value="${projectId}">
				<select name="searchType" class="input-group-prepend">
					<option value="t"  ${pageMaker.cri.searchType eq 't' ? 'selected':'' }>제목</option>
					<option value="d" ${pageMaker.cri.searchType eq 'd' ? 'selected':'' }>날짜</option>
					<option value="sd" ${pageMaker.cri.searchType eq 'sd' ? 'selected':'' }>시작일</option>
					<option value="ed" ${pageMaker.cri.searchType eq 'ed' ? 'selected':'' }>종료일</option>
				</select>
				<input class="form-control" type="text" name="keyword" id="datePicker" autocomplete="off">
				<button class="input-group-append btn bg-warning" type="submit">검색 </button>
			</div>
		</form>
	</div>
	<div class="row">
		<table class="table table-hover my-3">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>시킨사람</th>
				<th>시작일</th>
				<th>종료일</th>
			</tr>
			<c:forEach items="${personalScheduleList}" var="list">
				<tr>
					<td>${list.no}</td>
					<td onclick="detailPersonalSchedule(${list.no})" style="cursor:pointer;">${list.title}</td>
					<td>${list.chargerNickName}</td>
					<td><fmt:formatDate value="${list.startDate}" pattern="MM-dd"/></td>
					<td><fmt:formatDate value="${list.endDate}" pattern="MM-dd"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="row justify-content-center align-items-center">
		<div class="card">
			<%@ include file="/WEB-INF/views/pms/team/pagination.jsp" %>		
		</div>
	</div>
	
	<form id="jobForm">
		<input type="hidden" name="projectId" value="${projectId}">
		<input type='hidden' name="page" value="${pageMaker.cri.page}" />
		<input type='hidden' name="perPageNum" value="${pageMaker.cri.perPageNum}"/>
		<input type='hidden' name="searchType" value="${pageMaker.cri.searchType }" />
		<input type='hidden' name="keyword" value="${pageMaker.cri.keyword }" />
	</form>
</div>


<script src="${pageContext.request.contextPath}/resources/pms/js/common.js"></script>
<%@ include file="./personal_js.jsp" %>
<script>
window.addEventListener('load', function(){
	searchTypeDatepickerAble('select[name=searchType]');
});

function searchList_go(page,url){
	var jobForm=$('#jobForm');
	jobForm.find("[name='projectId']").val();
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	jobForm.attr("method","get");
	if(url){
		jobForm.attr("action",url)
	}else{
		jobForm.attr("action","list")
	}
	jobForm.submit();
}
</script>
