<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 06.05.2021
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <a href="${pageContext.request.contextPath}/">На головну</a>
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/login">Увійти</a>
        <a href="${pageContext.request.contextPath}/register">Зареєструватися</a>
    </c:if>
    <c:if test="${!empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/${sessionScope.urlProfile}">Ваша Сторінка</a>
        <a href="${pageContext.request.contextPath}/logout">Вийти</a>
    </c:if>
</div>
