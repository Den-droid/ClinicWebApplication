<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 05.05.2021
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../templates/head.jsp"/>
</head>
<body>
<jsp:include page="../templates/header.jsp"/>
<h1>Добрий день, <c:out value="${sessionScope.user.fullname}"/></h1>
<hr>
<form method="get" action="${pageContext.request.contextPath}/admin/search">
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
            <th>Змінити розклад</th>
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
                    <a href="${pageContext.request.contextPath}/admin/add_time?id=${doctor.id}">Додати часи прийому</a><br>
                    <a href="${pageContext.request.contextPath}/admin/edit_time?id=${doctor.id}">Змінити часи прийому</a><br>
                    <a href="${pageContext.request.contextPath}/admin/delete_time?id=${doctor.id}">Видалити часи прийому</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<%@ include file="../templates/footer.html" %>
</body>
</html>
