<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.contact.name}"/> <c:out value="${requestScope.contact.surname}"/> </title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/style/front.css"/>" />
</head>

<body>
<div id="wrapper">
    <div class="header">
        <c:import url="/WEB-INF/utils/menu.jsp"/>
    </div>

    <div class="container body">

        <c:set var="right" value="${sessionScope.user.right}" scope="page"/>

        <c:url var="modify_link" value="/research/contact/modify">
            <c:param name="id" value="${requestScope.contact.id}"/>
        </c:url>

        <c:url var="delete_link" value="/research/contact/delete">
            <c:param name="id" value="${requestScope.contact.id}"/>
        </c:url>

        <c:url var="referent_link" value="/research/contact">
            <c:param name="id" value="${requestScope.contact.referent.id}"/>
        </c:url>

        <c:url var="save_comment_link" value="/research/contact/saveComment">
            <c:param name="id" value="${requestScope.contact.referent.id}"/>
        </c:url>

        <c:choose>
            <c:when test="${requestScope.contact.reserved}">
                    <div class="row">
                        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">

                            <div class="card">
                                <div class="card-header flex-column">
                                    <h2> <b> <c:out value="${requestScope.contact.name} ${requestScope.contact.surname}"/> </b> </h2>
                                </div>
                                <div class="card-body">
                                    <p>Ce contact est réservé par :
                                        <a href="${referent_link}"><c:out value="${requestScope.contact.referent.name} ${requestScope.contact.referent.surname}"/></a>
                                    </p>

                                    <div class="d-flex justify-content-center pt-5 pb-3">
                                        <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                                            <a href="${modify_link}">
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
                    <div class="pt-5 pb-3">
                        <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                            <a href="${delete_link}">
                                <button class="btn btn-danger">Supprimer le contact</button>
                            </a>
                        </c:if>
                    </div>
                </div>

            </c:when>
            <c:when test="${not requestScope.is_visible}">
                <div>
                    <h3>Vous n'avez pas les droits pour voir ce contact.</h3>
                </div>
            </c:when>
            <c:otherwise>
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">
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
                                        <p><c:out value="${mail}"/></p>
                                    </c:forEach>
                                </p>
                            </div>

                            <div class="pt-3">
                                <p> <b>Téléphone : </b>
                                    <c:forEach var="phone" items="${requestScope.contact.phonesList}">
                                        <p><c:out value="${phone}"/></p>
                                    </c:forEach>
                                </p>
                            </div>

                            <div class="pt-3">
                                <p> <b>Adresse : </b> <c:out value="${requestScope.contact.address}"/> </p>
                            </div>


                            <div class="d-flex justify-content-center pt-5 pb-3">
                                <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                                    <a href="${modify_link}">
                                        <button class="btn btn-primary ">Modifier le contact</button>
                                    </a>
                                </c:if>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">
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
                                    <textarea class="form-control rounded-0" name="commentContent" rows="13" cols="40" form="commentForm"><c:out value="${requestScope.comment.content}"/></textarea>

                                    <div class="d-flex justify-content-center pt-3">
                                        <form method="post" action="${save_comment_link}" id="commentForm">
                                            <button type="submit" class="btn btn-primary">Sauvegarder</button>
                                        </form>
                                    </div>

                                </div>

                                <div class="tab-pane fade" id="history"
                                     role="tabpanel" aria-labelledby="history-tab">

                                    <c:forEach var="event" items="${requestScope.events}">

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
                        </div>
                    </div>
                </div>
            </div>

            <div class="d-flex justify-content-center">
                <div class="pt-5 pb-5">
                    <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                        <div class="form row justify-content-center pb-5">
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modifyContactModal">Supprimer</button>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="modifyContactModal" tabindex="-1" role="dialog" aria-labelledby="modifyContactModallLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Suppression</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Etes-vous sur de vouloir supprimer ce contact ?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
                                        <a href="${delete_link}">
                                            <button type="submit" class="btn btn-primary">Supprimer définitivement</button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </c:if>
                </div>
            </div>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="footer">
        <c:import url="/WEB-INF/utils/footer.jsp"/>
    </div>

</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
