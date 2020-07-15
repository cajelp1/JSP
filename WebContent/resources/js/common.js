

//팝업창 띄우기
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight){
	winleft = (screen.width - WinWidth)/2;
	wintop = (screen.height - WinHeight)/2;
	var win = window.open(UrlStr, WinTitle, "scrollbars=yes, width="+WinWidth+", "
			+"height="+WinHeight+", top="+wintop+", left="+winleft+", resizable=yes, status+yes");
	
	win.focus();
}

//팝업창 닫기
function CloseWindow(){
	window.opener.location.reload(true);
	window.close();
}


//취소하기 (뒤로가기)
function Cancel(){
	history.go(-1);
}

