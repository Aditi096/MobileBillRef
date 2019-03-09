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
		<div align="right" style="position: absolute; right: 500px;">
			<form name="Home Page" action="index" method="get">
				<table>
					<tr>
						<td><input type="submit" value="Home Page"></td>
					</tr>
				</table>
			</form>
		</div>
		<table>
			<form:form action="postPaidAccountPlanDetails" method="post"
				modelAttribute="plan">

				<tr>
					<td>Customer Id</td>
					<td><input type="text" name="customerID" size="30" /></td>
					<%-- <td><form:errors path="customerID" cssClass="error" /></td> --%>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td><input type="text" name="mobileNo" size="30" /></td>
					<%-- 	<td><form:errors path="mobileNo" cssClass="error" /></td> --%>
				</tr>

				<tr>
					<td><input type="submit" value="Submit"></td>
				</tr>

			</form:form>
		</table>
	</div>
	<div align="center" style="position: relative; top: 100px;">
		<c:if test="${flag==1}">
			<table cellpadding="10">
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
				<tr>
					<td>Rs. ${plan.monthlyRental}</td>
					<td>${plan.freeLocalCalls } Minutes</td>
					<td>${plan.freeStdCalls}Minutes</td>
					<td>${plan.freeLocalSMS}SMS</td>
					<td>${plan.freeStdSMS }SMS</td>
					<td>${plan.freeInternetDataUsageUnits}GB</td>
					<td>${plan.localCallRate}p/sec</td>
					<td>${plan.stdCallRate }p/sec</td>
					<td>${plan.localSMSRate }Rs/SMS</td>
					<td>${plan.stdSMSRate }Rs/SMS</td>
					<td>${plan.internetDataUsageRate }p/KB</td>
					<td>${plan.planCircle}</td>
					<td>${plan. planName }</td>
				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>