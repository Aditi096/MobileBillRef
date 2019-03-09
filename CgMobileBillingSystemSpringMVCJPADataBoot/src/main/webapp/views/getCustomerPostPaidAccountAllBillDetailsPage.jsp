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
		
		<form:form action="customerPostPaidAccountAllBillDetails" method="post"
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
					<td><input type="submit" value="PostPaid Account All Bill Details"></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div align="center">
	<c:if test="${flag==1}">
	<form>
		
	<c:forEach var="bill" items="${bills}">
		<table border="1">
			<tr>
					<td>Bill ID</td>
					<td>${bill.billID}</td>
				</tr>
				
					<tr>
					<td>No Of Local SMS</td>
					<td>${bill.noOfLocalSMS}</td>
				</tr>
				<tr>
					<td>No Of Std SMS</td>
					<td>${bill.noOfStdSMS}</td>
				</tr>
				<tr>
					<td>No Of Local Calls</td>
					<td>${bill.noOfLocalCalls}</td>
				</tr>
				<tr>
					<td>No Of Std Calls</td>
					<td>${bill.noOfStdCalls}</td>
				</tr>
				
				<tr>
					<td>Internet Data Usage Units</td>
					<td>${bill.internetDataUsageUnits}</td>
				</tr>
				<tr>
					<td>Total Bill Amount</td>
					<td>${bill.totalBillAmount}</td>
				</tr>
				<tr>
					<td>Local SMS Amount</td>
					<td>${bill.localSMSAmount}</td>
				</tr>
				<tr>
					<td>Plan ID</td>
					<td>${bill.stdSMSAmount}</td>
				</tr>
				<tr>
					<td>Std SMS Amount</td>
					<td>${bill.localCallAmount}</td>
				</tr>
				<tr>
					<td>Std Call Amount</td>
					<td>${bill.stdCallAmount}</td>
				</tr>
				<tr>
					<td>Internet Data Usage Amount</td>
					<td>${bill.internetDataUsageAmount}</td>
				</tr>
				<tr>
					<td>Services Tax</td>
					<td>${bill.servicesTax}</td>
				</tr>
				<tr>
					<td>Vat</td>
					<td>${bill.vat}</td>
				</tr>
				<br><br><br><br>
			</table>
			</c:forEach>
			</form>
			</c:if>
</div>
</div>
</body>
</html>