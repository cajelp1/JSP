<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<body>
<c:set var="pageMaker" value="${dataMap.pageMaker }" />	
  <div class="content-wrapper" >
  
  	<!-- Content Header (Page header) -->
    <section class="content-header">
    	<div class="container-fluid">
    		<div class="row mb-2">
    			<div class="col-sm-6">
	      			<h1>자유게시판</h1>
	      		</div>
	       		<div class="col-sm-6">
			      	
		      	</div>
	     	</div>
      	</div>
    </section>
    <section class="content">
		<div class="col-12">
            <div class="card">
              <div class="card-header">
                <button type="button" class="btn btn-primary" id="registBtn" onclick="OpenWindow('registForm.do','자료등록',600,400)">자료등록</button>
				<div id="keyword" class="card-tools" style="width:300px;">	
					<div class="input-group row">
						<select class="form-control col-4" name="searchType" id="searchType">
							<option ${pageMaker.cri.searchType eq 'tcw' ? 'selected' : '' } value="tcw">전체</option>
							<option ${pageMaker.cri.searchType eq 't' ? 'selected' : '' } value="t">제 목</option>
							<option ${pageMaker.cri.searchType eq 'w' ? 'selected' : '' } value="w">작성자</option>
						</select>
						<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${pageMaker.cri.keyword }"/>
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					</div>						
				</div>
              </div>
              <!-- /.card-header -->
              <div class="card-body table-responsive p-0" >
                <table class="table table-head-fixed text-nowrap">
                  <thead>
                    <tr>
						<th style="width:10%;">번 호</th>
						<th style="width:50%;">제 목</th>
						<th style="width:5%;">첨 부</th>
						<th style="width:15%;">작성자</th>
						<th>등록일</th>
						<th style="width:10%;">조회수</th>
					</tr>
                  </thead>
                  <tbody>
                  	<c:if test="${empty dataMap.boardList }">
						<tr>
							<td colspan="6">
								<strong>해당 내용이 없습니다</strong>
							</td>
						</tr>
					</c:if>
					
                    <c:forEach items="${dataMap.boardList }" var="board">
						<tr>
							<td>${board.bno }</td>
							<td id="boardTitle" style="text-align:left;max-width:100%; overflow:hidden; white-space:nowrap; text-overflow:elipsis;"> 
							<a href="javascript:OpenWindow('detail.do?bno=${board.bno }','상세보기',600,400);">
								<span class="col-sm-12">${board.title }
									<c:if test="${board.replycnt ne 0 }">		
										<span class="nav-item">															
										&nbsp;&nbsp;<i class="fa fa-comment"></i>
										<span class="badge badge-warning navbar-badge">${board.replycnt}</span>
										</span>
										
									</c:if>
								</span>
							</a>
							</td>
							<td>${fn:length(board.attachList)}
							</td>
							<td>${board.writer }</td>
							<td>
								<fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd"/>
							</td>
							<td><span class="badge bg-red">${board.viewcnt }</span></td>
						</tr>
					</c:forEach>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
              <div class="card-footer">
				<nav aria-label="pds list Navigation">
					<ul class="pagination justify-content-center m-0">
						<%@ include file="/WEB-INF/views/pagination/pagination.jsp" %>
					</ul>
				</nav>
			</div><!-- /.card-footer -->
            </div>
            <!-- /.card -->
          </div>
          
          
	</section><!-- /.content -->
  </div><!-- /.content-wrapper -->
  
  <form id="jobForm">
		  <input type='hidden' name="page" value="${pageMaker.cri.page}" />
		  <input type='hidden' name="perPageNum" 
		  		 value="${pageMaker.cri.perPageNum}"/>
		  <input type='hidden' name="searchType" 
		         value="${pageMaker.cri.searchType }" />
		  <input type='hidden' name="keyword" 
		         value="${pageMaker.cri.keyword }" />
  </form>

<script>
	$('#searchBtn').on('click',function(e){
		var jobForm = $('#jobForm');
		
		jobForm.find("[name='page']").val(1);
		jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
		jobForm.find("[name='keyword']").val($('input[name="keyword"]').val());
		
		jobForm.attr('action','list.do').attr('method','get')
		jobForm.submit();
	});
</script>




</body>