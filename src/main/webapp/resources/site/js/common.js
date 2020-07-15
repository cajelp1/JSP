function openWindow(url,width,height){

	var popupX = (window.screen.width / 2) - (width / 2);
	// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

	var popupY= (window.screen.height / 2) - (height / 2);
	window.open(url, '', 'status=no, height=' + height  + ', width=' + width  + ', left='+ popupX + ', top='+ popupY);
	
} 