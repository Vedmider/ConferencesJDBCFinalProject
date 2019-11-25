<!doctype html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="${sessionScope.bundle}"/>

<html lang="${sessionScope.locale}">
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
        <section class="home_banner_area">
            <div class="banner_inner">
				<div class="container">
					<div class="banner_content">
						<h2>
							<fmt:message key="index.home.banner.area"/>
						</h2>
					</div>
				</div>
            </div>
        </section>
        <!--================End Home Banner Area =================-->
        
        <!--================Welcome Area =================-->

        <!--================End Welcome Area =================-->
        
        <!--================Team Area =================-->
       
        <!--================End Team Area =================-->
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
        <script src="ui/js/gmaps.min.js"></script>
        <script src="ui/js/theme.js"></script>
		</section>

	</body>
</html>