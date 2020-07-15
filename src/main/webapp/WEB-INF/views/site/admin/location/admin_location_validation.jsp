<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
function validation(){
	var roomName = $.trim($('#roomName').val());
	if(roomName==""||roomName==null){
		alert('사무실 이름을 입력해주세요');
		return false;
	}
	var priceperday = $.trim($('#pricePerDay').val());
	if(priceperday==""||priceperday==null){
		alert('일일 대여료를 입력하세요.');
		return false;
	}
	var address = $.trim($('#address').val());
	if(address==""||address==null){
		alert('주소를 선택해주세요');
		return false;
	}
	address = $.trim($('#detailAddress').val());
	if(address==""||address==null){
		alert('상세 주소를 선택해주세요');
		return false;
	}
	var city = $.trim($('#city').val());
	if(city==""||city==null||city==0){
		alert('도시를 선택하세요.');
		return false;
	}
	var area = $.trim($('#area').val());
	if(area==""||area==null||area==0){
		alert('지역구를 선택하세요.');
		return false;
	}
	var profile = $.trim($('#newPic0').val());
	if(profile==""||profile==null){
		alert('사무실 대표사진을 등록해주세요');
		return false;
	}
	return true;
}
</script>