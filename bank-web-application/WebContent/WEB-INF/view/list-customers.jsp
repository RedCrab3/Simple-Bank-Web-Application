<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.luv2code.springdemo.utils.SortUtils"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customers List Page</title>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/resources/css/style.css">
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
					<th>Detail View</th>
				</tr>
				<!-- Loop and print all customers -->
				<c:forEach var="i" items="${customers}">
					<tr>
						<td>${i.accountNumber}</td>
						<td>${i.firstName} ${i.lastName}</td>
						<td>
							
								<!-- action="${pageContext.request.contextPath}/bank/detail"
								method="POST" style="display: inline;"-->
								<form action="getPin" method="POST">
								<input type="hidden" value=${i.id } name="customerId" id="customerId"/>								
								<button type="submit" class="update-button">Detail</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>