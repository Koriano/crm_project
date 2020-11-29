<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Droits utilisateurs</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">
                <h2>Gestion des droits</h2>

                <a href="<c:url value="/rights/account/add"/>"><button type="button">Créer un compte</button></a>
                

                <div class="card-body ">
                    <div class="d-flex justify-content-between">
                        <div class="col-auto">
                            <p class="text-secondary">
                                <c:out value="${contact.role}"/>
                            </p>
                        </div>

                        <div class="col-auto">
                            <p class="text-secondary">
                                <c:out value="${contact.entity.name}"/>
                            </p>
                        </div>
                    </div>
                </div>

    <div>
        <c:forEach var="account" items="${requestScope.accounts}">
            <c:url var="profile_link" value="/rights/account">
                <c:param name="id" value="${account.id}"/>
            </c:url>
            <a href="${profile_link}">
                <div>
                    <p><c:out value="${account.name}"/> (<c:out value="${account.right}"/>) </p>

                    <c:url var="modify_link" value="/rights/account/modify">
                        <c:param name="id" value="${account.id}"/>
                    </c:url>
                    <a href="${modify_link}"><button type="button">Modifier</button></a>

                    <c:url var="delete_link" value="/rights/account/delete">
                        <c:param name="id" value="${account.id}"/>
                    </c:url>
                    <a href="${delete_link}"><button type="button">Supprimer</button></a>
                </div>
            </a>
        </c:forEach>
    </div>


</body>
</html>
