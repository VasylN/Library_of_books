<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pc
  Date: 30.07.2017
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border=2>
    <thead>
    <tr>
        <th>User id</th>
        <th>First name</th>
        <th>Last name</th>
        <th>City</th>
        <th>Tel</th>
        <th>Email</th>
        <th>DOB</th>
        <th colspan=3>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.userid}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value ="${user.city}"/></td>
            <td><c:out value ="${user.tel}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${user.dob}"/></td>
            <td><a href="UserController?action=edit&userid=<c:out value="${user.userid}"/>">Update</a></td>
            <td><a href="UserController?action=delete&userid=<c:out value="${user.userid}"/>">Delete</a></td>
            <td><a href="BookController?action=review&userid=<c:out value="${user.userid}"/>">Review list of books</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="UserController?action=insert">Add user</a></p>
<p><a href="BookController?action=reviewAll&userid">Review All Books</a></p>
</body>
</html>