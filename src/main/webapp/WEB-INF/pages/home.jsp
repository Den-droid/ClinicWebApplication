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
<jsp:include page="templates/header.jsp"/>
<hr>
<form method="get" action="${pageContext.request.contextPath}/search">
    <label>
        <input type="text" name="name">
    </label>
    <label>
        <input type="submit" value="Пошук">
    </label>
</form>
<hr>
<c:if test="${!empty requestScope.error}">
    <div class="alert alert-danger" role="alert">
            ${requestScope.error}
    </div>
</c:if>
<c:if test="${empty requestScope.error}">
    <table>
        <tr>
            <th>Ім'я доктора</th>
            <th>Спеціальність</th>
            <th>Розклад</th>
        </tr>
        <c:forEach items="${requestScope.doctors}" var="doctor">
            <tr>
                <td>
                        ${doctor.fullname}
                </td>
                <td>
                        ${doctor.specialty}
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/timetable?id=${doctor.id}">Переглянути розклад</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<%@ include file="templates/footer.html" %>
</body>
</html>
