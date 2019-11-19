<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<html>
<head>
    <base href="${pageContext.request.contextPath}/">
</head>
<body>
Session scope <c:out value="${sessionScope.get(locale)}"/>
simple value <c:out value="${locale}"/>

Session scope <c:out value="${sessionScope.get(bundle)}"/>
simple value <c:out value="${bundle}"/>
<header class="header_area">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container box_1620">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h" href="app/index"><img src="ui/img/logo.png" alt=""></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto">
                        <li class="nav-item active"><a class="nav-link" href="index">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="ui/about-us.html">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="ui/speakers.html">Speakers</a>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Pages</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="ui/schedule.html">Schedule</a>
                                <li class="nav-item"><a class="nav-link" href="ui/venue.html">Venue</a>
                                <li class="nav-item"><a class="nav-link" href="ui/price.html">Pricing</a>
                                <li class="nav-item"><a class="nav-link" href="ui/elements.html">Elements</a></li>
                            </ul>
                        </li>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Blog</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="ui/blog.html">Blog</a></li>
                                <li class="nav-item"><a class="nav-link" href="ui/single-blog.html">Blog Details</a></li>
                            </ul>
                        </li >
                        <li class ="nav-item">
                            <c:choose>
                            <c:when test="${sessionScope.user == null}">
                                <a class="nav-link" href="login">Log in</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" >Welcome <c:out value="${sessionScope.user.firstName}"/></a>
                                <a class="nav-link" href="logout">Log out</a>
                            </c:otherwise>
                            </c:choose>
                        </li>
                        <li class="nav-item submenu dropdown">
                            <a href="" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Locale</a>
                            <ul class="dropdown-menu" >
                                <li class="nav-item"><a href="?sessionLocale=en" class="nav-link"><fmt:message key="header.locale.en" /></a></li>
                                <li class="nav-item"><a href="?sessionLocale=de" class="nav-link"><fmt:message key="header.locale.de" /></a></li>
                                <li class="nav-item"><a href="?sessionLocale=fr" class="nav-link"><fmt:message key="header.locale.fr" /></a></li>
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
