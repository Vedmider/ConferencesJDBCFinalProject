<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<html>
<head>
    <base href="${pageContext.request.contextPath}/">
</head>
<body>
<header class="header_area">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container box_1620">
                <!-- Brand and toggle get grouped for better mobile display -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto">
                        <li class="nav-item active"><a class="nav-link" href="index"><fmt:message key="header.home"/></a></li>
                        <li class="nav-item"><a class="nav-link" href="about"><fmt:message key="header.about"/></a></li>
                        <c:if test="${sessionScope.user != null}">
                        <li class="nav-item"><a class="nav-link" href="speakers"><fmt:message key="header.speakers"/></a>
                        <li class="nav-item"><a class="nav-link" href="administration"><fmt:message key="header.administration"/></a>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                        <li class="nav-item"><a class="nav-link" href="registration"><fmt:message key="header.registration"/></a>
                        </c:if>
                        <li class ="nav-item">
                            <c:choose>
                            <c:when test="${sessionScope.user == null}">
                                <a class="nav-link" href="login"><fmt:message key="header.login"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" ><fmt:message key="header.welcome"/>  <c:out value="${sessionScope.user.firstName}"/></a>
                                <a class="nav-link" href="logout"><fmt:message key="header.logout"/></a>
                            </c:otherwise>
                            </c:choose>
                        </li>
                        <li class="nav-item submenu dropdown">
                            <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><fmt:message key="header.locale"/> </a>
                            <ul class="dropdown-menu" >
                                <li class="nav-item"><a href="<c:out value="${origRequestURL}"/>?sessionLocale=en" class="nav-link"><fmt:message key="header.locale.en" /></a></li>
                                <li class="nav-item"><a href="<c:out value="${origRequestURL}"/>?sessionLocale=de" class="nav-link"><fmt:message key="header.locale.de" /></a></li>
                                <li class="nav-item"><a href="<c:out value="${origRequestURL}"/>?sessionLocale=fr" class="nav-link"><fmt:message key="header.locale.fr" /></a></li>
                                <li class="nav-item"><a href="<c:out value="${origRequestURL}"/>?sessionLocale=ua" class="nav-link"><fmt:message key="header.locale.ua" /></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
</body>
</html>
