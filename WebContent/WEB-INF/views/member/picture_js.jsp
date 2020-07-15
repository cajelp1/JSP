<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>

	$('input#inputFile').on('change',function(){
		
		//새로운 파일을 선택했으므로 check를 다시 0으로 초기화해준다.
		$('input[name="checkUpload"]').val(0);
		
		//jpg포맷만을 받고 싶으므로 해당 파일의 확장자를 잘라서 비교한다.
		var fileformat = this.value.substr(this.value.lastIndexOf(".")+1).toUpperCase();
		
		if(fileformat != 'JPG'){
			alert("이미지는 jpg형식만 가능합니다");
			return;
		};
		
		//올리려는 파일의 크기도 제한한다.
		if(this.files[0].size > 1024*1024*1){
			alert("파일은 1M미만이어야 합니다");
			return;
		};
		
		//위의 제한에 걸리지 않았을 경우, 파일명을 태그안에 표시한다. 
		document.getElementById('inputFileName').value = this.files[0].name;
		
		//파일에 내용물들이 모두 들어있는지 확인하고 이미지를 박스에 표시해준다. 
		if(this.files && this.files[0]){
			
			var reader = new FileReader();	//얘가 실제 file을 읽어오는 녀석임.
			
			reader.onload = function(e){	//e는 event.
				$('div#pictureView')
				.css({'background-image':'url('+e.target.result+')',
						'background-position':'center',
						'background-size':'cover',
						'background-repeat':'no-repeat'
				});	/*	background로 css를 주는 이유? img로 주면 드래그가 되고 , 해당 이미지를 크기에 딱 맞춰 보여준다. 
						하지만 background는 짧은 쪽을 기준으로 화면을 찌그러뜨리지 않고 그냥 넣는다.*/
			}
			reader.readAsDataURL(this.files[0]); /* 파일이 url형태로 나오지 않기 때문에 이렇게 넣는다. 
													이 때의 url값은 result로 나옴.... */ 
		}
	});
	
	
	//이후 submit을 눌렀을 때 실행할 jquery
	function upload_go(){
		
		//form 태그 양식을 객체화한다. submit으로 파일은 파라미터로 넘길 수 없으므로 데이터객체화를 실행한다. 
		// 유의점!!! form 태그에서 enctype="multipart/form-data" 라는 부분을 꼭!! 적어줘야 한다!!!
		
		var form = new FormData($('form[role="imageForm"]')[0]);
		
		if($('input[name="pictureFile"]').val()==""){
			alert("사진을 선택하세요");
			$('input[name="pictureFile"]').click();
			return;
		}
		
		$.ajax({
			url:"<%= request.getContextPath()%>/member/picture",
			data:form,
			type:'post',
			processData:false,	// 이 두개의 요소가 
			contentType:false,	// 반드시 있어야! 파일만 날아간다!
			// processData : 데이터를 query String으로 보내라고 명령하는 것
			// contentType : 헤더에 text/html , text/css 같은 식으로 표시하는 것. 여기서 false를 주고 enctype=multipart/
			success:function(data){
				$('input#oldFile').val(data);
				$('form[role="form"] > input[name="picture"]').val(data);
				$('input[name="checkUpload"]').val(1);
				alert("사진이 업로드 됐습니다");
			}
		});
	};
	
</script>

