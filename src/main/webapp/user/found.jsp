<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Founded User</title>
</head>
<body>
<h1>Users found follow your query:</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Country</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getCountry()}</td>
            <td>${user.getEmail()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
