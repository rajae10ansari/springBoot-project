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
    <h3>Liste des Filieres</h3>
  </div>

 

  <table class="table">
    <thead>
    <tr>
      <th scope="col">Titre de filiere</th>
      <th scope="col">code de filiere</th>
      <th scope="col">Année d'accredation</th>
      <th scope="col">Année de find'accredation</th>
      <th>Actions</th>
    </tr>
    </thead>

    <c:forEach items="${filiereList}" var="f">
      <tr>

        <td><c:out value="${f.titreFiliere}" /></td>
        <td><c:out value="${f.codeFiliere}" /></td>
        <td><c:out value="${f.anneeaccreditation}" /></td>
        <td><c:out value="${f.anneeFinaccreditation}" /></td>
         <td><c:out value="${f.anneeFinaccreditation}" /></td>
        


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
