<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	
	<div class="m-2 border">
		<div class="row bg-info m-0 p-3">
			<div class="col">
				<h4 style="display:inline; font-weight:bold;">${detailPersonal.title}</h4>
			<span class="float-right my-auto"><i class="fa fa-user"></i>&nbsp;${detailPersonal.chargerNickName}</span>
		</div>
		</div>
		<div class="row m-0 p-3 border-bottom">
			<div class="col-6">
				<i class="fa fa-calendar"></i>&nbsp;&nbsp;:&nbsp;
				<fmt:formatDate value="${detailPersonal.startDate}" pattern="yyyy-MM-dd (EE)"/>&nbsp;~&nbsp;
				<fmt:formatDate value="${detailPersonal.endDate}" pattern="yyyy-MM-dd (EE)"/>
			</div>
			<div class="col-3">
				<c:if test="${detailPersonal.processStaues eq 700}"><i class="fa fa-check-double"></i>&nbsp;&nbsp;:&nbsp;업무완료</c:if>
				<c:if test="${detailPersonal.processStaues eq 710}"><i class="fa fa-hourglass"></i>&nbsp;&nbsp;:&nbsp;업무진행</c:if>
				<c:if test="${detailPersonal.processStaues eq 720}"><i class="fa fa-pen"></i>&nbsp;&nbsp;:&nbsp;업무시작</c:if>
			</div>
			<div class="col-3">
				<c:if test="${empty detailPersonal.approvalYn}"><i class="fa fa-question"></i>&nbsp;&nbsp;:&nbsp;최종승인대기</c:if>
				<c:if test="${detailPersonal.approvalYn eq 'n'}"><i class="fa fa-user-times"></i>&nbsp;&nbsp;:&nbsp;최종승인대기</c:if>
				<c:if test="${detailPersonal.approvalYn eq 'y'}"><i class="fa fa-user-check"></i>&nbsp;&nbsp;:&nbsp;최종승인완료</c:if> 
			</div>
		</div>
		<div class="row p-3" style="min-height:200px;">
			<div class="col">${detailPersonal.content}</div>
		</div>
	</div>
	
<%--
	업무완료 : <i class="fa fa-check-double"></i><br>
	업무진행 : <i class="fa fa-hourglass"></i><br>
	업무시작 : <i class="fa fa-flag"></i><br>
	<br>
	승인필요 : <i class="fa fa-user-check"></i><br>
	승인ㄴㄴ : <i class="fa fa-user-times"></i><br>
	
 	<p> title : ${detailPersonal.title}</p>
	<p> content : ${detailPersonal.content}</p>
	<p> charger : ${detailPersonal.charger}</p>
	<p> chargerName : ${detailPersonal.chargerName}</p>
	<p> chargerNickName : ${detailPersonal.chargerNickName}</p>
	<p> processPercent : ${detailPersonal.processPercent}</p>
	<p> apporvalyn : ${detailPersonal.apporvalyn}</p>
	<p> processStaues : ${detailPersonal.processStaues}</p>
	<p> startDate : <fmt:formatDate value="${detailPersonal.startDate}" pattern="yyyy-MM-dd"/></p>
	<p> endDate : <fmt:formatDate value="${detailPersonal.endDate}" pattern="yyyy-MM-dd"/></p>
--%>
	<div class="m-2">
		<button type="button" class="btn bg-info" onclick="modifyPersonalSchedule(${detailPersonal.no});">
		<i class="fa fa-edit"></i>&nbsp;수정</button>
		<button type="button" class="btn bg-info" onclick="removePersonalSchedule(${detailPersonal.no});">
		<i class="fa fa-trash"></i>&nbsp;삭제</button>
	</div>
	
<script>
function modifyPersonalSchedule(no){
	location.href='${pageContext.request.contextPath}/pms/team/personalSchedule/modify?no='+no;
}

function removePersonalSchedule(no){
	if (confirm("${detailPersonal.title}를 삭제하시겠습니까?")) {
		location.href='${pageContext.request.contextPath}/pms/team/personalSchedule/remove?no='+no;
	}
}
</script>
