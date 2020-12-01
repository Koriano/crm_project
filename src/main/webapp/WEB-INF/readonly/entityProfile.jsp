<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.entity.name}"/></title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/style/front.css"/>" />
</head>

<body>
<div id="wrapper">
    <div class="header">
        <c:import url="/WEB-INF/utils/menu.jsp"/>
    </div>

    <c:set var="right" value="${sessionScope.user.right}" scope="page"/>
    <div class="container body">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">
                <div class="card">

                    <div class="card-header flex-column">
                        <h5 class="text-center"> <b> <c:out value="${requestScope.entity.name}"/> </b> </h5>
                    </div>

                    <div class="card-body">

                        <c:if test="${requestScope.entity.type == 'Entreprise'}">
                            <div class="pt-2">
                                <p> <b>SIRET : </b> <c:out value="${requestScope.entity.siret}"/> </p>
                            </div>
                        </c:if>


                        <div class="pt-3">
                            <p> <b>Type : </b> <c:out value="${requestScope.entity.type}"/> </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Adresse postale : </b> <c:out value="${requestScope.entity.address}"/> </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Nombre de stagiaires : </b> <c:out value="${requestScope.entity.intern_nb}"/> </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Description : </b> <c:out value="${requestScope.entity.description}"/> </p>
                        </div>


                        <div class="d-flex justify-content-center pt-5 pb-3">
                            <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                                <div class="row">
                                    <div class="col-auto">
                                        <a href="<c:url value="/research/entityProfile/modify"><c:param name="entityId" value="${requestScope.entity.id}"/></c:url>">
                                            <button class="btn btn-primary ">Modifier l'entité</button>
                                        </a>
                                    </div>

                                    <div class="col-auto">
                                        <div class="form row justify-content-center pb-5">
                                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#entityDel">Supprimer</button>
                                        </div>


                                        <!-- Modal -->
                                        <div class="modal fade" id="entityDel" tabindex="-1" role="dialog" aria-labelledby="entityDellLabel" aria-hidden="true">
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
                                                        <a href="<c:url value="/research/entityProfile/delete"><c:param name="entityId" value="${requestScope.entity.id}"/></c:url>">
                                                            <button type="submit" class="btn btn-primary">Supprimer définitivement</button>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:if>
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
