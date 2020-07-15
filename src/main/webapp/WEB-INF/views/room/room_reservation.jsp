<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body class="sidebar-mini" style="height: auto;">
<div class="wrapper">
    <div class="content-wrapper" style="min-height: 500px;">
		<div class="wrapper">
            <H1>예약하기</H1>
            <form role="form" id="reservation" method="post" action="/dsp/room/reservation.do">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <input type="hidden" id="meetingNo" name="no" value="${no}"/>

                <div class="card-body">
                    <div class="form-group">
                        <label for="projectName">프로젝트 이름 </label>
                        <select name="projectId" id="projectName">
                            <option name="" selected></option>
                            <c:forEach items="${projectList}" var="pro">
                                <option value="${pro.projectId}">${pro.projectName}</option>
                            </c:forEach>
                        </select>

                    </div>
                    <div class="form-group">
                        <label for="fromDate">예약시작일</label>
                        <input type="text" id="fromDate" name="startDate" class="datepicker" autocomplete="off"
                               placeholder="예약시작일"/>
                    </div>
                    <div class="form-group">
                        <label for="toDate">예약종료일</label>
                        <input type="text" id="toDate" name="endDate" class="datepicker" autocomplete="off"
                               placeholder="예약종료일">
                    </div>

                    <div class="card-footer">
                        <button type="submit" class="btn btn-primary" >예약하기</button>
                    </div>
            </form>
        </div>
        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">회의실 예약정보</h3>
                </div>
                <div class="card-body">
                    <div id="jsGrid1" class="jsgrid" style="position: relative; height: 100%; width: 100%;">
                        <div class="jsgrid-grid-header jsgrid-header-scrollbar">
                            <table class="jsgrid-table" id="reservationTb">
                                <tr class="jsgrid-header-row">
                                    <th class="jsgrid-header-cell jsgrid-header-sortable" style="width: 150px;">방번호
                                    </th>
                                    <th class="jsgrid-header-cell jsgrid-align-right jsgrid-header-sortable"
                                        style="width: 200px;">예약시작일
                                    </th>
                                    <th class="jsgrid-header-cell jsgrid-header-sortable" style="width: 200px;">
                                        예약종료일
                                    </th>
                                </tr>
                            </table>
                        </div>
                        <div class="jsgrid-grid-body" style="height: 300px;">
                            <table class="jsgrid-table" id="aa">
                                <tbody>
                                <c:forEach items="${reservationHistory}" var="res">
                                    <tr class="jsgrid-row">
                                        <td class="jsgrid-cell" style="width: 150px;" id="no">${res.NO}</td>
                                        <td class="jsgrid-cell jsgrid-align-right startDate"
                                            style="width: 200px;" id="statDate"><fmt:formatDate
                                                value="${res.STARTDATE}"
                                                pattern="yyyy-MM-dd"/></td>
                                        <td class="jsgrid-cell endDate" style="width: 200px;" id="endDate">
                                            <fmt:formatDate
                                                    value="${res.ENDDATE}" pattern="yyyy-MM-dd"/></td>
                                    </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="jsgrid-pager-container">
                        </div>
                        <div class="jsgrid-load-shader"
                             style="display: none; position: absolute; top: 0px; right: 0px; bottom: 0px; left: 0px; z-index: 1000;"></div>
                        <div class="jsgrid-load-panel"
                             style="display: none; position: absolute; top: 50%; left: 50%; z-index: 1000;">Please,
                            wait...
                        </div>
                    </div>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- /.card -->
        </section>
        <!-- /.content -->
    </div>

    <!-- /.content-wrapper -->
    <footer class="main-footer">
    </footer>
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <div id="sidebar-overlay"></div>
    </aside>
</div>

<!-- ./wrapper -->

<!-- page script -->
<script>

    /*
        $(function() {
            $( "#startDate" ).datepicker({
                dateFormat: 'yy.mm.dd',
                prevText: '이전 달',
                nextText: '다음 달',
                monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                dayNames: ['일','월','화','수','목','금','토'],
                dayNamesShort: ['일','월','화','수','목','금','토'],
                dayNamesMin: ['일','월','화','수','목','금','토'],
                showMonthAfterYear: true,
                changeMonth: true,
                changeYear: true,
                yearSuffix: '년'
            });
        }
    */
    $(function () {

        $(".datepicker").datepicker({
            dateFormat: 'yy-mm-dd',
            minDate: 0,
            prevText: '이전 달',
            nextText: '다음 달',
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNames: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            showMonthAfterYear: true,
            changeMonth: true,
            changeYear: true
        });

        function noShownDays() {
        }

    });


</script>


</body>

