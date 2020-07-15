<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>대시보드</title>

<!-- Font Awesome -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/fontawesome-free/css/all.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/css/adminlte.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<!-- jQuery UI -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.structure.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.theme.min.css">
<!-- 	<script  src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	<!-- bootstrap colorPicker -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- default-header.css 스타일 수정할거면 여기서 할것! -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/css/default-header.css">
</head>



<body>
	<div style="width: 100%; height: 100%">
		

		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="body">
			<tiles:insertAttribute name="body" />
		</div>

	</div>
</body>



<!-- Bootstrap -->                                   
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- jQuery UI -->                                   
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- AdminLTE App -->                                
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<script src="<%=request.getContextPath()%>/resources/pms/js/common.js"></script>

</html>