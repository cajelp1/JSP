<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 로고 -->

<!-- 로고 끝 -->
<body>
<style>
.slideImg{
	width: 100%;
}
.slide-div{
	text-align: center;
}
.wrapper slide-div{
	position: relative;
}
li{
	z-index: 50;
	left: 0px;
}
</style>
<script src="<%=request.getContextPath() %>/resources/site/js/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/site/js/jquery.bxslider.min.js"></script>
<script type="text/javascript">
$(function () {
    $('.bxslider').bxSlider({
    	auto: true,
    	speed: 500,
    	pause: 1500,
    	mode:'fade',
    	controls : true,
    	autoControls: true,
    	pager:false,
    	slideWidth: 1200,
    	responsive:true 
 
    })
})
$(document).ready(function() {
	var slider = $('.bxslider').bxSlider();
	var mql = window.matchMedia("screen and (max-width: 1200px)");
	mql.addListener(function(e) {
		if(!e.matches) {
			$(".bx-pager bx-default-pager").hide();
			slider.reloadSlider();
		}
			$(".bx-pager bx-default-pager").hide();
	}); 
	$(".bx-controls-direction").hide();	//onload시 시작버튼 숨김.
	$(".bx-pager-item").hide();
	$(".bx-controls-auto").hide();
	$(".bx-pager bx-default-pager").hide();
	$(".bx-controls bx-has-controls-direction bx-has-controls-auto bx-has-pager").hide();
});

</script>
<!-- 이미지 슬라이드 -->
<div class="container">
	<div class="wrapper slide-div">
	    <ul class="bxslider">
	        <li>
	            <img class="slideImg" src="<%=request.getContextPath() %>/resources/site/img/1.png" title="슬라이딩 테스트"/>
	        </li>
	        <li>
	            <img class="slideImg" src="<%=request.getContextPath() %>/resources/site/img/2.png" title="슬라이딩 테스트"/>
	        </li>
	        <li>
	            <img class="slideImg" src="<%=request.getContextPath() %>/resources/site/img/3.png" title="슬라이딩 테스트"/>
	        </li>
	        <%-- <li>
	            <img class="slideImg" src="<%=request.getContextPath() %>/resources/site/img/IU4.jpg" title="슬라이딩 테스트"/>
	        </li> --%>
	    </ul>
	</div>
</div>    
<!-- 이미지 슬라이드끝 -->
<br>
<!-- 장소 사진 -->
	<div class="container">
		<div class="row">
			<c:forEach var="recentImg" items="${dataMap.recentRoom}">
			<div class="col mb-4">
				<div class="card h-100">
					<img src="<%=request.getContextPath() %>/dsp/main/img?fileName=${recentImg.fileName}" class="card-img-top"
					  onerror="this.src='<%=request.getContextPath() %>/resources/site/img/unnamed.jpg'" >
					<div class="card-body">
						<h5 class="card-title">사무실 이름 : ${recentImg.roomName }</h5>
						<p class="card-text">사무실 주소 : ${recentImg.address }${recentImg.detailAddress }</p>
					</div>
				</div>
			</div>
		    </c:forEach>
	    </div>
    </div>
<!-- 장소 사진 끝 -->

<!-- 공지사항, 장소리스트 시작 한 row에 묶기 -->
<div class="container">
	<div class="row">
		<div class="col-6">
			<div class="noticeList">
				<section class="section-header">
				<strong class="sec_title">공지사항</strong>
				<a href="<%=request.getContextPath() %>/dsp/notice/list" class="more"><i class="icon_pack icon-more_notice"></i>더보기</a>			
				</section>
				<c:forEach var="notice" items="${dataMap.noticeList }">
					<table class="table table-sm">
						<thead>
							<tr>
								<th scope="col">
									<a onclick='openWindow("<%=request.getContextPath()%>/dsp/notice/detail?sno=${notice.sno}",720,640)'>
									<p class="notice_txt">${notice.title }</p>
									</a>
								</th>
							</tr>
						</thead>
					</table>
				</c:forEach>				
			</div>
		</div>
		<div class="col-6">
			<div class="roomList">
				<table class="table-responsive-sm">
					 <thead>
					    <tr>
					      <th scope="col">장소</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<tr>
					  		<td>대전 대덕인재개발원</td>
					  	</tr>
					  </tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- 공지사항, 장소리스트 끝 -->
</body>
</html>