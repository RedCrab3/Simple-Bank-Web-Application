<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.luv2code.springdemo.utils.SortUtils"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Transaction Form</title>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/css/style.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h1>Simple Bank Application</h1>
			</div>
		</div>
		
		<div id="container">
			<div id="content">
	
				<form action="OTP" method="POST">
					<!--  HTML Table Here -->
					<input type="hidden" name="Sender" id="Sender" value="${customerId}" />
					<table>
						<tr>
							<th>Select Recipient</th>
							<td>
								<select name="Recipient" style="font-style:'Dongle', sans-serif; font-size: 18px; background: #BBBBBB; line-height: 1.5; color: #495057; background-color: #fff; background-clip: padding-box; border: 1px solid #ced4da; border-radius: .25rem; transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out; padding:4px;" required>
									<option value="" selected disabled hidden>Select a recipient</option>
									<c:forEach var="i" items="${recipientCustomers}">
										<option value="${i.id}">
											${i.firstName} ${i.lastName} ${i.accountNumber}
										</option>
									</c:forEach>
								</select>
							</td>
							<th rowspan="2">
								<button type="submit" style="padding:15px;">Submit</button>
							</th>	
						</tr>
						<tr>
							<th>Enter Amount</th>
							<td>
								<div>
								  <span style="font-size:18px;">&#8377;</span>
								  <input type="number" name="Amount" required/>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>