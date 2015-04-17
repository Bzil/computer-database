<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="/cdb/resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="/cdb/resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="/cdb/resources/css/main.css" rel="stylesheet" media="screen">
</head>
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
				Error 500: An error has occured! <br />
				<pre>${message}</pre>
				<p>
					<c:forEach var="e" items="${exception}">
						${e}<br />
				</c:forEach>
				</p>
			</div>
		</div>
	</section>

	<script src="/cdb/resources/js/jquery.min.js"></script>
	<script src="/cdb/resources/js/bootstrap.min.js"></script>
	<script src="/cdb/resources/js/dashboard.js"></script>

</body>
</html>