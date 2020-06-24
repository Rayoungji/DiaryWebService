<%@ page language="java" contentType="text/html;charset=utf-8" %>
<html>
<head><title>loginSuccess</title></head>
<body>
<%
    String NAME = (String) request.getSession().getAttribute("name");
    Boolean LOGIN_OK = (Boolean) request.getAttribute("LOGIN_OK");
    if(LOGIN_OK){
%>
<script>
    alert('회원가입이 정상적으로 이루어졌습니다!!');
</script>
<b> welcome <%=NAME%></b><br/>
<%
    }
%>
</Form>
</body>
</html>
