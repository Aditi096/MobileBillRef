<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mobile Bill</title>
</head>
<body>
		<h2><a href="indexPage">Home Page</a></h2>
<center>
		<h1>Mobile Bill</h1>

	</center>
	<table  align="center" border='0' width='600px' cellpadding='10'
		cellspacing='0'>
			<tr><td>Bill Id</td><td>${bill.billID}</td></tr>
			<tr><td>Bill Month</td><td>${bill.billMonth}</td></tr>
			<tr><td>localSMSAmount</td><td>${bill.localSMSAmount}</td></tr>
			<tr><td>stdSMSAmount</td><td>${bill.stdSMSAmount}</td></tr>
			<tr><td>localCallAmount</td><td>${bill.localCallAmount}</td></tr>
			<tr><td>stdCallAmount</td><td>${bill.stdCallAmount}</td></tr>
			<tr><td>internetDataUsageAmount</td><td>${bill.internetDataUsageAmount}</td></tr>
			<tr><td>cgst</td><td>${bill.cgst}</td></tr>
			<tr><td>sgst</td><td>${bill.sgst}</td></tr>
			<tr><td>totalBillAmount</td><td>${bill.totalBillAmount}</td></tr>
	</table>
</body>
</html>