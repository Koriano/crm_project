<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.account.name}"/> </title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/style/front.css"/>" />
</head>
<body>
    <div id="wrapper">
        <div class="header">
            <c:import url="/WEB-INF/utils/menu.jsp"/>
        </div>


        <div class="container body">
            <c:url var="associated_contact_link" value="/research/contact">
                <c:param name="id" value="${requestScope.user.contact.id}"/>
            </c:url>

            <div class="container">
                <div class="row">
                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">

                        <div class="card">

                            <div class="card-header flex-column">
                                <h5 class="text-center"> <b> <c:out value="${requestScope.user.name}"/> </b> </h5>
                            </div>

                            <div class="card-body">
                                <div class="pt-3">
                                    <p> <b>Username : </b> <c:out value="${requestScope.user.username}"/> </p>
                                </div>

                                <div class="pt-3">
                                    <p> <b>Droit : </b> <c:out value="${requestScope.user.right}"/> </p>
                                </div>

                                <div class="pt-3">
                                    <p> <b>Contact associé : </b>
                                        <a href="${associated_contact_link}"><c:out value="${requestScope.user.contact.name} ${requestScope.user.contact.surname}"/></a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-center pt-5 pb-3">
                    <a href="<c:url value="/home/changePassword"/>"><button type="button" class="btn btn-primary">Modifier le mot de passe</button></a>
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
