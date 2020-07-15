<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
	<div class="container">
	<input id="projectId" type="hidden" value="${schedule.projectId }" />
	<div class="card card-primary">
		<div class="card-header">
			<h3 class="card-title">업무</h3>
			<div class="card-tools">
				<button type="button" class="btn btn-tool"
					data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
					<i class="fas fa-minus"></i>
				</button>
			</div>
		</div>
		<div class="card-body">
			<div class="form-group">
				<div class="row">
					<div class="col-2">
						<label for="inputName" class="label-right">업무</label>
					</div>
					<div class="col-10">
						<div id="inputName" class="form-control">${schedule.title}</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-2"  class="label-right" <%-- style="background: #89CFF0;border-radius:10px;" --%>> 
						<label for="date" class="label-right">날짜</label>				
					</div>
					<div class="col-10">
						<div id="date" class="form-control">
							<fmt:formatDate value="${schedule.startDate}" pattern="yyyy-MM-dd (EE)" /> ~ <fmt:formatDate value="${schedule.endDate}" pattern="yyyy-MM-dd (EE)" />
						</div>
					</div>
				</div>
			</div>
<%-- 			<div class="form-group">
				<div class="row">
					<div class="col-2">
						<label for="inputAttach">첨부파일</label>
					</div>
					<div class="col-10">
						<div id="inputAttach" class="form-control">첨부파일</div>
						첨부파일
					</div>
				</div>
			</div>--%>
			<div class="form-group">
				<div class="row">
					<div class="col-2">
						<label for="inputProgress" class="label-right">진행상황</label>
					</div>
					<div class="col-10">
						<div class="progress progress-sm">
                            <%-- <div class="progress-bar progress-bar-striped bg-green" role="progressbar" aria-volumenow="${schedule.processPercent}" aria-volumemin="0" aria-volumemax="100" style="width: ${schedule.processPercent}%">
                            </div> --%>
                            <c:if test="${schedule.processPercent eq 100 }">
								<div class="progress-bar progress-bar-striped bg-success" style="width: ${schedule.processPercent}%"></div>
							</c:if>
                        	<c:if test="${schedule.processPercent < 100}">
		                        <c:if test="${schedule.processPercent < 50}">
	                        		<div class="progress-bar progress-bar-striped bg-warning" style="width: ${schedule.processPercent}%"></div>
		                        </c:if>
		                        <c:if test="${schedule.processPercent > 50}">
	                          		<div class="progress-bar progress-bar-striped progress-bar-danger" style="width: ${schedule.processPercent}%"></div>
		                        </c:if>
                        </c:if>
                       	</div>
						<small>${schedule.processPercent}% 완료</small>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-2">
						<label for="inputProgress" class="label-right">내용</label>
					</div>
					<div class="col-10">
						${schedule.content }
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-2">
						<label for="inputAttach" class="label-right">처리</label>
					</div>
					<div class="col-10">
						<div class="row">
							<div class="col-3">
								<button type="button" class="btn btn-block btn-success" onclick="approveCompletion('${schedule.no}')"><i class="fa fa-user-check"></i>승인</button>
							</div>
							<div class="col-3">
								<button type="button" class="btn btn-block btn-danger" onclick="requestSchedule('${schedule.no}')"><i class="fa fa-user-times"></i>재요청</button>
							</div>
							<div class="col-3">
								<button type="button" class="btn btn-block btn-danger" onclick="modifySchedule('${schedule.no}')"><i class="fa fa-user-times"></i>수정</button>
							</div>
							<div class="col-3">
								<button type="button" class="btn btn-block btn-danger" onclick="removeSchedule('${schedule.no}')"><i class="fa fa-user-times"></i>삭제</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function modifySchedule(no){
		if(confirm('이 업무를 수정하시겠습니까?')){
			window.location.href="<%=request.getContextPath()%>/pms/admin/task/modify?no="+no			
		}
	}
	
	function removeSchedule(pk){
		if(confirm('이 업무를 삭제하시겠습니까?')){
			$.ajax({
				url:"<%=request.getContextPath()%>/pms/admin/task/remove?no="+pk,
				type : "post",
				beforeSend : function(xhr){ /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                    xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
                },
                data : {
                	no : pk
                },
				success:function(data){
					alert('삭제 처리가 완료되었습니다.');
					window.opener.location.reload();
					window.close();
				},
				error:function(xhr){
					alert('잠시후에 다시 시도하십시오.');
					console.log(xhr.status);
				}
			})
		}
	}

	function approveCompletion(pk){
		if(confirm('이 업무를 완료처리 하시겠습니까?')){
			$.ajax({		
				url:"<%=request.getContextPath()%>/pms/admin/task/approve",
				type : "post",
				beforeSend : function(xhr) { 
                    xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
                },
				data:{
					no : pk,
					projectId : ${schedule.projectId}
				},
				success:function(data){
					alert('업무 완료 처리하였습니다.');
					window.opener.location.reload();
					window.close();
				},
				error:function(xhr){
					alert('잠시후에 다시 시도하십시오.');
				}
			});
		} else {
			alert('취소하였습니다.');
		}
	}
	function requestSchedule(pk){
		if(confirm('이 업무를 담당자에게 재요청하시겠습니까?')){
			
			// 사유 썼는지 유효성 체크
			
			var reason = prompt("재요청 사유를 입력해주세요.");

			if(reason == null || reason == ""){
				alert("사유가 비어있습니다. 다시입력해주세요.")
				return;
			}
			
			$.ajax({
				url:"<%=request.getContextPath()%>/pms/admin/task/request",
				type : "post",
				beforeSend : function(xhr) { 
                    xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
                },
				data:{
					no : pk,
					projectId : ${schedule.projectId},
					"reason":reason
				},
				success:function(data){					
					alert('재요청 처리를 완료하였습니다.');
					window.opener.location.reload();
					window.close();
				},
				error:function(xhr){		
					
					alert('잠시후에 다시 시도하십시오.');
				}
			});
		} else {
			alert('취소하였습니다.');
		}
	}
</script>
<%-- <%@ include file="../include/summernote_js.jsp" %> --%>
</body>