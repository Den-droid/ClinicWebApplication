<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 05.05.2021
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../templates/head.jsp"/>
</head>
<body>
<jsp:include page="../templates/header.jsp"/>
<hr>
<div class="divCenter">
    <c:if test="${!empty requestScope.error}">
        <div class="alert alert-danger" role="alert">
                ${requestScope.error}
        </div>
    </c:if>
    <c:if test="${empty requestScope.error}">
        <table class="innerDivCenter">
            <tr>
                <th>Ім'я доктора</th>
                <th>Спеціальність</th>
                <th>Дата</th>
                <th>Час</th>
                <th>Кількість доступних місць</th>
                <th>Змінити час прийому</th>
            </tr>
            <c:forEach items="${requestScope.timetable}" var="appointment">
                <form action="" method="post">
                    <tr>
                        <td>
                                ${appointment.fullname}
                        </td>
                        <td>
                                ${appointment.specialty}
                        </td>
                        <td>
                            <label>
                                <input type="date" value="${appointment.date}" name="date" required/>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input type="time" value="${appointment.time}" name="time" required/>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input type="number" value="${appointment.placesAvailable}" min="1"
                                       name="placesAvailable" required/>
                            </label>
                        </td>
                        <td>
                            <input type="hidden" value="${appointment.id}" name="appointmentId">
                            <input type="submit" value="Змінити">
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@ include file="../templates/footer.html" %>
</body>
</html>
