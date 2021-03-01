<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h1>User list:</h1>
<table>
    <tr>
        <th>ID</th>
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
            <td>
                <a href="${pageContext.request.contextPath}/user/update?id=${user.getId()}&name=${user.getName()}&country=${user.getCountry()}&email=${user.getEmail()}">Update</a>
            </td>
            <td><a href="${pageContext.request.contextPath}/user/remove?id=${user.getId()}">Remove</a></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/user/add">Create new User</a>
<form action="${pageContext.request.contextPath}/users?action=find" method="post">
    <label for="value"></label><input type="text" name="value" id="value" placeholder="Search ...">
    <label for="option"></label><select name="find_by" id="option">
        <option value="name">Name</option>
        <option value="country">Country</option>
    </select>
    <input type="submit" value="Search">
</form>
</body>
</html>
