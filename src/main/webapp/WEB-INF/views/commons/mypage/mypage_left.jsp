<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="columns">
		<ul class="vertical menu" data-accordion-menu style="max-width: 250px">
			<li>
				<a href="#">내 정보</a>
					<ul class="menu vertical nested">
						<li><a href="/commons/mypage">내 정보 조회</a></li>
						<li><a href="/commons/mypage/modify?email=${member.email }">내 정보 수정</a></li>
						<li><a href="/commons/mypage/modifyPassword?email=${member.email }"">비밀번호 변경</a></li>
						<li><a href="/commons/mypage/removeCheck">회원 탈퇴</a></li>
					</ul>
			</li>
			<li><a href="#">결제 내역</a>
				<ul class="menu vertical nested">
<!-- 					<li><a href="#"></a></li> -->
<!-- 					<li><a href="#">Item 2B</a></li> -->
				</ul></li>
		</ul>
	</div>
</div>