<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 05.05.2021
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="templates/head.jsp"/>
</head>
<body>
<div class="divCenter">
    <h1 style="text-align: center;">Реєстрація</h1>
    <form name="RegForm" action="${pageContext.request.contextPath}/register"
          onsubmit="return validate()" method="post" class="innerDivCenter">
        <p id="nameP"> Повне ім'я: <input type="text"
                                        size="65" name="fullname" /></p>
        <br />
        <p id="emailP">Логін (email): <input type="text"
                                              size="65" name="email" /></p>
        <br />
        <p id="passwordP">Пароль: <input type="password"
                                           size="65" name="password" /></p>
        <br />
        <p id="confPasswordP">Підтвердити пароль: <input type="password"
                                                       size="65" name="confirmPassword" /></p>
        <br />
        <c:if test="${!empty requestScope.error}">
            ${requestScope.error}
        </c:if>
        <p>
            <input type="submit"
                   value="Зареєструватися" name="Submit" />
            <input type="reset"
                   value="Скинути" name="Reset" />
        </p>
    </form>
</div>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/scripts/registerValidator.js">
</script>
<%@ include file="templates/footer.html" %>
</body>
</html>
