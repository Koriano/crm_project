<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.contact.name}"/> <c:out value="${requestScope.contact.surname}"/> </title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div>
        <a href="<c:url value="/research/contact/modify"/>"><button>Modifier le contact</button></a>
        <a href="<c:url value="/research/contact/delete"/>"><button>Supprimer le contact</button></a>

        <div>
            <h2> <b> <c:out value="${requestScope.contact.name} ${requestScope.contact.surname}"/> </b> </h2>
            <p> <b>Role : </b> <c:out value="${requestScope.contact.role}"/> </p>
            <p> <b>Entité : </b> <c:out value="${requestScope.contact.entity}"/> </p>

            <p> <b>Mail : </b>
                <c:forEach var="mail" items="${requestScope.contact.mailsList}">
                    <p><c:out value="${mail}"/></p> <br>
                </c:forEach>
            </p>

            <p> <b>Téléphone : </b>
                <c:forEach var="phone" items="${requestScope.contact.phonesList}">
                    <p><c:out value="${phone}"/></p> <br>
                </c:forEach>
            </p>

            <p> <b>Adresse : </b> <c:out value="${requestScope.contact.address}"/> </p>
        </div>

        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Comment')" id="defaultOpen">Comment</button>
            <button class="tablinks" onclick="openTab(event, 'History')">History</button>
        </div>

        <div id="Comment" class="tabcontent">
            <textarea name="commentContent" rows="10" cols="100" form="commentForm"><c:out value="${requestScope.comment.content}"/></textarea>
            <form method="post" action="<c:url value="/saveComment"/>" id="commentForm">
                <input type="submit" value="Sauvegarder">
            </form>
        </div>

        <div id="History" class="tabcontent">
            <c:forEach var="event" items="${requestScope.contact.eventsList}">
                <div>
                    <h4><c:out value="${event.name}"/> (<c:out value="${event.type}"/>)</h4>
                    <p><b>Date : </b> <c:out value="${event.date}"/></p>
                    <p><b>Description : </b> <c:out value="${event.description}"/></p>
                </div>
            </c:forEach>
        </div>
    </div>

    <c:import url="/WEB-INF/utils/footer.jsp"/>
    <script src="<c:out value="/js/tabs_script.js"/>"></script>
</body>
</html>
