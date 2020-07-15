<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%-- 우편번호 script --%>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script> 
<script>

function goSubmit(String){
	//close면 창닫기, submit이면 파일보내기
	if(String == 'post'){
		if(!$('input[name="checkID"]')){
			alert("아이디 입력은 필수입니다");
		}
		if($('input[name="checkID"]').val()==0){
			alert("아이디 중복체크를 해주세요");
			return;
		}
		if($("#licenseDoc")[0].files[0].size > 1024*1024*50){
			alert("주민등록등본 파일을 50MB 이하로 올려주세요");
			return;
		}
		if($("#graduDoc")[0].files[0].size > 1024*1024*50){
			alert("졸업증명서 파일을 50MB 이하로 올려주세요");
			return;
		}
		if($("#scoreDoc")[0].files[0].size > 1024*1024*50){
			alert("성적증명서 파일을 50MB 이하로 올려주세요");
			return;
		}
		//히히! 유효성 안해!
		var form = $('#registForm')
		form.submit();
	}else if(String == 'close'){
		window.close();
	}
}

function CheckID(){
	//아이디 중복체크
	var id = $('#id').val();
	$.ajax({
		url:"<%=request.getContextPath()%>/employee/idCheck",
		type:'post',
		data:{'id':id},
		success:function(data){
			alert("사용 가능한 아이디입니다.");
			$('input[name="checkID"]').val(1); //중복체크 확인
		},
		error:function(xhr){
			alert("아이디가 이미 존재합니다.");
		}
	});
}
//아이디란이 바뀌면 아이디 체크가 사라짐
$('#id').on('change',function(){
	$('input[name="checkID"]').val(0);
});

//으애ㅐ래앨내앨내애래내
//이메일 직접입력 체크했을 때. 근데 이거 click으로 되나?
$('#directInput').on('change',function(){
	if(this.checked){
		$('select[name=email]').attr('disabled','disabled');
		
	}else {
		$('select[name=email]').removeAttr('disabled');
		
	}
});

function SearchAddress(){
	//주소검색창... 아이고오오오 ㅠㅠㅠ
	new daum.Postcode({
		oncomplete: function(data) {
			var addr = ''; 
            var extraAddr = '';
			
            if (data.userSelectedType === 'R') { 
                addr = data.roadAddress;
            } else { 
                addr = data.jibunAddress;
            }
			
            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                document.getElementById("address[1]").value = extraAddr;
            
            } else {
                document.getElementById("address[1]").value = '';
            }
            document.getElementById('postCode').value = data.zonecode;
            document.getElementById("address[0]").value = addr;
		}
	}).open();
}

//사원번호 자동완성
$('#regDate').on('change',getEno);
$('select[name="dept_no"]').on('change',getEno);
function getEno(){
	var dept_no = $('select[name="dept_no"]').val();
	var regDate = $('#regDate').val();
	
	if(!dept_no){
		$('#eno').val('');
		return;
	}
	if(!regDate){
		$('#eno').val('');
		return;
	}
	
	var eno1 = regDate.substring(2,4)+regDate.substring(5,7)+regDate.substring(8)+dept_no;
	
	$.ajax({
		url:"<%=request.getContextPath()%>/employee/getEno",
		type:'post',
		data:{deptno:dept_no},
		success:function(eno){
			var eno2 = eno.length >= 3 ? eno : new Array(3 - eno.length + 1).join('0') + eno;
			$('#eno').val(eno1+eno2);
		},
		error:function(){
			alert("사원번호를 가져올 수 없습니다.\n관리자에게 문의하세요.")
		}
	});
};


//regDate datePicker띄우기
$("#regDate").datepicker({
});


//경력칸 추가하기
function RegistCareer(){
	//추가되는 칸을 구별하기위해 숫자를 세서 div id="coName"에 넣어놓는다. 이후 구별할 tagid가 됨.
	var countDiv = $('#coName').attr('tagid');
	var tagid;
	if(!countDiv){
		tagid = 0;
		$('#coName').attr('tagid',tagid);
	}else{
		tagid = Number(countDiv)+1;
		$('#coName').attr('tagid',tagid);
	}
	//formname과 tagid를 넘겨주면 자동으로 input을 추가하는 function 
	addFormNames('coName',tagid);
	addFormNames('job',tagid);
	addFormNames('dept',tagid);
	addFormNames('position',tagid);
	//startDate, endDate 추가
	addCareerDates('startDay',tagid);
	addCareerDates('endDay',tagid);
	//삭제버튼 추가
	$('#remove').append("<button style='border:3;outline:0;display:block;height:29px;' class='input-group bg-red delete' type='button' tagid='"+tagid+"'>X</button>");
}
function addFormNames(name,tagid){
	var input=$('<input>').attr({"class":"col-xs-12","type":"text","name":name,"tagid":tagid})
	$('#'+name).append(input);
}
function addCareerDates(name,tagid){
	var input=$('<input>').attr({"class":"col-xs-6","type":"text","name":name,"tagid":tagid,"readonly":"readonly"})
	$(input).datepicker({});
	$('#year').append(input);
}
//경력칸 삭제하기
$('div#remove').on('click','button',function(event){
	var tagid = $(this).attr('tagid');
	var list = $('input[tagid='+tagid+']');
	for(i=0;i<list.length;i++){
		list[i].remove();
	}
	this.remove();
});


//주민등록등본, 졸업증명서, 성적증명서 삭제하기
$('button[data-role="scoreDoc"]').on('click',function(){
	$('#scoreDoc').val('');
});
$('button[data-role="graduDoc"]').on('click',function(){
	$('#graduDoc').val('');
});
$('button[data-role="licenseDoc"]').on('click',function(){
	$('#licenseDoc').val('');
});




///////////////////////////////////////////////////////////////////
//사진보여주기
$('input#picture').on('change',function(){
	
	var fileFormat 
		= this.value.substr(this.value.lastIndexOf(".")+1).toUpperCase();
	
	if(fileFormat=="JPG" || fileFormat=="GIF" || fileFormat=="PNG"){
		
		if(this.files[0].size > 1024*1024*5){ 
			alert("사진 용량은 5MB이하만 가능합니다.");
			return;
		};
		
		if (this.files && this.files[0]){
			
			var reader = new FileReader();
			reader.onload = function(e) {
				//이미지 미리보기
				$('div#picturePreView')
					.css({'background-image':'url('+e.target.result+')',
					'background-position':'center',
					'background-size':'cover',
					'background-repeat':'no-repeat'
				});
			}
			reader.readAsDataURL(this.files[0]);
		}
	}else{
		alert("파일은 이미지 파일만 업로드 가능합니다")
	}
});

</script>