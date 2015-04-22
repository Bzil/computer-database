<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>

<jsp:include page="/WEB-INF/view/import/head.jsp" ></jsp:include>
 
 <body>
<jsp:include page="/WEB-INF/view/import/header.jsp" ></jsp:include>
<section id="main">
	<div class="container">
		<h1 id="homeTitle">${page.count} <spring:message code="computer.found" /></h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="#" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search" class="form-control" placeholder="<spring:message code="search.name" />" /> 
					<input type="submit" id="searchsubmit" value="<spring:message code="filter.by.name" />" class="btn btn-primary" />
				</form>
			</div>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="add"><spring:message code="add.computer" /></a> 
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="edit.computer" /></a>
				</div>
			</security:authorize>
		</div>
	</div>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>
	</security:authorize>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<th class="editMode" style="width: 60px; height: 22px;"><input type="checkbox" id="selectall" /> 
							<span style="vertical-align: top;"> - <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();"> <i class="fa fa-trash-o fa-lg"></i></a> </span>
						</th>
					</security:authorize>
					<th><spring:message code="computer.name" /> <span style="float: right;"> 
					<c:choose>
								<c:when
									test="${page.orderBy == 'DESC' && page.column == 'computer.name'}">
									<mylib:link body="&#x21E7;" page="${page.currentPage}"
										active="false" target="dashboard" search="${page.search}"
										column="name" dir="ASC" size="${page.offset}" />
								</c:when>
								<c:otherwise>
									<mylib:link body="&#x21E9;" page="${page.currentPage}"
										active="false" target="dashboard" search="${page.search}"
										column="name" dir="DESC" size="${page.offset}" />
								</c:otherwise>
							</c:choose>
					</span>
					</th>
					<th><spring:message code="introduced.date" /> <span style="float: right;"> <c:choose>
								<c:when
									test="${page.orderBy == 'DESC' && page.column == 'computer.introduced'}">
									<mylib:link body="&#x21E7;" page="${page.currentPage}"
										active="false" target="dashboard" search="${page.search}"
										column="introduced" dir="ASC" size="${page.offset}" />
								</c:when>
								<c:otherwise>
									<mylib:link body="&#x21E9;" page="${page.currentPage}"
										active="false" target="dashboard" search="${page.search}"
										column="introduced" dir="DESC" size="${page.offset}" />
								</c:otherwise>
							</c:choose>
					</span>
					</th>
					<!-- Table header for Discontinued Date -->
					<th><spring:message code="discontinued.date" /> <span style="float: right;"> <c:choose>
								<c:when
									test="${page.orderBy == 'DESC' && page.column == 'computer.discontinued'}">
									<mylib:link body="&#x21E7;" page="${page.currentPage}"
										active="false" target="dashboard" search="${page.search}"
										column="discontinued" dir="ASC" size="${page.offset}" />
								</c:when>
								<c:otherwise>
									<mylib:link body="&#x21E9;" page="${page.currentPage}"
										active="false" target="dashboard" search="${page.search}"
										column="discontinued" dir="DESC" size="${page.offset}" />
								</c:otherwise>
							</c:choose>
					</span>
					</th>
					<!-- Table header for Company -->
					<th><spring:message code="company" /> <span style="float: right;"> <c:choose>
								<c:when
									test="${page.orderBy == 'DESC' && page.column == 'computer.company.id'}">
									<mylib:link body="&#x21E7;" page="${page.currentPage}"
										active="false" target="dashboard" search="${page.search}"
										column="company_id" dir="ASC" size="${page.offset}" />
								</c:when>
								<c:otherwise>
									<mylib:link body="&#x21E9;" page="${page.currentPage}"
										active="false" target="dashboard" search="${page.search}"
										column="company_id" dir="DESC" size="${page.offset}" />
								</c:otherwise>
							</c:choose>
					</span>
					</th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach items="${page.entities}" var="computer">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${computer.id}"></td>
						<td><security:authorize access="hasRole('ROLE_ADMIN')"><a href="edit?id=${computer.id}" onclick=""></security:authorize>
						${computer.name}<security:authorize access="hasRole('ROLE_ADMIN')"></a></security:authorize>
						</td>
						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td>${computer.companyName}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</section>

<footer class="navbar-fixed-bottom">
	<mylib:pagination end="${page.endPage}" active="${page.currentPage}"
		start="${page.startPage}"></mylib:pagination>

</footer>
<jsp:include page="/WEB-INF/view/import/footer.jsp"></jsp:include>

</body>
</html>