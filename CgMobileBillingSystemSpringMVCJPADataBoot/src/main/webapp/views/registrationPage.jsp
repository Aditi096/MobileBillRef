<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title> Registration </title>
<style>
.error{
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
<div align="center">
<h2>Enter Customer Details </h2>
<div align="right" style="position:absolute; right:500px;">
 <form name="Home Page" action="index" method="get" >
<table>
<tr>
<td><input type="submit" value="Home Page"></td>
</tr>
</table>
</form> 
</div>
<table>

<form:form action="registerCustomer" method="post" modelAttribute="customer">

<tr>
<td>First Name</td>
<td><form:input path="firstName" size="30"/></td>
<td><form:errors path="firstName" cssClass="error"/></td>
</tr>


<tr>
<td>Last Name</td>
<td><form:input path="lastName" size="30"/></td>
<td><form:errors path="lastName" cssClass="error"/></td>
</tr>

<tr>
<td>Email ID</td>
<td><form:input path="emailID" size="30"/></td>
<td><form:errors path="emailID" cssClass="error"/></td>
</tr>

<tr>
<td>Date Of Birth</td>
<td><form:input path="dateOfBirth" size="30"/></td>
<td><form:errors path="dateOfBirth" cssClass="error"/></td>
</tr>
<tr>
<td>City</td>
<td><form:input path="billingAddress.city" size="30"/></td>
<td><form:errors path="billingAddress.city" cssClass="error"/></td>
</tr>
<tr>
<td>State</td>
<td><form:input path="billingAddress.state" size="30"/></td>
<td><form:errors path="billingAddress.state" cssClass="error"/></td>
</tr>
<tr>
<td>Pin Code</td>
<td><form:input path="billingAddress.pinCode" size="30"/></td>
<td><form:errors path="billingAddress.pinCode" cssClass="error"/></td>
</tr>
<tr>
<td><input type="submit" value="Submit"></td>
</tr>
</form:form>
</table>
</div>
<div align="center">${requestScope.message}

</div>

</body>
</html>
