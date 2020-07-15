<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
$('select').on('change',function(){
	$('#role').val($(this).val());
})

function submit(){
	var form = $('#form');
	form.submit();
}

function cancel(){
	window.close();
}
</script>