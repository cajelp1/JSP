<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%-- <title><tiles:getAsString name="title"/></title> --%>

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
</head>



<body>
	<div style="width: 100%; height: 100%">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
<!-- 		<div id="left"> -->
<%-- 			<tiles:insertAttribute name="left" /> --%>
<!-- 		</div> -->
		<div id="body">
			<tiles:insertAttribute name="body" />
		</div>
<!-- 		<div id="footer"> -->
<%-- 			<tiles:insertAttribute name="footer" /> --%>
<!-- 		</div> -->
	</div>
</body>


<!-- jQuery -->
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap -->                                   
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- jQuery UI -->                                   
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- AdminLTE App -->                                
<script src="<%=request.getContextPath()%>/resources/pms/bootstrap/dist/js/adminlte.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<script src="<%=request.getContextPath()%>/resources/pms/js/common.js"></script>

</html>