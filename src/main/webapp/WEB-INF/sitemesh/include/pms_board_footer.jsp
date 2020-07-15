<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- footer용 패딩 -->
<div>
    &nbsp;<br>
    &nbsp;<br>
    &nbsp;<br>
    &nbsp;<br>
    &nbsp;<br>
    &nbsp;<br>
    &nbsp;<br>
</div>

<!-- main footer -->
<footer class="main-footer">
    <div class="container">
        <!-- <div class="main-footer row">
            <br>
            <br>
            <br>
        </div> -->
        <div class="main-footer ">
            <div class="main-footer row">
                <div class="col-sm-4 m-auto">
                    <img src="<%=request.getContextPath() %>/resources/site/img/dsp-logo.png" style="height:50px;">
                </div>
                <div class="main-footer col-sm-8 text-right small">
                    <p class="footer-context m-2">
                        <a href="#">이용안내</a>&nbsp;/&nbsp;
                        <a href="#">정보처리약관</a>&nbsp;/&nbsp;
                        ㈜디에스프로젝트
                        <br> 사업자등록번호 : 306 82 05291 / 대표이사 김령환 <br> 대전광역시 중구 중앙로
                        76 영민빌딩 205호 <br> ©2020. DSPROJECT Corp
                    </p>
                </div>
            </div>
        </div>
    </div>
</footer>
<style>
    .main-footer{
        background-color: #1E90FF;
    }
    .footer-context{
        color: white;
    }
    .main-footer a{
        color: white;
    }


    /* footer위치를 위한 css */
    body{
        position: relative;
        min-height: 100vh;
    }
    footer {
        position: absolute;
        bottom: 0;
        width: 100%;
    }
</style>
<%@ include file="../Alarm_js.jsp"%>
<%@ include file="summernote.jsp" %>
