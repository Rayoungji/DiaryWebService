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
    <link href="../../resource/intro//css/clean-blog.min.css" rel="stylesheet">

</head>

<body>
<%
    String email = (String) request.getSession().getAttribute("email");
    System.out.println("aboutPage's email>>>> "+email);
%>
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
                <% if(email != null){%>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/mypage">MyPage</a>
                </li>
                <%}%>
                <% if(email == null){%>
                <li class="nav-item">
                    <a class="nav-link" href="/signin">SignIn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/signup">SignUp</a>
                </li>
                <%}%>
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
                    <h1>About Me</h1>
                    <span class="subheading">About our Service.</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <p>일기를 기록하는 다이어리 서비스입니다. 오늘 당신의 하루는 어땠나요? My Diary 서비스를 이용하여 당신의 하루를 기록하세요!!</p>
            <p>본 서비스는 회원가입 후 사용이 가능하십니다. 회원 정보는 userInfo 페이지에서 수정 및 탈퇴가 가능하며, 가입 후
                마이페이지에서 여러 기능을 사용하실 수 있습니다. 원하시는 기능을 선택하여 서비스를 사용하여보세요.</p>
            <p>캘린더에서는 날짜별 일기목록을 확인 할 수 있습니다. 캘린더에 있는 일기를 클릭 혹은 리스트 보기에 들어가서 원하시는 일기를 선택하여
                일기를 열람하실 수 있습니다. 또한 날짜 구간 별로 일기 검색이 가능합니다. 제목 검색과 키워드 검색을 통해 해당하는 일기를 열람하실 수 있습니다.</p>

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
