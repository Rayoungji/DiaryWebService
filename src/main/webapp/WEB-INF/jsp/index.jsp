<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My Dairy Service - start Diary Service</title>

    <!-- Bootstrap core CSS -->
    <link href="../../resource/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="../../resource/intro/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="../../resource/css/clean-blog.min.css" rel="stylesheet">

</head>

<body>
<%
    String name = (String) request.getSession().getAttribute("name");
    String email = (String) request.getSession().getAttribute("email");
    System.out.println("indexPage - name:"+name+" email:"+email);
%>
<%
        Boolean SIGNUPSUCESS = (Boolean) request.getAttribute("SIGNUPSUCESS");
        if(SIGNUPSUCESS){
%>
<script>
    alert('회원가입이 정상적으로 이루어졌습니다!!');
</script>
<%
        }
%>

<%
    Boolean LOGINSUCESS = (Boolean) request.getAttribute("LOGINSUCESS");
    if(LOGINSUCESS){
%>
<script>
    alert('로그인이 정상적으로 이루어졌습니다!!');
</script>
<%
        }
%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">My Diary</a>
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
                <%
                    if(!LOGINSUCESS){
                %>
                <li class="nav-item">
                    <a class="nav-link" href="/signin">SignIn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/signup">SignUp</a>
                </li>
                <% }
                %>
                <%
                    if(LOGINSUCESS){
                    %>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/mypage">MyPage</a>
                </li>
               <% }
                    %>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Header -->
<header class="masthead">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <h1>About Diary</h1>
                    <span class="subheading">Introduce about service.</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
        </div>
    </div>
</div>

<hr>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <ul class="list-inline text-center">
                    <li class="list-inline-item">
                        <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
                </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
                </span>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                </span>
                        </a>
                    </li>
                </ul>
                <p class="copyright text-muted">Copyright &copy; Your Website 2020</p>
            </div>
        </div>
    </div>
</footer>

<!-- Bootstrap core JavaScript -->
<script src=".../../resource/intro/vendor/jquery/jquery.min.js"></script>
<script src="../../resource/intro/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for this template -->
<script src="../../resource/intro/js/clean-blog.min.js"></script>

</body>

</html>
