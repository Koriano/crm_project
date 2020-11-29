<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier une entité</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/style/front.css"/>" />
</head>

<body>

<div id="wrapper">
    <div class="header">
        <c:import url="/WEB-INF/utils/menu.jsp"/>
    </div>


    <div class="container body">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">
                <c:url var="form_link" value="/research/entityProfile/modify">
                    <c:param name="entityId" value="${requestScope.entity.id}"/>
                </c:url>
                <form method="post" action="${form_link}">
                    <fieldset>

                        <c:import url="/WEB-INF/utils/entityForm.jsp"/>

                        <div class="form row justify-content-center pb-5">
                            <button type="submit" class="btn btn-primary">Mettre à jour</button>
                        </div>

                        <span><c:out value="${requestScope.form.result || requestScope.form.result == null ? null : 'Echec de la modification : vérifiez les données entrées.'}"/></span>

                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    <div class="footer">
        <c:import url="/WEB-INF/utils/footer.jsp"/>
    </div>

</div>

<script src="<c:url value="/js/entity_form_script.js"/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>