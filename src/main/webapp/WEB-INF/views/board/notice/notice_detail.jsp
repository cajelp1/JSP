<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>공지사항 ${notice.nno }번 상세보기</title>

<body>
	<!-- Page Path Begins -->
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li>
				<i class="ace-icon fa fa-comments-o home-icon bigger-150"></i>
					게시판
			</li>
			<li>
					공지사항
			</li>
			<li class="active">
					${notice.nno }
			</li>
		</ul>
		<%@ include file="/WEB-INF/views/commons/user_label.jsp" %>
	</div>
	<!-- Page Path Ends -->
	
	<!-- Page Content Begins -->
	<div class="page-content" style="padding-bottoms:8px;">
	
		<!-- 상단 우측버튼 -->
		<div class="row">
			<div class="wizard-actions">
				<c:if test="${loginUser.id eq notice.writer}">					
					<button type="button" class="btn btn-sm btn-white btn-bold"  onclick="modify_go();">
						<i class="red ace-icon fa fa-pencil bigger-120"></i><b>편집</b>
					</button>		
					<button type="button" class="btn btn-sm btn-white btn-bold" onclick="remove_go();" >
						<i class="red ace-icon fa fa-trash bigger-120"></i><b>삭제</b>
					</button>		
				</c:if>
				<button type="button" class="btn btn-sm btn-white btn-bold" onclick="javascript:docPrint('document');">
					<i class="grey ace-icon fa fa-print bigger-120"></i><b>인쇄</b>
				</button>
				<button type="button" class="btn btn-sm btn-white btn-bold" onclick="javascript:closeDoc();">
					<i class="grey ace-icon fa fa-times bigger-120"></i><b>닫기</b>
				</button>
			</div>
		</div>
		<!-- 상단 우측버튼 -->
		
<!-- <body style="margin:1px;"> -->
<!-- <div id="pageScroll" class="wrapper"> -->
<form role="form" id="registForm" class="form-horizontal" action="/upload" method="post" enctype="multipart/form-data">

	<div class="hr_line">&nbsp;</div>
	<div class="form-group">
		<label for="writer" class="col-xs-4 col-sm-2 control-label no-padding-right bolder g_label required">
		작성자</label>
		<div class="col-xs-8 col-sm-10 g_value" >
			<a href="#" class="maninfo" rel="${notice.writer }">			
				<input id="writer" name="writer" style="width:70%;" class="form-control required"  
					   type="text" readonly value="${notice.writer }"
					   style="background:#aaa;"/>			
			</a>
		</div>		
	</div>
	
	<div class="form-group" style="display:none;">
		<label for="dms.hotFlag" class="col-xs-4 col-sm-2 control-label no-padding-right bolder g_label"></label> 
			<div class="col-xs-8 col-sm-10 g_value"></div>
	</div>
	<div class="form-group openDate" style="display: visible;"><!-- 2018-01-18 게시기간 사용안함 -->
		<label for="dms.subject" class="col-xs-4 col-sm-2 control-label no-padding-right bolder g_label">
			게시기간
		</label>
		<div class="col-xs-8 col-sm-10 g_value">
			<input readonly id="openDate" name="startDate" style="color:#919191;" class="dateInput" readonly="readonly" type="text" value='<fmt:formatDate value="${notice.startDate }" pattern="yyyy-MM-dd" />'/>
				~
			<input  readonly id="closeDate" name="endDate" class="dateInput" readonly="readonly" type="text" value="<fmt:formatDate value="${notice.endDate }" pattern="yyyy-MM-dd" />"/>
			<div class="checkbox" style="display:inline;">
				&nbsp;
				<label>
					<input ${empty notice.endDate ? 'checked':'' }  
						readonly type="checkbox" class="ace" id="never" name="never"><span class="lbl">&nbsp;영구게시</span>
				</label>
			</div>
		</div>
	</div>	
	<div class="form-group bbsId" style="display:visible;">
		<label for="dms.subject" class="col-xs-4 col-sm-2 control-label no-padding-right bolder g_label">
		구&nbsp;&nbsp;&nbsp;분</label>
		<div class="col-xs-8 col-sm-10 g_value">
			<select id="point" name="point" disabled>				
				<option value="0" ${notice.point eq 0 ? 'selected':'' }>일반</option>
				<option value="1" ${notice.point eq 1 ? 'selected':'' }>중요</option>
			</select>
		</div>
	</div> 
	
	<div class="form-group">
		<label for="title" class="col-xs-4 col-sm-2 control-label no-padding-right bolder g_label required">
		제&nbsp;&nbsp;&nbsp;목</label>
		<div class="col-xs-8 col-sm-10 g_value">
			<input  readonly id="title" name="title" onkeydown="CheckTextCount(this, 100);" class="form-control required" required="required " type="text" value="${notice.title }"/>
		</div>
	</div>  
	<div class="form-group">
		<div id="content" class="col-xs-12 col-sm-12 g_value" >${notice.content }</div>			
	</div>  
	<br/>
	<div class="form-group">
		<ul class="mailbox-attachments clearfix uploadedList"></ul>
	</div>	
	
	
		
</form>

</div>

<form role="form" method="post">    
    <input type='hidden' name='nno' value ="${notice.nno}">    
</form>   



<%@ include file="/WEB-INF/views/board/notice/notice_detail_js.jsp" %>

<script>
	var ischecked = $('#never').is(":checked");
	if(ischecked){
		$("#closeDate").val( "-----" );
	}
</script>	
</body>







