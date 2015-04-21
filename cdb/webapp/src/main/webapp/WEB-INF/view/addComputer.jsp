<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="/WEB-INF/view/import/head.jsp" ></jsp:include>
 
 <body>
<jsp:include page="/WEB-INF/view/import/header.jsp" ></jsp:include>
<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <h1><spring:message code="add.computer" /> </h1>
                <c:if test="${!empty message}">
                    <div class="has-error">${message}</div>
                </c:if>
                <form:form commandName="computerDto" action="add" method="POST">
                    <fieldset>
                        <div class="form-group">
                        	<spring:message code="computer.name" var="computerName"/>
                            <form:label path="name" for="name">${computerName}</form:label>
                            <form:input path="name" type="text" cssClass="form-control" id="name" name="name" placeholder="${computerName}" />
                            <font color="red"><form:errors path="name" cssClass="has-error" /></font>
                        </div>
                        <div class="form-group">
                        	<spring:message code="date.pattern" var="pattern"/>
                            <form:label path="introduced" for="introduced"><spring:message code="introduced.date" /></form:label>
                            <form:input path="introduced" type="date" cssClass="form-control" id="introduced" name="introduced"
                                   placeholder="${pattern}" />
							<font color="red"><form:errors path="introduced" cssClass="has-error" /> </font>                                  
                        </div>
                        <div class="form-group">
                            <form:label path="discontinued" for="discontinued"><spring:message code="discontinued.date" /></form:label>
                            <form:input path="discontinued" type="date" cssClass="form-control" id="discontinued" name="discontinued"
                                   placeholder="${pattern}" />
							<font color="red"><form:errors path="discontinued" cssClass="has-error" />  </font>                                 
                        </div>
                        <div class="form-group">
                       		 <spring:message code="select.company" var="company"/>
                            <form:label path="companyId" for="companyId"><spring:message code="company" /></form:label>
                            <form:select id="companyId" name="companyId" path="companyId" cssClass="form-control" multiple="false">
                            	<form:option value="0" label="${company}"/>
                            	<form:options items="${companies}" itemValue="id" itemLabel="name" />
                            </form:select>
							<font color="red"><form:errors path="companyId" cssClass="has-error" /> </font>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="<spring:message code="button.add" />" class="btn btn-primary"  id="buttonControl" disabled="disabled">
                        <spring:message code="or" />
                        <a class="btn btn-default" href="<c:url value="/dashboard" />"><spring:message code="button.cancel" /></a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/view/import/footer.jsp"></jsp:include>
<script src="<c:url value="/resources/js/validateField.js" />"></script>
</body>
</html>