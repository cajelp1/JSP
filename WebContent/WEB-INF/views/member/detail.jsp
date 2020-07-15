<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <%@ include file="/WEB-INF/views/include/open_header.jsp" %> --%>

<body>
  <div class="content-wrapper">
	<!-- Content Header (Page header) -->
	  <section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>회원상세</h1>
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
				        <li class="breadcrumb-item">
				        	<a href="lis">
					        	<i class="fa fa-dashboard"></i> 회원관리
					        </a>
				        </li>
				        <li class="breadcrumb-item active">
				        	상세보기
				        </li>		        
   	  				</ol>
 				</div>
 			</div>
		</div>
	</section>

	<section class="content register-page" style="height: 586.391px;">       
		<div class="register-box" style="min-width:450px;">
	    	<form role="form" class="form-horizontal" action="regist.do" method="post">
	        	<div class="register-card-body" >
				
	            	<div class="row"  style="height:170px;">
						<div class="mailbox-attachments clearfix col-md-12" style="text-align: center;">							
							<div id="pictureView" style="border: 1px solid green; height: 150px; width: 150px; margin: 0 auto;"></div>														
						</div>
					</div>
	                <div class="form-group row" >
	                  <label for="inputEmail3" class="col-sm-3 control-label text-right">아이디</label>
	                  <div class="col-sm-9">
	                    <input name="id" type="text" class="form-control" id="inputEmail3" value="${member.id }">
	                  </div>
	                </div>
	                <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">패스워드</label>
	                  <div class="col-sm-9">
	                    <input name="pwd" type="password" class="form-control" id="inputPassword3" value="${member.pwd }">
	                  </div>
	                </div>
	                <div class="form-group row">
	                  <label for="inputName3" class="col-sm-3 control-label text-right">이&nbsp;&nbsp;름</label>
	                  <div class="col-sm-9">
	                    <input name="name" type="text" class="form-control" id="inputName3" value="${member.name }">
	                  </div>
	               </div>
	                <div class="form-group row">
	                  <label for="inputEmail3" class="col-sm-3 control-label text-right">이메일</label>
	                  <div class="col-sm-9">
	                    <input name="email" type="email" class="form-control" id="inputEmail3" value="${member.email }">
	                  </div>
	                </div>
	                <div class="form-group row">
	                  <label for="inputPhone3" class="col-sm-3 control-label text-right">전화번호</label>
	                  <div class="col-sm-9">
	                  	<input name="phone" type="text" class="form-control" id="inputPhone3" value="${member.phone.substring(0,3) }-${member.phone.substring(3,7)}-${member.phone.substring(7) }">	                
	                  </div>
	                </div>
	              </div>  <!-- /.card body -->
	              <div class="card-footer">
	        			<div class="row">
			          		<div class="col-sm-3 text-center">
			          			<button type="button" id="modifyBtn" class="btn btn-warning ">수 정</button>
			          		</div>
			          		<div class="col-sm-3 text-center">
				          		<button type="button" id="deleteBtn" class="btn btn-danger" >삭 제</button>
			          		</div>
			          		<c:if test="${member.enabled eq 1 }">
				          		<div class="col-sm-3 text-center">
				          			<button type="button" id="disabledBtn" class="btn btn-info" >정 지</button>
				          		</div>
			          		</c:if>
			          		<c:if test="${member.enabled ne 1 }">
				          		<div class="col-sm-3 text-center">
					          		<button type="button" id="enabledBtn" class="btn btn-info" >활 성</button>
				          		</div>
			          		</c:if>
			          		<div class="col-sm-3 text-center">
			            		<button type="button" id="listBtn" onclick="CloseWindow();" class="btn btn-primary pull-right">닫 기</button>
			            	</div>
		          	    </div>
	        		</div>	<!-- /.card footer -->
	      	  </form>
      	  </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<!-- post parameter -->
<form name="postForm">
	<input type="hidden" name="id" value="${member.id }" />
</form>


<%-- <%@ include file="/WEB-INF/views/include/open_footer.jsp" %> --%>

<script>

	var imageURL = "picture/get?picture=${member.picture}";
	$('div#pictureView').css({	'background-image' : 'url('+imageURL+')',
								'background-position' : 'center',
								'background-size' : 'cover',
								'background-repeat' : 'no-repeat'
	});
	
	/* 
	$.ajax({
		url : 'picture/get?picture=abcd.jpg',
		type : 'get',
		success : function(e){
			alert(e);
		},
		error : function(e){
			alert(e);
		}
	});
	*/
	
	$('button#modifyBtn').on('click',function(e){
		location.href="modify?id=${member.id}";
		
		/*form태그를 위에서 선언하고 해당 form 태그의 attr을 이용할 수도 있다.
		
		form.attr{
			'action':'submit',
			'method':'post'
		}
		
		*/
	});
	
	$('button#disabledBtn').on('click',function(e){
		location.href="disabled?id=${member.id}";
	});
	
	$('button#enabledBtn').on('click',function(e){
		location.href="enabled?id=${member.id}";
	});
	
	$('button#deleteBtn').on('click',function(e){
		var pwd = prompt("패스워드를 입력하세요");
		
		$.ajax({
			url : "checkPassword?pwd="+pwd,
			type: "get",
			success:function(data){
				if(data=='success'){
					location.href="remove?id=${member.id}"
				}else{
					alert("패스워드가 맞지 않습니다");
					return;
				}
			}
		})
	
/* 		$.getJSON("checkPassword?pwd="+pwd, function(data){
			if(data=="success"){
				alert("success");
			}else{
				alert("패스워드가 맞지 않습니다.")
				return;
			}
		}) // 얘 안 먹힌다.. getJSON은 돌아오는 타입을 JSON으로 제한하기 때무네... 우리는 get으로 String을 리턴하는데 그게 안댄댜... 
*/
		
		
/*		//이렇게하면 소스코드에 패스워드가 그냥 찍힘... 그러니까 서블렛을 한번 거치자...
		
		if(pwd!="${loginUser.pwd}"){
			alert("패스워드가 맞지 않습니다.")
			return;
		}else{
			location.href="remove?id=${member.id}";
		}
*/
	});
	
	
</script>


</body>


  
  