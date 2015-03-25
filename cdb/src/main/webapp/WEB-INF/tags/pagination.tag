<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ attribute name="start" required="true" type="java.lang.Integer" description=""%>
<%@ attribute name="end" required="true" type="java.lang.Integer" description=""%>
<%@ attribute name="active" required="true" type="java.lang.Integer" description=""%>
<div class="container text-center">
	<ul class="pagination">
		
		<c:if test="${active > 1}">
			<li>
				<mylib:link body="&laquo;" target="dashboard" page="${active - 1}" active="false"></mylib:link></li>
		</c:if>
		
		<c:forEach var="i" begin="${start+1}" end="${end}">
			
				<c:choose>
					<c:when test="${active == i}">
						<li>
							<mylib:link body="${i}" page="${i+1}" target="dashboard" active="true" />
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<mylib:link body="${i}" page="${i+1}" target="dashboard" active="false" />
						</li>
					</c:otherwise>
				</c:choose>
			
		</c:forEach>
		
		<c:if test="${active < page.pageNb}">
			<li>
				<mylib:link body="&raquo;" target="dashboard" page="${active +1}" active="false"></mylib:link>
			</li>
		</c:if>
		
	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default">10</button>
		<button type="button" class="btn btn-default">50</button>
		<button type="button" class="btn btn-default">100</button>
	</div>
</div>