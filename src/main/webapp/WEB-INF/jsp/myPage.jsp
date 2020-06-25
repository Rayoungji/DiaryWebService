<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.skuniv.member.entity.Member"%>
<%@page import="com.skuniv.diary.entity.Diary" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.awt.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    request.setCharacterEncoding("euc-kr");
%>
<%
    Calendar cal = Calendar.getInstance();
    String strYear = request.getParameter("year");
    String strMonth = request.getParameter("month");
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int date = cal.get(Calendar.DATE);
    if (strYear != null) {
        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth);
    } else {
    }
    //년도/월 셋팅
    cal.set(year, month, 1);
    int startDay = cal.getMinimum(java.util.Calendar.DATE);
    int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
    int newLine = 0;
    //오늘 날짜 저장.
    Calendar todayCal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    int intToday = Integer.parseInt(sdf.format(todayCal.getTime()));

%>
<%
    String email = (String) request.getSession().getAttribute("email");
    System.out.println("myPage's email>>>> "+email);
    Member member = (Member) request.getAttribute("member");
    String name = member.getName();
    List<Diary> diaryList = (List<Diary>) request.getAttribute("diaryList");

%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My Dairy Service - start Diary Service</title>

    <!-- Bootstrap core CSS -->
    <link href="../../resource/intro/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="../../resource/intro/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="../../resource/intro/css/clean-blog.min.css" rel="stylesheet">

</head>

<body>
<script>
    function onClick(getId) {
        location.href="/diarydetail"+"?id="+getId;
    }
</script>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-calendar"></i>
        </div>
        <a class="navbar-brand" href="index.html">My Diary</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/about">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/userinfo">UserInfo</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Header -->
<header class="masthead" style="background-color: #F7BE81">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading" style="height:600px">
                    <h1>About Me</h1>
                    <span class="subheading"><%=name%>'s Diary</span>
                    <br>
                    <hr>
                    <ul class="list-inline text-center">
                        <li class="list-inline-item">
                            <a href="/insertdiary">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-calendar fa-stack-1x fa-inverse"></i>
                    <a class="subheading" style="font-size: medium">글작성</a>
                </span>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="/diarylist">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-search fa-stack-1x fa-inverse"></i>
                    <a class="subheading" style="font-size: medium">리스트 보기</a>
                </span>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="/keywordsearch">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-key fa-stack-1x fa-inverse"></i>
                    <a class="subheading" style="font-size: medium">키워드 검색</a>
                </span>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="/durationsearch">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-calendar fa-stack-1x fa-inverse"></i>
                    <a class="subheading" style="font-size: medium">구간검색</a>
                </span>
                            </a>
                        </li>
                    </ul>
                    <hr>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
