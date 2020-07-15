<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
<!-- bootstrap colorPicker -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
<!-- Select2  -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/select2/css/select2.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<!-- jQuery -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
</head>
<body>
	<div style="width: 100%; height: 100%">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="body">
			<tiles:insertAttribute name="body" />
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>

<!-- Bootstrap -->                                   
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- jQuery UI -->                                   
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- AdminLTE App -->                                
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>
<!-- bootstrap ColorPicker -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- datePicker Korean Setting -->
<script src="<%=request.getContextPath()%>/resources/pms/js/datepicker-ko.js"></script>
<!-- Select 2 -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/select2/js/select2.full.min.js"></script>

<script src="<%=request.getContextPath()%>/resources/pms/js/common.js"></script>

</html>