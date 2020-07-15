<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
alert("결제가 취소되었습니다.")
window.location.href="<%=request.getContextPath()%>/dsp/admin/payment/detail?reservNo="+"${param.reservNo}";
</script>