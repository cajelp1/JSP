//팝업창들 뛰우기
//새로운 Window창을 Open할 경우 사용되는 함수 ( arg : 주소 , 창타이틀 , 넓이 , 길이 )
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
							+"height="+ WinHeight +", top="+ wintop +", left=" 
							+ winleft +", resizable=yes, status=yes"  );
  win.focus() ; 
}

//팝업창 닫기
function CloseWindow(){
	window.opener.location.reload(true);
	window.close();
}

// 회원등록시 사진업로드
function SubmitMember(formRole){
	var uploadCheck = $('input[name="checkUpload"]').val();
	if(!(uploadCheck>0)){
		alert("사진 업로드는 필수입니다.");
		// $('input[name="pictureFile"]').click();
		return;
	}
	var form = $('form[role='+ formRole +']');
	form.submit();
}

var searchTypeDatepickerAble = function(selector){
	$(selector).on('change',function(){
		if ($(this).val() == 't') {
			$('#datePicker').datepicker("destroy");
		} else {
			$('#datePicker').datepicker({ dateFormat: "yy-mm-dd"});
		}
		$('#datePicker').val("");
	});
}
function go_back(){
   window.history.back();
}