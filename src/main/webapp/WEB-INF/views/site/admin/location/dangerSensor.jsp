<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<body>
	<div class="container p-3 m-4 border">
		<div class="row">
			<p>설정된 온도 : </p>
			<p>${settingData.temperatured }</p>&nbsp;&nbsp;&nbsp;
			<p>설정된 습도 : </p>
			<p>${settingData.humidity }</p>&nbsp;&nbsp;&nbsp;
			<p>설정된 거리 : </p>
			<p>${settingData.distance }</p>&nbsp;&nbsp;&nbsp;
<%-- 			설정된 불쾌지수 : <p>${settingData.discomfortIndex }</p> --%>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-warning" onclick="javascript:history.go(-1);">뒤로가기</button>
		</div>
	</div>
	
	<div class="container">
		<button type="button" class="btn btn-info" data-toggle="modal" data-target="#modal-lg" id="openModalBtn">
           	<i class="fas fa-exclamation-circle"></i>
           	위험범위 설정
		</button>
		<form class="float-right" id="searchForm" style="display:inline;" action="getDangerSensorData">
			<input type="hidden" name="roomNo" value="${roomNo}">
	  		<input type="hidden" name="page" value="${pageMaker.cri.page }">
			<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum }">
	  		<select name="searchType">
	  			<option value="no" <c:if test="${pageMaker.cri.searchType eq 'no'}">selected</c:if>>번호</option>
	  			<option value="rn" <c:if test="${pageMaker.cri.searchType eq 'rn'}">selected</c:if>>방이름</option>
	  		</select>
	  		<input name="keyword" <c:if test="${!empty pageMaker.cri.keyword}">value="${pageMaker.cri.keyword}"</c:if>>
	  		<input type="submit" value="검색">
	  	</form>
	  	<br/>
		<table class="table table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th style="width:10%">No.</th>
					<th style="width:25%">위험원인</th>
					<th style="width:10%">최고값</th>
					<th style="width:25%">방이름</th>
					<th style="width:20%">등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dangerDataList}" var="danger">
					<tr>
						<td>${danger.no }</td>
						<td>${danger.reason }</td>
						<td>${danger.val }</td>
						<td>${danger.roomName }</td>
						<td><fmt:formatDate value="${danger.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


<!-- 모달 영역 -->
<div class="modal fade" id="modal-lg">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">온도 설정</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			</div>
			
			<div class="modal-body">
				설정된 온도 : 
				<input type="text" class="sensorSetInfo" name="temp" value="${settingData.temperatured }"><br/>
				설정된 습도 :
				<input type="text" class="sensorSetInfo"name="humi" value="${settingData.humidity }"><br/>
				설정된 거리 : 
				<input type="text" class="sensorSetInfo" name="distance" value="${settingData.distance }"><br/>
<%-- 				설정된 불쾌지수 : <input type="text" id="disIndex" name="disIndex" value="${settingData.discomfortIndex }"><br/> --%>
			</div>
			
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" id="modalSelectComplete">설정완료</button>
			</div>
		</div> <!-- modal-content 끝 -->
	</div>
</div> <!-- 모달 영역 끝 --> 
	
<%@ include file="./sensorPagination.jsp" %>
<script>
window.addEventListener('load', function() {
	var text = $('<p class="checkText">');
	var regexpNumber = /^[0-9]*$/
	
	$('input[name=temp]').on('change', function(){
		var tempval = $(this).val();
		if (tempval > 50 || !regexpNumber.test(tempval)) {
			$(this).focus();
			text.text("온도는 50이하의 숫자를 입력해주세요");
			$('.modal-body').append(text);
			$(this).val("");
		}
	});
	$('input[name=humi]').on('change', function(){
		var humival = $(this).val();
		if (humival > 100 || !regexpNumber.test(humival)) {
			$(this).focus();
			text.text("습도는 99이하의 숫자를 입력해주세요");
			$('.modal-body').append(text);
			$(this).val("");
		}
	});
	$('input[name=distance]').on('change', function(){
		var distanceval = $(this).val();
		if (distanceval > 10 || !regexpNumber.test(distanceval)) {
			$(this).focus();
			text.text("거리는 10이하의 숫자를 입력해주세요");
			$('.modal-body').append(text);
			$(this).val("");
		}
	});
	
	/* 모달이 닫히는 순간 일어나는 이벤트*/
	$('#modal-lg').on('hide.bs.modal', function (evnet) {
		
	});
	
	/* 선택완료 클릭시 이벤트 */
	$('#modalSelectComplete').on('click', function(){
		if ($('input[name=temp]').val() == null || $('input[name=temp]').val() == "") {
			return false;
		}
		if ($('input[name=humi]').val() == null || $('input[name=humi]').val() == "") {
			return false;
		}
		if ($('input[name=distance]').val() == null || $('input[name=distance]').val() == "") {
			return false;
		}
		
		
		var senData = {
			roomNo : $('input[name=roomNo]').val()
			,temperatured : $('input[name=temp]').val()
			,humidity : $('input[name=humi]').val()
			,distance : $('input[name=distance]').val()
// 			,discomfortIndex : $('input[name=disIndex]').val()
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/dsp/sensor/setSensor",
			type : 'get',
			data : senData,
			success : function(data){
				location.reload(true);
			}
		});
	});
});


function getSettingSensorData(){
	$.ajax({
		url : "${pageContext.request.contextPath}/sensor/getSettingSensorData?roomNo="+${roomNo},
		type : "get",
		success : function(data){
			$('#temp').text(data.temperatured);
			$('#humi').text(data.humidity);
			$('#distance').text(data.distance);
			$('#disIndex').text(data.discomfortIndex);
		}
	});
}

function getDangerSensorData(){
	$.ajax({
		url : "${pageContext.request.contextPath}/sensor/getDangerSensorData?roomNo="+${roomNo},
		type : "get",
		success : function(data){
			console.log(data);
		}
	});
}
</script>
</body>
