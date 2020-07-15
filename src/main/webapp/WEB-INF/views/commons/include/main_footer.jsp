<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style>

.menu-hover-lines {
	text-align: center;
	text-transform: uppercase;
	font-weight: 500;
	letter-spacing: 1px;
	transition: all 0.35s ease;
}

.menu-hover-lines li a {
	padding: 0.75rem 0;
	color: rgba(10, 10, 10, 0.5);
	position: relative;
	margin-left: 1rem;
}

.menu-hover-lines li:first-child a {
	margin-left: 0;
}

.menu-hover-lines li.active>a {
	background-color: transparent;
}

.menu-hover-lines a:before, .menu-hover-lines a::after {
	height: 3px;
	position: absolute;
	content: '';
	transition: all 0.35s ease;
	background-color: #1779ba;
	width: 0;
}

.menu-hover-lines a::before {
	top: 0;
	left: 0;
}

.menu-hover-lines a::after {
	bottom: 0;
	right: 0;
}

.menu-hover-lines a:hover, .menu-hover-lines li.active>a {
	color: #0a0a0a;
	transition: all 0.35s ease;
}

.menu-hover-lines a:hover::before, .menu-hover-lines .active a::before,
	.menu-hover-lines a:hover::after, .menu-hover-lines .active a::after {
	width: 100%;
}
</style>
<body>
	<div class="row"><br><br><br>
	</div>
	<div class="callout large secondary">
      <div class="row">
      	<div class="large-4 columns">
      	<img src="https://picsum.photos/400/100">
      	</div>
        <div class="large-8 columns text-right" >
          <h5>Vivamus Hendrerit Arcu Sed Erat Molestie</h5>
          <p>㈜디에스프로젝트 / 사업자등록번호 : 306 82 05291 / 대표이사 김령환 <br>
          	대전광역시 중구 중앙로 76 영민빌딩 205호 ©2020. DSPROJECT Corp</p>
        </div>
      </div>
    </div>

</body>
</html>