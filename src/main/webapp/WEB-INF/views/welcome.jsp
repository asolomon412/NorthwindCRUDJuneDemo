<%--
  Created by IntelliJ IDEA.
  User: Antonella
  Date: 7/21/17
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="z"%>
<html>
<head>
    <title>Spring Demo</title>
</head>
<body>
${message}

<a href="getNewCust">Add Customer</a>

<form action="searchByCity" method="get">
    <input type="text" name = "city">
    <input type="submit" value="Search">
</form>


<table border="1">
   <z:forEach var="myVar" items="${cList}">
       <tr>
           <td>${myVar.customerId}</td>
           <td>${myVar.companyName}</td>
           <td><a href="delete?id=${myVar.customerId}"> Delete </a></td>
           <td><a href="update?id=${myVar.customerId}"> Edit </a></td>
       </tr>

   </z:forEach>

</table>
</body>
</html>
