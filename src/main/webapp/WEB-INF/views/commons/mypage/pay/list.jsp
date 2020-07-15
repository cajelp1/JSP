<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 내역</title>
</head>
<style>

</style>
<body>

<div class="container">
	<div class="wrapper">
		<!-- test card -->
<%-- 		<c:if test="${reservationList eq null }">
		<span>결제 내역이 없습니다.</span>
		</c:if> --%>
		<c:forEach var="reservationList" items="${reservationList }">
			<div class="card mb-12" style="max-width: 1080px;">
				<div class="row no-gutters">
			    	<div class="col-md-4">
			    		<div class="img" style="padding: 5px;">
				      		<img src="/dsp/room/img?room=${reservationList.meetingNo}" style="width: 100%;">
			    		</div>
			    	</div>
			    	<div class="col-md-8">
			      		<div class="card-body">
			      			<input type="hidden" value="${reservationList.meetingNo }" />
			        		<h5 class="card-title">사무실 이름 : ${reservationList.roomName }</h5>
			        		<p class="card-text">사무실 주소 : ${reservationList.address } ${reservationList.detailAddress }</p>
			        		<p class="card-text">예약날짜 : <strong id="startDate"><fmt:formatDate value="${reservationList.startDate }" pattern="yyyy-MM-dd"/></strong>  ~ <strong id="endDate"><fmt:formatDate value="${reservationList.endDate }" pattern="yyyy-MM-dd"/></strong> </p>
			        		<p class="card-text">결제 금액 : ${reservationList.amount }</p>
			        		<p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
			        		<button type="button" onclick="cancel(<fmt:formatDate value="${reservationList.startDate }" pattern="yyyyMMdd"/>,${reservationList.no });">예약 취소</button>
			        		<button type="button" onclick="detail(${reservationList.meetingNo });">사무실 상세 보기</button>
			      		</div>
				    </div>
			  	</div>
			</div>		
		</c:forEach>
		<!-- test card 끝 -->
	</div>
</div>
<script>
	function cancel(startDate, no){

		var date = parseInt((new Date(startDate) - new Date()) / (24 * 60 * 60 * 1000));
		if(date < 14){
			if(!confirm('100% 환불받지 못할 수 있습니다.\n그래도 예약취소하시겠습니까?')){
				return;
			}
		}	
		
		$.ajax({
			url:"${pageContext.request.contextPath}/dsp/my/pay/cancel?no="+no,
			data:{
				"no":no
			},
			type:"post",
			success:function(result){
				alert('예약 취소가 완료되었습니다.');
			},
			error:function(xhr){
				alert('관리자에게 문의하십시오');
			}
		})
	}
	function detail(no){
// 		alert('상세보기');
		openWindow('<%=request.getContextPath()%>/dsp/room/detail/'+no,720,640);
	}
</script>
</body>
</html>