<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
	
	<div class="container m-4"><h3>${room.roomName}</h3></div>
	
	<div class="m-4 p-3 border">
		<div class="row">
			<div class="col-sm-3">주소 :</div>
			<div class="col-sm-9">${room.address}&nbsp;&nbsp;${room.detailAddress}</div>
		</div>
		<div class="row">
			<div class="col-sm-3">수용가능인원 :</div>
			<div class="col-sm-9">최대 ${room.capacity}명 ${room.capacity == 21 ? '이상' : ''}</div>
		</div>
		<div class="row">
			<div class="col-sm-3">일일 사용료 :</div>
			<div class="col-sm-9">${room.pricePerDay!=0 ? room.pricePerDay : '직접 문의해주세요'}</div>
		</div>
	</div>
	
	<c:if test="${profileImg.fileName ne ''}">
		<div class="p-3 m-4 border" id="profileImg">
			<img style="width: 100%;" src="<% request.getContextPath();%>/dsp/room/picture?fileName=${profileImg.fileName}"
				onerror="this.src='<%=request.getContextPath() %>/resources/site/img/unnamed.jpg'">	
		</div>
	</c:if>
	
	<c:forEach var="file" items="${imgList }">
		<div class="p-3 m-4 border">
			<img style="width: 100%;" src="<% request.getContextPath();%>/dsp/admin/location/picture?fileName=${file.fileName}"
				onerror="this.src='<%=request.getContextPath() %>/resources/site/img/unnamed.jpg'">
		</div>
	</c:forEach>
	
	<div class="row m-2">
		<div class="col">
		<div id="map" style="width:auto;height:300px;margin-top:10px;"></div>
		</div>
	</div>
	
	<div class="row m-4">
		<c:if test="${loginUser ne null}">
		    <button type="button" onclick="openWindow('/dsp/room/reservation/'+${room.no},720,640);">예약하기</button>
		</c:if>
	</div>

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
</html>