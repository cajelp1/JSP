<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<body>
<style type="text/css">
.blockquote-content{
	font-size: x-large;
}
</style>
<div class="container">
<h4 class="text-center">회원탈퇴</h4>
	<div class="row justify-content-center">
		<div class="col">
			<div class="card row justify-content-center" style="width: 36rem;">
			  <div class="card-body">
			    <p class="card-text">
					회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해주세요.<br>
					
					사용하고 계신 아이디("<span style="color: blue;">${member.email }</span>")는 탈퇴할 경우 재사용 및 복구가 불가능합니다.<br>
					<span style="color: red;">탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구</span>가 불가하오니 신중하게 선택하시기 바랍니다.<br>
					
					탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.<br>
					서비스 이용기록은 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.<br>
					삭제되는 내용을 확인하시고 필요한 데이터는 미리 백업을 해주세요.<br>
					
					<span style="color: red;">탈퇴 후에는 아이디 <span style="color: blue;">${member.email }</span> 로 다시 가입할 수 없으며 아이디와 데이터는 복구할 수 없습니다.</span><br>
					</p>
			  </div>
			</div>
<%-- 				<h4 class="blockquote-title" style="text-align: center;">탈퇴 안내</h4>
				<blockquote>
					<p class="blockquote-content">
										회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해주세요.<br>
					
					사용하고 계신 아이디("<span style="color: blue;">${member.email }</span>")는 탈퇴할 경우 재사용 및 복구가 불가능합니다.<br>
					<span style="color: red;">탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구</span>가 불가하오니 신중하게 선택하시기 바랍니다.<br>
					
					탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.<br>
					서비스 이용기록은 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.<br>
					삭제되는 내용을 확인하시고 필요한 데이터는 미리 백업을 해주세요.<br>
					
					<span style="color: red;">탈퇴 후에는 아이디 <span style="color: blue;">${member.email }</span> 로 다시 가입할 수 없으며 아이디와 데이터는 복구할 수 없습니다.</span><br>
					</p>
				</blockquote> --%>

			<button type="button" class="button" onclick="remove()">확인</button>
	</div>
	</div>
</div>
<script>
	function remove(){
		var input = prompt('안내 사항을 모두 확인하였으며, 이에 동의합니다.\n탈퇴를 진행하려면"탈퇴한다"를 입력해주세요');
		if(input != '탈퇴한다'){
			if(!input){
				alert('탈퇴를 취소하였습니다.');
				return;
			}
			alert('"탈퇴한다."를 올바르게 입력해주세요.');
			return;
		}
		location.href="<%=request.getContextPath() %>/dsp/my/removeMember?email=${member.email}";
	}
</script>
</body>
</html>