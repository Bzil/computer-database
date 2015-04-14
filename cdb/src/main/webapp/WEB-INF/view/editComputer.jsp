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
				<h1>Edit Computer</h1>
				<c:if test="${!empty message}">
					<div class="has-error">${message}</div>
				</c:if>
				<form:form commandName="computerDto" action="edit" method="POST">
					<input type="hidden" value="${computer.id}" name="id" />
					<fieldset>
						<div class="form-group">
							<form:label path="name" for="name">Computer name</form:label>
                            <form:input path="name" type="text" cssClass="form-control" id="name" name="name" 
                            	placeholder="Computer name" value="${computer.name}" />
                            <form:errors path="name" cssClass="has-error" />
						</div>
						<div class="form-group">
							<form:label path="introduced" for="introduced">Introduced date</form:label>
                            <form:input path="introduced" type="date" cssClass="form-control" id="introduced" name="introduced"
                                   placeholder="DD-MM-YYYY" value="${computer.introduced}" />
							<form:errors path="introduced" cssClass="has-error" />
						</div>
						<div class="form-group">
							<form:label path="discontinued" for="discontinued">Discontinued date</form:label>
                            <form:input path="discontinued" type="date" cssClass="form-control" id="discontinued" name="discontinued"
                                   placeholder="DD-MM-YYYY" value="${computer.discontinued}"/>
							<form:errors path="discontinued" cssClass="has-error" /> 
						</div>
						 <div class="form-group">
                            <label for="companyId">Company</label>
                            <select class="form-control" id="companyId" name="companyId">
                                <c:forEach var="c" items="${companies}">
                                    <c:choose>
                                        <c:when test="${(!empty computer.companyId != null) && (computer.companyId == c.id)}">
                                            <option value="${c.id}" selected>${c.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${c.id}">${c.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Edit" class="btn btn-primary"
							id="buttonControl" disabled="disabled"> or <a
							href="dashboard" class="btn btn-default">Cancel</a>
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