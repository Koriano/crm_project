<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Changer son mot de passe</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div class="row">
        <div class="col"></div>

        <div class="col pt-3">
            <div class="container-md pt-3 pb-3">

                <form method="post" action="<c:url value="/home/changePassword"/>">
                    <fieldset>

                        <div class="form-group">
                            <label for="old_password">Ancien mot de passe <span>*</span></label>
                            <input type="password" id="old_password" name="old_password" maxlength="100" class="form-control"/>
                        </div>
                        <span>${requestScope.form.errors['old_password']}</span>

                        <c:import url="/WEB-INF/utils/passwordForm.jsp"/>

                        <div class="form row justify-content-center">
                            <button type="submit" class="btn btn-primary">Modifier le mot de passe</button>
                        </div>

                        <span>${requestScope.form.result}</span>

                    </fieldset>
                </form>

            </div>
        </div>

        <div class="col"></div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
