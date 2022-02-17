<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 05.05.2021
  Time: 20:26
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
        <input name="name">
    </label>
    <label>
        <input type="submit" value="Пошук">
    </label>
</form>
<hr>
<c:if test="${!empty requestScope.message}">
    <div class="alert alert-info" role="alert">
            ${requestScope.message}
    </div>
</c:if>
<c:if test="${!empty requestScope.error}">
    <div class="alert alert-danger" role="alert">
            ${requestScope.error}
    </div>
</c:if>
<br>
<c:if test="${empty requestScope.error}">
    <table>
        <tr>
            <th>Ім'я доктора</th>
            <th>Спеціальність</th>
            <th>Дата</th>
            <th>Час</th>
            <th>Кількість доступних місць</th>
            <th>Зареєструватись на прийом</th>
        </tr>
        <c:forEach items="${requestScope.timetable}" var="appointment">
            <tr>
                <td>
                        ${appointment.fullname}
                </td>
                <td>
                        ${appointment.specialty}
                </td>
                <td>
                        ${appointment.date}
                </td>
                <td>
                        ${appointment.time}
                </td>
                <td>
                        ${appointment.placesAvailable}
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/timetable?id=${param.id}" method="post">
                        <input type="hidden" value="${appointment.id}" name="appointmentId">
                        <input type="submit" value="Записатися">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<%@ include file="templates/footer.html" %>
</body>
</html>

