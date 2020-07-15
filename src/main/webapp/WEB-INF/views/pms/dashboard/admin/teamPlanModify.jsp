<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/pms/css/jquery.timepicker.css">
<section class="content container-fluid">
	<input type="hidden" id="no" value="${plan.no }">
	<div class="row justify-content-center">
		<div class="col-md-10" style="max-width:960px;">
			<div class="card card-info">
				<div class="card-header" style="margin-top:5px;">
					<h2 class="card-title p-1">
						<i class="far fa-calendar-alt"></i>
						팀 일정 등록
					</h2>
					<div class="card-tools">
						<button type="button" class="btn btn-warning" onclick="window.close();">취소</button>
					</div>
				</div><!--end card-header  -->
				<div class="card-body pad">
					<form id="modifyForm" name="modifyForm" action="modify" role="form" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<input type="hidden" name="no" value="${plan.no}">
						<input type="hidden" name="projectId" value="${plan.projectId}">
						<input type="hidden" name="memberId" value="${sessionScope.loginUser.email}">
						<div class="row">
							<div class="form-group col-sm-8">
								<label for="title">제목</label>
								<input type="text" class="form-control" id="title" name="title" value="${plan.title }" >
							</div>
							<div class="form-group col-sm-4">
								<label for="nickname">작성자</label>
								<input type="text" class="form-control" id="email" value="${plan.memberId}" readonly>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6">
								<label for="fromDate">시작일</label>
								<input type="text" id="fromDate" class="form-control daterangepicker" name="startDate"
								value='<fmt:formatDate value="${plan.startDate}" pattern="yyyy-MM-dd"/>'
								autocomplete="off" readonly required size="8" maxlength="5">
							</div>
							<div class="form-group col-sm-6">
								<label for="toTime">시간</label>
								<input type="text" id="fromTime" name="fromTime" class="time form-control"
								value='<fmt:formatDate value="${plan.startDate}" pattern="HH:mm"/>'/>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-sm-6	">
								<label for="toDate">종료일</label>
								<input type="text" id="toDate" class="form-control" name="endDate"
								value='<fmt:formatDate value="${plan.endDate}" pattern="yyyy-MM-dd"/>'
								autocomplete="off" readonly><br/>
							</div>
							<div class="form-group col-sm-6">
								<label for="toTime">시간</label>
								<input type="text" id="fromTime" name="toTime" class="time form-control"
								value='<fmt:formatDate value="${plan.endDate}" pattern="HH:mm"/>'/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-9">
								<label for="address">장소</label>
								<input type="text" id="address" name="address" class="form-control" value="${plan.address }" onclick="searchAddress()" readonly>
							</div>
<%-- 							<div class="col-sm-3">
								<label for="alarmTime">알림 시간</label>
								<select class="form-control" name="alarmTime">
									<option value="10">10분 전</option>
									<option value="20">20분 전</option>
									<option value="30">30분 전</option>
									<option value="60">1시간 전</option>
									<option value="120">2시간 전</option>
		                        </select>
							</div>--%>
							<div class="col-sm-3">
								<div class="my-colorpicker2 colorpicker-element" data-colorpicker-id="2">
							 		<label for="color">달력 표시 색깔</label>
							 		<div class="row">
								 		<input type="color" id="color" class="form-control" name="color" value="${plan.color }" readonly>
								 	</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label for="address">상세장소</label>
								<input type="text" id="detailaddress" name="detailAddress" value="${plan.detailAddress }" class="form-control">
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
								class="form-control required" >${plan.content }</textarea>
						</div>
						<input type="hidden" id="latitude" class="form-control" name="latitude" value="${plan.latitude }" readonly>
						<input type="hidden" id="longitude" class="form-control" name="longitude" value="${plan.longitude }" readonly>
						<input type="hidden" id="no" class="form-control" name="no" value="${plan.no }">
						<input type="hidden" id="projectId" class="form-control" name="projectId" value="${plan.projectId }">
						<input type="hidden" id="code" class="form-control" name="code" value="${plan.code }">
					</form>
				</div><!--end card-body  -->
				<div class="card-footer" style="text-align:right;">
					<button type="button" class="btn btn-primary col-sm-3" onclick="go('modify')">수정</button>

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
				$('form[role=form]').submit();
			}
		}
// 		if(url === 'remove'){
// 			if(confirm('일정을 삭제하시겠습니까?')){
// 				window.location.href=url+"?no="+no;
// 			}
// 		}
	}
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/pms/css/jquery.timepicker.css">
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