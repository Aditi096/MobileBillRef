<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}

li a {
	float: left;
	background-color: #333;
	display: block;
	color: white;
	text-align: center;
	padding: 16px 20px;
	text-decoration: none;
}

table {
	background-color: #DDDDDD;
}
</style>
<title>Withdraw Page</title>
</head>
<body>
<h2><a href="indexPage">Home Page</a></h2>
	<form action="mobileBillGeneration" method="post" name="billform">
		<center>
			<center>
				<h1>Mobile Bill</h1>
			</center>
			<table border='0' width='600px' cellpadding='10' cellspacing='20'>
				<tr>
					<td style="font-size: 20pt;">Customer Id:</td>
					<td><input type="text" name="customerID"
						style="font-size: 20pt;" required></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;">MobileNo:</td>
					<td><input type="number" name="mobileNo"
						style="font-size: 20pt;"  required></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;" required>Bill Month:</td>
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
					<td style="font-size: 20pt;">No of Local SMS:</td>
					<td><input type="number" name="noOfLocalSMS"
						style="font-size: 20pt;" required></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;">No of Std SMS:</td>
					<td><input type="number" name="noOfStdSMS"
						style="font-size: 20pt;" required></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;" required>No of Local Calls:</td>
					<td><input type="number" name="noOfLocalCalls"
						style="font-size: 20pt;"></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;" required>No of Std Calls:</td>
					<td><input type="number" name="noOfStdCalls"
						style="font-size: 20pt;"></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;" required>Internet Data Usage Units:</td>
					<td><input type="number" name="internetDataUsageUnits"
						style="font-size: 20pt;"></td>
				</tr>
				<tr>
					<td><input type="reset" value="Reset" style="font-size: 20pt;"></td>
					<td><input type="submit" value="Submit"
						style="font-size: 20pt;"></td>
						
						
				</tr>
			</table>
		</center>

	</form>
</body>
</html>