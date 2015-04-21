<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/view/import/head.jsp" ></jsp:include>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="<c:url value="/dashboard" />">
				Application - Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 404: Page not found. Too bad bitch! <br />
			</div>
		</div>
	</section>

	<script src="/cdb/resources/js/jquery.min.js"></script>
	<script src="/cdb/resources/js/bootstrap.min.js"></script>
	<script src="/cdb/resources/js/dashboard.js"></script>

</body>
</html>