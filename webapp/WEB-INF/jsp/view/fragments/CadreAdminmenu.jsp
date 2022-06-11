<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link active"
                                aria-current="page"
                                href="${pageContext.request.contextPath}/cadreadmin/showHome">Home</a></li>

        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false"> Filiere
            Management </a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/showFormCA">Add Filiere</a>
                </li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/manageFilieres">Manage fILIERE </a></li>



            </ul>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false"> Niveau
            Management </a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/showFormNiveau">Add Niveau</a>
                </li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/manageNiveau">Manage Niveau </a></li>



            </ul>
        </li>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false"> Module
            Management </a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/showFormModule">Add Module</a>
                </li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/manageModules">Manage Module </a></li>



            </ul>
        </li>

        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false"> Element
            Management </a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/showFormElement">Add Element</a>
                </li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/manageElements">Manage Element</a></li>



            </ul>
        </li>


        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false">Export Data </a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/exportFilieres">Export Filieres</a></li>
            </ul></li>



        <li class="nav-item"><form
                action="${pageContext.request.contextPath}/admin/serachfiliere"
                class="d-flex" method="GET">
            <input name="cin" class="form-control me-2" type="search"
                   placeholder="Saisir CIN" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form></li>

        <li class="nav-item"><f:form
                action="${pageContext.request.contextPath}/logout" method="POST">

            <button type="submit" class="btn btn-link">logout</button>

        </f:form></li>

    </ul>



</div>


