<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Événements</title>
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
                            <a class="nav-link active" id="my-events-tab" data-toggle="tab" href="#my-events" role="tab" aria-controls="my-events" aria-selected="true">Mes événements</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="concerned-events-tab" data-toggle="tab" href="#concerned-events" role="tab" aria-controls="concerned-events" aria-selected="false">Les événements auquels je participe</a>
                        </li>
                    </ul>

                    <div class="tab-content">

                        <div class="tab-pane fade show active" id="my-events" role="tabpanel" aria-labelledby="my-events-tab">

                            <div class="tabcontent">
                                <div class="container mx-auto pt-5">
                                    <a href="<c:url value="/myEvents/create"/>">
                                        <button class="btn btn-info btn-block btn-lg d-flex justify-content-between align-items-center">
                                            Organiser un événement
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-plus-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7.5-3a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"></path>
                                            </svg>
                                        </button>
                                    </a>
                                </div>


                                <%-- Iterating over created events --%>
                                <c:forEach var="event" items="${requestScope.my_events}">

                                    <div class="container-fluid pt-4">
                                        <a href="<c:url value="/myEvents/details">
                                            <c:param name="id" value="${event.id}"/>
                                        </c:url>">
                                            <div class="card">
                                                <div class="card-header flex-column">
                                                    <h4 class="text-secondary"> <b> <c:out value="${event.name}"/> </b> </h4>
                                                </div>

                                                <div class="card-body ">
                                                    <div class="d-flex justify-content-between">
                                                        <div class="col-auto">
                                                            <p class="text-secondary">
                                                                <c:out value="${event.date}"/>
                                                            </p>
                                                        </div>

                                                        <div class="col-auto">
                                                            <p class="text-secondary">
                                                                <c:out value="${event.type}"/>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>

                                </c:forEach>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="concerned-events" role="tabpanel" aria-labelledby="concerned-events-tab">
                            <div class="tabcontent">

                                <%-- Iterating over concerned events --%>
                                <c:forEach var="event" items="${requestScope.concerned_events}">
                                    <div class="container-fluid pt-4">
                                        <a href="<c:url value="/myEvents/details">
                                            <c:param name="id" value="${event.id}"/>
                                        </c:url>">

                                            <div class="card">
                                                <div class="card-header flex-column">
                                                    <h4 class="text-secondary"> <b>
                                                        <c:out value="${event.name}"/> </b> </h4>
                                                </div>

                                                <div class="card-body ">
                                                    <div class="d-flex justify-content-between">
                                                        <div class="col-auto">
                                                            <p class="text-secondary">
                                                                <c:out value="${event.date}"/>
                                                            </p>
                                                        </div>

                                                        <div class="col-auto">
                                                            <p class="text-secondary">
                                                                <c:out value="${event.type}"/>
                                                            </p>
                                                        </div>
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
