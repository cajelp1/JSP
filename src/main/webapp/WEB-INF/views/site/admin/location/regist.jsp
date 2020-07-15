<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<style>
	.title{
		text-align: center;
	}
</style>
<div class="container">
	<h4 class="title">사무실 등록</h4>
	<form id="registForm" encType="multipart/form-data" action="regist?${_csrf.parameterName}=${_csrf.token}" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" id="register" name="register" value="DolphinShark@naver.com" />
		<p>&nbsp;</p>
		<div class="row m-2">
			<div class="col">
			<label for="roomName">사무실 이름</label>
			</div>
			<div class="col">
			<label for="pricePerDay">일일대여료</label>
			</div>
		</div>
		<div class="row m-2">
			<div class="col">
			<input type="text" id="roomName" class="form-control" name="roomName">
			</div>
			<div class="col">
			<input type="text" id="pricePerDay" class="form-control" name="pricePerDay" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')">
			</div>
		</div>
		<br>
		
		<div class="row m-2">
			<div class="col">
			<label for="zibunAddress">실제 주소</label>
			</div>
		</div>
		<div class="row m-2">
			<div class="col">
			<input type="text" id="address" name="address" class="form-control" placeholder="주소" readonly>
			<input type="text" id="detailAddress" name="detailAddress" class="form-control" placeholder="상세주소">
			<input type="button" class="btn btn-info" onclick="searchAddress()" value="주소 검색"><br>
			</div>
		</div>
		<div class="row m-2">
			<div class="col">
			<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
			</div>
		</div>
		<br>
		<div class="row m-2">
			<div class="col">
				<input type="text" id="latitude" class="form-control" name="latitude" placeholder="위도" readonly>
			</div>
			<div class="col">
				<input type="text" id="longitude" class="form-control" name="longitude" placeholder="경도" readonly>
			</div>
		</div>
		<br>
		
		<div class="row m-2">
			<div class="col">검색시 도시</div>
			<div class="col">검색시 지역구</div>
			<div class="col">검색시 수용인원</div>
		</div>
		<div class="row m-2">
			<div class="col">
			<select name="city" id="city" class="form-control">
				<option value="0" <c:if test="${room.city eq ''}">selected</c:if>>전체보기</option>
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
		<!-- 첨부파일 시작 -->
		<div class="row m-2">
			<div class="col">
				사무실 대표 사진 <strong style="color: red;">대표사진 등록은 필수입니다!</strong>
			</div>
		</div>
		<div class="row m-2">
			<div class="col input-group">
				<input type="file" id="newPic0" class="form-control" name="profileImg" style="display:none;" >
				<input type="text" id="picName0" class="form-control" value="${room.attachFileList[0].fileName }" readonly>
				<div class="input-group-append">
				    <button id="change0" class="btn btn-outline-primary" type="button">파일선택</button>
				</div>
			</div>
		</div>
		<div class="row m-2">
			<div class="col">
				사무실 사진
			</div>
		</div>
		<div class="row m-2">
			<div class="col input-group">
				<input type="file" id="newPic1" class="form-control" name="picture" style="display:none;" >
				<input type="text" id="picName1" class="form-control" value="${room.attachFileList[1].fileName }" readonly>
				<div class="input-group-append">
				    <button id="change1" class="btn btn-outline-primary" type="button">파일선택</button>
				</div>
			</div>
		</div>
		<div class="row m-2">
			<div class="col input-group">
				<input type="file" id="newPic2" class="form-control" name="picture" style="display:none;" >
				<input type="text" id="picName2" class="form-control" value="${room.attachFileList[2].fileName }" readonly>
				<div class="input-group-append">
				    <button id="change2" class="btn btn-outline-primary" type="button">파일선택</button>
				</div>
			</div>
		</div>
		<!-- 첨부파일 끝 -->
		<div class=".container-sm m-4 float-right">
			<button type="button" class="btn btn-secondary" onclick="regist()">등록</button>
			<button type="button" class="btn btn-secondary" onclick="window.close()">닫기</button>
		</div>
	</form>
</div>
<%@ include file="admin_location_validation.jsp" %>
<script>

function regist(){
	var check = validation();
	if(check == true){
		var fileValue = $('#newPic0').val();
		if(fileValue==""){
			$("#newPic0").attr("disabled",true);			
		}
		fileValue= $('#newPic1').val();
		if(fileValue==""){
			$("#newPic1").attr("disabled",true);			
		}
		fileValue= $('#newPic2').val();
		if(fileValue==""){
			$("#newPic2").attr("disabled",true);			
		}
		/* if(confirm('사무실 정보를 등록하겠습니까?')){ */
		$('#registForm').submit();			
		/* } */
	}
}


$('#city').on('change',function(){
	var city = $('option:selected',this).val();
	
	$.ajax({
		url : '<%=request.getContextPath()%>/dsp/room/getJusoArea',
		type : "post",
		data : { 'city' : city },
		success : function(res){
			
			code = "";
			
			if(res.length > 0){
				
				$.each(res, function(i){
					code += "<option value='"+res[i].area+"'>"+res[i].area+" ("+res[i].detail+")"+"</option>"
				})
				
				// 내용을 지우는데는 empty(), remove() 두가지가 있다. 
				// remove는 태그 자체를 없애기때문에 여기서는 사용하지 않는다.
				$('#area').empty();
				
			}
			
			$('#area').html(code);
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		}
	})
})

$('#ok').on('click',function(e){
	
	var city = $('#city').val();
	var area = $('#area').val();
	var capacity = $('#capacity').val()+"";
	
	$.ajax({
		url : '<%=request.getContextPath()%>/dsp/room/getMeetingRooms/1',
		type:'post',
		contentType:"application/json; charset=UTF-8",
		data : JSON.stringify({city : city, area:area, capacity:capacity}),
		success : function(res){
			code = "";
			
			if(res.roomList.length == 0){
				code = "<h4>검색 결과가 없습니다</h4>"
			}else{
				
				$.each(res.roomList, function(i){
					code += "<div class='rooms' id='"+res.roomList[i].no+"'>"+res.roomList[i].no+"</div>";
				})
			}
			$('#result').empty();
			$('#result').html(code);
			
			//pagination 관련해서 추가해야함
			
		},
		error : function(xhr){
			alert("목록을 가져올 수 없습니다");
		}
	});
})

$('div').on('click','.rooms',function(){
	openWindow('showRoom/'+this.id,'600','720');
});

// document.getElementById('latitude').addEventListener('click',alertAddress);
// document.getElementById('longitude').addEventListener('click',alertAddress);
// 	function alertAddress(){
// 		alert('주소검색을 해주세요');	
// 	}
</script>
<%@ include file="address_js.jsp" %>
<%@ include file="./modify_js.jsp" %>
</body>