<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<div class="container">
	
	<div class="row p-2" style="position:relative;"><!-- top -->
		<button type="button" class="btn bg-green" onclick="location.href='listReadN?projectId=${projectId}'">미확인 알림</button>&nbsp;&nbsp;
		<button type="button" class="btn bg-green" onclick="location.href='listAll?projectId=${projectId}'">전체 알림</button>&nbsp;&nbsp;
		
		<form action="list" method="get" class="float-right" style="position:absolute;right:10px;">
			<input type="hidden" name="page" value="${pageMaker.cri.page }">
			<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum }">
			<div class="input-group">
				<select class="input-group-prepend" name="searchType" style="right:0;">
					<option value="t"  ${pageMaker.cri.searchType eq 't' ? 'selected':'' }>제목</option>
					<option value="d"  ${pageMaker.cri.searchType eq 'sender' ? 'selected':'' }>보낸사람</option>
				</select>
				<input type="text" name="keyword" class="form-control" id="datePicker" autocomplete="off" value="${pageMaker.cri.keyword }" style="right:0;">
				<button type="submit" class="input-group-append btn bg-warning" style="right:0;">검색</button>
			</div>
		</form>
	</div><!-- top end -->
	
	<table class="table table-hover my-3">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>보낸사람</th>
			<th>보낸날짜</th>
		</tr>
		<c:forEach items="${notHistorylist}" var="list">
			<tr>
				<td>${list.no}</td>
				<td onclick="detailNotHistory('${list.url}', ${list.no})" style="cursor:pointer;">${list.title}</td>
				<td>${list.senderNickName}</td>
				<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
	</table>
	<div class="row justify-content-center align-items-center">
		<div class="card">
			 <nav aria-label="member list Navigation">
				<ul class="pagination justify-content-center m-0">
					<li class="page-item">
						<a class="page-link" href="javascript:searchList_go(1);">
							<i class="fas fa-angle-double-left"></i>
						</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="javascript:searchList_go(${pageMaker.prev ? pageMaker.startPage-1 : 1});">
							<i class="fas fa-angle-left"></i>
						</a>
					</li>
					<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">						
					<li class="page-item 
						<c:out value="${pageMaker.cri.page == pageNum?'active':''}"/>">
						<a class="page-link" href="javascript:searchList_go(${pageNum});" >${pageNum }</a>
					</li>
					</c:forEach>
					<li class="page-item">
						<a class="page-link" href="javascript:searchList_go(${pageMaker.next ? pageMaker.endPage+1 : pageMaker.cri.page});">
							<i class="fas fa-angle-right" ></i>
						</a>
					</li>
					<li class="page-item">
						<a class="page-link" href="javascript:searchList_go(${pageMaker.realEndPage} );">
							<i class="fas fa-angle-double-right"></i>
						</a>
					</li>	
				</ul>
			</nav>  
		</div>
	</div>
	
	<form id="jobForm">
		<input type='hidden' name="page" value="${pageMaker.cri.page}" />
		<input type='hidden' name="perPageNum" value="${pageMaker.cri.perPageNum}"/>
		<input type='hidden' name="searchType" value="${pageMaker.cri.searchType }" />
		<input type='hidden' name="keyword" value="${pageMaker.cri.keyword }" />
		<input type='hidden' name="projectId" value="${projectId}" />
	</form>
</div><!-- .container -->


<script src="${pageContext.request.contextPath}/resources/pms/js/common.js"></script>
<script>
var detailNotHistory = function(url, no){
	$.ajax({
		url : '<%=request.getContextPath()%>/pms/notHistory/readMessage',
		type : 'get',
		async : false,
		data : {
			no : no
		},
		success : function(data){
			if (screen.height < 1000) {
				OpenWindow(url, '알림보기', 700, 560);
			} else {
				OpenWindow(url, '알림보기', 700, 710);
			}
		},
		error : function(error){
			alert("잠시후 다시 시도해주세요.");
		}
	});
	location.reload();
}

window.addEventListener('load', function(){
	searchTypeDatepickerAble('select[name=searchType]');
});
</script>
<script>
function searchList_go(page,url){
	
	var jobForm=$('#jobForm');
	jobForm.find("[name='projectId']").val();
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val();
	jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	jobForm.attr("method","get");
	if(url){
		jobForm.attr("action",url)
	}else{
		jobForm.attr("action","listAll")
	}
	jobForm.submit();
}
</script>
