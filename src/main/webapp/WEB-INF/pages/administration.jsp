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
								plannedDateTime
							</div>
							<div class="cell">
								happenedDateTime
							</div>
							<div class="cell">
								address
							</div>
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