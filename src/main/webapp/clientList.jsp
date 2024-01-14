<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Clients</title>
</head>
<body>
<h1>List of Clients</h1>
<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Contact</th>
        <th>Tariff</th>
        <th>Provider</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.name}</td>
            <td>${client.contact}</td>
            <td>${client.tariff}</td>
            <td>${client.provider}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
