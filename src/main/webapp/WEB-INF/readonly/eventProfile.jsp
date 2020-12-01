<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.account.name}"/> </title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <c:url var="modify_link" value="/myEvents/details/modify">
        <c:param name="id" value="${requestScope.event.id}"/>
    </c:url>

    <c:url var="delete_link" value="/myEvents/details/delete">
        <c:param name="id" value="${requestScope.event.id}"/>
    </c:url>

    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">

                <div class="card">

                    <div class="card-header flex-column">
                        <h5 class="text-center"> <b> <c:out value="${requestScope.event.name}"/> </b> </h5>
                    </div>

                    <div class="card-body">
                        <div class="pt-3">
                            <p> <b>Date : </b> <c:out value="${requestScope.date}"/> </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Type : </b> <c:out value="${requestScope.event.type}"/> </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Description </b>
                                <c:out value="${requestScope.event.description}"/>
                            </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Participants : </b>
                                <c:forEach var="contact" items="${requestScope.event.contactsList}">

                                    <c:url var="contact_link" value="/research/contact">
                                        <c:param name="id" value="${contact.id}"/>
                                    </c:url>

                                    <p> <a href="${contact_link}"> <c:out value="${contact.name} ${contact.surname}"/> </a> </p>
                                </c:forEach>
                            </p>
                        </div>

                        <c:if test="${requestScope.event.contactsList[0].id == sessionScope.user.contact.id}">
                            <div class="d-flex justify-content-center pt-5 pb-3">
                                <a href="${modify_link}">
                                    <button class="btn btn-primary ">Modifier</button>
                                </a>
                            </div>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>

        <div class="col pt-4">
            <div class="container-lg pt-3 pb-3">

            </div>
        </div>


        <div class="col"></div>
    </div>

    <c:if test="${requestScope.event.contactsList[0].id == sessionScope.user.contact.id}">
        <div class="d-flex justify-content-center">
            <div class="pt-5 pb-5">
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
                                Etes-vous sur de vouloir supprimer ce compte ?
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
            </div>
        </div>
    </c:if>

    <c:import url="/WEB-INF/utils/footer.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
