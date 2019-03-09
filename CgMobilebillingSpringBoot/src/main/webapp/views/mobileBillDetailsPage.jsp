<%@page import="com.cg.mobilebilling.beans.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2><a href="indexPage">Home Page</a></h2>
<center>
		<h1>Mobile Bill Details</h1>
	</center>
	<%Bill bill=(Bill)request.getAttribute("bill");%>
	<table  align="center" border='0' width='600px' cellpadding='10'
		cellspacing='0'>
			<tr><td>Bill Id:</td><td><%=bill.getBillID()%></td></tr>
			<tr><td>Mobile No:</td><td><%=bill.getPostpaidAccount().getMobileNo()%></td></tr>
			<tr><td>Bill Month:</td><td><%=bill.getBillMonth()%></td></tr>
			<tr><td>localSMSAmount</td><td><%=bill.getLocalSMSAmount()%></td></tr>
			<tr><td>stdSMSAmount</td><td><%=bill.getStdSMSAmount()%></td></tr>
			<tr><td>localCallAmount</td><td><%=bill.getLocalCallAmount()%></td></tr>
			<tr><td>stdCallAmount</td><td><%=bill.getStdCallAmount()%></td></tr>
			<tr><td>internetDataUsageAmount</td><td><%=bill.getInternetDataUsageAmount()%></td></tr>
			<tr><td>cgst</td><td><%=bill.getCgst()%></td></tr>
			<tr><td>sgst</td><td><%=bill.getSgst()%></td></tr>
			<tr><td>totalBillAmount</td><td><%=bill.getTotalBillAmount()%></td></tr>
			
			
	</table>
</body>
</html>