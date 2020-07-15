<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
div.a:hover {
  overflow: visible;
}
</style>
<div class="border m-3 shadow">
	<div class="card-top p-2 text-right border-bottom">
		<h2 class="card-title p-1" style="margin-top:4px;">
			<i class="fa fa-file"></i>
			자료실
		</h2>
<%-- 		<span class="float-left font-weight-bold">${proBoardVO.boardName }</span> --%>
		<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="modify();"><i class="fa fa-edit"></i>&nbsp;&nbsp;수정</button>
		<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="remove();"><i class="fa fa-trash"></i>&nbsp;&nbsp;삭제</button>
		<button type="button" class="btn btn-sm bg-white shadow-sm" onclick="popUpClose();"><i class="far fa-window-close"></i>&nbsp;&nbsp;닫기</button>
	</div>
	
	<!-- <div class="border m-2"> -->
		<div class="row m-0 border-bottom">
			<div class="col border-right p-3 my-auto" id="title"><h4>${proBoardVO.title}</h4></div>
			<div class="col-3 my-auto" >
				<div class="p-1 font-weight-bold" id="writer" style="white-space: nowrap; overflow: hidden; text-overflow:ellipsis;">${member.nickname}</div>
				<div class="p-1 text-sm" id="viewCnt">
				<fmt:formatDate value="${proBoardVO.regDate}" pattern="yy-MM-dd HH:mm"/><br>
				조회 ${proBoardVO.viewCnt}
				</div>
			</div>
		</div>
		<div class="row m-0 p-3" style="min-height:250px;">
            ${proBoardVO.content}
        </div>
        
	<c:if test="${boardMaster.fileYn eq 'y'}">
	<c:if test="${proBoardVO.fileOpenYn eq 'y'}">
        <div class="row m-0 bg-light">
			<c:forEach items="${attachFileList}" var="file">
		        <div class="col-md-4 col-sm-4 col-xs-12"  style="cursor:pointer;"
					onclick="download(${file.no});">
					<div class="info-box m-2">	
						<span class="info-box-icon bg-yellow">
							<i class="fa fa-copy"></i>
						</span>
						<div class="info-box-content" style="white-space: nowrap; overflow: hidden; text-overflow:ellipsis;">
							<span class ="info-box-text" style="white-space: nowrap; overflow: hidden; text-overflow:ellipsis;">
								<fmt:formatDate value="${file.regdate}" pattern="yyyy-MM-dd"/>
							</span>
							<span class ="info-box-number" style="overflow: hidden; text-overflow:ellipsis;">${file.originFilename }</span>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	</c:if>
    <!-- </div> -->
</div> <!-- .container -->

<!-- 아래 c:if 부터 댓글 관련 태그 -->
	<c:if test="${boardMaster.replyYn eq 'y'}">
		<!-- reply component start --> 
	<div class="border m-3 shadow">
		<!-- <div class="row">
			<div class="col"> -->
				<!-- <div class="card card-info"> -->					
					<!-- <div class="card-body"> -->
						<!-- The time line -->
						<div class="timeline m-0">
							<!-- timeline time label -->
							<div class="card-top time-label p-2 mb-10 font-weight-bold" id="repliesDiv" style="margin-right:0px;">
								댓글 목록
							</div>
						</div>
							<div class='text-center mb-2'>
								<ul id="pagination" class="pagination justify-content-center m-0">
								
								</ul>
							</div>
					<!-- </div> -->
					<c:if test="${loginUser ne null}">
					<div class="input-group p-2 bg-light">
						<input class="form-control" type="hidden" placeholder="USER ID" 
							   id="newReplyWriter" readonly value="${loginUser.email }"> 
						<input class="form-control" type="text"	placeholder="덧글을 200자 이내로 입력하세요" 
						       id="newReplyText">
						<div class="input-group-append">
							<button type="button" class="btn btn-outline-secondary" id="replyAddBtn">댓글 등록</button>
						</div>
					</div>
					</c:if>
				<!-- </div> -->
				
			<!-- </div> --><!-- end col-md-12 -->
		<!-- </div> --><!-- end row -->
	
		<!-- Modal -->
		<div id="modifyModal" class="modal modal-default fade" role="dialog">
		  <div class="modal-dialog">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title" style="display:none;"></h4>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>        
		      </div>
		      <div class="modal-body" data-rno>
		        <p><input type="text" id="replytext" class="form-control"></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-info" id="replyModBtn">수정</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
	</c:if>
<!-- 댓글 태그 종료 -->
<%@ include file="./reply_js.jsp" %>


<script type="text/javascript">
    function download(fileNo) {
        if (confirm("파일을 다운로드 하실건가요?")) {
        	location.href="${pageContext.request.contextPath}/pms/project/downloadFile?no="+fileNo;
        } else {
			return;
        }
    }
    
    function modify() {
        location.href="${pageContext.request.contextPath}/pms/team/pds/modify?no=${proBoardVO.no}&projectId=${projectId}";
    }
    function remove(){
    	if(confirm('이 글을 삭제하시겠습니까?')){
    		location.href="${pageContext.request.contextPath}/pms/team/pds/remove?no=${proBoardVO.no}";    		
    	}
    }
    function popUpClose() {
        self.close();
        opener.document.location.reload();
    }
</script>
