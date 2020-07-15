<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	$('#cancel').on('click',function(){
		var confirms = confirm("결제를 취소하시겠습니까?")
		if(confirms){
			window.location.href="<%=request.getContextPath()%>/dsp/admin/payment/modify?reservNo="+"${param.reservNo}";
		}
	});
</script>