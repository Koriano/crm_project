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

    <h2>Gestion des droits</h2>
    <a href="<c:url value="/rights/account/add"/>"><button type="button">Créer un compte</button></a>

    <div>
        <form method="post" action="<c:url value="/rights"/>">
            <c:forEach var="account" items="${requestScope.accounts}">
                <div>
                    <label for="<c:out value="${account.username}"/> "> <c:out value="${account.name}"/></label>
                    <select id="<c:out value="${account.username}"/>" name="<c:out value="${account.username}"/>">
                        <c:forEach var="right" items="${requestScope.rights}">
                            <option value="<c:out value="${right}"/>" <c:out value="${account.right == right ? 'selected':''}"/>> <c:out value="${right}"/> </option>
                        </c:forEach>
                    </select>
                    <br>

                    <c:url var="modify_link" value="/rights/account/modify">
                        <c:param name="username" value="${account.username}"/>
                    </c:url>
                    <a href="${modify_link}"><button type="button">Modifier</button></a>

                    <c:url var="delete_link" value="/rights/account/delete">
                        <c:param name="username" value="${account.username}"/>
                    </c:url>
                    <a href="${delete_link}"><button type="button">Supprimer</button></a>
                </div>
            </c:forEach>
            <span>${requestScope.form.errors['right']}</span>

            <button type="submit">Mettre à jour</button>
            <span>${requestScope.form.result}</span>
        </form>
    </div>

</body>
</html>
