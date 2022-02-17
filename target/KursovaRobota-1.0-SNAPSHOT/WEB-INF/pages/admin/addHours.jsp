<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 05.05.2021
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../templates/head.jsp"/>
</head>
<body>
<jsp:include page="../templates/header.jsp"/>
<hr>
<div class="divCenter">
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
    <c:if test="${empty requestScope.error}">
        <table class="innerDivCenter">
            <tr>
                <th>Ім'я доктора</th>
                <th>Спеціальність</th>
                <th>Дата</th>
                <th>Час</th>
                <th>Кількість доступних місць</th>
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
                </tr>
            </c:forEach>
        </table>
        <form method="post" action="">
            <label>
                Дата прийому
                <input type="date" name="date" required/>
            </label>
            <br>
            <label>
                Час прийому
                <input type="time" name="time" required/>
            </label>
            <br>
            <label>
                Кількість доступних місць
                <input type="number" name="placesAvailable" min="1" required/>
            </label>
            <br>
            <input type="submit" value="Додати час прийому">
        </form>
    </c:if>
</div>
<%@ include file="../templates/footer.html" %>
</body>
</html>