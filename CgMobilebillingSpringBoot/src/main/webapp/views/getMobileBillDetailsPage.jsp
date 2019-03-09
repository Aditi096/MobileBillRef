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
<form action="mobileBillDetails" method="post" name="billform">
		<center>
			<center>
				<h1>Get Mobile Bill Details</h1>
			</center>
			<table border='0' width='600px' cellpadding='10' cellspacing='20'>
				<tr>
					<td style="font-size: 20pt;">Customer Id:</td>
					<td><input type="text" name="customerID"
						style="font-size: 20pt;" required></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;">Mobile No:</td>
					<td><input type="number" name="mobileNo"
						style="font-size: 20pt;" required></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;">Bill Month:</td>
					<td><select name="billMonth" style="font-size: 20pt;" required>
					 <option value="" selected disabled hidden>Select Month</option>
							<option value="January">January</option>
							<option value="February">February</option>
							<option value="March">March</option>
							<option value="April">April</option>
							<option value="May">May</option>
							<option value="June">June</option>
							<option value="July">July</option>
							<option value="August">August</option>
							<option value="September">September</option>
							<option value="October">October</option>
							<option value="November">November</option>
							<option value="December">December</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="reset" value="Reset" style="font-size: 20pt;"></td>
					<td><input type="submit" value="Submit"
						style="font-size: 20pt;"></td>
						<td><input type="submit" value="Bill Pdf"
						style="font-size: 20pt;" onclick="billform.action='billpdfreport';return true;"></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>