<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<form:form action="customerAllPostPaidAccountDetails" method="post"
			modelAttribute="customer">
			<table>
				<tr>
					<td>Customer ID</td>
					<td><form:input path="customerID" size="30" /></td>
					<td><form:errors path="customerID" cssClass="error" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="PostPaid Account Details"></td>
				</tr>
			</table>
		</form:form>
	</div>

	<div align="center" style="position: relative; top: 100px;">
		<c:if test="${flag==1}">
			<form>
				<table>
					<tr>
						<th>Mobile Number</th>
						<th>Plan ID</th>
					</tr>
					<c:forEach var="postpaidAccount" items="${postpaidAccounts}">

						<tr>
							<td>${postpaidAccount.mobileNo}</td>
							<td>${postpaidAccount.plan.planID}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</c:if>

	</div>
</body>
</html>