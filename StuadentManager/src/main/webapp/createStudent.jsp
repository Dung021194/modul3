<%--
  Created by IntelliJ IDEA.
  User: DUNGHUYEN
  Date: 2/5/2023
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Title</title>
</head>
<body>

<h1>Create Student</h1>
<form action="students?action=create" method="post">
  <table>
    <tr>
      <td><label for="name">Name:</label></td>
      <td><input type="text" name="name" id="name" placeholder="Enter name"></td>
    </tr>
    <tr>
      <td><label for="email">Email:</label></td>
      <td><input type="text" name="email" id="email" placeholder="Enter email"></td>
    </tr>
    <tr>
      <td><label for="birth">Date of Birth:</label></td>
      <td><input type="text" pattern="^\d{4}/\d{2}/\d{2}$" name="birth" id="birth" placeholder="Enter date of birth YYYY/MM/DD"></td>
    </tr>
    <tr>
      <td><label for="address">Address:</label></td>
      <td><input type="text" name="address" id="address" placeholder=" Enter Address"></td>
    </tr>
    <tr>
      <td><label for="phone">Phone Number:</label></td>
      <td><input type="text" name="phone" id="phone" placeholder=" Phone Number"></td>
      </tr>
    <tr>
      <td><label >Class:</label></td>
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
        <button type="submit">Create</button>
        <a href="/students" style="text-decoration: none">
          <button type="button">Back to home</button>
        </a>
      </td>
    </tr>
  </table>
</form>

</body>
</html>
