<!DOCTYPE html>
<html lang="en">
<%@page import="com.skuniv.member.entity.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    function goBack() {
        location.href="/mypage";
    }
    function modify() {
        location.href="/userinfomodify";
    }
</script>
<header class="masthead" style="background-color: #F7BE81">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <h1>UserInfo</h1>
                    <div class="col-lg-8 col-md-10 mx-auto">
                        <br>
                        <hr>
                        <form method="post" action="/deleteaccount" class="user">
                            <%
                                System.out.println("is mypage jsp running??");
                                Member member = (Member)request.getAttribute("member");
                            %>
                            <div class="form-group" style="position: absolute">
                                Name:  <% out.println(member.getName()); %>
                            </div>
                            <br>
                            <div class="form-group" style="position: absolute">
                               Phone:   <% out.println(member.getPhone()); %>
                            </div>
                            <br>
                            <div class="form-group" style="position: absolute">
                                Adress:  <% out.println(member.getAddress()); %>
                            </div>
                            <br>
                            <div class="form-group" style="position: absolute">
                                Email:  <% out.println(member.getEmail()); %>
                            </div>
                            <br>
                            <hr>
                            <br>
                            <input type="button" value="Update Info" class="btn btn-secondary btn-user btn-block" onClick="modify()">
                            <input type="submit" value="Delete Account" class="btn btn-secondary btn-user btn-block">
                            <input type="button" value="Previous" class="btn btn-secondary btn-user btn-block" onClick="goBack()">
                        </form>
                    </hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</header>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src=".../../resource/intro/vendor/jquery/jquery.min.js"></script>
<script src="../../resource/intro/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Custom scripts for this template -->
<script src="../../resource/intro/js/clean-blog.min.js"></script>

</body>
