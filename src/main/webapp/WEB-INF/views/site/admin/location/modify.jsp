<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<form class="form container" id="modifyForm" encType="multipart/form-data" action="<%=request.getContextPath() %>/dsp/admin/location/modify/${room.roomNo}?${_csrf.parameterName}=${_csrf.token}" method="post">

	<input type="hidden" id="register" name="register" value="${sessionScope.loginUser.email}" />
	<input type="hidden" id="roomNo" name="roomNo" value="${room.roomNo }" />
	<input type="text" id="oldPic0" class="form-control" name="oldPic" style="display:none;" value="${room.profile.no }">
	<input type="text" id="oldPic1" class="form-control" name="oldPic" style="display:none;" value="${room.attachFileList[0].no }">
	<input type="text" id="oldPic2" class="form-control" name="oldPic" style="display:none;" value="${room.attachFileList[1].no }">
	<p>&nbsp;</p>
	<div class="row form-group">
		<div class="col-3">
		<label for="roomName">사무실 이름 :</label>
		</div>
		<div class="col-9">
		<input type="text" id="roomName" class="form-control" name="roomName" value="${room.roomName }">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-3">
		<label for="roomName">일일사용료 :</label>
		</div>
		<div class="col-9">
		<input type="text" id="pricePerDay" class="form-control" name="pricePerDay" value="${room.pricePerDay }">
		</div>
	</div>
	
	<div class="row form-group">
		<div class="col-3">
		<label for="zibunAddress">실제 주소 :</label>
		</div>
		<div class="col-9">
		<input type="text" id="address" name="address" class="form-control" value="${room.address }">		
		</div>
	</div>
	<div class="row form-group">
		<div class="col-9">
			<input type="text" id="detailAddress" name="detailAddress" class="form-control" value="${room.detailAddress }">
		</div>
		<div class="col-3">
			<input type="button" class="btn btn-info" onclick="searchAddress()" value="주소 검색">
		</div>
	</div>
	<div class="row form-group">
		<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
	</div>
	
	<div class="row form-group">
		<div class="col-2">
		<label for="latitude">위도 :</label>
		</div>
		<div class="col-4">
		<input type="text" id="latitude" class="form-control" name="latitude" value="${room.latitude }" readonly>
		</div>
		<div class="col-2">
		<label for="longitude">경도 :</label>
		</div>
		<div class="col-4">
		<input type="text" id="longitude" class="form-control" name="longitude" value="${room.longitude }" readonly>
		</div>
	</div>
	
	<div class="row form-group">
		<div class="col">검색조건 : 
		</div>
	</div>
	<div class="row form-group">
		<div class="col">
		<select name="city" id="city" class="form-control">
			<option value="서울" <c:if test="${room.city eq '서울'}">selected</c:if>>서울</option>
			<option value="경기" <c:if test="${room.city eq '경기'}">selected</c:if>>경기</option>
			<option value="강원" <c:if test="${room.city eq '강원'}">selected</c:if>>강원</option>
			<option value="충청" <c:if test="${room.city eq '충청'}">selected</c:if>>충청</option>
			<option value="인천" <c:if test="${room.city eq '인천'}">selected</c:if>>인천</option>
			<option value="전라" <c:if test="${room.city eq '전라'}">selected</c:if>>전라</option>
			<option value="경상" <c:if test="${room.city eq '경상'}">selected</c:if>>경상</option>
			<option value="부산" <c:if test="${room.city eq '부산'}">selected</c:if>>부산</option>
			<option value="제주" <c:if test="${room.city eq '제주'}">selected</c:if>>제주</option>
	  	</select>
		</div>
		<div class="col">
		<select name="area" id="area" class="form-control">
			<c:forEach var="area" items="${jusoArea}">
				<option value="${area.area }" <c:if test="${room.area eq area.area}">selected</c:if>>${area.area }</option>
			</c:forEach>
	  	</select>
		</div>
		<div class="col">
		<select name="capacity" id="capacity" class="form-control">
		    <option value="2" <c:if test="${room.capacity == 2}">selected</c:if>>2명</option>
		    <option value="4" <c:if test="${room.capacity == 4}">selected</c:if>>4명</option>
		    <option value="8" <c:if test="${room.capacity == 8}">selected</c:if>>8명</option>
		    <option value="10" <c:if test="${room.capacity == 10}">selected</c:if>>10명</option>
		    <option value="12" <c:if test="${room.capacity == 12}">selected</c:if>>12명</option>
		    <option value="15" <c:if test="${room.capacity == 15}">selected</c:if>>15명</option>
		    <option value="20" <c:if test="${room.capacity == 20}">selected</c:if>>20명</option>
		    <option value="21" <c:if test="${room.capacity == 21}">selected</c:if>>20명 이상</option>
	  	</select>
		</div>
	</div>
	<br>
	<br>
	<div class="row form-group">
		<div class="col">
			사무실 대표 사진
		</div>
	</div>
	<div class="row form-group">
		<div class="col input-group">
			<input type="file" id="newPic0" class="form-control" name="profileImg" style="display:none;" accept=".jpg,.jpeg,.png,.gif,.bmp"/>
			<input type="text" id="picName0" class="form-control" value="${room.profile.originFileName }" readonly>
			<div class="input-group-append">
			    <button id="change0" class="btn btn-outline-primary" type="button">추가/변경</button>
