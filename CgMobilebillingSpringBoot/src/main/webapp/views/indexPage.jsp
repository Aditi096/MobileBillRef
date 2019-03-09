<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mobile Billing</title>
</head>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 18px 24px;
	text-decoration: none;
}

li a:hover {
	background-color: #4CAF50;
}
</style>
<body>
	<header> <font size=6 color="white"> <marquee
			behavior="scroll" direction="left">Mobile Billing</marquee>
	</font> </header>
	<font size=5>
		<ul>
			<li><a href="registerCustomer">Customer Registration</a></li>
			<li><a href="openPostpaidAccount">Create Postpaid Account</a></li>
			<li><a href="generateMonthlyBill">Generate Mobile Bill</a></li>
			<li><a href="getCustomerDetails">Get Customer Details</a></li>
			<li><a href="getAllCustomerDetails">Get All Customer Details</a></li>
			<li><a href="getPostpaidAccountDetails">Get Postpaid Account
					Details</a></li>
			<li><a href="getCustomerAllPostpaidAccountDetails">Get
					Customer All Postpaid Account Details</a></li>
			<li><a href="getMobileBillDetails">Get Mobile Bill Details</a></li>
			<li><a href="getMobileAllBillDetails">Get Mobile All Bill
					Details</a></li>
			<li><a href="changePlan">Change Plan</a></li>
			<li><a href="getCustomerPostPaidAccountPlanDetails">Get
					Customer PostPaidAccount Plan Details</a></li>
			<li><a href="closeCustomerPostPaidAccount">Close Customer
					PostPaid Account</a></li>
			<li><a href="deleteCustomer">Delete Customer</a></li>
		</ul>
	</font>
</body>
</html>