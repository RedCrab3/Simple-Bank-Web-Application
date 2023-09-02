<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.luv2code.springdemo.utils.SortUtils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Details Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h1>Welcome! ${customer.firstName} ${customer.lastName}</h1>
		</div>
	</div>
	
	<c:choose>
		<c:when test="${flag == 'invalid_OTP'}">
			<script>
				alert("Transaction failed: Invalid OTP");
			</script>
		</c:when>
		<c:when test="${flag == 'insufficient_Balance'}">
			<script>
				alert("Transaction failed: Insufficient Balance in sender's bank account");
			</script>
		</c:when>
	</c:choose>

	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Account Number</th>
					<td>${customer.accountNumber}</td>
				</tr>
				<tr>
					<th>Bank Balance</th>
					<td>&#8377; <fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${customer.balance}"/></td>
				</tr>
				<tr>
					<th>Email Id</th>
					<td>${customer.email}</td>
				</tr>
				<tr>
					<th>Phone Number</th>
					<td>${customer.number}</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<form method="GET" action="list" style="display:inline;">
				<button type="submit" class="update-button">Back to Customer List</button>
			</form>
			<form method="POST" action="transaction" style="display:inline;">
				<input type="hidden" name="Sender" id="Sender" value="${customer.id}" />
				<button type="submit" class="update-button">Perform a Transaction</button>
			</form>
			<form method="POST" action="transactionList" style="display:inline;">
				<input type="hidden" name="customerId" id="customerId" value="${customer.id}" />
				<button type="submit" class="update-button">Transaction History</button>
			</form>
		</div>
	</div>
</body>
</html>