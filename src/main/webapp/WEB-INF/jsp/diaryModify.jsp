<!DOCTYPE html>
<html lang="en">
<%@page import="com.skuniv.diary.entity.Diary"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Clean Blog - Start Bootstrap Theme</title>

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
<%
    System.out.println("is diaryModify jsp running??");
    Diary diary = (Diary) request.getAttribute("diary");
%>
<script>
    function goBack() {
        location.href="/mypage";
    }
</script>

<!-- Page Header -->
<header class="masthead" style="background-color: #F7BE81">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>Modify Diary</h1>
                    <span class="subheading"><% out.println(diary.getDate()); %>'s Diary</span>
                    <br>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-8 col-md-10 mx-auto">
                                <form method="post" action="/diarymodify.do" >
                                    <input type="text" name="date" value="<%=diary.getDate()%>" style="display: none">
                                    <div class="col-lg-8 col-md-10 mx-auto">
                                        <div class="form-group">
                                            <label>Title</label>
                                            <input type="text" class="form-control" name="title" value="<%=diary.getTitle()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Context</label>
                                            <textarea rows="5" type="text" class="form-control" name="context" placeholder="<%=diary.getContext()%>"></textarea>
                                        </div>
                                        <br>
                                        <button type="submit" class="btn btn-secondary">Modify</button>
                                        <a onclick="goBack()" class="btn btn-secondary">GoBack</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

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

<!-- Contact Form JavaScript -->
<script src="../../resource/intro/js/jqBootstrapValidation.js"></script>
<script src="../../resource/intro/js/contact_me.js"></script>

<!-- Custom scripts for this template -->
<script src="../../resource/intro/js/clean-blog.min.js"></script>

</body>

</html>
