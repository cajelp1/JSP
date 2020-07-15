

//팝업창을 띄우기
//새로운 window창을 open할 경우 사용되는 함수  (arg : 주소, 상타이틀, 넓이, 길이)
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight){
	winleft = (screen.width - WinWidth)/2;
	wintop = (screen.height - WinHeight)/2;
	var win = window.open(UrlStr, WinTitle, "scrollbars=yes, width="+WinWidth+", "
			+"height="+WinHeight+", top="+wintop+", left="+winleft+", resizable=yes, status=yes");
	
	win.focus();
}

//팝업창 닫기
function CloseWindow(){
	//팝업창을 닫을 때 그 팝업창을 부른 부모창을 reload.
	window.opener.location.reload(true);
	window.close();
}


//--------------------------------------------------


//회원가입  및 업데이트
function SubmitMember(formRole){
	var uploadCheck = $('input[name="checkUpload"]').val();
	if(!(uploadCheck>0)){
		alert("사진을 업로드 해주세요.");
		//('input[name=pictureFile]').click();
		return;
	}
	var form = $('form[role="'+formRole+'"]');
	form.submit();
}

//취소하기 (뒤로가기)
function Cancel(){
	history.go(-1);
}

