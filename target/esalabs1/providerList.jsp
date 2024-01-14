<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Providers</title>
</head>
<body>
<h1>List of Providers</h1>
<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Clients</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${providers}" var="provider">
        <tr>
            <td>${provider.name}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty provider.clients}">
                        <!-- Если у провайдера есть клиенты -->
                        <c:forEach items="${provider.clients}" var="client">
                            <!-- Вывод информации о клиентах -->
                            ${client.name}, ${client.contact}, ${client.tariff}<br>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        No clients
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
