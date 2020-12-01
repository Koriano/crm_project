<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/style/front.css"/>" />
</head>

<body>
<div id="wrapper">
    <div class="header">
        <c:import url="/WEB-INF/utils/menu.jsp"/>
    </div>


    <div class="container body">

        <div class="row">
            <div class="col-lg-6 col-md-8 col-sm-9 mx-auto pt-5 pb-4">

                <div class="d-flex justify-content-center pt-4 pb-3">
                    <form class="form-inline" method="post" action="<c:url value="/research"/>">
                        <input class="form-control mr-sm-3" type="search" placeholder="Rechercher" aria-label="Search" name="research" value="${param.research}">
                        <button class="btn btn-outline-info my-3 my-sm-0" type="submit">Rechercher</button>
                    </form>
                </div>

                <div class="card-body">

                    <ul class="nav nav-tabs" role="tablist">
                        <!--Définition des onglets-->
                        <li class="nav-item">
                            <a class="nav-link active" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="true">Contact</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="entite-tab" data-toggle="tab" href="#entite" role="tab" aria-controls="entite" aria-selected="false">Entité</a>
                        </li>
                    </ul>

                    <div class="tab-content">

                        <div class="tab-pane fade show active" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                            <c:set var="right" value="${sessionScope.user.right}"/>

                            <div id="Contacts" class="tabcontent">
                                <div class="container mx-auto pt-5">
                                    <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                                        <a href="<c:url value="/addContact"/>">
                                            <button class="btn btn-info btn-block btn-lg d-flex justify-content-between align-items-center">
                                                Ajouter un contact
                                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-plus-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7.5-3a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"></path>
                                                </svg>
                                            </button>
                                        </a>
                                    </c:if>
                                </div>


                                <!-- Iterating over contacts -->
                                <c:forEach var="contact" items="${requestScope.contacts}" varStatus="boucle">

                                    <c:set var="is_visible" value="${false}"/>

                                    <c:forEach var="sector" items="${sessionScope.user.sectors}">
                                        <c:forEach var="contact_sector" items="${sector.contactList}">

                                            <c:if test="${contact.id == contact_sector.id}">
                                                <c:set var="is_visible" value="${true}"/>
                                            </c:if>

                                        </c:forEach>
                                    </c:forEach>

                                    <c:if test="${is_visible}">
                                        <div class="container-fluid pt-4">

                                            <c:url var="contact_link" value="/research/contact">
                                                <c:param name="id" value="${contact.id}"/>
                                            </c:url>

                                            <a href="${contact_link}">
                                                <div class="card">
                                                    <div class="card-header flex-column">
                                                        <h4 class="text-secondary"> <b> <c:out value="${contact.name} ${contact.surname}"/> </b> </h4>
                                                    </div>

                                                    <div class="card-body ">
                                                        <div class="d-flex justify-content-between">
                                                            <div class="col-auto">
                                                                <p class="text-secondary">
                                                                    <c:out value="${contact.role}"/>
                                                                </p>
                                                            </div>

                                                            <div class="col-auto">
                                                                <p class="text-secondary">
                                                                    <c:out value="${contact.entity.name}"/>
                                                                </p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="entite" role="tabpanel" aria-labelledby="entite-tab">
                            <div id="Entities" class="tabcontent">

                                <div class="container-sm pt-5">
                                    <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                                        <a href="<c:url value="/addEntity"/>">
                                            <button class="btn btn-info btn-block btn-lg d-flex justify-content-between align-items-center">
                                                Ajouter une entité
                                                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house-door-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M6.5 10.995V14.5a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5h-4a.5.5 0 0 1-.5-.5V11c0-.25-.25-.5-.5-.5H7c-.25 0-.5.25-.5.495z"></path>
                                                    <path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"></path>
                                                </svg>
                                            </button>
                                        </a>
                                    </c:if>
                                </div>

                                <!-- Iterating over entities -->
                                <c:forEach var="entity" items="${requestScope.entities}" varStatus="boucle">
                                    <div class="container-fluid pt-4">
                                        <a href="<c:url value="/research/entityProfile">
                                                <c:param name="entityId" value="${entity.id}"/>
                                            </c:url>">

                                            <div class="card">
                                                <div class="card-header flex-column">
                                                    <h4 class="text-secondary"> <b>
                                                        <c:out value="${entity.name}"/> </b> </h4>
                                                </div>

                                                <div class="card-body ">
                                                    <div class="d-flex justify-content-between">
                                                        <p class="text-secondary">
                                                            <c:out value="${entity.type}"/>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <c:import url="/WEB-INF/utils/footer.jsp"/>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
