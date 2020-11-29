<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un secteur</title>
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

                <form method="post" action="<c:url value="/sectors/modify"/>">
                    <fieldset>

                        <c:import url="/WEB-INF/utils/sectorForm.jsp"/>

                        <div class="form row justify-content-center pt-4 pb-5">
                            <button type="submit" class="btn btn-primary">Mettre à jour</button>
                        </div>

                        <span>${requestScope.form.result}</span>

                    </fieldset>
                </form>

            </div>
        </div>
    </div>

    <div class="footer">
        <c:import url="/WEB-INF/utils/footer.jsp"/>
    </div>

</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
