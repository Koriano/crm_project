<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Secteurs</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <h2>Gestion des secteurs</h2>
    <a href="<c:url value="/sectors/add"/>"><button type="button">Créer un secteur</button></a>

    <div>
        <c:forEach var="sector" items="${requestScope.sectors}">
            <div>
                <h4><c:out value="${sector.name}"/></h4>

                <%-- Modify link --%>
                <c:url var="modify_link" value="/sectors/modify">
                    <c:param name="name" value="${sector.name}"/>
                </c:url>
                <a href="${modify_link}"><button>Modifier</button></a>

                <%-- Delete link --%>
                <c:url var="delete_link" value="/sectors/delete">
                    <c:param name="name" value="${sector.name}"/>
                </c:url>
                <a href="${delete_link}"><button>Supprimer</button></a>
            </div>
        </c:forEach>
    </div>

</body>
</html>
