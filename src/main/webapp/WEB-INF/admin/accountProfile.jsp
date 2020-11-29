<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.account.name}"/> </title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/style/front.css"/>" />
</head>
<body>
<div id="wrapper">
    <div class="header">
        <c:import url="/WEB-INF/utils/menu.jsp"/>
    </div>


    <div class="container body">
        <c:url var="associated_contact_link" value="/research/contact">
            <c:param name="id" value="${requestScope.account.contact.id}"/>
        </c:url>

        <c:url var="modify_link" value="/rights/account/modify">
            <c:param name="id" value="${requestScope.account.id}"/>
        </c:url>

        <c:url var="delete_link" value="/rights/account/delete">
            <c:param name="id" value="${requestScope.account.id}"/>
        </c:url>

        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">

                    <div class="card">

                        <div class="card-header flex-column">
                            <h5 class="text-center"> <b> <c:out value="${requestScope.account.name}"/> </b> </h5>
                        </div>

                        <div class="card-body">
                            <div class="pt-3">
                                <p> <b>Username : </b> <c:out value="${requestScope.account.username}"/> </p>
                            </div>

                            <div class="pt-3">
                                <p> <b>Droit : </b> <c:out value="${requestScope.account.right}"/> </p>
                            </div>

                            <div class="pt-3">
                                <p> <b>Contact associé : </b>
                                    <a href="${associated_contact_link}"><c:out value="${requestScope.account.contact.name} ${requestScope.account.contact.surname}"/></a>
                                </p>
                            </div>

                            <div class="pt-3">
                                <p> <b>Secteurs associés : </b>
                                    <c:forEach var="sector" items="${requestScope.account.sectors}">

                                        <c:url var="sector_link" value="/sectors/modify">
                                            <c:param name="id" value="${sector.id}"/>
                                        </c:url>

                                        <p> <a href="${sector_link}"> <c:out value="${sector.name}"/> </a> </p>
                                    </c:forEach>
                                </p>
                            </div>

                            <div class="d-flex justify-content-center pt-5 pb-3">
                                <a href="${modify_link}">
                                    <button class="btn btn-primary ">Modifier le compte</button>
                                </a>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>


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
    </div>
    <div class="footer">
        <c:import url="/WEB-INF/utils/footer.jsp"/>
    </div>

</div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
