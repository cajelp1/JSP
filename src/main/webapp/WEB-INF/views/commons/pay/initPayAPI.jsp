<%--
  Created by IntelliJ IDEA.
  User: ruddn
  Date: 2020-06-10
  Time: 오전 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결제</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<script type="text/javascript">
    var IMP = window.IMP; // 생략가능
    IMP.init('imp88502309'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    IMP.request_pay({
        pg : 'inicis', // version 1.1.0부터 지원.
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : '회의실결제',
        amount : 100,
        buyer_email : '${paymentUserEmail}',
        buyer_name : '${paymentUserName}',
        buyer_tel : '${paymentUserPhoneNum}',
        buyer_addr : '서울특별시 강남구 삼성동',
        buyer_postcode : '123-456',
        m_redirect_url : '/commons/pay/resultPayment'
    }, function(rsp) {
        if ( rsp.success ) {
            alert(rsp);
           location.href="/commons/pay/resultPayment";

        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            alert(msg);
        }
    });
</script>
</body>
</html>
