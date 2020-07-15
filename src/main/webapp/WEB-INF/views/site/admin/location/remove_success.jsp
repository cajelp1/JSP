<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
alert("상태 변경이 완료되었습니다")
self.location="<%=request.getContextPath()%>/dsp/admin/location/detail/"+${roomNo}
</script>