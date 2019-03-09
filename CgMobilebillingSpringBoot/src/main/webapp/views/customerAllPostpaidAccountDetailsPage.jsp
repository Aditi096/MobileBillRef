<%@page import="com.cg.mobilebilling.beans.PostpaidAccount"%>
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
	<h1 align="center">Postpaid Account Details:</h1>
	
	</header>
<%
		ArrayList<PostpaidAccount> list = new ArrayList<PostpaidAccount>();
		list = (ArrayList<PostpaidAccount>) request.getAttribute("postpaids");
		if(!list.isEmpty()){
	%>
	<section> <br>
	<table border="1" align="center">
		<tr>
			<th>Mobile No</th>
			<th>Plan Id</th>
			<th>Customer Id</th>
		</tr>
		<%
			for (PostpaidAccount postpaidAccount:list) {
		%>

		<tr>
			<td><%=postpaidAccount.getMobileNo()%></td>
			<td><%=postpaidAccount.getPlan().getPlanID()%></td>
			<td><%=postpaidAccount.getCustomer().getCustomerID()%></td>
		</tr>
		<%
			}
		}else{
		%>
		<h2>No postpaid Accounts found!!!</h2>
		<%} %>
	</table>
	</section>
</body>
</html>