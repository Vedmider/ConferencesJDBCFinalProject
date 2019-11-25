<!doctype html>
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
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="ui/img/favicon.png" type="image/png">
        <title></title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="ui/css/bootstrap.css">
        <link rel="stylesheet" href="ui/vendors/linericon/style.css">
        <link rel="stylesheet" href="ui/css/font-awesome.min.css">
        <link rel="stylesheet" href="ui/vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="ui/vendors/lightbox/simpleLightbox.css">
        <link rel="stylesheet" href="ui/vendors/nice-select/css/nice-select.css">
        <link rel="stylesheet" href="ui/vendors/animate-css/animate.css">
        <!-- main css -->
        <link rel="stylesheet" href="ui/css/style.css">
        <link rel="stylesheet" href="ui/css/responsive.css">
    </head>
    <body>
        
        <!--================Header Menu Area =================-->
        <header class="header_area">
            <div class="main_menu">
            	<nav class="navbar navbar-expand-lg navbar-light">
					<div class="container box_1620">
						<!-- Brand and toggle get grouped for better mobile display -->
						<a class="navbar-brand logo_h" href="index"><fmt:message key="header.home"/> </a>
						<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
							<ul class="nav navbar-nav menu_nav ml-auto">
								<li class="nav-item active"><a class="nav-link" href="index"><fmt:message key="header.home"/> </a></li>
							</ul>
						</div> 
					</div>
            	</nav>
            </div>
        </header>
        <!--================Header Menu Area =================-->
        
        <!--================Home Banner Area =================-->
        <section class="banner_area">
            <div class="banner_inner d-flex align-items-center">
            	<div class="overlay bg-parallax" data-stellar-ratio="0.9" data-stellar-vertical-offset="0" data-background=""></div>
				<div class="container">
					<div class="banner_content text-center">
						<div class="page_link">
							<a href="index"><fmt:message key="header.home"/> </a>
							<a href="speakers"><fmt:message key="header.speakers"/> </a>
						</div>
						<h2><fmt:message key="header.speakers"/> </h2>
					</div>
				</div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->
        
        <!--================Team Area =================-->
		<c:choose>
			<c:when test="${sessionScope.conferences != null}">
				<section class="team_area p_120">
					<div class="container">
						<div class="main_title"></div>
							<table style="width:100%">
								<tr>
									<th>ID</th>
									<th>Theme</th>
									<th>Address</th>
									<th>Planned date</th>
								</tr>
								<c:forEach items="${sessionScope.conferences}" var="conference">
								<tr>
									<td><c:out value="${conference.id}"/></td>
									<td><c:out value="${conference.theme}"/></td>
									<td><c:out value="${conference.address}"/></td>
									<td><c:out value="${conference.plannedDateTime}"/></td>
								</tr>
								</c:forEach>
							</table>
					</div>
				</section>
			</c:when>
			<c:otherwise> <h3>There is no speakers yet</h3></c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${sessionScope.speakers != null}">
		<section class="team_area p_120">
        	<div class="container">
        		<div class="main_title">
        			<h2></h2>
				</div>
        		<c:forEach items="${sessionScope.speakers}" var="speaker">
				<div class="row team_inner">
        			<div class="col-lg-3 col-sm-6">
        				<div class="team_item">
        					<div class="team_img">
        						<img class="img-fluid" src="ui/img/team/team-1.jpg" alt="">
        						<div class="hover">
        							<a href="#"><i class="fa fa-facebook"></i></a>
        							<a href="#"><i class="fa fa-twitter"></i></a>
        							<a href="#"><i class="fa fa-linkedin"></i></a>
        						</div>
        					</div>
        					<div class="team_name">
        						<h4><c:out value="${speaker.firstName}"/> <c:out value="${speaker.lastName}"/></h4>
        						<p>Email: <c:out value="${speaker.email}"/></p>
        					</div>
        				</div>
        			</div>
        		</div>
				</c:forEach>
        	</div>
        </section>
		</c:when>
			<c:otherwise> <h3>There is no speakers yet</h3></c:otherwise>
		</c:choose>
        <!--================End Team Area =================-->
        
        <!--================ start footer Area  =================-->
		<!--================ End footer Area  =================-->
        
        
        
        
        
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="ui/js/jquery-3.2.1.min.js"></script>
        <script src="ui/js/popper.js"></script>
        <script src="ui/js/bootstrap.min.js"></script>
        <script src="ui/js/stellar.js"></script>
        <script src="ui/vendors/lightbox/simpleLightbox.min.js"></script>
        <script src="ui/vendors/nice-select/js/jquery.nice-select.min.js"></script>
        <script src="ui/vendors/isotope/imagesloaded.pkgd.min.js"></script>
        <script src="ui/vendors/isotope/isotope-min.js"></script>
        <script src="ui/vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="ui/js/jquery.ajaxchimp.min.js"></script>
        <script src="ui/vendors/flipclock/timer.js"></script>
        <script src="ui/vendors/counter-up/jquery.waypoints.min.js"></script>
        <script src="ui/vendors/counter-up/jquery.counterup.js"></script>
        <script src="ui/js/mail-script.js"></script>
        <!--gmaps Js-->
        <script src="ui/js/gmaps.min.js"></script>
        <script src="ui/js/theme.js"></script>
    </body>
</html>