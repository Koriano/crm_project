<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier une entité</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
<c:import url="/WEB-INF/utils/menu.jsp"/>

<div class="row">
    <div class="col"></div>

    <div class="col pt-3">
        <div class="container-md pt-3 pb-3">

            <form method="post" action="<c:url value="/research/entityProfile/modify"/>">
                <fieldset>

                    <c:import url="/WEB-INF/utils/entityForm.jsp"/>

                    <div class="form row justify-content-center">
                        <button type="submit" class="btn btn-primary">Mettre à jour</button>
                    </div>

                    <span>${requestScope.form.result}</span>

                </fieldset>
            </form>

        </div>
    </div>

    <div class="col"></div>
</div>

<c:import url="/WEB-INF/utils/footer.jsp"/>
<script src="<c:url value="/js/entity_form_script.js"/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>