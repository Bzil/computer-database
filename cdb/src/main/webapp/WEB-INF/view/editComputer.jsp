<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="header.jsp"%>

<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id: ${computer.id}
				</div>
				<h1><spring:message code="edit.computer" /></h1>
				<c:if test="${!empty message}">
					<div class="has-error">${message}</div>
				</c:if>
				<form:form commandName="computerDto" action="edit" method="POST">
					<input type="hidden" value="${computer.id}" name="id" />
					<fieldset>
						<div class="form-group">
							<spring:message code="computer.name" var="computerName"/>
							<form:label path="name" for="name">${computerName}</form:label>
                            <form:input path="name" type="text" cssClass="form-control" id="name" name="name" placeholder="${computerName}" value="${computer.name}" />
                            <form:errors path="name" cssClass="has-error" />
						</div>
						<div class="form-group">
							<spring:message code="date.pattern" var="pattern"/>
                            <form:label path="introduced" for="introduced"><spring:message code="introduced.date" /></form:label>
                            <form:input path="introduced" type="date" cssClass="form-control" id="introduced" name="introduced"
                                   placeholder="${pattern}" value="${computer.introduced}" />
							<form:errors path="introduced" cssClass="has-error" />
						</div>
						<div class="form-group">
							<form:label path="discontinued" for="discontinued"><spring:message code="discontinued.date" /></form:label>
                            <form:input path="discontinued" type="date" cssClass="form-control" id="discontinued" name="discontinued"
                                   placeholder="${pattern}" value="${computer.discontinued}"/>
							<form:errors path="discontinued" cssClass="has-error" /> 
						</div>
						 <div class="form-group">
						 	<spring:message code="select.company" var="company"/>
                            <form:label path="companyId" for="companyId"><spring:message code="company" /></form:label>
                            <form:select id="companyId" name="companyId" path="companyId" cssClass="form-control" multiple="false">
                            	 <form:option value="0" label="${company}"/>
                            	<form:options items="${companies}" itemValue="id" itemLabel="name" />
                            </form:select>
                         </div>
					</fieldset>
					<div class="actions pull-right">
                        <input type="submit" value="<spring:message code="button.edit" />" class="btn btn-primary" id="buttonControl" disabled="disabled">
                        <spring:message code="or" />
                        <a class="btn btn-default" href="<c:url value="/dashboard" />"><spring:message code="button.cancel" /></a>
                    </div>
				</form:form>
			</div>
		</div>
	</div>
</section>
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/validateField.js" />"></script>
</body>
</html>