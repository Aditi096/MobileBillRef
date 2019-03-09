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
<div align="center" >
<h2>Enter Plan Details </h2>
<div align="right" style="position:absolute; right:500px;" class="w3-container">
 <form name="Home Page" action="index" method="get" >
<table>
<tr>
<td><input type="submit" value="Home Page" class="w3-button w3-indigo"></td>
</tr>
</table>
</form> 
</div>


<form:form action="savePlan" method="post" modelAttribute="plan">
<table>
<tr>
<td>Monthly Rental</td>
<td><form:input path="monthlyRental" size="30"/></td>
<td><form:errors path="monthlyRental" cssClass="error"/></td>
</tr>

<tr>
<td>Free Local Calls</td>
<td><form:input path="freeLocalCalls" size="30"/></td>
<td><form:errors path="freeLocalCalls" cssClass="error"/></td>
</tr>

<tr>
<td>Free Std Calls</td>
<td><form:input path="freeStdCalls" size="30"/></td>
<td><form:errors path="freeStdCalls" cssClass="error"/></td>
</tr>

<tr>
<td>Free Local SMS</td>
<td><form:input path="freeLocalSMS" size="30"/></td>
<td><form:errors path="freeLocalSMS" cssClass="error"/></td>
</tr>

<tr>
<td>Free Std SMS</td>
<td><form:input path="freeStdSMS" size="30"/></td>
<td><form:errors path="freeStdSMS" cssClass="error"/></td>
</tr>

<tr>
<td>Free Internet Data Usage Units</td>
<td><form:input path="freeInternetDataUsageUnits" size="30"/></td>
<td><form:errors path="freeInternetDataUsageUnits" cssClass="error"/></td>
</tr>

<tr>
<td>Local Call Rate</td>
<td><form:input path="localCallRate" size="30"/></td>
<td><form:errors path="localCallRate" cssClass="error"/></td>
</tr>

<td>Std Call Rate</td>
<td><form:input path="stdCallRate" size="30"/></td>
<td><form:errors path="stdCallRate" cssClass="error"/></td>
</tr>

<tr>
<td>Local SMS Rate</td>
<td><form:input path="localSMSRate" size="30"/></td>
<td><form:errors path="localSMSRate" cssClass="error"/></td>
</tr>

<td>Std SMS Rate</td>
<td><form:input path="stdSMSRate" size="30"/></td>
<td><form:errors path="stdSMSRate" cssClass="error"/></td>
</tr>

<tr>
<td>Internet Data Usage Rate</td>
<td><form:input path="internetDataUsageRate" size="30"/></td>
<td><form:errors path="internetDataUsageRate" cssClass="error"/></td>
</tr>

<td>Plan Circle</td>
<td><form:input path="planCircle" size="30"/></td>
<td><form:errors path="planCircle" cssClass="error"/></td>
</tr>

<tr>
<td>Plan Name</td>
<td><form:input path="planName" size="30"/></td>
<td><form:errors path="planName" cssClass="error"/></td>
</tr>
</table>

<tr>
<td><input type="submit" value="Submit">
</td>
</tr>
 
</form:form>
</div>
<div align="center">${requestScope.message}

</div>
</body>
</html>