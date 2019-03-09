<%@page import="com.cg.mobilebilling.beans.Customer"%>
<%@page import="java.util.ArrayList"%>
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
<section>
<%
		ArrayList<Customer> list = new ArrayList<Customer>();
		list = (ArrayList<Customer>) request.getAttribute("customers");
		if(!list.isEmpty()){
	%>
	<table>
		<tr>
			<th>Customer Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>emailId</th>
			<th>Date of Birth</th>
			<th>City</th>
			<th>State</th>
			<th>Pin Code</th>
		</tr>
		<%
			for (Customer customer:list) {
		%>

		<tr>
			<td><%=customer.getCustomerID()%></td>
			<td><%=customer.getFirstName()%></td>
			<td><%=customer.getLastName()%></td>
			<td><%=customer.getEmailID()%></td>
			<td><%=customer.getDateOfBirth()%></td>
			<td><%=customer.getBillingAddress().getCity()%></td>
			<td><%=customer.getBillingAddress().getState()%></td>
			<td><%=customer.getBillingAddress().getPinCode()%></td>
		</tr>
		<%
			}
		}else{
		%>
		<h3>No Customer Details found!!!!</h3>
		<%} %>
	</table>
	</section>
</body>
</html>