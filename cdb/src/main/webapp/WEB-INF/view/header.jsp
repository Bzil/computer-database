<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		
		
		<div id="navbar" class="collapse navbar-collapse pull-right">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle "
					data-toggle="dropdown" role="button" aria-expanded="false"><spring:message
							code="button.language" /> <span class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="?lang=en">
							<img src="<c:url value="/resources/fonts/uk.png" />" alt="uk flag" style="width:25px;height:20px"> <spring:message code="lang.en" /></a>
						</li>
						<li>
							<a href="?lang=fr"><img src="<c:url value="/resources/fonts/fr.png" />" alt="fr flag" style="width:25px;height:20px"> <spring:message code="lang.fr" /></a>
						</li>
					</ul></li>
			</ul>
		</div>
		</div>
	</header>