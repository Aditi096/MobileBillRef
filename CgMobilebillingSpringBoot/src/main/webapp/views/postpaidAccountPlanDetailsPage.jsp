<%@page import="com.cg.mobilebilling.beans.Plan"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
h2{
color:red;
font-weight:bold;
}
</style>
</head>
<body>
<h2><a href="indexPage">Home Page</a></h2>
<%
		Plan plan = (Plan) request.getAttribute("plan");
	%>
	<table>
		<tr>
			<th>Plan Id</th>
			<th>Monthly Rental</th>
			<th>freeLocalCalls</th>
			<th> freeStdCalls</th>
			<th>freeLocalSMS</th>
			<th> freeStdSMS</th>
			<th> freeInternetDataUsageUnits</th>
			<th>localCallRate
			</th>
			<th>stdCallRate</th>
			<th>localSMSRate</th>
			<th>stdSMSRate</th>
			<th> internetDataUsageRate</th>
			<th>planCircle</th>
			<th> planName</th>
		</tr>
		<tr>
			<td><%=plan.getPlanID()%></td>
			<td><%=plan.getMonthlyRental()%></td>
			<td><%=plan.getFreeLocalCalls()%></td>
			<td><%=plan.getFreeStdCalls()%></td>
			<td><%=plan.getFreeLocalSMS()%></td>
			<td><%=plan.getFreeStdSMS()%></td>
			<td><%=plan.getFreeInternetDataUsageUnits()%></td>
			<td><%=plan.getLocalCallRate()%></td>
			<td><%=plan.getStdCallRate()%></td>
			<td><%=plan.getLocalSMSRate()%></td>
			<td><%=plan.getStdSMSRate()%></td>
			<td><%=plan.getInternetDataUsageRate()%></td>
			<td><%=plan.getPlanCircle()%></td>
			<td><%=plan.getPlanName()%></td>
		</tr>
</body>
</html>