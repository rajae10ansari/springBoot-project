<%@page import="com.gsnotes.web.models.FiliereModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
        <h3>Formulaire d'ajouter une filiere</h3>
    </div>
<div>
 <f:form action="addFiliere" method="POST" modelAttribute="filiereModel" >

        <div class="row">
            <div class="col">
                <label>Titre de Filiere</label>
                <f:input path="titreFiliere" type="text" class="form-control"
                         placeholder="titreFiliere" />
                <f:errors path="titreFiliere" class="text-danger" />
            </div>
                <div class="col">
                    <label>Code de Filiere</label>
                    <f:input path="codeFiliere" type="text" class="form-control"
                             placeholder="codeFiliere" />
                    <f:errors path="codeFiliere" class="text-danger" />
                </div>
        </div>
                <div class="row">
                    <div class="col">
                        <label>Annee d'accreditation de Filiere</label>
                        <f:input path="anneeaccreditation" type="number" class="form-control"
                                 placeholder="anneeaccreditation" />
                        <f:errors path="anneeaccreditation" class="text-danger" />
                    </div>
                    <div class="col">
                            <label>Annee de fin d'accreditation de Filiere</label>
                            <f:input path="" type="text" class="form-control"
                                     placeholder="anneeFinaccreditation" />
                            <f:errors path="anneeFinaccreditation" class="text-danger" />
                        </div>
                        <div class="col">
                        <label>Coordinateur associé</label>

                        <f:select path="enseignant" multiple="false" size="1"
                                  class="form-control">
                            <f:options items="${enseignantList}" itemValue="idUtilisateur" itemLabel="nom" />
                        </f:select>
                </div>
                       
                </div>
            <div style="text-align: right">
                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <button type="reset" class="btn btn-secondary">Annuler</button>
            </div>
            </f:form>
        </div>

        </div>
</div>
<div class="container">
<div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Titre de filiere </th>
            <th scope="col">code de filiere </th>
            <th scope="col">Année d'accreditation</th>
            <th scope="col">Année de fin d'accreditation</th>
            <th scope="col">Coordinateur</th>
            
            
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach items="${filiereList}" var="f">
            <tr>

                <td><c:out value="${f.titreFiliere}" /></td>
                <td><c:out value="${f.codeFiliere}" /></td>
                <td><c:out value="${f.anneeaccreditation}" /></td>
                <td><c:out value="${f.anneeFinaccreditation}" /></td>
                <td><c:out value="${f.enseignant.nom}" /></td>


                <td>
                    <ul>
                        <li><a
                                href="${pageContext.request.contextPath}/cadreadmin/deleteFiliere/${f.idFiliere}">Delete</a></li>

                        <li><a
                                href="${pageContext.request.contextPath}/cadreadmin/updateFiliereForm/${f.idFiliere}">Update</a></li>


                    </ul>
                </td>

            </tr>

        </c:forEach>

    </table>
</div>

</html>