<!-- 			    <button id="cancel0" class="btn btn-outline-danger" type="button" onclick="removeAttach(0)">삭제</button> -->
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class="col">
			사무실 사진
		</div>
	</div>
	<div class="row form-group">
		<div class="col input-group">
			<input type="file" id="newPic1" class="form-control" name="picture" style="display:none;" accept=".jpg,.jpeg,.png,.gif,.bmp" />
			<input type="text" id="picName1" class="form-control" value="${room.attachFileList[0].originFileName }" readonly>
			<div class="input-group-append">
			    <button id="change1" class="btn btn-outline-primary" type="button">추가/변경</button>
<!-- 			    <button id="cancel1" class="btn btn-outline-danger" type="button" onclick="removeAttach(1)">삭제</button> -->
			</div>
		</div>
	</div>
	<div class="row form-group">
		<div class="col input-group">
			<input type="file" id="newPic2" class="form-control" name="picture" style="display:none;" accept=".jpg,.jpeg,.png,.gif,.bmp"/>
			<input type="text" id="picName2" class="form-control" value="${room.attachFileList[1].originFileName }" readonly>
			<div class="input-group-append">
			    <button id="change2" class="btn btn-outline-primary" type="button">추가/변경</button>
<!-- 			    <button id="cancel2" class="btn btn-outline-danger" type="button" onclick="removeAttach(2)">삭제</button> -->
			</div>
		</div>
	</div>
	<c:if test="${room.profile.originFileName ne null }">
	<div class="container p-3 border" id="profileImg">
		<label>프로필 이미지</label>
		<img id="profile" style="width: 100%;" src="<% request.getContextPath();%>/dsp/admin/location/picture?fileName=${room.profile.fileName}">	
	</div>
	</c:if>
	<c:if test="${room.attachFileList ne null }">
	<div class="container p-3 mb-3 border">
		<label>상세 이미지</label>
		<c:forEach var="file" items="${room.attachFileList }">
		<img id="" style="width: 49%;" src="<% request.getContextPath();%>/dsp/admin/location/picture?fileName=${file.fileName}">
		</c:forEach>
	</div>
	</c:if>
	<div class="container p-3 mb-3 border">관제 관련사항...
		<br><br><br><br><br><br><br>
	</div>
	<div class="container mb-3 text-right">
		<button type="button" class="btn btn-secondary" onclick="modify()">수정</button>
		<button type="button" class="btn btn-secondary" onclick="window.history.go(-1)">취소</button>
	</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<%@ include file="./modify_js.jsp" %>
<%@ include file="address_js.jsp" %>

</body>
</html>