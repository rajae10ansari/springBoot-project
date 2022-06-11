<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/adminHeader.jsp" />

<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <jsp:include page="../fragments/CadreAdminmenu.jsp" />

        </div>
    </nav>






    <div>
        <h3>Liste des modules</h3>
    </div>

    <div>



    </div><p style="text-align:right"><a href="${pageContext.request.contextPath}/cadreadmin/exportFilieres"><img
        src="${pageContext.request.contextPath}/resources/img/excel.png" width="30">Export Excel</p></a>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Titre de module</th>
            <th scope="col">code de module</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach items="${moduleList}" var="m">
            <tr>

                <td><c:out value="${m.titre}" /></td>
                <td><c:out value="${m.code}" /></td>
                <td>
                    <ul>
                    <li><a
                            href="${pageContext.request.contextPath}/cadreadmin/associerModuleToNiveau/${m.idModule}"> niveau associé à ce module</a></li>


                    </ul>
            </td>

        </tr>

    </c:forEach>

</table>
</div>

</html>
