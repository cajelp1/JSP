<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
window.onload = function(){
	longPolling();
}
function longPolling(){
    $.ajax({
    	url : '<%=request.getContextPath()%>/pms/notHistory/realTimeMessage',
    	type : 'get',
    	data : { 
    		projectId : ${param.projectId}
    	},
    	success : function(data){
    		alarmListView(data);
    	}
    	,timeout : 5000,
    	complete: setTimeout(function() { longPolling(); }, 10000)
    });
}

function openAlarm(url, no){
	$.ajax({
		url : '<%=request.getContextPath()%>/pms/notHistory/readMessage',
		type : 'get',
		async : false,
		data : {
			no : no
		},
		success : function(data){
			window.open(url ,'_black','width=800, height=800');
			$.ajax({
	        	url : '<%=request.getContextPath()%>/pms/notHistory/realTimeMessage',
	        	type : 'get',
	        	data : { 
	        		projectId : ${param.projectId}
	        	},
	        	success : function(data){
	        		alarmListView(data);
	        	}
	        });
		},
		error : function(error){
			alert("잠시후 다시 시도해주세요.");
		}
	})
	return false;	
}

function alarmListView(data){
	$('#NonReadCount').text(data.length);
	$('.lpoll').remove();
	for (var alarm of data) {
		var diviver = $('<div class="dropdown-divider lpoll"></div>');
		var anchor = $('<span data-toggle="hover" data-placement="bottom" data-html="true" class="dropdown-item lpoll"></span>');
		var title = $('<span id="NotHistoryTitle"></span>');
		var writer = $('<span class="float-right text-muted text-sm" id="NotHistoryWriter"></span>');
		
		anchor.attr('no', alarm['no']);
		anchor.attr('urlInfo', alarm['url']);
		anchor.attr('data-original-title', alarm['senderNickName']);
		anchor.attr('data-content', alarm['title']);
		anchor.attr('onclick', "openAlarm($(this).attr('urlInfo'), $(this).attr('no'));");
		title.text(alarm['title']);
		writer.text(alarm['senderNickName']);
		switch (alarm['type']) {
		// 쪽지
		  case 910: 
			anchor.append($('<span style="width:40px; display:inline-block;"/>').append($('<i class="fas fa-envelope mr-2"></i>')));
			break;
		// 일정
		  case 911:
			anchor.append($('<span style="width:40px; display:inline-block;"/>').append($('<i class="far fa-calendar-alt"></i>')));
			break;
		// 프로젝트초대, 탈퇴
		  case 912:
			anchor.append($('<span style="width:40px; display:inline-block;"/>').append($('<i class="fas fa-user"></i>')));
		    break;
		// 공지사항
		  case 913:
			anchor.append($('<span style="width:40px; display:inline-block;"/>').append($('<i class="fas fa-volume-up"></i>')));
		    break;
		}
		anchor.append(title);
		anchor.append(writer);

		$('.dropdown-header').after( $("<div class='container'/>").append(anchor) );
		$('.dropdown-header').after(diviver);
		
		$('.dropdown-item').popover({
			trigger : 'hover'
		});
	}
	$('.dropdown-item > span > i').css('font-size', '30px');
}
</script>