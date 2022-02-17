<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 05.05.2021
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="templates/head.jsp"/>
</head>
<body>
<div style="text-align: center">
    <h1>Login</h1>
    <form action="" method="post" id="loginForm">
        <label for="email">Email:</label>
        <input name="email" id="email" size="30"/>
        <br><br>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" size="30"/>
        <br>
        <c:if test="${!empty requestScope.error}">
            ${requestScope.error}
        </c:if>
        <br><br>
        <button type="submit">Login</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/scripts/loginValidator.js">
</script>
<%@ include file="templates/footer.html" %>
</body>
</html>
