<!DOCTYPE html>
<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>
<html lang="${sessionScope.locale}">
<head>
	<title><fmt:message key="header.administration"/></title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<base href="${pageContext.request.contextPath}/">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="ui/administration-page-resources/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ui/administration-page-resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ui/administration-page-resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ui/administration-page-resources/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ui/administration-page-resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ui/administration-page-resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ui/administration-page-resources/css/util.css">
	<link rel="stylesheet" type="text/css" href="ui/administration-page-resources/css/main.css">
<!--===============================================================================================-->
</head>
<body>
<c:import url="header.jsp" />
	<c:if test="${sessionScope.conferences != null}">
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
					<div class="table">
						<div class="row header">
							<div class="cell">
								ID
							</div>
							<div class="cell">
								Theme
							</div>
							<div class="cell">
								PlannedDateTime
							</div>
							<div class="cell">
								HappenedDateTime
							</div>
							<div class="cell">
								Address
							</div>
							<c:if test="${sessionScope.role == 'ADMIN'}">
								<div class="cell">
									Delete
								</div>
							</c:if>
						</div>
						<c:forEach items="${sessionScope.conferences}" var="conference">
						<div class="row">
							<div class="cell" data-title="ID">
								<c:out value="${conference.id}"/>
							</div>
							<div class="cell" data-title="Theme">
								<c:out value="${conference.theme}"/>
							</div>
							<div class="cell" data-title="Planned date">
								<c:out value="${conference.plannedDateTime}"/>
							</div>
							<div class="cell" data-title="Happened date">
								<c:out value="${conference.happenedDateTime}"/>
							</div>
							<div class="cell" data-title="Address">
								<c:out value="${conference.address}"/>
							</div>
							<c:if test="${sessionScope.role == 'ADMIN'}">
								<div class="cell" data-title="Delete">
									<a href="db-action?entity=conference&type=delete&id=<c:out value="${conference.id}"/>">Delete</a>
								</div>
							</c:if>
						</div>
						</c:forEach>
					</div>
				Create/Update Conference
				<form action="db-action" id="db-action-form">
					<label for="id">ID</label>
					<input type="text" name="id" id="id" value=""><br>
					<label for="theme">Theme</label>
					<input type="text" name="theme" id="theme" value="" ><br>
					<label for="plannedDateTime">Planned date</label>
					<input type="datetime-local" name="plannedDateTime" id="plannedDateTime" value="" ><br><br>
					<label for="happenedDateTime">Happened date</label>
					<input type="datetime-local" name="happenedDateTime" id="happenedDateTime" value="" ><br><br>
					<label for="type">Type of operation</label>
					<select id="type">
						<option value="create">Create</option>
						<option value="update">Update</option>
					</select>
					<input type="submit" value="Submit">
				</form>
			<c:forEach items="${sessionScope.conferences}" var="conference">
				<h3>Conference: </h3> <c:out value="${conference.theme}"/>
				<h3>Reports</h3>
				<div class="table">
					<div class="row header">
						<div class="cell">
							ID
						</div>
						<div class="cell">
							Title
						</div>
						<div class="cell">
							Speaker
						</div>
						<div class="cell">
							Time start
						</div>
						<div class="cell">
							Number of registered
						</div>
						<div class="cell">
							Number of attended
						</div>
					</div>
					<c:forEach items="${conference.reports}" var="report">
						<div class="row">
							<div class="cell" data-title="ID">
								<c:out value="${report.id}"/>
							</div>
							<div class="cell" data-title="Title">
								<c:out value="${report.title}"/>
							</div>
							<div class="cell" data-title="Speaker">
								<c:set var="fullName" value="${report.speaker.firstName}  ${report.speaker.lastName}"/>
								<c:out value="${fullName}"/>
							</div>
							<div class="cell" data-title="Time start">
								<c:out value="${report.timeStart}"/>
							</div>
							<div class="cell" data-title="Registered">
								<c:out value="${report.registered}"/>
							</div>
							<div class="cell" data-title="Attended">
								<c:out value="${report.attended}"/>
							</div>
						</div>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
	</div>
	</c:if>
	<c:if test="${sessionScope.speakers != null}">
		<table>
			<tr>
				<th>ID</th>
				<th>Full name</th>
				<th>Address</th>
				<th>Planned date</th>
			</tr>
		<c:forEach items="${sessionScope.speakers}" var="speaker">
			<tr>
				<td><c:out value="${speaker.id}"/></td>
				<td><c:set var="fullName" value="${speaker.firstName}  ${speaker.lastName}"/>
					<c:out value="${fullName}"/></td>
				<td><c:out value="${speaker.email}"/></td>
				<td><c:out value="${speaker.rating}"/></td>
				<td><c:out value="${speaker.bonuses}"/></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<c:if test="${sessionScope.users != null}">
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table">
					<div class="row header">
						<div class="cell">
							ID
						</div>
						<div class="cell">
							Full name
						</div>
						<div class="cell">
							Login
						</div>
						<div class="cell">
							Email
						</div>
						<div class="cell">
							Role
						</div>
					</div>
					<c:forEach items="${sessionScope.users}" var="user">
						<div class="row">
							<div class="cell" data-title="ID">
								<c:out value="${user.id}"/>
							</div>
							<div class="cell" data-title="Theme">
								<td><c:set var="fullName" value="${user.firstName}  ${user.lastName}"/>
									<c:out value="${fullName}"/></td>
							</div>
							<div class="cell" data-title="Planned date">
								<c:out value="${user.login}"/>
							</div>
							<div class="cell" data-title="Happened date">
								<c:out value="${user.email}"/>
							</div>
							<div class="cell" data-title="Address">
								<c:out value="${user.role.roleTitle}"/>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	</c:if>

<!--===============================================================================================-->	
	<script src="ui/administration-page-resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="ui/administration-page-resources/vendor/bootstrap/js/popper.js"></script>
	<script src="ui/administration-page-resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="ui/administration-page-resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="ui/administration-page-resources/js/main.js"></script>

</body>
</html>