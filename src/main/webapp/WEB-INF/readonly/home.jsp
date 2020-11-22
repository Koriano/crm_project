<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div>
        <h2>Bonjour, <c:out value="${sessionScope.user.name}"/></h2>
        <p>Vous pouvez : </p>
        <a href="<c:url value="/research"/>">Rechercher un contact ou une entité</a>
        <br>
        <a href="<c:url value="/myEvents"/> ">Consulter vos événements</a>
        <br>

        <c:set var="right" value="${sessionScope.user.right}" scope="page"/>

        <c:if test="${right == 'Alimentation' or right == 'Administrateur'}">
            <a href="<c:url value="/addContact"/> ">Ajouter un contact</a>
            <br>
            <a href="<c:url value="/addEntity"/> ">Ajouter une entité</a>
            <br>
            <a href="<c:url value="/importContacts"/> ">Importer des contacts</a>
            <br>
        </c:if>
        <c:if test="${right == 'Administrateur'}">
            <a href="<c:url value="/accounts"/> ">Gérer les comptes</a>
            <br>
            <a href="<c:url value="/sectors"/> ">Gérer les secteurs</a>
            <br>
            <a href="<c:url value="/exportContacts"/> ">Exporter des contacts</a>
            <br>
        </c:if>
    </div>
</body>
</html>
