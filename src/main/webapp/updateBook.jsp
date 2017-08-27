<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pc
  Date: 30.07.2017
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="BookController" name="formAddBook">
    Book ID : <input type="text" readonly="readonly" name="bookId"
                     value="<c:out value="${book.bookId}"/>"/> <br/>
    Title : <input type="text" name="title"
                   value="<c:out value="${book.title}"/>"/> <br/>
    Author : <input type="text" name="author"
                    value="<c:out value="${book.author}"/>"/> <br/>
    Genre : <input type="text" name="genre"
                   value="<c:out value="${book.genre}"/>"/> <br/>
    <input type="submit" value="ok">
     <p><a href="BookController?action=review&userid=<c:out value="${book.userid}"/>">Back</a></p>
</form>
</body>
</html>
