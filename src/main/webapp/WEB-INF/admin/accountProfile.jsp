<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.account.name}"/> </title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <c:url var="associated_contact_link" value="/research/contact">
        <c:param name="id" value="${requestScope.account.contact.id}"/>
    </c:url>

    <c:url var="modify_link" value="/rights/account/modify">
        <c:param name="username" value="${requestScope.account.username}"/>
    </c:url>

    <c:url var="delete_link" value="/rights/account/delete">
        <c:param name="username" value="${requestScope.account.username}"/>
    </c:url>

    <div class="row">
        <div class="col"></div>

        <div class="col pt-4">
            <div class="container-lg pt-3 pb-3">

                <div class="card">

                    <div class="card-header flex-column">
                        <h5 class="text-center"> <b> <c:out value="${requestScope.account.name}"/> </b> </h5>
                    </div>

                    <div class="card-body">
                        <div class="pt-3">
                            <p> <b>Username : </b> <c:out value="${requestScope.account.username}"/> </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Droit : </b> <c:out value="${requestScope.account.right}"/> </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Contact associé : </b>
                                <a href="${associated_contact_link}"><c:out value="${requestScope.account.contact.name} ${requestScope.account.contact.surname}"/></a>
                            </p>
                        </div>

                        <div class="pt-3">
                            <p> <b>Secteurs associés : </b>
                                <c:forEach var="sector" items="${requestScope.account.sectors}">

                                    <c:url var="sector_link" value="/sectors/modify">
                                        <c:param name="name" value="${sector.name}"/>
                                    </c:url>

                                    <p> <a href="${sector_link}"> <c:out value="${sector.name}"/> </a> </p>
                                </c:forEach>
                            </p>
                        </div>

                        <div class="d-flex justify-content-center pt-5 pb-3">
                            <a href="${modify_link}">
                                <button class="btn btn-primary ">Modifier le compte</button>
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div class="col pt-4">
            <div class="container-lg pt-3 pb-3">

            </div>
        </div>


        <div class="col"></div>
    </div>


    <div class="d-flex justify-content-center">
        <div class="pt-5 pb-5">
            <a href="${delete_link}">
                <button class="btn btn-danger">Supprimer le compte</button>
            </a>
        </div>
    </div>

    <c:import url="/WEB-INF/utils/footer.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>
</body>
</html>
