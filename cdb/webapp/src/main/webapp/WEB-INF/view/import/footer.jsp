<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<script>
    var local = "${pageContext.response.locale}";
</script>
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <script src="<c:url value="/resources/js/dashboard_shortcut.js" />"></script>
</security:authorize>