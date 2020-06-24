<%@ page language="java" contentType="text/html;charset=utf-8" %>


<html>
<head><title>SignUp</title></head>
<body>
<form method="post" action="/signUp.do" class="user">
    <div class="form-group">
        <input type="text" class="form-control form-control-user" name="name" placeholder="Name">
    </div>
    <div class="form-group">
        <input type="text" class="form-control form-control-user" name="address" placeholder="Address">
    </div>
    <div class="form-group">
        <input type="text" class="form-control form-control-user" name="phone" placeholder="Phone">
    </div>
    <div class="form-group">
        <input type="text" class="form-control form-control-user" name="email" placeholder="Email">
    </div>
    <div class="form-group">
        <input type="password" class="form-control form-control-user" name="password" placeholder="Password">
    </div>
    <input type="submit" value="가입 완료" class="btn btn-primary btn-user btn-block">
    <input type="button" value="취소" onclick="cancel()" class="btn btn-primary btn-user btn-block">
</form>
</body>
</html>
