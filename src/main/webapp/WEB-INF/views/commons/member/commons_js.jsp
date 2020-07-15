<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
// 	function openWindow(url, width, height){
// 		window.open(url,'',"width="+width+", height="+height);
// 	}
	
	function formRegist(){
		var check = validationCheck();
		console.log(check);
		if(!check){
			return;
		}
		var registForm = document.getElementById('registForm');
		registForm.submit();
	}
	function imageSelect() {
		upload_go();
	}
	
	$('input#inputFile').on('change',function(event){
		$('input[name="checkUpload"]').val(0);
		
		var fileFormat=
			this.value.substr(this .value.lastIndexOf(".")+1).toUpperCase();
		//이미지 확장자 jpg 확인
		if(fileFormat!="PNG" && fileFormat!="JPG"){
			alert("이미지는 jpg 또는 png 형식만 가능합니다.");
			return;
		} 
		//이미지 파일 용량 체크
		if(this.files[0].size>1024*1024*2){
			alert("사진 용량은 2MB 이하만 가능합니다.");
			return;
		};
		
		document.getElementById('inputFileName').value=this.files[0].name;
		
		if (this.files && this.files[0]) {
			
	        var reader = new FileReader();
	        
	        reader.onload = function (e) {
	        	//이미지 미리보기	        	
	        	$('div#pictureView')
	        	.css({'background-image':'url('+e.target.result+')',
					  'background-position':'center',
					  'background-size':'cover',
					  'background-repeat':'no-repeat'
	        		});
	        }
	        
	        reader.readAsDataURL(this.files[0]);
		}
	});
	
	function upload_go(){
		//form 태그 양식을 객체화	
		var form = new FormData($('form[role="imageForm"]')[0]);
		
		if($('input[name="pictureFile"]').val()==""){
			alert("사진을 선택하세요.");
			$('input[name="pictureFile"]').click();
			return;
		};	
		$.ajax({
			url:"<%=request.getContextPath()%>/commons/member/uploadImg",
			data : form,
			type : 'post',
			beforeSend : function(xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
            },
			processData : false,
			contentType : false,
			success : function(data) {
				$('input#oldFile').val(data);
				$('form[role="form"] > input[name="picture"]').val(data);
				$('input[name="checkUpload"]').val(1);
				alert("사진을 업로드 했습니다.");
			},
			error : function(xhr, exception) {
				alert("파일 업로드를 실패했습니다.");
			}
		});

	}
</script>


<!-- 주소 검색 -->
<script	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function searchAddress() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById("postCode").value = data.zonecode;
				document.getElementById("address").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("detailAddress").focus();
			}
		}).open();
	}
</script>