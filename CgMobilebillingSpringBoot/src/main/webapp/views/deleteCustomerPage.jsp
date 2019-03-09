<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<title>Delete Customer</title>
</head>
<body>
<h2><a href="indexPage">Home Page</a></h2>
<header>
	<h1 align="center">Delete Customer</h1>
	
	</header>
	<section>
	<form action="deleteCustomerSuccessful" method="post">
			<table border='0' width='600px' cellpadding='10' cellspacing='20' align="center">
				<tr>
					<th>Customer Id:</th>
					<td><input type="text" name="customerID" required></td>
				</tr>
					<td></td>
					<td><input type="submit" value="Submit" ></td>
				</tr>
			</table>
	</form>
	<div align="center"><h1><font color="red">${errorMessage}</font></h1></div>
	</section>

</body>
</html>