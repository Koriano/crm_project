<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Organiser un événement</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">

                <form method="post" action="<c:url value="/myEvents/create"/>" id="event_form">
                    <fieldset>

                        <c:import url="/WEB-INF/utils/eventForm.jsp"/>

                        <div class="form row justify-content-center">
                            <button type="submit" class="btn btn-primary">Créer l'événement</button>
                        </div>

                        <span>${requestScope.form.result}</span>

                    </fieldset>
                </form>
            </div>
        </div>
    </div>

<%--    <c:import url="/WEB-INF/utils/footer.jsp"/>--%>
    <script src="<c:url value="/js/contact_form_script.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
