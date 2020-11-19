<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Research</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>
    <a href="/addContact"><button>Ajouter Contact</button></a>
    <div id="corps">
        <!-- Iterating over contacts -->
        <c:forEach var="contact" items="${requestScope.contacts}" varStatus="boucle">
            <a href="<c:url value="/research/contact">
                        <c:param name="id" value="${contact.id}"/>
                    </c:url>">
                <div>
                    <h4> <b> <c:out value="${contact.name} ${contact.surname}"/> </b> </h4>
                    <p> <b>Role : </b> <c:out value="${contact.role}"/> </p>
                    <p> <b>Entité : </b> <c:out value="${contact.entity}"/> </p>

                    <p> <b>Mail : </b>
                    <c:forEach var="mail" items="${contact.mailsList}">
                        <c:out value="${mail}"/> <br>
                    </c:forEach>
                    </p>

                    <p> <b>Phone : </b>
                        <c:forEach var="phone" items="${contact.phonesList}">
                            <c:out value="${phone}"/> <br>
                        </c:forEach>
                    </p>

                    <p> <b>Adresse : </b> <c:out value="${contact.address}"/> </p>
                </div>
            </a>
        </c:forEach>

        <!-- Iterating over entities -->
        <c:forEach var="entity" items="${requestScope.entities}" varStatus="boucle">
            <a href="<c:url value="/entity">
                        <c:param name="name" value="${entity.name}"/>
                     </c:url>">
                
                <h4> <c:out value="${entity.name}"/></h4>
                <p> <b>Type : </b> <c:out value="${entity.type}"/> </p>
                <p> <b>Adresse : </b> <c:out value="${entity.address}"/> </p>

                <c:if test="${entity.type == 'Entreprise'}">
                    <p> <b>Siret : </b> <c:out value="${entity.siret}"/> </p>
                </c:if>

                <p> <b>Nombre de stagiaires : </b> <c:out value="${entity.address}"/> </p>
                <p> <b>Description : </b> <c:out value="${entity.description}"/> </p>
            </a>
        </c:forEach>
    </div>
</body>
</html>
