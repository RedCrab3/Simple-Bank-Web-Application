<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.luv2code.springdemo.utils.SortUtils"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>OTP Form</title>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/css/style.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h1>Simple Bank Application</h1>
			</div>
		</div>
		<script>
			var deadline = 60;
		</script>
		<div id="container">
			<div id="content">
				<form action="verify" method="POST">
					<!--  HTML Table Here -->
					<input type="hidden" name="Sender" id="Sender" value="${Sender}" />
					<input type="hidden" name="Recipient" id="Recipient" value="${Recipient}" />
					<input type="hidden" name="Amount" id="Amount" value="${Amount}" />
					<label for="otp">Enter your four digit One time password sent to your registered mobile number</label>
					<input type="number" name="otp" id="otp" maxlength="5" size="5" required />
					<button type="submit" class="update-button" id="submit" style="padding:15px;">Submit</button>
					<span id="timer">01:00</span>
					<script>
					var start = 1;
					var x = setInterval(function() {
						var diff = deadline-start;
						if (diff >= 10)
							document.getElementById("timer").innerHTML = "00:" + diff;
						else
							document.getElementById("timer").innerHTML = "00:0" + diff;
						if (diff<0) {
							clearInterval(x);
							document.getElementById("timer").innerHTML = "00:00";
							document.getElementById("submit").disabled = true;
						}
						start = start + 1;
					},1000);		
					</script>	
				</form>
			</div>
		</div>
		<div id="container">
			<div id="content">
				<form method="POST" action="OTP">
					<input type="hidden" name="Sender" id="Sender" value="${Sender}" />
					<input type="hidden" name="Recipient" id="Recipient" value="${Recipient}" />
					<input type="hidden" name="Amount" id="Amount" value="${Amount}" />
					<input type="submit" value="Resend OTP" />
				</form>
			</div>
		</div>
	</body>
</html>