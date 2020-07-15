<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script>
window.addEventListener('load', function(){
	//datepicker 한국어로 사용하기 위한 언어설정
	$.datepicker.setDefaults($.datepicker.regional['ko']); 
	
	//시작일.
	$('#fromDate').datepicker({
	    dateFormat: "yy-mm-dd",             // 날짜의 형식
	    onClose: function( selectedDate ) {    
	        // 시작일(fromDate) datepicker가 닫힐때 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
	        $("#toDate").datepicker( "option", "minDate", selectedDate );
	    }                
	});
	
	//종료일
	$('#toDate').datepicker({
	    dateFormat: "yy-mm-dd",
	    onClose: function( selectedDate ) {
	        // 종료일(toDate) datepicker가 닫힐때 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
	        $("#fromDate").datepicker( "option", "maxDate", selectedDate );
	    }                
	});
});
	
<%--	//color picker with addon
	$('.my-colorpicker2').colorpicker();

	$('.my-colorpicker2').on('colorpickerChange', function(event) {
	  $('.my-colorpicker2 .fa-square').css('color', event.color.toString());
	});
	
	$('.my-colorpicker2 i').css("color", $('.my-colorpicker2 input[name=color]').val());--%>
</script>