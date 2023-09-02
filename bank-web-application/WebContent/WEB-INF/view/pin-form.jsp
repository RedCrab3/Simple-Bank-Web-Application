<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Add Customer</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pin-form.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h1>Simple Bank Application</h1>
			</div>
		</div>
		<div id="container">
			<div id="content">
				<form action="detail" method="POST">
					<input type="hidden" name="customerId" id="customerId" value="${customerId}" />
					<table>
						<tr style="text-align:center;">
							<td><label>Enter your pin:</label></td>
							<td>
								<input type="password" name="pin" id="pin" />
							</td>
							<td>
								<button type="submit" style="padding:15px;" class="update-button">Submit</button>
						    </td>
						</tr>
					</table>
				</form>
				<div style="clear; both;"></div>
				<p style="padding-left:240px;">
  					<input type="button" value="Back to List" onclick="window.location.href='${pageContext.request.contextPath}/bank/list';" 
					class="add-button"/>				
				</p>
			</div>
		</div>
	</body>
</html>