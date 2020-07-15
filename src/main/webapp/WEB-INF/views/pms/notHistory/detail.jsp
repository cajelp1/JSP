<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
div.a:hover {
  overflow: visible;
}
</style>
<div class="border m-3 shadow">
	<div class="bg-info p-2 text-right border-bottom">
		<span class="float-left font-weight-bold"></span>
	</div>
	
	<!-- <div class="border m-2"> -->
	<div class="row m-0 border-bottom">
		<div class="col border-right p-3 my-auto" id="title"><h4>${detail.title}</h4></div>
		<div class="col-3 my-auto" >
			<div class="p-1 font-weight-bold" id="writer" style="white-space: nowrap; overflow: hidden; text-overflow:ellipsis;">${detail.senderNickName}</div>
			<div class="p-1 text-sm" id="viewCnt">
				<fmt:formatDate value="${detail.regDate}" pattern="yy-MM-dd HH:mm"/>&nbsp;
			</div>
		</div>
	</div>
	<div class="row m-0 p-3" style="min-height:250px;">
		${detail.content}
    </div>
</div>