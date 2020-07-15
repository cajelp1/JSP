<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style>
.fileDrop{
	width:100%;
	height:200px;
	border:1px dotted blue;	
}
small{
	margin-left:3px;
	font-weight:bold;
	color:gray;
}
</style>
</head>


<body>
	<h3>Ajax File Upload</h3>
	<div class="fileDrop"></div>
	
	<div class="uploadedList" ></div>
	
	<button type="button" id='submitBtn'>등록</button>
</body>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script>
	
	//드래그앤드롭되는 대상
	$(this).on("dragenter dragover drop",function(event){
		event.preventDefault();
	});
	//드래그앤드롭이 일어나는 상대
	$(".fileDrop").on("dragenter dragover",function(event){
		event.preventDefault();
	});
	
	$(".fileDrop").on("drop",function(event){
		event.preventDefault();
		
		var files=event.originalEvent.dataTransfer.files; //ajax안의 javascript event가 originalEvent.
		
		for(var i=0;i<files.length;i++){
			//alert(files[i].name);
			addFile(files[i]);
		}
		
	});
	
	function addFile(file){
		var formData=new FormData();
		formData.append("file",file);
		
		$.ajax({
			url:"uploadAjax",
			type:"post",
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				var str="";
				if(checkImageType(data)){
					/* 썸네일 */
					str="<div><a class='thumnail' href='displayFile?fileName="+getImageLink(data)+"'>"
					   +"<img src='displayFile?fileName="+data+"'/>"
					   +"</a><small data-src='"+data+"'><button>X</button></small></div>";
				}else{
					/* 텍스트  */
					str="<div><a class='thumnail' href='displayFile?fileName="+data+"'>"
						 +getOriginalName(data)+"</a>"
						 +"<small data-src='"+data+"'>"
						 +"<button>X</button></small></div>";
				}		
				$(".uploadedList").append(str);
			},
			error:function(error){
				alert("파일 업로드가 실패했습니다.");
			}
		});
	}
	
	function checkImageType(fileName){
		fileName=fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
		var pattern=/jpg|gif|png|jpeg/i;
		return fileName.match(pattern);
	}
	
	function getOriginalName(fileName){
		if(checkImageType(fileName)){
			return;
		}
		var idx=fileName.indexOf("$$")+1;
		return fileName.substr(idx);
	}
	
	function getImageLink(fileName){
		if(!checkImageType(fileName)){
			return;
		}
		var front = fileName.substr(0,12);	//앞의 날짜를 빼고
		var end = fileName.substr(14);		//얜 어디부터 자른거야..? /2020/05/08/s_uuid$$image.jpg 
											//그리고 왜 substr? substring이 아닌고야...?
		return front+end;
	}
	
	$('.uploadedList').on('click','small',function(event){
		//alert("delete btn");
		var data = $(this).attr("data-src");
		var that = $(this);
		var fileData = {fileName:data};
		
		$.ajax({
			url:"deleteFile",
			type:"post",
			data: JSON.stringify(fileData),
			contentType:"application/json",	//보내는 data 형식 지정
			success:function(data){
				that.parent("div").remove();
			},
			error: function(error){
				alert("첨부파일 삭제에 실패했습니다");
			}
		});
	});
	
</script>

</html>
