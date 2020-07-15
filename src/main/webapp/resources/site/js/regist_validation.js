/**
 * 
 */

//약관 동의했는지 여부 유효성 체크
function agreeCheck() {
	var chkbox = document.getElementsByName('agree');
	var chk = false;
	for (var i = 0; i < chkbox.length; i++) {
		if (chkbox[i].checked) {
			chk = true;
		} else {
			chk = false;
		}
	}
	if (chk) {
		window.location.href="/commons/member/regist";
	} else {
		alert("모든 약관에 동의해 주세요.")
	}
}