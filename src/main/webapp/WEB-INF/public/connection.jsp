<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Research</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <h1>Connexion au CRM</h1>

    <form method="post" action="<c:url value="/connect"/>">
        <fieldset>
            <label for="username">Nom d'utilisateur </label>
            <input type="text" id="username" name="username" value="<c:out value="${requestScope.account.username}"/>" maxlength="25">
            <br>

            <label for="password">Mot de passe </label>
            <input type="password" id="password" name="password" maxlength="100">
            <br>

            <button>Se connecter</button>

            <span>${requestScope.form.result}</span>
        </fieldset>
    </form>

</body>
</html>
