<%--
  Created by IntelliJ IDEA.
  User: Antonella
  Date: 7/31/17
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<form action="addNewCustomer" method="post">
    Customer ID: <input type="text" name="custID" required> <br>
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
