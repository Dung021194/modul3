
<%--
  Created by IntelliJ IDEA.
  User: DUNGHUYEN
  Date: 2/5/2023
  Time: 2:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>Update form</h1>
<form action="students?action=update&id=${student.id}" method="post">
  <table>
    <tr>
      <td><label for="name">Name:</label></td>
      <td><input type="text" name="name" id="name" value="${student.name}"></td>
    </tr>
    <tr>
      <td><label for="birth">Date of birth:</label></td>
      <td><input type="text" name="birth" id="birth" value="${student.dateOfBirth}"></td>
    </tr>
    <tr>
      <td><label for="email">Email:</label></td>
      <td><input type="text" pattern="^\d{4}/\d{2}/\d{2}$" name="email" id="email" value="${student.email}"></td>
    </tr>
    <tr>
      <td><label for="address">Address:</label></td>
      <td><input type="text" name="address" id="address" value="${student.address}"></td>
    </tr>
    <tr>
      <td><label for="phone">Phone number:</label></td>
      <td><input type="text" name="phone" id="phone" value="${student.phoneNumber}"></td>
    </tr>
    <tr>
      <td><label>Class:</label></td>
      <td>
        <select name="class">
          <c:forEach var="c" items="${listClass}">

            <option value="${c.id}">${c.name}</option>
          </c:forEach>
        </select>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <button type="submit">Update</button>
        <a href="/students" style="text-decoration: none">
          <button>Back to home</button>
        </a>
      </td>
    </tr>
  </table>
</form>

</body>
</html>
