<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.js"></script>
<script type="text/x-handlebars-template" id="room-list-template">
{{#each .}}
<div class="col-md-4 mb-3">
	<div class="card h-100" >
		<div style="height:200px; background-image:url('<%=request.getContextPath() %>/dsp/room/img?room={{no}}'); background-repeat:no-repeat; background-size:cover; background-position:center">
		</div>
		<div class="card-body">
			<h5 class="card-title">{{roomName }}</h5>
			<p class="card-text">{{address}}&nbsp;{{detailAddress}}</p>
		</div>
		<div class="btn-group">
			<button type="button" class="btn btn-sm btn-outline-secondary" onclick="openWindow('<%=request.getContextPath() %>/dsp/room/detail/{{no}}',720,640)">상세보기</button>
		</div>
	</div>
</div>
{{/each}}
</script>
<script>

$('#city').on('change',function(){
	var city = $('option:selected',this).val();

	$.ajax({
		url : '<%request.getContextPath();%>/dsp/room/getJusoArea',
		type : "post",
		data : { 'city' : city },
		success : function(res){
			
			code = "<option value=''>지역구 전체보기</option>";
			
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

//handleBar
var printData = function(roomlist,target,templateObject){
	var template = Handlebars.compile(templateObject.html());
	var code = template(roomlist);
	$('#result').empty();
	target.html(code);
}

$('#ok').on('click',function(e){
	
	var city = $('#city').val();
	var area = $('#area').val();
	var capacity = $('#capacity').val()+"";
	
	$.ajax({
		url : '<%request.getContextPath();%>/dsp/room/getMeetingRooms/1',
		type:'post',
		contentType:"application/json; charset=UTF-8",
		data : JSON.stringify({city : city, area:area, capacity:capacity}),
		success : function(res){
			
			if(res.roomList.length == 0){
				code = "<h4>검색 결과가 없습니다</h4>"
				$('#result').empty();
				$('#result').html(code);
			}else{
				alert("anasdf");
				printData(res.roomList,$('#result'),$('#room-list-template'));
				//pagination 관련해서 추가해야함			
			}
			
		},
		error : function(xhr){
			alert("목록을 가져올 수 없습니다");
		}
	});
})

$('div').on('click','.rooms',function(){
	openWindow('<%=request.getContextPath()%>/dsp/room/detail/'+this.id,'600','720');
});


</script>