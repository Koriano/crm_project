<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.entity.name}"/></title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
<c:import url="/WEB-INF/utils/menu.jsp"/>


<c:set var="right" value="${sessionScope.user.right}" scope="page"/>

<div class="container">
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
                                    <a href="<c:url value="/research/entityProfile/delete"><c:param name="entityId" value="${requestScope.entity.id}"/></c:url>">
                                        <button class="btn btn-danger">Supprimer l'entité</button>
                                    </a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

</body>
</html>
