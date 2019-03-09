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
	border:2px solid red;
    border-collapse: collapse;
    border-radius:40px;
}
table, th, td {
    border-bottom: 2px solid red;
}
</style>
</head>
<body>

<div align="center">
<div align="right" style="position:absolute; right:500px;">
 <form name="Home Page" action="index" method="get" >
<table>
<tr>
<td><input type="submit" value="Home Page"></td>
</tr>
</table>
</form> 
</div>


<div align="center">
	
		<form:form action="postpaidAccountDetails" method="post"
			modelAttribute="customer">
			<table>
			
			<tr>
					<td>Customer ID</td>
					<td><form:input path="customerID" size="30" /></td>
					<td><form:errors path="customerID" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td><input type="text" name="mobileNo" size="30" /></td>
					<%-- <td><form:errors path="mobileNo" cssClass="error" /></td> --%>
				</tr>
				<tr>
					<td><input type="submit" value="Get Postpaid Account Details"></td>
				</tr>
			</table>
		</form:form>
	</div>
	
	
	<div align="center" style="position:relative; top:50px;">
	<c:if test="${flag==1}">
			<table cellpadding="20">
			<tr>
			<th>Mobile Number</th>
			<th>Plan ID</th>
			</tr>
			<tr>
					<td>${postpaidAccount.mobileNo}</td>
					<td>${postpaidAccount.plan.planID}</td>
				</tr>
			</table>
			</c:if>
</div>
	
	</div>
</body>
</html>