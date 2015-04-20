<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>
<jsp:include page="/WEB-INF/view/import/header.jsp"></jsp:include>

<body>
	<jsp:include page="/WEB-INF/view/import/menu.jsp"></jsp:include>
	<section id="main">
		<form:form commandName="login" method="POST" name="login">

		Username:<form:input path="username" />
			<font color="red"><form:errors path="username" /></font>
			<br />
			<br />

		Password:<form:password path="password" />
			<font color="red"><form:errors path="password" /></font>
			<br />
			<br />

			<input type="submit" value="Login" />

		</form:form>
	</section>
	
	<jsp:include page="/WEB-INF/view/import/footer.jsp"></jsp:include>

</body>
</html>