<%--        <div class="col-lg-8 col-md-10 mx-auto">--%>

    <!--날짜 네비게이션  -->
    <table width="100%" border="0" cellspacing="1" cellpadding="1"
           id="KOO"  style="border: 1px ">
        <tr>
            <td height="60">

                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td height="10"></td>
                    </tr>
                    <tr>
                        <td align="center">
                            <!-- 전년도 로 가는 버튼--> <a
                                href="<c:url value='/mypage' />?year=<%=year - 1%>&amp;month=<%=month%>"
                                target="_self"> <b>&lt;&lt;</b>
                        </a> <%
                            if (month > 0) {
                        %> <a
                                href="<c:url value='/mypage' />?year=<%=year%>&amp;month=<%=month - 1%>"
                                target="_self"> <b>&lt;</b>
                            <!-- 이전달 -->
                        </a> <%
                        } else {
                        %> <b>&lt;</b> <%
                            }
                        %> &nbsp;&nbsp; <%=year%>년 <%=month + 1%>월

                            &nbsp;&nbsp; <%
                            if (month < 11) {
                        %> <a
                                href="<c:url value='/mypage' />?year=<%=year%>&amp;month=<%=month + 1%>"
                                target="_self"> <!-- 다음달 -->
                            <b>&gt;</b>

                        </a> <%
                        } else {
                        %> <b>&gt;</b> <%
                            }
                        %> <a
                                href="<c:url value='/mypage' />?year=<%=year + 1%>&amp;month=<%=month%>"
                                target="_self"> <!-- 다음해 -->
                            <b>&gt;&gt;</b>
                        </a>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <br>
    <div class="container">
        <br>
        <table border="2" cellspacing="1" cellpadding="1" bgcolor="#FFFFFF">
            <THEAD>
            <table width="910" cellpadding="2" cellspacing="1" border="1"
                   align="center">
                <tr height="30">
                    <td width="130"><font size="2">일</font></td>
                    <td width="130"><font size="2">월</font></td>
                    <td width="130"><font size="2">화</font></td>
                    <td width="130"><font size="2">수</font></td>
                    <td width="130"><font size="2">목</font></td>
                    <td width="130"><font size="2">금</font></td>
                    <td width="130"><font size="2">토</font></td>
                </tr>
                <%
                    //처음 빈공란 표시
                    int index;
                    int startD = 0;
                    for (index = 1; index < start; index++) {
                        out.println("<TD >&nbsp;</TD>");
                        newLine++;
                    }
                    for (index = 1; index <= endDay; index++) {
                        String color = "";
                        // 각 라인의 첫번째 칸 날자 빨간색 처리
                        if (newLine == 0) {
                            color = "RED";
                            // 각 라인의 6번쨰 칸 토요일 파란색 처리
                        } else if (newLine == 6) {
                            color = "#529dbc";
                            // 나머지 칸 검은색
                        } else {
                            color = "BLACK";
                        }
                        ;
                        String sUseDate = Integer.toString(year);
                        sUseDate += Integer.toString(month + 1).length() == 1 ? "0" + Integer.toString(month + 1)
                                : Integer.toString(month + 1);
                        sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index)
                                : Integer.toString(index);
                        int iUseDate = Integer.parseInt(sUseDate);
                        String backColor = "#EFEFEF";
                        // 오늘 날짜 기준 배경색 진한색 처리
                        if (iUseDate == intToday) {
                            backColor = "#c9c9c9";
                        }
                        out.println("<TD valign='top' align='left' height='92px' bgcolor='" + backColor + "' nowrap>");
                %>
                <font color='<%=color%>'> <!-- 일자 찍기 -->
                    <%
                            out.println(index);
                            out.println("<BR>");
                            out.println("<html><body>");
                                    for (int i = startD; i < diaryList.size(); i++) {
                                        Diary diary = diaryList.get(i);
                                        String diaryDate[] = diaryList.get(i).getDate().toString().split("-");
                                        String diaryD = diaryDate[0]+diaryDate[1]+diaryDate[2];
                                        if (diaryD.equals(sUseDate)) {
                                            out.print(
                                                    "<input class='btn btn-secondary' onClick='onClick(" +diary.getId()+
                                                            ")' style='width:80pt; height:15pt' size=1 value='"
                                                            +diary.getTitle()+"'" + "</input>");
                                            startD++;
                                            break;
                                        }

                                    }
                            out.println("</body></html>");
                            out.println("<BR>");
                            //기능 제거
                            out.println("</TD>");
                            newLine++;
                            if (newLine == 7) {
                                out.println("</TR>");
                                if (index <= endDay) {
                                    out.println("<TR>");
                                }
                                newLine = 0;
                            }
                        }
                        //마지막 공란 LOOP
                        while (newLine > 0 && newLine < 7) {
                            out.println("<TD>&nbsp;</TD>");
                            newLine++;
                        }
                    %>

                </font>
            </table>
            </THEAD>
        </TABLE>
    </div>
<%--        </div>--%>
    </div>
</div>

<hr>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <p class="copyright text-muted">Copyright &copy; My Diary (Youngji) 2020</p>
            </div>
        </div>
    </div>
</footer>

<!-- Bootstrap core JavaScript -->
<script src="../../resource/intro/vendor/jquery/jquery.min.js"></script>
<script src="../../resource/intro/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for this template -->
<script src="js/clean-blog.min.js"></script>

</body>

</html>
