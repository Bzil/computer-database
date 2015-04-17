<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>
<%@ include file="header.jsp"%>
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
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="add"><spring:message code="add.computer" /></a> 
				<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="edit.computer" /></a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="#" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;"><input type="checkbox" id="selectall" /> 
						<span style="vertical-align: top;"> - <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();"> <i class="fa fa-trash-o fa-lg"></i></a> </span>
					</th>
					<th><spring:message code="computer.name" /> <span style="float: right;"> 
					<c:choose>
								<c:when
									test="${page.orderBy == 'DESC' && page.column == 'NAME'}">
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
									test="${page.orderBy == 'DESC' && page.column == 'INTRODUCED'}">
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
									test="${page.orderBy == 'DESC' && page.column == 'DISCONTINUED'}">
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
									test="${page.orderBy == 'DESC' && page.column == 'COMPANY_ID'}">
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
						<td><a href="edit?id=${computer.id}" onclick="">${computer.name}</a>
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
<script> var local = "${pageContext.response.locale}";</script>
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>