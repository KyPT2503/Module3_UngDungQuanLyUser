<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create new user</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/users?action=add" method="post">
    <table>
        <tr>
            <th>Id</th>
            <td><label>
                <input type="text" name="id" value="${user.getId()}">
            </label></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><label>
                <input type="text" name="name" value="${user.getName()}">
            </label></td>
        </tr>
        <tr>
            <th>Country</th>
            <td><label>
                <input type="text" name="country" value="${user.getCountry()}">
            </label></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><label>
                <input type="text" name="email" value="${user.getEmail()}">
            </label></td>
        </tr>
        <tr>
            <th></th>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form>
</body>
</html>
