<!DOCTYPE html>
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <base href="${pageContext.request.contextPath}/">
    <!-- Font Icon -->
    <link rel="stylesheet" href="ui/registration/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="ui/registration/css/style.css">
</head>
<body>

    <div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form" action="registration">
                        <h2 class="form-title">Create account</h2>
                        <div class="form-group">
                            <input type="text" class="form-input" required="required" name="name" id="login" placeholder="Your Login"/>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-input" required="required" name="email" id="email" placeholder="Your Email"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" required="required" name="name" value="First Name" id="first-name" placeholder="First Name"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" required="required" name="name" value="Last Name" id="last-name" placeholder="Last Name"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" required="required" name="password" id="password" placeholder="Password"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-input" required="required"  name="re_password" id="re_password" placeholder="Repeat your password"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up"/>
                        </div>
                        <div class="register-message"></div>
                    </form>
                    <p class="loginhere">
                        <fmt:message key="register.have.account"/> <a href="login" class="loginhere-link"><fmt:message key="header.login"/></a>
                    </p>
                    <p class="loginhere">
                        <a href="index" class="loginhere-link"><fmt:message key="header.home"/> </a>
                    </p>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="ui/registration/vendor/jquery/jquery.min.js"></script>
    <script src="ui/registration/js/main.js"></script>
</body>
</html>
