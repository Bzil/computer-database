<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>

<jsp:include page="/WEB-INF/view/import/head.jsp"></jsp:include>

<body>
<jsp:include page="/WEB-INF/view/import/header.jsp"></jsp:include>
<section id="main">
    <div class="container">

        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span><span class="sr-only">Error: </span>
                <spring:message code="login.error"/>
            </div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="alert alert-success" role="alert">
                <div class="msg"><spring:message code="logout.success"/></div>
            </div>
        </c:if>

        <form name='connect' action='login' method='POST'>
            <!-- Username input-->
            <div class="form-signin input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input class="form-control up" name='username' placeholder="<spring:message code="username"/>"
                       type="text"/>
            </div>

            <div class="form-signin input-group">
                <!-- Password input-->
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input class="form-control down" name='password' placeholder="<spring:message code="password"/>"
                       type="password"/>
            </div>

            <!-- Button (Double) -->
            <div class="form-signin-button">
                <div class="controls">
                    <button type="submit" id="connect" name="connect" class="btn  btn-def btn-block btn-success">
                        <spring:message code="login"/>
                    </button>
                    <button type="reset" id="reset" name="reset" class="btn  btn-def btn-block">
                        <spring:message code="button.cancel"/>
                    </button>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</section>

<jsp:include page="/WEB-INF/view/import/footer.jsp"></jsp:include>

</body>
</html>