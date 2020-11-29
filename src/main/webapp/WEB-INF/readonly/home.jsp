<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />

    <style>
        a:hover {
            text-decoration: none;
        }
    </style>
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-8 col-sm-9 mx-auto pt-5 pb-4">
                <div class="text-center">
                    <h2>Bonjour, <c:out value="${sessionScope.user.name}"/></h2>
                </div>

                <div class="container-fluid pt-5">
                    <a href="<c:url value="/research"/>">
                        <button class="btn btn-outline-secondary btn-block btn-lg d-flex justify-content-between align-items-center">
                            <p>
                                Rechercher un contact ou une entité
                            </p>
                            <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"></path>
                                <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"></path>
                            </svg>
                        </button>
                    </a>
                </div>

                <div class="container-fluid pt-4">
                    <a href="<c:url value="/myEvents"/> ">
                        <button class="btn btn-outline-secondary btn-block btn-lg d-flex justify-content-between align-items-center">
                            Consulter vos événements
                            <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-calendar-event" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"></path>
                                <path d="M11 6.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1z"></path>
                            </svg>
                        </button>
                    </a>
                </div>


                <c:set var="right" value="${sessionScope.user.right}" scope="page"/>

                <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                    <div class="container-fluid pt-4">
                        <a href="<c:url value="/addContact"/> ">
                            <button class="btn btn-outline-secondary btn-block btn-lg d-flex justify-content-between align-items-center">
                                Ajouter un contact
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-person-plus-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7.5-3a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"></path>
                                </svg>
                            </button>
                        </a>
                    </div>

                    <div class="container-fluid pt-4">
                        <a href="<c:url value="/addEntity"/> ">
                            <button class="btn btn-outline-secondary btn-block btn-lg d-flex justify-content-between align-items-center">
                                Ajouter une entité
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-house-door-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M6.5 10.995V14.5a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .146-.354l6-6a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 .146.354v7a.5.5 0 0 1-.5.5h-4a.5.5 0 0 1-.5-.5V11c0-.25-.25-.5-.5-.5H7c-.25 0-.5.25-.5.495z"></path>
                                    <path fill-rule="evenodd" d="M13 2.5V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"></path>
                                </svg>
                            </button>
                        </a>
                    </div>

                    <div class="container-fluid pt-4">
                        <a href="<c:url value="/importContacts"/> ">
                            <button class="btn btn-outline-secondary btn-block btn-lg d-flex justify-content-between align-items-center">
                                Importer des contacts
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-box-arrow-in-down-left" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M9.636 2.5a.5.5 0 0 0-.5-.5H2.5A1.5 1.5 0 0 0 1 3.5v10A1.5 1.5 0 0 0 2.5 15h10a1.5 1.5 0 0 0 1.5-1.5V6.864a.5.5 0 0 0-1 0V13.5a.5.5 0 0 1-.5.5h-10a.5.5 0 0 1-.5-.5v-10a.5.5 0 0 1 .5-.5h6.636a.5.5 0 0 0 .5-.5z"></path>
                                    <path fill-rule="evenodd" d="M5 10.5a.5.5 0 0 0 .5.5h5a.5.5 0 0 0 0-1H6.707l8.147-8.146a.5.5 0 0 0-.708-.708L6 9.293V5.5a.5.5 0 0 0-1 0v5z"></path>
                                </svg>
                            </button>
                        </a>
                    </div>
                </c:if>


                <c:if test="${right == 'Administrateur'}">
                    <div class="container-fluid pt-4">
                        <a href="<c:url value="/rights"/> ">
                            <button class="btn btn-outline-secondary btn-block btn-lg d-flex justify-content-between align-items-center">
                                Gérer les comptes
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-lock-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M2.5 9a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9z"></path>
                                    <path fill-rule="evenodd" d="M4.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"></path>
                                </svg>
                            </button>
                        </a>
                    </div>

                    <div class="container-fluid pt-4">
                        <a href="<c:url value="/sectors"/> ">
                            <button class="btn btn-outline-secondary btn-block btn-lg d-flex justify-content-between align-items-center">
                                Gérer les secteurs
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-diagram-3" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M6 3.5A1.5 1.5 0 0 1 7.5 2h1A1.5 1.5 0 0 1 10 3.5v1A1.5 1.5 0 0 1 8.5 6v1H14a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-1 0V8h-5v.5a.5.5 0 0 1-1 0V8h-5v.5a.5.5 0 0 1-1 0v-1A.5.5 0 0 1 2 7h5.5V6A1.5 1.5 0 0 1 6 4.5v-1zM8.5 5a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1zM0 11.5A1.5 1.5 0 0 1 1.5 10h1A1.5 1.5 0 0 1 4 11.5v1A1.5 1.5 0 0 1 2.5 14h-1A1.5 1.5 0 0 1 0 12.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm4.5.5A1.5 1.5 0 0 1 7.5 10h1a1.5 1.5 0 0 1 1.5 1.5v1A1.5 1.5 0 0 1 8.5 14h-1A1.5 1.5 0 0 1 6 12.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm4.5.5a1.5 1.5 0 0 1 1.5-1.5h1a1.5 1.5 0 0 1 1.5 1.5v1a1.5 1.5 0 0 1-1.5 1.5h-1a1.5 1.5 0 0 1-1.5-1.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"></path>
                                </svg>
                            </button>
                        </a>
                    </div>

                    <div class="container-fluid pt-4">
                        <a href="<c:url value="/exportContacts"/> ">
                            <button class="btn btn-outline-secondary btn-block btn-lg d-flex justify-content-between align-items-center">
                                Exporter des contacts
                                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-box-arrow-up-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" d="M8.636 3.5a.5.5 0 0 0-.5-.5H1.5A1.5 1.5 0 0 0 0 4.5v10A1.5 1.5 0 0 0 1.5 16h10a1.5 1.5 0 0 0 1.5-1.5V7.864a.5.5 0 0 0-1 0V14.5a.5.5 0 0 1-.5.5h-10a.5.5 0 0 1-.5-.5v-10a.5.5 0 0 1 .5-.5h6.636a.5.5 0 0 0 .5-.5z"></path>
                                    <path fill-rule="evenodd" d="M16 .5a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h3.793L6.146 9.146a.5.5 0 1 0 .708.708L15 1.707V5.5a.5.5 0 0 0 1 0v-5z"></path>
                                </svg>
                            </button>
                        </a>
                    </div>

                </c:if>

            </div>
        </div>

        <div>
            <a href="<c:url value="/home/changePassword"/>"><button type="button">Modifier le mot de passe</button></a>
        </div>
    </div>



    <c:import url="/WEB-INF/utils/footer.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
