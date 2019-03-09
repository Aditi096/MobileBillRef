<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {
    border-collapse: collapse;
    border-radius:40px;
  
}

th{
	font-color:red;
	}

table, th, td {
    border-bottom: 2px solid coral;
}
</style>
</head>

<body>

	<div align="center">
		<div align="right" style="position: absolute; right: 300px;">
			<form name="Home Page" action="index" method="get">
				<table>
					<tr>
						<td><input type="submit" value="Home Page"></td>
					</tr>
				</table>
			</form>
		</div>

		<div>
			<table   cellpadding="20">
			<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email ID</th>
			<th>Date Of Birth</th>
			<th>Pin Code</th>
			<th>City</th>
			<th>State</th>
			</tr>
				<c:forEach var="customer" items="${customers}">
					<tr>
						<td>${customer.firstName }</td>
						<td>${customer.lastName }</td>
						<td>${customer.emailID }</td>
						<td>${customer.dateOfBirth }</td>
						<td>${customer.billingAddress.pinCode }</td>
						<td>${customer.billingAddress.city }</td>
						<td>${customer.billingAddress.state }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>