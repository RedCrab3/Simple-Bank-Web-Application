<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Pin</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h1>Simple Bank Application</h1>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<form:form action="saveCustomer" modelAttribute="customer"
				method="POST">
				<!-- Associate form data with customer id for update purposes
				Very important as without it context will be lost that this is an update
				not an new addition by losing the original customer id and saving it as 
				a new entry. This will track the customer so that backend can know which
				customer is the update operation being conducted on -->
				<form:hidden path="id" />
				<table>
					<tr>
						<td><label>First Name:*</label></td>
						<td><form:input path="firstName" /> <form:errors
								path="firstName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Last Name:*</label></td>
						<td><form:input path="lastName" /> <form:errors
								path="lastName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Email:*</label></td>
						<td><form:input path="email" /> <form:errors path="email"
								cssClass="error" /></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;"><input
							type="submit" value="Save" class="save" /></td>
					</tr>
				</table>
			</form:form>
			<div style=""></div>
			<p style="padding-left: 240px;">
				<input type="button" value="Back to List"
					onclick="window.location.href='${pageContext.request.contextPath}/customer/list';"
					class="add-button" />
			</p>
		</div>
	</div>
</body>
</html>