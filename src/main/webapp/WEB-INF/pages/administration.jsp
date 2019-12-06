<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css"
          href="ui/administration-page-resources/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="ui/administration-page-resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="ui/administration-page-resources/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="ui/administration-page-resources/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="ui/administration-page-resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="ui/administration-page-resources/css/util.css">
    <link rel="stylesheet" type="text/css" href="ui/administration-page-resources/css/main.css">
    <link type="text/css" rel="stylesheet" href="css/tail.datetime-default-green.css" />
    <!--===============================================================================================-->
</head>
<body>
<c:import url="header.jsp"/>

<div class="container-table100">
    <div class="wrap-table100">
        <c:if test="${sessionScope.conferences != null}">
            <div class="wrap-table100">
                <h3>Conferences</h3>
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
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        Create/Update Conference
                        <form action="db-action" id="db-action-form" onsubmit="return conferenceValidate()">
                            <label for="id">ID</label>
                            <input type="text" name="id" id="id" value="">
                            <label for="theme">Theme</label>
                            <input type="text" name="theme" id="theme" value="">
                            <label for="plannedDate">Planned date</label>
                            <input type="date" name="plannedDate" id="plannedDate" value=""/>
                            <div>
                            <label for="plannedTime">Planned time</label>
                            <input type="time"  name="plannedTime" id="plannedTime" value=""/>
                            </div>
                            <div>
                            <label for="happenedDate">Happened date</label>
                            <input type="date" name="happenedDate" id="happenedDate" value="">
                            </div>
                            <div>
                            <label for="happenedTime">Happened time</label>
                            <input type="time" name="happenedTime" id="happenedTime"/>
                            </div>
                            <label for="type">Type of operation</label>
                            <select name="type" id="type" required>
                                <option value="create">Create</option>
                                <option value="update">Update</option>
                            </select>
                            <input type="text" name="entity" value="conference" hidden>
                            <input type="submit" id="conference-submit" value="Submit">
                        </form>
                        <br>
                    </c:if>
                </div>
            </div>
            <c:forEach items="${sessionScope.conferences}" var="conference">
                <h3>Conference: <c:out value="${conference.theme}"/></h3>
                <h3>Reports</h3>
                <div class="wrap-table100">
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
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                            <div class="cell">Delete</div>
                        </c:if>
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
                                <c:set var="fullName"
                                       value="${report.speaker.id} ${report.speaker.firstName}  ${report.speaker.lastName}"/>
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
                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                <div class="cell" data-title="Delete">
                                    <a href="db-action?entity=report&type=delete&id=<c:out value="${report.id}"/>">Delete</a>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    Create/Update Report
                    <form action="db-action" id="db-action-form" onsubmit="return reportValidate()">
                        <label for="id">ID</label>
                        <input type="text" name="id" id="report-id" value="">
                        <label for="title">Title</label>
                        <input type="text" name="title" id="title" value="">
                        <label for="timeStart">Start time</label>
                        <input type="time" name="timeStart" id="timeStart" value="">
                        <label for="speaker">Speaker ID</label>
                        <input type="text" name="speaker" id="speaker" value="">
                        <label for="registered">Number of registered</label>
                        <input type="number" name="registered" id="registered" value="">
                        <label for="attended">Number of attended</label>
                        <input type="number" name="attended" id="attended" value="">
                        <input type="number" name="conferenceId" id="conferenceId" value="" hidden>
                        <label for="type">Type of operation</label>
                        <select name="type" id="report-operation-type" required>
                            <option value="create">Create</option>
                            <option value="update">Update</option>
                        </select>
                        <input type="text" name="entity" value="report" hidden>
                        <input type="submit" id="report-submit" value="Submit">
                    </form>
                    <br>
                </c:if>
            </c:forEach>
            </div>

        </c:if>
        <c:if test="${sessionScope.speakers != null}">
            <div class="wrap-table100">
                <div class="table">
                    <div class="row header">
                        <div class="cell">
                            ID
                        </div>
                        <div class="cell">Full name</div>
                        <div class="cell">Email</div>
                        <div class="cell">Rating</div>
                        <div class="cell">Bonuses</div>
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                            <div class="cell">Delete</div>
                        </c:if>
                    </div>
                    <c:forEach items="${sessionScope.speakers}" var="speaker">
                        <div class="row">
                            <div class="cell" data-title="id">
                                <c:out value="${speaker.id}"/>
                            </div>
                            <div class="cell" data-title="fullName">
                                <c:set var="fullName" value="${speaker.firstName}  ${speaker.lastName}"/>
                                <c:out value="${fullName}"/>
                            </div>
                            <div class="cell" data-title="email">
                                <c:out value="${speaker.email}"/>
                            </div>
                            <div class="cell" data-title="rating">
                                <c:out value="${speaker.rating}"/>
                            </div>
                            <div class="cell" data-title="bonuses">
                                <c:out value="${speaker.bonuses}"/>
                            </div>
                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                <div class="cell" data-title="Delete">
                                    <a href="db-action?entity=speaker&type=delete&id=<c:out value="${speaker.id}"/>">Delete</a>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <c:if test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'MODERATOR'}">
                Create/Update Speaker
                <form action="db-action" id="db-action-form" onsubmit="return speakerValidate()">
                    <label for="id">ID</label>
                    <input type="text" name="id" id="speaker-id" value="">
                    <label for="login">Login</label>
                    <input type="text" name="login" id="login" value="">
                    <label for="password">Password</label>
                    <input type="text" name="password" id="password" value="">
                    <label for="firstName">First Name</label>
                    <input type="text" name="firstName" id="firstName" value="">
                    <label for="lastName">Last Name</label>
                    <input type="text" name="lastName" id="lastName" value="">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" value="">
                    <label for="speakerRole">Role</label>
                    <select name="userRole" id="speakerRole" required>
                        <option value="1">ADMIN</option>
                        <option value="3">MODERATOR</option>
                        <option value="4">SPEAKER</option>
                        <option value="5">USER</option>
                    </select>
                    <label for="type">Type of operation</label>
                    <select name="type" id="speaker-operation-type" required>
                        <c:if test="${sessionScope.role == 'MODERATOR' || sessionScope.role == 'ADMIN'}">
                            <option value="create">Create</option>
                        </c:if>
                        <option value="update">Update</option>
                    </select>
                    <input type="text" name="entity" value="speaker" hidden>
                    <input type="submit" id="speaker-submit" value="Submit">
                </form>
                <br>
            </c:if>

            <br>
        </c:if>
        <c:if test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'MODERATOR'}">
            <c:if test="${sessionScope.users != null}">
                <h3>Users</h3>
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
                            <c:if test="${sessionScope.role == 'ADMIN'}">
                                <div class="cell">Delete</div>
                            </c:if>
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
                                <c:if test="${sessionScope.role == 'ADMIN'}">
                                    <div class="cell" data-title="Delete">
                                        <a href="db-action?entity=user&type=delete&id=<c:out value="${user.id}"/>">Delete</a>
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <h3>Create/Update User</h3>
                    <form action="db-action" id="db-action-form" onsubmit="return userValidate()">
                        <label for="id">ID</label>
                        <input type="text" name="id" id="user-id" value="">
                        <label for="login">Login</label>
                        <input type="text" name="login" id="user-login" value="">
                        <label for="password">Password</label>
                        <input type="text" name="password" id="user-password" value="">
                        <label for="firstName">First Name</label>
                        <input type="text" name="firstName" id="user-firstName" value="">
                        <label for="lastName">Last Name</label>
                        <input type="text" name="lastName" id="user-lastName" value="">
                        <label for="email">Email</label>
                        <input type="email" name="email" id="user-email" value="">
                        <label for="userRole">Role</label>
                        <select name="userRole" id="userRole" required>
                            <option value="1">ADMIN</option>
                            <option value="3">MODERATOR</option>
                            <option value="4">SPEAKER</option>
                            <option value="5">USER</option>
                        </select>
                        <label for="type">Type of operation</label>
                        <select name="type" id="user-operation-type" required>
                            <option value="create">Create</option>
                            <option value="update">Update</option>
                        </select>
                        <input type="text" name="entity" value="user" hidden>
                        <input type="submit" id="user-submit" value="Submit">
                    </form>
                    <br>
                </c:if>
            </c:if>
        </c:if>
    </div>
