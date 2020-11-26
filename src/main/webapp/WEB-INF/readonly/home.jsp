<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html style="height: 100%;">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body style="height: 100%;">
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">
                <h2>Bonjour, <c:out value="${sessionScope.user.name}"/></h2>
                <p>Vous pouvez : </p>
                <a href="<c:url value="/research"/>">Rechercher un contact ou une entité</a>
                <br>
                <a href="<c:url value="/myEvents"/> ">Consulter vos événements</a>
                <br>

                <c:set var="right" value="${sessionScope.user.right}" scope="page"/>

                <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
                    <a href="<c:url value="/addContact"/> ">Ajouter un contact</a>
                    <br>
                    <a href="<c:url value="/addEntity"/> ">Ajouter une entité</a>
                    <br>
                    <a href="<c:url value="/importContacts"/> ">Importer des contacts</a>
                    <br>
                </c:if>
                <c:if test="${right == 'Administrateur'}">
                    <a href="<c:url value="/rights"/> ">Gérer les comptes</a>
                    <br>
                    <a href="<c:url value="/sectors"/> ">Gérer les secteurs</a>
                    <br>
                    <a href="<c:url value="/exportContacts"/> ">Exporter des contacts</a>
                    <br>
                </c:if>

            </div>
        </div>
    </div>



    <c:import url="/WEB-INF/utils/footer.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
