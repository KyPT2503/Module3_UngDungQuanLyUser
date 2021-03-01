<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
</head>
<body>
<h1>Update:</h1>
<form action="${pageContext.request.contextPath}/user/update" method="post">
    <table>
        <tr>
            <th>Id</th>
            <td><label>
                <input type="text" name="id" value="${id}">
            </label></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><label>
                <input type="text" name="name" value="${name}">
            </label></td>
        </tr>
        <tr>
            <th>Country</th>
            <td><label>
                <input type="text" name="country" value="${country}">
            </label></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><label>
                <input type="text" name="email" value="${email}">
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
