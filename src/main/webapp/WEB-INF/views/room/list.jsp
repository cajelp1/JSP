<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">

</style>


</head>
<body>
	
	<div class="container">
		<form id="criteria" action="<%=request.getContextPath()%>/dsp/room/list" method="get">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="input-group mb-4">
			<div class="input-group">
			<select name="city" id="city" class="custom-select ">
				<option value="">도시 전체보기</option>
				<option value="서울" ${pageMaker.cri.city eq '서울' ? 'selected' : "" }>서울</option>
				<option value="경기" ${pageMaker.cri.city eq '경기' ? 'selected' : "" }>경기</option>
				<option value="강원" ${pageMaker.cri.city eq '강원' ? 'selected' : "" }>강원</option>
				<option value="충청" ${pageMaker.cri.city eq '충청' ? 'selected' : "" }>충청</option>
				<option value="인천" ${pageMaker.cri.city eq '인천' ? 'selected' : "" }>인천</option>
				<option value="전라" ${pageMaker.cri.city eq '전라' ? 'selected' : "" }>전라</option>
				<option value="경상" ${pageMaker.cri.city eq '경상' ? 'selected' : "" }>경상</option>
				<option value="부산" ${pageMaker.cri.city eq '부산' ? 'selected' : "" }>부산</option>
				<option value="제주" ${pageMaker.cri.city eq '제주' ? 'selected' : "" }>제주</option>
			</select>
			
			<select name="area" id="area" class="custom-select ">
				<option value="">지역구 전체보기</option>
			</select>
			
			<select name="capacity" id="capacity" class="custom-select ">
				<option value="" ${pageMaker.cri.capacity eq '' ? 'selected' : ''}>인원 전체보기</option>
				<option value="2" ${pageMaker.cri.capacity eq '2' ? 'selected' : ''}>2명</option>
				<option value="4" ${pageMaker.cri.capacity eq '4' ? 'selected' : ''}>4명</option>
				<option value="8" ${pageMaker.cri.capacity eq '8' ? 'selected' : ''}>8명</option>
				<option value="10" ${pageMaker.cri.capacity eq '10' ? 'selected' : ''}>10명</option>
				<option value="12" ${pageMaker.cri.capacity eq '12' ? 'selected' : ''}>12명</option>
				<option value="15" ${pageMaker.cri.capacity eq '15' ? 'selected' : ''}>15명</option>
				<option value="20" ${pageMaker.cri.capacity eq '20' ? 'selected' : ''}>20명</option>
				<option value="21" ${pageMaker.cri.capacity eq '21' ? 'selected' : ''}>20명 이상</option>
			</select>
			<div class="input-group-append">
				<button type="submit" class="btn btn-outline-secondary" id="ok">확인</button>
			</div>
			</div>
		</div>
		</form>
	</div>
	<hr>
	<h6 class="container" style="color:${pageMaker.totalCount==0 ? 'red' : 'blue'};">확인 가능한 사무실 : ${pageMaker.totalCount}개</h6>
	<br>
	<div class="container">
		<div class="wrapper">
		<div class="row" id="result">
			
			<c:forEach var="room" items="${roomList}">
				<div class="col-md-4 mb-3">
					<div class="card h-100" >
						<div style="height:200px; background-image:url('<%=request.getContextPath() %>/dsp/room/profile?room=${room.no}'),url('<%=request.getContextPath() %>/resources/site/img/unnamed.jpg'); background-repeat:no-repeat; background-size:cover; background-position:center">
						</div>
						<%-- <img class="card-img-top" src="<%=request.getContextPath() %>/dsp/room/img?room=${room.no}" > --%>
						<div class="card-body">
							<h5 class="card-title">${room.roomName }</h5>
							<p class="card-text">${room.address }&nbsp;${room.detailAddress }</p>
						</div>
						<div class="btn-group">
		            		<button type="button" class="btn btn-sm btn-outline-secondary" onclick="openWindow('<%=request.getContextPath() %>/dsp/room/detail/${room.no}',720,640)">상세보기</button>
		                </div>
					</div>
				</div>
			</c:forEach>
			
		</div>
		</div>
	</div>
	
	<%@ include file="roomlistPaging_js.jsp"%>
	<%@ include file="list_js.jsp"%>
	
</body>