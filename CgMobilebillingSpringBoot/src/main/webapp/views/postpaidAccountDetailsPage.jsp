<%@page import="com.cg.mobilebilling.beans.PostpaidAccount"%>
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

		<h1>Postpaid Account Details</h1>
	</header>
	<section>
	<%PostpaidAccount postpaid=(PostpaidAccount)request.getAttribute("postpaid");%>
	<table  align="center" border='0' width='600px' cellpadding='10'
		cellspacing='0'>
			<tr><td>Mobile No:</td><td><%=postpaid.getMobileNo() %></td></tr>
			<tr><td>Customer Id:</td><td><%=postpaid.getCustomer().getCustomerID()%></td></tr>
			<tr><td>Plan Id:</td><td><%=postpaid.getPlan().getPlanID() %></td></tr>
			
	</table>
	</section>
</body>
</html>