<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty sessionScope.loginUser && sessionScope.loginUser.role eq 'ROLE_ADMIN'}">
<script>
var maxNo;
window.onload = function(){
	getDangerData();
}
function getDangerData(){
    $.ajax({
    	url : '<%=request.getContextPath()%>/dsp/sensor/dangeDataMaxNo',
    	type : 'get',
//         beforeSend: function( xhr ) {
//             xhr.setRequestHeader(header, token);
//         },
    	data : { },
    	success : function(no){
//     		console.log('maxNo : ' + maxNo);
//     		console.log('no : ' + no);
    		if (no > maxNo) {
    			AlarmPopUp(maxNo, no);
			}
    		maxNo = no;
    	}
    	,timeout : 5000,
    	complete: setTimeout(function() { getDangerData(); }, 10000)
    });
}

function AlarmPopUp(maxNo, no){
	if (window.open) {
		window.close();
	}
	if (screen.height < 1000) {
		OpenWindow("/dsp/sensor/dangerAlarm?maxNo="+maxNo + "&currentNo=" + no, '위험알림!', 700, 560);
	} else {
		OpenWindow("/dsp/sensor/dangerAlarm?maxNo="+maxNo + "&currentNo=" + no, '위험알림!', 700, 710);
	}
}

function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
							+"height="+ WinHeight +", top="+ wintop +", left=" 
							+ winleft +", resizable=yes, status=yes"  );
//   win.focus() ; 
}
</script>
</c:if>