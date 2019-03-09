<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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


	<div align="right" style="position: absolute; right: 500px;">
		<form name="Home Page" action="index" method="get">
			<table>
				<tr>
					<td><input type="submit" value="Home Page"></td>
				</tr>
			</table>
		</form>
	</div>

	<div align="center" style="position:relative; top:50px;">
	<table   cellpadding="10">
	<tr>
		<th>Monthly Rental</th>
		<th>Free Local Calls</th>
		<th>Free Std Calls</th>
		<th>Free Local SMS</th>
		<th>Free Std SMS</th>
		<th>Free Internet Data Usage Units</th>
		<th>Local Call Rate</th>
		<th>Std Call Rate</th>
		<th>Local SMS Rate</th>
		<th>Std SMS Rate</th>
		<th>Internet Data Usage Rate</th>
		<th>Plan Circle</th>
		<th>Plan Name</th>
		</tr>
		<c:forEach var="plan" items="${plans}">
				<tr>
					<td>${plan.monthlyRental}</td>
					<td>${plan.freeLocalCalls }</td>
					<td>${plan.freeStdCalls}</td>
					<td>${plan.freeLocalSMS}</td>
					<td>${plan.freeStdSMS }</td>
					<td>${plan.freeInternetDataUsageUnits}</td>
					<td>${plan.localCallRate}</td>
					<td>${plan.stdCallRate }</td>
					<td>${plan.localSMSRate }</td>
					<td>${plan.stdSMSRate }</td>
					<td>${plan.internetDataUsageRate }</td>
					<td>${plan.planCircle}</td>
					<td>${plan. planName }</td>
				</tr>
		
		</c:forEach>
			</table>

	</div>

</body>
</html>