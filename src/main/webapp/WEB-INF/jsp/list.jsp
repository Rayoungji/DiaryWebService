<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="EUC-KR">
</head>
<body>
<h1>MemberList</h1>
<ul>
    <c:forEach var="member" items="${members}" varStatus="status">
        <li>${status.index+1}: ${member.name}, ${member.email}, ${member.created_at}</li>
    </c:forEach>
</ul>
</body>
</html>
