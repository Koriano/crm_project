<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Droits utilisateurs</title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto pt-5">
                <div class="text-center">
                    <h2>Gestion des droits</h2>
                </div>


                <div class="container pt-4 pb-5">
                    <a href="<c:url value="/rights/account/add"/>">
                        <button class="btn btn-info btn-block btn-lg d-flex justify-content-between align-items-center">
                            Créer un compte
                            <svg width="1.5em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-lock2" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M8 6a1 1 0 0 0-1 1v1h2V7a1 1 0 0 0-1-1zm2 2.076V7a2 2 0 1 0-4 0v1.076c-.54.166-1 .597-1 1.224v2.4c0 .816.781 1.3 1.5 1.3h3c.719 0 1.5-.484 1.5-1.3V9.3c0-.627-.46-1.058-1-1.224z"/>
                                <path d="M4 0h5.5v1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h1V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z"/>
                                <path d="M9.5 3V0L14 4.5h-3A1.5 1.5 0 0 1 9.5 3z"/>
                            </svg>
                        </button>
                    </a>
                </div>


                <div class="container-fluid pt-2">
                    <c:forEach var="account" items="${requestScope.accounts}">
                        <div class="container pt-2 pb-2">
                            <c:url var="profile_link" value="/rights/account">
                                <c:param name="id" value="${account.id}"/>
                            </c:url>

                            <a href="${profile_link}">
                                <div class="card">
                                    <div class="card-header flex-column">
                                        <h4 class="text-secondary">
                                            <b><c:out value="${account.name}"/></b>
                                        </h4>
                                    </div>

                                    <div class="card-body">
                                        <div class="row justify-content-between">
                                                <p class="text-secondary">
                                                    <c:out value="${account.right}"/>
                                                </p>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>


</body>
</html>
