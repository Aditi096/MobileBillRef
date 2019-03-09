<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<h2>Generate Bill</h2>
		<div align="right" style="position: absolute; right: 500px;">
			<form name="Home Page" action="index" method="get">
				<table>
					<tr>
						<td><input type="submit" value="Home Page"></td>
					</tr>
				</table>
			</form>
		</div>
		<form:form action="generateMonthlyBill" method="post" modelAttribute="bill">
			<table>
				<tr>
					<td>Customer ID</td>
					<td><input type="text" name="customerID" size="30" /></td>
					<%-- 	<td><form:errors path="customerID" cssClass="error" /></td> --%>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td><input type="text" name="mobileNo" size="30" /></td>
					<%-- 	<td><form:errors path="mobileNo" cssClass="error" /></td> --%>
				</tr>
				<tr>
					<td>Number of Local SMS</td>
					<td><form:input path="noOfLocalSMS" size="30" /></td>
					<td><form:errors path="noOfLocalSMS" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Number of Std SMS</td>
					<td><form:input path="noOfStdSMS" size="30" /></td>
					<td><form:errors path="noOfStdSMS" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Number of Local Calls</td>
					<td><form:input path="noOfLocalCalls" size="30" /></td>
					<td><form:errors path="noOfLocalCalls" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Number of Std Calls</td>
					<td><form:input path="noOfStdCalls" size="30" /></td>
					<td><form:errors path="noOfStdCalls" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Internet Data Usage Units</td>
					<td><form:input path="internetDataUsageUnits" size="30" /></td>
					<td><form:errors path="internetDataUsageUnits"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td>Bill Month</td>
					<td><form:input path="billMonth" size="30" /></td>
					<td><form:errors path="billMonth" cssClass="error" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div align="center">${requestScope.message}</div>

	</div>
</body>
</html>