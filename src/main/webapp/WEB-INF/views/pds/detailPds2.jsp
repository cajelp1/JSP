<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<body>
	<c:set var="attachList" value="${pds.attachList }"></c:set>
	<!-- Content Wrapper. Contains page content -->
  	<div  style="max-width:800px;min-width:420px;margin:0 auto;min-height:812px;">
	
   	<jsp:include page="content_header.jsp">
	   	<jsp:param value="자유게시판" name="subject"/>
	   	<jsp:param value="list.do" name="url"/>
	   	<jsp:param value="상세보기" name="item"/>
   </jsp:include>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card-header">
					<h3 class="card-title">상세보기</h3>
				</div>
				<div class="card-body">
					<div class="form-group col-sm-12">
						<label for=title>제 목</label>
						<input type="text" class="form-control" id="title" name="title" 
						value="${pds.title }" readonly/>
					</div>
					<div class="row">
						<div class="form-group col-sm-5">
							<label for=writer>작성자</label>
							<input type="text" class="form-control" id="writer" name="writer" 
							value="${pds.writer }" readonly/>
						</div>
						<div class="form-group col-sm-5">
							<label for=regDate>작성일</label>
							<input type="text" class="form-control" id="regDate" name="regDate" 
							value="<fmt:formatDate value="${pds.regDate }" pattern="yyyy-MM-dd" />" readonly/>
						</div>
						<div class="form-group col-sm-2">
							<label for="viewcnt">조회수</label>
							<input type="text" class="form-control" id="viewcnt" name="viewcnt" 
							value="${pds.viewcnt }" readonly/>
						</div>
					</div><!-- .row end -->
					<div class="form-group col-sm-12">
						<label for="content">내 용</label>
						<div id="content">${pds.content }</div>
					</div>
					<div class="form-group col-sm-12">
						<c:if test="${loginUser.id eq pds.writer }">
						<button type="button" id="modifyBtn" class="btn btn-warning">MODIFY</button>						
					    <button type="button" id="removeBtn" class="btn btn-danger">REMOVE</button>
					    </c:if>
					    <button type="button" id="listBtn" class="btn btn-primary">CLOSE</button>
					</div>
				</div><!-- .card-body end -->
				<div class="card-footer">
					<c:if test="${attachList ne null }">
						<c:forEach var="attachVO" items="${attachList }" step="1">
						
						<div>${attachVO.fileName}</div>
						
						</c:forEach>
					</c:if>
				</div><!-- .card-footer -->
			</div><!-- .col-md-12 end -->
		</div><!-- .row end -->
	</section><!-- main section end -->
	</div><!-- content wrapper -->
	
	<form role="form">
  	<input type='hidden' name='pno' value ="${pds.pno}">
  	<c:if test="${attachList ne null }">
		<c:forEach var="attachVO" items="${attachList }" step="1">
			<input type='hidden' name='uploadPath' value ="${attachVO.uploadPath}">
		</c:forEach>
	</c:if>
  	<input type='hidden' name='page' value ="${param.page}">
    <input type='hidden' name='perPageNum' value ="${param.perPageNum}">
    <input type='hidden' name="searchType" 
		         value="${param.searchType }" />
	<input type='hidden' name="keyword" 
		         value="${param.keyword }" />
  </form>
	
	
<script>
	
	var formObj = $("form[role='form']");

	$('button#modifyBtn').on('click',function(evnet){
		//alert('modify btn click');
		formObj.attr({
			'action':'modifyForm.do',
			'method':'post'
		});
		formObj.submit();
	});
	
	$("#removeBtn").on("click", function(){
		var answer = confirm("정말 삭제하시겠습니까?");
		if(answer){		
			formObj.attr("action", "remove.do");
			formObj.attr("method", "post");
			formObj.submit();
		}
	});
	
	$("#listBtn").on("click", function(){
		window.opener.location.reload(true);
		window.close();
	});
	
	
	
</script>
	
	
	
</body>





