<%--
  Created by IntelliJ IDEA.
  User: DUNGHUYEN
  Date: 2/4/2023
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Student</h1>

<form action="/students?action=search" method="post">
    <input name="search" type="text">
    <button type="submit">Search</button>
</form>
<a href="/students?action=create">Create new Student</a> <br>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date of birth</th>
        <th>Email</th>
        <th>Address</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach var="s" items="${studentList}">
    <tr>
        <td>${s.id}</td>
        <td>${s.name}</td>
        <td>${s.dateOfBirth}</td>
        <td>${s.email}</td>
        <td>${s.address}</td>
        <td><a href="/students?action=update&id=${s.id}">
            <button>Update</button>
        </a></td>
        <td><a href="/students?action=delete&id=${s.id}">
            <button>Delete</button>
        </a></td>
    </tr>
    </c:forEach>


</body>
</html>
