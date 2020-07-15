<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>

$(document).ready(function(){
	var city = "${param.city}";
	var area = "${param.area}";
	if(city != "" && area != ""){
		getArea(city, area);
	}
});

$('#city').on('change',function(){
	var city = $('option:selected',this).val();
	getArea(city);
});
var token = $("meta[name='_csrf']").attr("content");

var header = $("meta[name='_csrf_header']").attr("content");
function getArea(city, selectedArea){
	$.ajax({
		url : '<%request.getContextPath();%>/dsp/room/getJusoArea',
		type : "post",
		beforeSend: function( xhr ) {
		  xhr.setRequestHeader(header, token);
		},
		data : { 'city' : city },
		success : function(res){
			
			code = "<option value=''>지역구 전체보기</option>";
			
			if(res.length > 0){
				
				$.each(res, function(i){
					if(selectedArea && res[i].area == selectedArea){
						code += "<option value='"+res[i].area+"' selected>"+res[i].area+" ("+res[i].detail+")"+"</option>"
					}else{
						code += "<option value='"+res[i].area+"'>"+res[i].area+" ("+res[i].detail+")"+"</option>"
					}
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
};

$('div').on('click','.rooms',function(){
	openWindow('<%=request.getContextPath()%>/dsp/room/detail/'+this.id,'600','720');
});


</script>