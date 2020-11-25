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

<div>
    <c:set var="right" value="${sessionScope.user.right}" scope="page"/>

    <c:if test="${right == 'Alimentation CRM' or right == 'Administrateur'}">
        <a href="<c:url value="/research/entity/modify"/>"><button>Modifier l'entité</button></a>
        <a href="<c:url value="/research/entity/delete"/>"><button>Supprimer l'entité</button></a>
    </c:if>

    <div>
        <div>
            <h2> <b> <c:out value="${requestScope.entity.name}"/> </b> </h2>
            <c:if test="${requestScope.entity.type == 'Entreprise'}">
                <p> <b>SIRET : </b> <c:out value="${requestScope.entity.siret}"/> </p>
            </c:if>
            <p> <b>Type : </b> <c:out value="${requestScope.entity.type}"/> </p>
            <p> <b>Adresse postale : </b> <c:out value="${requestScope.entity.address}"/> </p>
            <p> <b>Description : </b> <c:out value="${requestScope.entity.description}"/> </p>
    </div>
</div>

<script src="<c:out value="/js/tabs_script.js"/>"></script>
</body>
</html>
