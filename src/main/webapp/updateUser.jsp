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
<form method="post" action="UserController" name="formAddUser">
    User ID : <input type="text" readonly="readonly" name="userid"
                     value="<c:out value="${user.userid}"/>"/> <br/>
    First name : <input type="text" name="firstName"
                        value="<c:out value="${user.firstName}"/>"/> <br/>
    Last name : <input type="text" name="lastName"
                       value="<c:out value="${user.lastName}"/>"/> <br/>

    City : <input type="text" name="city"
                       value="<c:out value="${user.city}"/>"/> <br/>

    Tel : <input type="text" name="tel"
                       value="<c:out value="${user.tel}"/>"/> <br/>
    DOB : <input type="text" name="dob"
                 value="<fmt:formatDate pattern="MM/dd/yyyy" value="${user.dob}"/>"/> <br/>
    Email : <input type="text" name="email"
                   value="<c:out value="${user.email}"/>"/> <br/>
    <input type="submit" value="ok">
    <p><a href="UserController?action=listUser">Back</a></p>
</form>
</body>
</html>
