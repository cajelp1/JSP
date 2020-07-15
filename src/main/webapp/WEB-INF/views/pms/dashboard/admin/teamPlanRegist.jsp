<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/pms/css/jquery.timepicker.css">
<!-- Main content -->
<section class="content container-fluid">
	<div class="row justify-content-center">
		<div class="col-md-10" style="max-width:960px;">
			<div class="card card-info">
				<div class="card-header" style="margin-top:5px;">
					<h2 class="card-title p-1">
						<i class="far fa-calendar-alt"></i>
						공식일정 등록
					</h2>
					<div class="card-tools">
						<button type="button" class="btn btn-warning" onclick="window.close();">취소</button>
					</div>
				</div><!--end card-header  -->
				<div class="card-body pad">
					<form id="registForm" name="registForm" action="regist" role="form" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<input type="hidden" name="projectId" value="${projectId}">
						<input type="hidden" name="memberId" value="${sessionScope.loginUser.email}">
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="title">제목</label>
								<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요">
							</div>
							<div class="form-group col-sm-4">
								<label for="nickname">작성자</label>
								<input type="text" class="form-control" id="email" value="${sessionScope.loginUser.nickname}" readonly="readonly">
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label for="fromDate">시작일</label>
								<input type="text" id="fromDate" class="form-control daterangepicker" name="startDate" autocomplete="off" readonly required size="8" maxlength="5">
							</div>
							<div class="form-group col-sm-6">
								<label for="fromTime">시간</label>
								<input type="text" id="fromTime" name="fromTime" class="time form-control"/>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6	">
								<label for="toDate">종료일</label>
								<input type="text" id="toDate" class="form-control" name="endDate"  autocomplete="off" readonly><br/>
							</div>
							<div class="form-group col-sm-6">
								<label for="toTime">시간</label>
								<input type="text" id="fromTime" name="toTime" class="time form-control"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-9">
								<label for="address">장소</label>
								<input type="text" id="address" name="address" class="form-control" placeholder="주소" onclick="searchAddress()">
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
							</div> --%>
							<div class="col-sm-3">
								<div class="my-colorpicker2 colorpicker-element" data-colorpicker-id="2">
							 		<label for="color">색상 선택</label>
							 		<div class="row">
								 		<div class="col-sm-8">
									 		<div class="input-group" data-colorpicker-id="2">
                    							<input type="color" name="color" class="form-control" >
						                    </div>
								 		</div> 
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label for="address">상세장소</label>
								<input type="text" id="detailaddress" name="detailAddress" class="form-control" placeholder="주소">
							</div>
						</div>						
						<div class="row">
							<div class="col">
							<div id="map" style="width:100%;height:300px;margin-top:10px;display:none"></div>	
							</div>
						</div>
						
						<div class="form-group">
							<label for="content" >내용</label>
							<textarea rows="10" cols="10" id="content" name="content"
								class="form-control required" ></textarea>
						</div>
						<input type="hidden" id="latitude" class="form-control" name="latitude" placeholder="위도" readonly>
						<input type="hidden" id="longitude" class="form-control" name="longitude" placeholder="경도" readonly>
					</form>
				</div><!--end card-body  -->
				<div class="card-footer" style="text-align:right;">
					<button type="button" class="btn btn-primary col-sm-3" onclick="sendSubmit();">등록</button>
				</div><!--end card-footer  -->
			</div><!-- end card -->				
		</div><!-- end col-md-10 -->
	</div><!-- end row -->
</section>
<script	src="${pageContext.request.contextPath}/resources/pms/js/common.js"></script>
<script>

	function sendSubmit(){
/* 		alert($('input[type=color]').val());
		return; */
		$('form[role=form]').submit();
	}
</script>
<%@ include file="/WEB-INF/views/pms/include/picker_js.jsp" %>
<%@ include file="/WEB-INF/views/pms/include/summernote_js.jsp" %>
<script src="<%=request.getContextPath() %>/resources/pms/js/jquery.timepicker.min.js"></script>

<script>
$(".time").timepicker({
	step: 30,            //시간간격 : 30분

	timeFormat: "H:i"    //시간:분 으로표시
});
</script>


<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d18d1e95f28970d92520308251b71596&libraries=services"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });


    function searchAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                        
                        //위도를 반환해서 input type hidden에 넣어준다.
//                         var latlng = coords.LatLng(37, 127);
                        console.log("위도 = " + result.y);
                        document.getElementById('latitude').value=result.y;
                        
                        //경도를 반환해서 input type hidden에 넣어준다.
//                         var latlng = coords.LatLng(37, 127);
//                         latlng.getLat();
                        console.log("경도 = " + result.x);
                        document.getElementById('longitude').value=result.x;
                    }
                });
            }
        }).open();
    }
</script>
</body>