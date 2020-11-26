<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.contact.name}"/> <c:out value="${requestScope.contact.surname}"/> </title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <c:set var="right" value="${sessionScope.user.right}" scope="page"/>

    <c:choose>
        <c:when test="${requestScope.contact.reserved}">
            <div class="row">
                <div class="col"></div>

                <div class="col pt-4">
                    <div class="container-lg pt-3 pb-3">
                        <div class="card">
                            <div class="card-header flex-column">
                                <h2> <b> <c:out value="${requestScope.contact.name} ${requestScope.contact.surname}"/> </b> </h2>
                            </div>
                            <div class="card-body">
                                <p>Ce contact est réservé par :

                                    <c:url var="referent_link" value="/research/contact">
                                        <c:param name="id" value="${requestScope.contact.referent.id}"/>
                                    </c:url>

                                    <a href="${referent_link}"><c:out value="${requestScope.contact.referent.name} ${requestScope.contact.referent.surname}"/></a>
                                </p>

                                <div class="d-flex justify-content-center pt-5 pb-3">
                                    <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                                        <a href="<c:url value="/research/contactProfile/modify"/>">
                                            <button class="btn btn-primary ">Modifier le contact</button>
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col"></div>
            </div>
            <div class="d-flex justify-content-center">
                <div class="pt-5">
                    <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                        <a href="<c:url value="/research/contactProfile/delete"/>">
                            <button class="btn btn-danger">Supprimer le contact</button>
                        </a>
                    </c:if>
                </div>
            </div>

        </c:when>
        <c:otherwise>

            <div class="row">
                <div class="col"></div>

                <div class="col pt-4">
                    <div class="container-lg pt-3 pb-3">

                        <div class="card">

                            <div class="card-header flex-column">
                                    <h5 class="text-center"> <b> <c:out value="${requestScope.contact.name} ${requestScope.contact.surname}"/> </b> </h5>
                            </div>

                            <div class="card-body">
                                <div class="pt-3">
                                    <p> <b>Role : </b> <c:out value="${requestScope.contact.role}"/> </p>
                                </div>

                                <div class="pt-3">
                                    <p> <b>Entité : </b> <c:out value="${requestScope.contact.entity.name}"/> </p>
                                </div>

                                <div class="pt-3">
                                    <p> <b>Mail : </b>
                                        <c:forEach var="mail" items="${requestScope.contact.mailsList}">
                                            <p><c:out value="${mail}"/></p> <br>
                                        </c:forEach>
                                    </p>
                                </div>

                                <div class="pt-3">
                                    <p> <b>Téléphone : </b>
                                        <c:forEach var="phone" items="${requestScope.contact.phonesList}">
                                            <p><c:out value="${phone}"/></p> <br>
                                        </c:forEach>
                                    </p>
                                </div>

                                <div class="pt-3">
                                    <p> <b>Adresse : </b> <c:out value="${requestScope.contact.address}"/> </p>
                                </div>

                                
                                <div class="d-flex justify-content-center pt-5 pb-3">
                                    <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                                        <a href="<c:url value="/research/contactProfile/modify"/>">
                                            <button class="btn btn-primary ">Modifier le contact</button>
                                        </a>
                                    </c:if>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                        
                <div class="col pt-4">
                    <div class="container-lg pt-3 pb-3">
                        <div class="card">

                            <div class="card-header flex-column">
                                <h5 class="text-center"> <b> Informations </b> </h5>
                            </div>

                            <div class="card-body">
                                <ul class="nav nav-tabs" role="tablist">
                                    <!--Définition des onglets-->
                                    <li class="nav-item">
                                        <a class="nav-link active" id="comment-tab" data-toggle="tab" href="#comment"
                                           role="tab" aria-controls="comment" aria-selected="true">Comment</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="history-tab" data-toggle="tab" href="#history"
                                           role="tab" aria-controls="history" aria-selected="false">History</a>
                                    </li>
                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane fade show active" id="comment"
                                         role="tabpanel" aria-labelledby="comment-tab">
                                        <textarea class="form-control rounded-0" name="commentContent" rows="10" cols="40" form="commentForm"><c:out value="${requestScope.comment.content}"/></textarea>

                                        <div class="d-flex justify-content-center pt-3">
                                            <form method="post" action="<c:url value="/research/contactProfile/saveComment"/>" id="commentForm">
                                                <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                            </form>
                                        </div>

                                    </div>

                                    <div class="tab-pane fade" id="history"
                                         role="tabpanel" aria-labelledby="history-tab">
                                        <c:forEach var="event" items="${requestScope.contact.eventsList}">
                                            <div>
                                                <h4><c:out value="${event.name}"/> (<c:out value="${event.type}"/>)</h4>
                                                <p><b>Date : </b> <c:out value="${event.date}"/></p>
                                                <p><b>Description : </b> <c:out value="${event.description}"/></p>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col"></div>
            </div>

            <div class="d-flex justify-content-center">
                <div class="pt-5">
                    <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                        <a href="<c:url value="/research/contactProfile/delete"/>">
                            <button class="btn btn-danger">Supprimer le contact</button>
                        </a>
                    </c:if>
                </div>
            </div>



        </c:otherwise>
    </c:choose>

    <c:import url="/WEB-INF/utils/footer.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>