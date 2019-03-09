<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<h2>Get Bill Details</h2>
		<div align="right" style="position: absolute; right: 500px;">
			<form name="Home Page" action="index" method="get">
				<table>
					<tr>
						<td><input type="submit" value="Home Page"></td>
					</tr>
				</table>
			</form>
		</div>
		
		
		<form:form action="mobileBillDetails" method="post"
			modelAttribute="bill">
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
					<td>Bill Month</td>
					<td><form:input path="billMonth" size="30" /></td>
					<td><form:errors path="billMonth" cssClass="error" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Bill Details"></td>
				</tr>
			</table>
		</form:form>
	</div>
<div align="center">
<c:if test="${flag==1 }">
			<table>
		<tr>
		<td>${requestScope.bill}	</td>
		</tr>
		</table>
		</c:if>
</div>
</body>
</html>