<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- foundation-float.min.css: Compressed CSS with legacy Float Grid -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.6.3/dist/css/foundation-float.min.css"
	integrity="sha256-4ldVyEvC86/kae2IBWw+eJrTiwNEbUUTmN0zkP4luL4="
	crossorigin="anonymous">

<!-- foundation-prototype.min.css: Compressed CSS with prototyping classes -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.6.3/dist/css/foundation-prototype.min.css"
	integrity="sha256-BiKflOunI0SIxlTOOUCQ0HgwXrRrRwBkIYppEllPIok="
	crossorigin="anonymous">

<!-- foundation-rtl.min.css: Compressed CSS with right-to-left reading direction -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/foundation-sites@6.6.3/dist/css/foundation-rtl.min.css"
	integrity="sha256-F+9Ns8Z/1ZISonBbojH743hsmF3x3AlQdJEeD8DhQsE="
	crossorigin="anonymous">
</head>
<style>
/*icon styles*/
.fi-social-facebook {
	color: dodgerblue;
	font-size: 2rem;
}

.fi-social-youtube {
	color: red;
	font-size: 2rem;
}

.fi-social-pinterest {
	color: darkred;
	font-size: 2rem;
}

i.fi-social-instagram {
	color: brown;
	font-size: 2rem;
}

i.fi-social-tumblr {
	color: navy;
	font-size: 2rem;
}

.fi-social-twitter {
	color: skyblue;
	font-size: 2rem;
}

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
	<!-- Top bar end -->
	<div class="top-bar" id="mainNavigation">
		<!-- 로고랑 사이트 이름 넣기 -->
		
		<!--       <div class="top-bar-left"> -->
		<!--         <ul class="menu vertical medium-horizontal"> -->
		<!--           <li class="menu-text hide-for-small-only">Marketing Site</li> -->
		<!--           <li><a href="#">One</a></li> -->
		<!--           <li><a href="#">Two</a></li> -->
		<!--         </ul> -->
		<!--       </div> -->
		<div class="top-bar-right">
			<ul class="menu vertical medium-horizontal">
				<li><a href="/commons/login">로그인 / 회원가입</a></li>
				<li><a href="#">프로젝트실 찾기</a></li>
			</ul>
		</div>
	</div>
	<!-- Top bar end -->
	
	<!-- main menu -->
	<div class="top-bar align-center" id="main-menu">
		<ul class="menu menu-hover-lines">
			<li class="active"><a href="/">Home</a></li>
			<li><a href="#">사이트 소개</a></li>
			<li><a href="#">이용안내</a></li>
			<li><a href="#">장소찾기</a></li>
			<li><a href="#">프로젝트 만들기</a></li>
		</ul>
	</div>
		<a class="button large" href="#" >PMS 시작하기</a>
	<!-- main menu end -->

</body>
</html>