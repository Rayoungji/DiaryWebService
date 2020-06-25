<html lang="en">
<%@page import="com.skuniv.diary.entity.Diary" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <link href="../../resource/intro//css/clean-blog.min.css" rel="stylesheet">

</head>

<body>
<%
    List<Diary> diaryList = (List<Diary>) request.getAttribute("diaryList");
%>
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
                <div class="page-heading">
                    <h1>Diary List</h1>
                    <span class="subheading">Click it, if you want Details.</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
<c:forEach var="diary" items="${diaryList}" varStatus="status">
            <div class="post-preview">
                <a onclick="onClick(${diary.id})">
                    <h2 class="post-title">
                        ${diary.title}
                    </h2>
                    <h3 class="post-subtitle">
                        ${diary.context}
                    </h3>
                </a>
                <p class="post-meta">
                    on ${diary.date}</p>
            </div>
            <hr>
</c:forEach>
            <!-- Pager -->
            <div class="clearfix">
                <a class="btn btn-primary float-right" href="/mypage">Previous &rarr;</a>
            </div>
        </div>
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
