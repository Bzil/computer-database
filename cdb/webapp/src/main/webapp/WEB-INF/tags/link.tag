<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="target" required="true" type="java.lang.String"
              description="Sets the link target" %>
<%@ attribute name="page" required="true" type="java.lang.Integer"
              description="" %>
<%@ attribute name="size" required="false" type="java.lang.Integer"
              description="" %>
<%@ attribute name="search" required="false" type="java.lang.String"
              description="" %>
<%@ attribute name="column" required="false" type="java.lang.String"
              description="" %>
<%@ attribute name="dir" required="false" type="java.lang.String"
              description="" %>
<%@ attribute name="body" required="true" type="java.lang.String"
              description="" %>
<%@ attribute name="active" required="true" type="java.lang.Boolean"
              description="" %>

<c:choose>
    <c:when test="${active == true}">
        <a href="${target}?id=${page}&size=${size}&search=${search}&column=${column}&dir=${dir}"
           style="background: #dddddd;">${body}</a>
    </c:when>
    <c:otherwise>
        <a href="${target}?id=${page}&size=${size}&search=${search}&column=${column}&dir=${dir}">${body}</a>
    </c:otherwise>
</c:choose>
