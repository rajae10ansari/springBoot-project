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
        <h3>Liste des niveaux</h3>
    </div>

    <p style="text-align:right"><a href="${pageContext.request.contextPath}/cadreadmin/exportNiveaus"><img
        src="${pageContext.request.contextPath}/resources/img/excel.png" width="30">Export Excel</p></a>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Titre de niveau </th>
                <th scope="col">Alias de niveau</th>
                <th scope="col">Filiere de niveau</th>
                <th>Actions</th>
            </tr>
            </thead>

            <c:forEach items="${niveauList}" var="p">
                <tr>
                    <td><c:out value="${p.titre}" /></td>
                    <td><c:out value="${p.alias}" /></td>
                    <td><c:out value="${p.filiere.titreFiliere}" /></td>
                    <td>


                        <ul>
                            <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/cadreadmin/deleteNiveau/${p.idNiveau}">Supprimer</a></li>

                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/cadreadmin/updateNiveauForm/${p.idNiveau}/">Modifier</a></li>


                        </ul>
                    </td>

                </tr>

            </c:forEach>

        </table>
    </div>


</div>
</html>