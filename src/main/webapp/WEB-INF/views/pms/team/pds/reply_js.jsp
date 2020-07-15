<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.js"></script>
<script type="text/x-handlebars-template" id="reply-list-template">
{{#each .}}
<div class="replyLi" data-rno={{no}}>
	<i class="fas fa-comments bg-yellow"></i>
	<div class="timeline-item" >
		<span class="time">
			<i class="fa fa-clock"></i>{{mDateFormat regdate}}
			<button class="btn btn-primary btn-xs" id="modifyReplyBtn"
				data-replyer={{writer}} data-toggle="modal" data-target="#modifyModal">수정</button>
			<button type="button" class="btn btn-danger btn-xs" id="replyDelBtn" data-no={{no}}>삭제</button>
		</span>
		
		<h3 class="timeline-header"><strong style="display:none;">{{no}}</strong>{{nickname}}</h3>
		<div class="timeline-body">{{content}} </div>
	</div>
</div>
{{/each}}
</script>



<script type="text/javascript">
window.addEventListener('load', function(){
	
	//date 타입을 다르게 보여주기. 데이터가 json임을 기억하자.
	Handlebars.registerHelper("mDateFormat", function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth()+1;
		var date = dateObj.getDate();
		return year+"/"+month+"/"+date;
	});
	
	
	
	//반복되어 사용되기 때문에 function으로 사용하여 호출한다.
	var printData = function(replyArr,target,templateObject){
		var template = Handlebars.compile(templateObject.html());
		var html = template(replyArr);
		$('.replyLi').remove();
		target.after(html);
	}
	
	
	//reply list
	function getPage(pageInfo){
	
		$.ajax({
			url:pageInfo,
			type:"get",
			success:function(dataMap){
				printData(dataMap.replyList,$('#repliesDiv'),$('#reply-list-template'));
				printPaging(dataMap.pageMaker,$('.pagination'));
			},
			error:function(xhr){
				alert("서비스 장애로 댓글 목록을 가져올 수 없습니다.")
			}
		});
	}
	
	var replyPage=1;
	
	//function은 호출하지 않으면 실행되지 않는다. 여기서는 화면에 보여주기위해 의도적으로 한번 호출한다.
	getPage("<%=request.getContextPath()%>/pms/reply/list?pbno=${param.no}&page="+replyPage);
	
	
	//reply pagination
	 var printPaging=function(pageMaker,target){
		
		var str="";
		
		if(pageMaker.prev){
			
			str+="<li class='page-item'><a class='page-link' href='"+(pageMekr.startPage-1)
				+"'> <i class='fas fa-angle-left'/> </a></li>";
			
		}
		for(var i=pageMaker.startPage; i <= pageMaker.endPage; i++){
			var strClass = pageMaker.cri.page == i ? 'active' : '';
			str+="<li class='page-item "+strClass+"'><a class='page-link' href='"+i
			+"'> "+i+" </a></li>";
			
		}
		if(pageMaker.next){
			
			str+="<li class='page-item'><a class='page-link' href='"+(pageMekr.nextPage+1)
				+"'> <i class='fas fa-angle-left'/> </a></li>";
			
		}
		
		target.html(str);
	}
	
	
	//다음 페이지로 넘어갈 때 
	//브라우저가 읽은 DOM에는 li가 추가되어 있지 않다. 그래서 deligate를 해줘야함.
	$('.pagination').on('click','li a',function(event){ 
		//alert("reply page click");
		event.preventDefault();
		
		replyPage=$(this).attr("href");
		getPage("<%= request.getContextPath()%>/pms/reply/list?pbno=${param.no}&page="+replyPage);
	});
	
	
	//regist reply
	$('#replyAddBtn').on('click',function(e){
		//alert('add reply btn');
		
		var writer=$('#newReplyWriter').val();
		var content=$('#newReplyText').val();
		
		if(content.trim().length == 0){
			alert("덧글 내용은 필수입니다.");
			$('#newReplyText').focus().css("border-color","red");
			return;
		}
		if(content.length > 200){
			alert("덧글은 200자 이내로 입력하세요.");
			$('#newReplyText').focus().css("border-color","red");
			return;
		}
		
		var data={
			"pbno":"${param.no}",
			"writer":writer,
			"content":content
		}
		
		$.ajax({
			url:"<%= request.getContextPath()%>/pms/reply/regist",
			type:"post",
			data:JSON.stringify(data),
			beforeSend : function(xhr){ /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
            },
			contentType:"application/json",	//보내는 data 형식 지정
			dataType:"text", //받는 data형식 지정
			
			success:function(data){
				alert("댓글이 등록되었습니다.")
				getPage("<%= request.getContextPath()%>/pms/reply/list?pbno=${param.no}&page="+data);
				$('#newReplyText').val("")	//답글 작성했던 곳 지우기
				
			},
			error:function(xhr){
				alert("댓글 등록이 불가합니다.")
			}
		})
		
	});
	
	
	//reply modify 권한체크
	$('div.timeline').on('click','#modifyReplyBtn',function(event){
		//로그인 사용자 확인
		var replyer=$(event.target).attr("data-replyer");
		if(replyer!="${loginUser.email}"){
			alert("수정 권한이 없습니다.")
			$(this).attr("data-toggle","");
		}
	});
	
	
	//수정창에 data 표시
	$('div.timeline').on('click','.replyLi',function(event){
		var reply=$(this);
		$('#replytext').val(reply.find('.timeline-body').text());
		$('.modal-title').html(reply.attr('data-rno'));
	});
	
	
	//modify 수정 이벤트
	$('#replyModBtn').on('click',function(event){
		
		var no = $('.modal-title').text();
		var content = $('#replytext').val();
		
		var data={
				no:no,
				content:content
		}
		
		$.ajax({
			url:"<%= request.getContextPath()%>/pms/reply/modify",
			type:"post",
			data:JSON.stringify(data),
			beforeSend : function(xhr){ /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
            },
			contentType:"application/json",
			success:function(result){
				alert("수정되었습니다.");
				getPage("<%= request.getContextPath()%>/pms/reply/list?pbno=${param.no}&page="+replyPage);
			},
			error:function(error){
				alert("수정 실패했습니다.")
			},
			complete:function(){	//success든 error든 마지막에 실행시킴. finally같은 구문.
				$('#modifyModal').modal('hide');
			}
		});
	});
	
	//delete 이벤트
	$(document).on('click', '#replyDelBtn', function(event){
		if (!confirm("댓글을 삭제하시겠습니까?")) {
			return;
		}
		
		var no = $(this).attr('data-no');
		
		var sendData={
				pbno:'${param.no}',
				no:no,
				replyPage:replyPage	//만약 지운게 11번째 덧글이면 페이지는 2 페이지고 덧글 내용은 없는 상태일 수 있음.
		};
		
		$.ajax({
			url:"<%= request.getContextPath()%>/pms/reply/remove",
			type:"post",
			data:JSON.stringify(sendData),
			beforeSend : function(xhr){ /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
            },
			contentType:"application/json",
			success:function(data){
				alert("댓글이 삭제되었습니다.")
				getPage("<%= request.getContextPath()%>/pms/reply/list?pbno=${param.no}&page="+data);
			},
			error:function(error){
				alert("삭제 실패했습니다.")
			}
		});
	}); 
});
</script>
