<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
header {
	background-color: #666;
	padding: 30px;
	text-align: center;
	font-size: 15px;
	color: white;
}

section {
	width: 100%;
	font-size: 20px;
	background-color: #f1f1f1;
	height: 1000px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<h2><a href="indexPage">Home Page</a></h2>
<header>
		<h1>Customer Details</h1>

	</header>
	<section border="1" align="center">
	<table  align="center" border='0' width='600px' cellpadding='10'
		cellspacing='0'>
			<tr><td>Customer Id</td><td>${customer.customerID}</td></tr>
			<tr><td>First Name</td><td>${customer.firstName}</td></tr>
			<tr><td>Last Name</td><td>${customer.lastName}</td></tr>
			<tr><td>emailId</td><td>${customer.emailID}</td></tr>
			<tr><td>Date of Birth</td><td>${customer.dateOfBirth}</td></tr>
			<tr><td>City</td><td>${customer.billingAddress.city}</td></tr>
			<tr><td>State</td><td>${customer.billingAddress.state}</td></tr>
			<tr><td>Pin Code</td><td>${customer.billingAddress.pinCode}</td></tr>
			
			
	</table>
	</section>
</body>
</html>