<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>

<body class="text-center" style="background-color: #f5f5f5;">
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card card-signin my-5" style="border-radius: 1rem; border-width: 3px;">
                    <div class="card-body">
                        <h5 class="card-title text-center">Connexion au CRM</h5>
                        <form class="form-signin" method="post" action="<c:url value="/connect"/>">

                            <div class="form-label-group pt-3">
                                <input type="text" id="username" name="username" class="form-control" maxlength="25" placeholder="Nom d'utilisateur" value="<c:out value="${requestScope.account.username}"/>">
                                <label for="username">Nom d'utilisateur</label>
                            </div>

                            <div class="form-label-group pt-3">
                                <input type="password"  id="password" name="password" class="form-control" maxlength="100" placeholder="Password" required>
                                <label for="password">Mot de passe</label>
                            </div>

                            <div class="pt-3">
                                <button class="btn btn-primary" type="submit">Se connecter</button>
                                <span>${requestScope.form.result}</span>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
