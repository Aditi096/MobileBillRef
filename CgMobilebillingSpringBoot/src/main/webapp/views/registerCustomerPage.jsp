<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script>
function validate(){
	var alphaExp = /^[a-zA-Z\s]+$/;
	var city=document.getElementById("city").value;
	var state=document.getElementById("state").value;
	var pinCode=document.getElementById("pinCode").value;
	if ( !city.match(alphaExp) ||city==" " || city==null) {
        alert("City must be characters and should not be empty");
        return false;
    }
	if ( !state.match(alphaExp) || state==" " || state==null) {
        alert("State must be characters and should not be empty");
        return false;
    }
	if(isNaN(pinCode) || pinCode<99999|| pinCode>1000000){
		alert("Pin Code must be number with 6 digits");
    return false;
	}
}
</script>
<style>
button {
	background-color: #4CAF50;
	color: white;
	padding: 16px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}
.error{
color:red;
font-weight:bold;
}
.signUp {
	float: left;
	width: 100%;
}
table {
	background-color: #DDDDDD;
}
</style>
</head>
<body>
<h2><a href="indexPage">Home Page</a></h2>
<center>

<form:form action='customerRegistration' method='post' modelAttribute="customer" onsubmit="return validate()" >
		<table border='0' width='600px' cellpadding='10' cellspacing='20'>
		<tr>
				<td style="font-size: 20pt;">First Name:</td>
				<td><form:input path="firstName" id="firstName" size="30" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td style="font-size: 20pt;">Last Name:</td>
				<td><form:input path="lastName" id="lastName" size="30" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td style="font-size: 20pt;">emailId:</td>
				<td><form:input path="emailID" id="emailID" size="30" /></td>
				<td><form:errors path="emailID" cssClass="error" /></td>
			</tr>
			<tr>
				<td style="font-size: 20pt;">Date Of Birth:</td>
				<td><form:input path="dateOfBirth" id="dateOfBirth" size="30" /></td>
				<td><form:errors path="dateOfBirth" cssClass="error" /></td>
			</tr>
			<tr>
				<td style="font-size: 20pt;">City:</td>
				<td><form:input path="billingAddress.city" id="city" size="30"/></td>
				<td><form:errors path="billingAddress.city" cssClass="error" /></td>
			</tr>
			<tr>
				<td style="font-size: 20pt;">State:</td>
				<td><form:input path="billingAddress.state" id="state" size="30"/></td>
				<td><form:errors path="billingAddress.state" cssClass="error" /></td>
			</tr>
			<tr>
				<td style="font-size: 20pt;">Pin Code:</td>
				<td><form:input path="billingAddress.pinCode" id="pinCode" size="30" /></td>
				<td><form:errors path="billingAddress.pinCode" cssClass="error" /></td>
			</tr>
			<tr>
				<td><div align="center"><button type="submit" class="signup"
									style="font-size: 20pt; width: 320px">Submit</button></div></td>
			</tr>
		</table>
	</form:form>
	</center>
</body>
</html>