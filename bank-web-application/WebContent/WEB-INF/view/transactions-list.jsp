<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.luv2code.springdemo.utils.SortUtils"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Transactions List Page</title>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/style.css">
	<style>
		tr.odd{
			background: #FFFFFF
		}
		tr.even{
			background: #BBBBBB
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h1>Simple Bank Application</h1>
		</div>
	</div>
	
	<c:choose>
		<c:when test="${flag == '1'}">
			<script>
				alert("Invalid Pin");
			</script>
		</c:when>
	</c:choose>
	
	<div id="container">
		<div id="content">

			<!--  HTML Table Here -->
			
			<table>
				<tr>
					<th>Account Number</th>
					<th>Account Owner</th>
					<th>Timestamp</th>
					<th>Amount</th>
				</tr>
				<!-- Loop and print all customers -->
				<c:forEach var="i" items="${transactions}" varStatus="status">
					<tr id="row${i.transaction_id }" class="${status.count % 2 == 0 ? 'even' : 'odd'}">
						<td id="account${i.transaction_id }"></td>
						<td id="name${i.transaction_id }"></td>
						<td>${i.timestamp}</td>
						<td>
							<span id="amount${i.transaction_id }"></span>
							${i.amount}
						</td>
					</tr>
							<script>
								if ("${i.senderAccount}" == "${customer.accountNumber}"){
									document.getElementById("row${i.transaction_id }").style.color="darkred";
									document.getElementById("account${i.transaction_id }").innerHTML= "${i.recipientAccount}";
									document.getElementById("name${i.transaction_id }").innerHTML= "${i.recipientName}";
									document.getElementById("amount${i.transaction_id }").innerHTML= "&#8722";
								}
								else{
									document.getElementById("row${i.transaction_id }").style.color="green";
									document.getElementById("account${i.transaction_id }").innerHTML= "${i.senderAccount}";
									document.getElementById("name${i.transaction_id }").innerHTML= "${i.senderName}";
									document.getElementById("amount${i.transaction_id }").innerHTML= "&#43";
								}
							</script>
				</c:forEach>
			</table>
			 <form method="GET" action="securedCustDet" style="display:inline;">
	        	<input type="hidden" name="customerId" id="customerId" value="${customer.id}" />
				<button type="submit" class="update-button">Back to Details</button>
			</form> 
		</div>
	</div>
</body>
</html>