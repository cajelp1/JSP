<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>센서 데이터 셋팅</title>
</head>
<body>
	<span id="temp">${sensor.temperatured}</span>
	<span id="humi">${sensor.humidity}</span>
	<span id="distance">${sensor.distance}</span>
	<span id="roomNo">${sensor.roomNo}</span>
</body>
</html>