</div>
<c:if test="${sessionScope.previous != null}">
    <a href="administration?gotoPage=<c:out value="${sessionScope.previous}"/>">Previous</a>
</c:if>
<c:if test="${sessionScope.next != null}">
    <a href="administration?gotoPage=<c:out value="${sessionScope.next}"/>">Next</a>
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
<script src="ui/js/jquery.maskedinput.min.js"></script>
<script type="text/javascript" src="js/tail.datetime.min.js"></script>
<script type="text/javascript">

    function conferenceValidate() {

        let id = document.getElementById("id").value;
        let theme = document.getElementById("theme").value;
        let plannedDateTime = document.getElementById("plannedDateTime").value;
        let type = document.getElementById("type").value;

        if (type == 'update') {
            if (id == '') {
                alert("ID cannot be empty during update")
                return false;
            }
            return true;
        } else if (type == 'create') {
            if (id != '') {
                alert("ID cannot be filled in during create")
                return false;
            }
            if (theme == '' || plannedDateTime == '') {
                alert("Theme and planned date should not be empty")
                return false;
            }
        }
        return true;
    }

    function reportValidate() {

        let id = document.getElementById("report-id").value;
        let title = document.getElementById("title").value;
        let timeStart = document.getElementById("timeStart").value;
        let type = document.getElementById("type").value;

        if (type == 'update') {
            if (id == '') {
                alert("ID cannot be empty during update")
                return false;
            }
            return true;
        } else if (type == 'create') {
            if (id != '') {
                alert("ID cannot be filled in during create")
                return false;
            }
            if (title == '' || timeStart == '') {
                alert("Title and time of start  date should not be empty")
                return false;
            }
        }
        return true;
    }

    function speakerValidate() {

        let id = document.getElementById("id").value;
        let login = document.getElementById("login").value;
        let password = document.getElementById("password").value;
        let email = document.getElementById("email").value;
        let firstName = document.getElementById("firstName").value;
        let lastName = document.getElementById("lastName").value;
        let speakerRole = document.getElementById("speakerRole").value;
        let type = document.getElementById("type").value;

        if (type == 'update') {
            if (id == '') {
                alert("ID cannot be empty during update")
                return false;
            }
            return true;
        } else if (type == 'create') {
            if (id != '') {
                alert("ID cannot be filled in during create")
                return false;
            }
            if (password == '' || login == '' || email == '' || firstName == '' || lastName == '' || speakerRole == '') {
                alert("Theme and planned date should not be empty")
                return false;
            }
        }
        return true;
    }

    function userValidate() {
        let id = document.getElementById("user-id").value;
        let login = document.getElementById("user-login").value;
        let password = document.getElementById("user-password").value;
        let email = document.getElementById("user-email").value;
        let firstName = document.getElementById("user-firstName").value;
        let lastName = document.getElementById("user-lastName").value;
        let userRole = document.getElementById("userRole").value;
        let type = document.getElementById("type").value;

        if (type == 'update') {
            if (id == '') {
                alert("ID cannot be empty during update")
                return false;
            }
            return true;
        } else if (type == 'create') {
            if (id != '') {
                alert("ID cannot be filled in during create")
                return false;
            }
            if (password == '' || login == '' || email == '' || firstName == '' || lastName == '' || userRole == '') {
                alert("Theme and planned date should not be empty")
                return false;
            }
        }
        return true;
    }

</script>

</body>
</html>