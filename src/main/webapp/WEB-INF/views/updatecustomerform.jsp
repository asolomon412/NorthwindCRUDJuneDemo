<%--
  Created by IntelliJ IDEA.
  User: Antonella
  Date: 8/2/17
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Customer Form</title>
</head>
<body>
<form action="updateform" method="post">
    Customer ID: ${id}<input type=hidden name="custID" value="${id}"> <br>
    Company Name: <input type="text" name="compName" required> <br>
    Contact Name: <input type="text" name="contName"> <br>
    Contact Title: <input type="text" name="contTitle"> <br>
    Address: <input type="text" name="address"> <br>
    City: <input type="text" name="city"> <br>
    Region: <input type="text" name="region"> <br>
    Postal Code: <input type="text" name="zip"> <br>
    Country: <input type="text" name="country"> <br>
    Phone: <input type="text" name="phone"> <br>
    Fax: <input type="text" name="fax"> <br>
    <input type="submit" value="Add Customer">

</form>
</body>
</html>
