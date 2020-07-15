<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<%-- <span id="AduinoRoomNo">${room.roomNo}</span> --%>
	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<h3>${room.roomName}</h3>
			</div>
			<div class="col-sm-4" style="text-align:right;">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/dsp/sensor/getDangerSensorData?roomNo=${room.roomNo}'">위험설정 및 내역</button>
			</div>
		</div>
	</div>
	<div class="container p-3 m-4 border" >
		<div class="row">
			<div class="col-sm-3">실제주소 :</div>
			<div class="col-sm-9">${room.address}&nbsp;&nbsp;${room.detailAddress}</div>
		</div>
		<div class="row">
			<div class="col-sm-3">검색주소 :</div>
			<div class="col-sm-9">${room.city} / ${room.area} ( ${room.detail} ) </div>
		</div>
		<div class="row">
			<div class="col-sm-3">일일 사용료 :</div>
			<div class="col-sm-9">${room.pricePerDay} 원</div>
		</div>
	</div>

	<c:if test="${room.profile ne null}">
		<div class="container p-3 m-4 border" id="profileImg">
			<img style="width: 100%;" src="<% request.getContextPath();%>/dsp/admin/location/picture?fileName=${room.profile.fileName}"
			onerror="this.src='<%=request.getContextPath() %>/resources/site/img/unnamed.jpg'">	
		</div>
	</c:if>
	
	<c:forEach var="file" items="${room.attachFileList }">
		<div class="container p-3 m-4 border">
			<img src="<% request.getContextPath();%>/dsp/admin/location/picture?fileName=${file.fileName}"
			onerror="this.src='<%=request.getContextPath() %>/resources/site/img/unnamed.jpg'">
		</div>
	</c:forEach>
	
	<div class="container m-4 border">
		<div id="map" style="height:300px;margin-top:10px;margin-bottom:10px;"></div>
	</div>
	<!-- <div class=".container-sm p-3 m-4 border">관제 관련사항...
		<br><br><br><br><br><br><br><br><br><br><br>
	</div> -->
	<div class="container m-4">
		<button type="button" class="btn btn-outline-secondary" onclick="go('<% request.getContextPath();%>/dsp/admin/location/modify/${room.roomNo}')">수정</button>
		<c:if test="${room.deleteyn == 'n' }">
			<button type="button" class="btn btn-outline-secondary" onclick="remove('y')">삭제</button>
		</c:if>
		<c:if test="${room.deleteyn == 'y' }">
			<button type="button" class="btn btn-outline-secondary" onclick="remove('n')">복구</button>
		</c:if>
	</div>
	
<script>
	function go(url){
		self.location=url;
	}
	
	function remove(value){
		if(confirm('이 회의실의 정보를 삭제/복구하겠습니까?')){
			location.href="<%=request.getContextPath()%>/dsp/admin/location/remove/${room.roomNo}?deleteyn="+value;
		}
	}
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d18d1e95f28970d92520308251b71596&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(${room.latitude},${room.longitude}), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(${room.latitude},${room.longitude}); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);
 
</script>
</body>