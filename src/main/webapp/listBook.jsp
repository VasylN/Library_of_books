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
        <th>BookID</th>
        <th>Title</th>
        <th>Author</th>
        <th>Genre</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.bookId}"/></td>
            <td><c:out value="${book.title}"/></td>
            <td><c:out value="${book.author}"/></td>
            <td><c:out value="${book.genre}"/></td>
            <td><a href="BookController?action=edit&bookid=<c:out value="${book.bookId}"/>">Update</a></td>
            <td><a href="BookController?action=delete&bookid=<c:out value="${book.bookId}"/>&userid=<c:out value="${book.userid}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="BookController?action=insert">Add book</a></p>
<p><a href="UserController?action=listUser">Back</a></p>
</body>
</html>
