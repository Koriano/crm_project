<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un contact</title>
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

                <form method="post" action="<c:url value="/addContact"/>">
                    <fieldset>

                        <c:import url="/WEB-INF/utils/contactForm.jsp"/>

                        <div class="form row justify-content-center pb-5 pt-2">
                            <button type="submit" class="btn btn-primary">Créer contact</button>
                        </div>

                        <c:if test="${not requestScope.form.isDouble() || requestScope.contact == null}">
                            <div class="text-danger pb-1">${requestScope.form.result}</div>
                        </c:if>

                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    <div class="footer">
        <c:import url="/WEB-INF/utils/footer.jsp"/>
    </div>

</div>

    <script src="<c:url value="/js/contact_form_script.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
