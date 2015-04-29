<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="target" required="true" type="java.lang.String"
              description="Sets the link target" %>
<%@ attribute name="page" required="true" type="java.lang.Integer"
              description="" %>
<%@ attribute name="body" required="true" type="java.lang.String"
              description="" %>
<%@ attribute name="orderBy" required="true" type="java.lang.String"
              description="" %>
<a
        href="<c:url value="dashboard">
			<c:param name="currentPage" value="${page.currentPage}" />
			<c:param name="size" value="${page.offset}" />
			<c:param name="search" value="${page.search}" />
			
			
			<c:choose>
				<c:when test="${page.option == 'DESC' }">
					<c:param name="option" value="" />
				</c:when>
				<c:otherwise>
					<c:param name="option" value="DESC" />
				</c:otherwise>
			</c:choose>
		</c:url> " aria-label="LastPage"> <span aria-hidden="true">${body}</span>
</a>
<c:if test="${page.orderBy == '${oderBy}' }">
    <div id="right">
        <c:choose>
            <c:when test="${page.option == 'DESC'}">
                &#x21E7;
            </c:when>
            <c:otherwise>
                &#x21E9;
            </c:otherwise>
        </c:choose>
    </div>
</c:if>