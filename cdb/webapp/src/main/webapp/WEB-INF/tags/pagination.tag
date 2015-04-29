<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib" %>

<%@ attribute name="page" required="true" type="com.excilys.cdb.page.ComputerPage" description="" %>
                      
<div class="container text-center">
    <ul class="pagination">
    

        <c:if test="${page.currentPage > 1}">
            <li>
                <mylib:link body="&laquo;" target="dashboard" page="1" size="${page.offset}" active="false"
                            search="${page.search}"></mylib:link>
            </li>
            <li>
                <mylib:link body="&lsaquo;" target="dashboard" page="${page.currentPage - 1}" size="${page.offset}" active="false"
                            search="${page.search}"></mylib:link>
            </li>
        </c:if>

        <c:forEach var="i" begin="${page.startPage+1}" end="${page.endPage}">
            <c:choose>
                <c:when test="${page.currentPage == i}">
                    <li>
                        <mylib:link body="${i}" page="${i+1}" size="${page.offset}" target="dashboard" active="true"
                                    search="${page.search}"/>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <mylib:link body="${i}" page="${i+1}" size="${page.offset}" target="dashboard" active="false"
                                    search="${page.search}"/>
                    </li>
                </c:otherwise>
            </c:choose>

        </c:forEach>

        <c:if test="${page.currentPage < page.pageNb }">
            <li>
                <mylib:link body="&rsaquo;" target="dashboard" page="${page.currentPage +1}" size="${page.offset}" active="false"
                            search="${page.search}"></mylib:link>
            </li>
            <li>
                <mylib:link body="&raquo;" target="dashboard" page="${page.pageNb}" size="${page.offset}" active="false"
                            search="${page.search}"></mylib:link>
            </li>
        </c:if>


    </ul>
    <div class="btn-group btn-group-sm pull-right" role="group">
        <button type="button" class="btn btn-default"
                onclick="document.location.href='dashboard?id=${page.currentPage}&size=10&search=${page.search}'">10
        </button>
        <button type="button" class="btn btn-default"
                onclick="document.location.href='dashboard?id=${page.currentPage}&size=50&search=${page.search}'">50
        </button>
        <button type="button" class="btn btn-default"
                onclick="document.location.href='dashboard?id=${page.currentPage}&size=100&search=${page.search}'">100
        </button>
    </div>
</div>