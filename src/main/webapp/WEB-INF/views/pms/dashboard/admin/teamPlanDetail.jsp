<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<section class="content container-fluid">
	<input type="hidden" id="no" value="${plan.no }">
	<div class="row justify-content-center">
		<div class="col-md-10" style="max-width:960px;">
			<div class="card card-info">
				<div class="card-header" style="margin-top:5px;">
					<h2 class="card-title p-1">
						<i class="far fa-calendar-alt"></i>
						팀 일정 조회
					</h2>
					<div class="card-tools">
						<button type="button" class="btn btn-warning" onclick="window.close();">취소</button>
					</div>
				</div><!--end card-header  -->
				<div class="card-body pad">
					<form id="registForm" name="registForm" action="regist" role="form" method="post">
						<input type="hidden" name="projectId" value="${projectId}">
						<input type="hidden" name="register" value="${sessionScope.loginUser.email}">
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="title">제목</label>
								<input type="text" class="form-control" id="title" name="title" value="${plan.title }" readonly>
							</div>
							<div class="form-group col-sm-4">
								<label for="nickname">작성자</label>
								<input type="text" class="form-control" name="memberId" id="email" value="${plan.memberId}" readonly="readonly">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label for="fromDate">시작시간</label>
								<input type="text" id="fromDate" class="form-control" name="startDate" value='<fmt:formatDate value="${plan.startDate }" pattern="yyyy-MM-dd hh:mm"/>'  readonly>
							</div>

							<div class="form-group col-sm-6	">
								<label for="toDate">종료시간</label>
								<input type="text" id="toDate" class="form-control" name="endDate"  value='<fmt:formatDate value="${plan.endDate }" pattern="yyyy-MM-dd hh:mm"/>'  readonly><br/>
							</div>
						</div>
						<c:if test="${plan.detailAddress ne null }">
						<div class="row">
							<div class="col-sm-12">
								<label for="address">장소</label>
								<input type="text" id="address" name="address" class="form-control" value="${plan.address }" readonly>
							</div>
							<%-- <div class="col-sm-3">
								<label for="alarmTime">알림 시간</label>
								<select class="form-control" name="alarmTime">
									<option value="10">10분 전</option>
									<option value="20">20분 전</option>
									<option value="30">30분 전</option>
									<option value="60">1시간 전</option>
									<option value="120">2시간 전</option>
		                        </select>
							</div>--%>
						</div>
						<div class="row">
							<div class="col">
								<label for="address">상세장소</label>
								<input type="text" id="detailaddress" name="detailAddress" value="${plan.detailAddress }" class="form-control" readonly>
							</div>
						</div>						
						<div class="row">
							<div class="col">
							<div id="map" style="width:100%;height:300px;margin-top:10px;display:none"></div>	
							</div>
						</div>
						</c:if>
						<div class="form-group">
							<label for="content" >내용</label>
							<div>${plan.content }</div>
						</div>
						<input type="hidden" id="latitude" class="form-control" name="latitude" placeholder="위도" readonly value="${plan.latitude}">
						<input type="hidden" id="longitude" class="form-control" name="longitude" placeholder="경도" readonly value="${plan.longitude}">
					</form>
				</div><!--end card-body  -->
				<div class="card-footer" style="text-align:right;">
					<button type="button" class="btn btn-primary col-sm-3" onclick="go('modify')">수정</button>
					<button type="button" class="btn btn-primary col-sm-3" onclick="go('remove')">삭제</button>
				</div><!--end card-footer  -->
			</div><!-- end card -->				
		</div><!-- end col-md-10 -->
	</div><!-- end row -->
</section>
<script>
	var no = document.getElementById('no').value;
	function go(url){
		if(url === 'modify'){
			if(confirm('일정을 수정하시겠습니까?')){
				window.location.href=url+"?no="+no;
			}
		}
		if(url === 'remove'){
			if(confirm('일정을 삭제하시겠습니까?')){
				window.location.href=url+"?no="+no;
			}
		}
	}
</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d18d1e95f28970d92520308251b71596&libraries=services"></script>
<script>
var latitude = document.getElementById('latitude').value;
var longitude = document.getElementById('longitude').value;


var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(latitude,longitude), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(longitude,longitude); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);


var coords = new daum.maps.LatLng(latitude, longitude);
// 지도를 보여준다.
mapContainer.style.display = "block";
map.relayout();
// 지도 중심을 변경한다.
map.setCenter(coords);
// 마커를 결과값으로 받은 위치로 옮긴다.
marker.setPosition(coords)


 
</script>
</body>