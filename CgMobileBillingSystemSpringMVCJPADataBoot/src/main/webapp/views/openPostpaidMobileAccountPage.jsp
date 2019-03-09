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
<div align="right" style="position:absolute; right:500px;">
 <form name="Home Page" action="index" method="get" >
<table>
<tr>
<td><input type="submit" value="Home Page"></td>
</tr>
</table>
</form> 
</div>

<table>

<form:form action="openPostpaidAccount" method="post" modelAttribute="customer">
<tr>
<td>Customer ID</td>
<td><form:input path="customerID" size="30"/></td>
<td><form:errors path="customerID" cssClass="error"/></td>
</tr>

<tr>
<td>Plan ID</td>
<td><input type="text" name="planID" size="30"/></td>
<%-- <td><form:errors path="postpaidAccounts.plan.planID" cssClass="error"/></td> --%>
</tr>

<td><input type="submit" value="Submit"></td>
</tr>
</form:form>
</table>

</div>
<div align="center">${requestScope.message}

</div>
</body>
</html>