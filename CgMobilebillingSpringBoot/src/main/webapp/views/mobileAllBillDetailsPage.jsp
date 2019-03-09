<%@page import="com.cg.mobilebilling.beans.Bill"%>
<%@page import="java.util.ArrayList"%>
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
<%
		ArrayList<Bill> list = new ArrayList<Bill>();
		list = (ArrayList<Bill>) request.getAttribute("bills");
		if(!list.isEmpty()){
	%>
	<table>
		<tr>
			<th>Bill Id:</th>
			<th>Mobile No:</th>
			<th>Bill Month:</th>
			<th>localSMSAmount</th>
			<th>stdSMSAmount</th>
			<th>localCallAmount</th>
			<th>stdCallAmount</th>
			<th>internetDataUsageAmoun</th>
			<th>cgst</th>
			<th>sgst</th>
			<th>totalBillAmount</th>
		</tr>
		<%
			for (Bill bill:list) {
		%>

		<tr>
			<td><%=bill.getBillID()%></td>
			<td><%=bill.getPostpaidAccount().getMobileNo()%></td>
			<td><%=bill.getBillMonth()%></td>
			<td><%=bill.getLocalSMSAmount()%></td>
			<td><%=bill.getStdSMSAmount()%></td>
			<td><%=bill.getLocalCallAmount()%></td>
			<td><%=bill.getStdCallAmount()%></td>
			<td><%=bill.getInternetDataUsageAmount()%></td>
			<td><%=bill.getCgst()%></td>
			<td><%=bill.getSgst()%></td>
			<td><%=bill.getTotalBillAmount()%></td>
		</tr>
		<%
			}
		}else{
		%>
		<h2> Bill Details Not Found!!! </h2>
		<%} %>
	</table>
</body>
</html>