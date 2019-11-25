<!doctype html>
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
        <!-- Required meta tags -->
        <meta charset="utf-8">
		<base href="${pageContext.request.contextPath}/">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="ui/img/favicon.png" type="image/png">
        <title>Conferences</title>
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
		<c:import url="header.jsp" />
        <!--================Header Menu Area =================-->
        
        <!--================Home Banner Area =================-->
        <section class="banner_area">
            <div class="banner_inner d-flex align-items-center">
            	<div class="overlay bg-parallax" data-stellar-ratio="0.9" data-stellar-vertical-offset="0" data-background=""></div>
				<div class="container">
					<div class="banner_content text-center">
						<div class="page_link">
							<a href="index"><fmt:message key="header.home"/></a>
						</div>
						<h2>About Event</h2>
					</div>
				</div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->
        
        <!--================Event Time Area =================-->
        <section class="event_time_area ab_event pad_top">
        	<div class="container">
        		<div class="event_time_inner">
        			<div class="row">
        				<div class="col-lg-6">
        					<div class="event_text">
        						<h3><fmt:message key="about.event.start"/> </h3>
        						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore.</p>
        					</div>
        				</div>
        				<div class="col-lg-6">
        					<div class="timer_inner">
								<div id="timer" class="timer">
									<div class="timer__section days">
										<div class="timer__number"></div>
										<div class="timer__label"><fmt:message key="about.days"/> </div>
									</div>
									<div class="timer__section hours">
										<div class="timer__number"></div>
										<div class="timer__label"><fmt:message key="about.hours"/></div>
									</div>
									<div class="timer__section minutes">
										<div class="timer__number"></div>
										<div class="timer__label"><fmt:message key="about.minutes"/></div>
									</div>
									<div class="timer__section seconds">
										<div class="timer__number"></div>
										<div class="timer__label"><fmt:message key="about.seconds"/></div>
									</div>
								</div>
							</div>
        				</div>
        			</div>
        		</div>
        	</div>
        </section>
        <!--================End Event Time Area =================-->
        
        <!--================Welcome Area =================-->
        <section class="welcome_area pad_btm">
        	<div class="container">
        		<div class="welcome_inner row">
        			<div class="col-lg-5">
        				<div class="welcome_img">
        					<img class="img-fluid" src="ui/img/welcome-1.jpg" alt="">
        				</div>
        			</div>
        			<div class="col-lg-6 offset-lg-1">
        				<div class="welcome_text">
        					<h3><fmt:message key="about.welcome.conferences.app"/> </h3>
        					<p><fmt:message key="about.welcome.app.text"/> </p>
        					<a class="main_btn" href="#">View More Details</a>
        				</div>
        			</div>
        		</div>
        	</div>
        </section>
        <!--================End Welcome Area =================-->
        
        <!--================End Event Schedule Area =================-->
        <section class="home_map_area">
       		<div class="home_details">
				<div class="container">
					<div class="box_home_details">
						<div class="media">
							<div class="d-flex">
								<i class="lnr lnr-home"></i>
							</div>
							<div class="media-body">
								<h4>California, United States</h4>
								<p>Santa monica bullevard</p>
							</div>
						</div>
						<div class="media">
							<div class="d-flex">
								<i class="lnr lnr-clock"></i>
							</div>
							<div class="media-body">
								<h4>Monday to Wednesday</h4>
								<p>17-19 June, 218</p>
							</div>
						</div>
						<div class="media">
							<div class="d-flex">
								<i class="lnr lnr-envelope"></i>
							</div>
							<div class="media-body">
								<h4>support@colorlib.com</h4>
								<p>Send us your query anytime!</p>
							</div>
						</div>
					</div>
				</div>
       		</div>
        </section>
        <!--================End Event Schedule Area =================-->
        
        <!--================Team Area =================-->

        <!--================End Team Area =================-->
        
        <!--================Price Area =================-->

        <!--================End Price Area =================-->
        
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