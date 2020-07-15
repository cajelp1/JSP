
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body style="width: 700px;height: 700px;">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
    var pricePerDay=${pricePerDay};
    var diffDay=${diffDay};
    var IMP = window.IMP; // 생략가능
    IMP.init('imp88502309'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    IMP.request_pay({
        pg : 'inicis', // version 1.1.0부터 지원.
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : '회의실 대여료',
        amount : pricePerDay*diffDay
    }, function(rsp) {
        if ( rsp.success ) {
            var msg = '결제가 완료되었습니다.';
            location.href='/dsp/room/resultPayment?startDate=${startDate}&&endDate=${endDate}&&roomNumber=${roomNumber}&&projectId=${projectId}&&amount='+${pricePerDay}*${diffDay};
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
</script>
</body>
</html>
