<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="changePlanDetails" method="post">
<h2><a href="indexPage">Home Page</a></h2>
		<center>
		
			<center>
				<h1>Get Postpaid Account</h1>
			</center>
			<table border='0' width='600px' cellpadding='10' cellspacing='20'>
				<tr>
					<td style="font-size: 20pt;">Customer Id:</td>
					<td><input type="text" name="customerID"
						style="font-size: 20pt;" required></td>
				</tr>
						<tr>
					<td style="font-size: 20pt;">Mobile Number:</td>
					<td><input type="number" name="mobileNo"
						style="font-size: 20pt;" required></td>
				</tr>
				<tr>
					<td style="font-size: 20pt;">Plan Id:</td>
					<td><input type="radio" name="planID"
							style="font-size: 20pt;" value="1">1&nbsp; <input
							type="radio" name="planID" style="font-size: 14pt;"
							value="2">2&nbsp; <input type="radio"
							name="planID" style="font-size: 14pt;" value="3" required>3
						</td>
				</tr>
				<tr><td></td><td><a href="getAllPlanDetails">Get All Plan Details:</a></tr>
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