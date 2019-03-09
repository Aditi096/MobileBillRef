<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}

li a {
	float: left;
	background-color: #333;
	display: block;
	color: white;
	text-align: center;
	padding: 16px 20px;
	text-decoration: none;
}

table {
	background-color: #DDDDDD;
}
</style>
<title>Account Details</title>
</head>
<body>
	<h2><a href="indexPage">Home Page</a></h2>
	<form action="getCustomer" method="post">
		<center>
			<h1> Get Customer Details</h1>
			<table border='0' width='600px' cellpadding='10' cellspacing='20'>
				<tr>
					<td style="font-size: 20pt;">Customer Id:</td>
					<td><input type="text" name="customerID"
						style="font-size: 20pt;" required></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Get Details"
						style="font-size: 20pt;"></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>