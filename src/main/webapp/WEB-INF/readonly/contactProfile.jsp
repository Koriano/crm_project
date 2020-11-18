<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="${requestScope.contact.name}"/> <c:out value="${requestScope.contact.surname}"/> </title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>

    <div id="corps">
        <div>
            <h2> <b> <c:out value="${requestScope.contact.name} ${requestScope.contact.surname}"/> </b> </h2>
            <p> <b>Role : </b> <c:out value="${requestScope.contact.role}"/> </p>
            <p> <b>Entité : </b> <c:out value="${requestScope.contact.entity}"/> </p>

            <p> <b>Mail : </b>
                <c:forEach var="mail" items="${requestScope.contact.mailsList}">
                    <c:out value="${mail}"/> <br>
                </c:forEach>
            </p>

            <p> <b>Phone : </b>
                <c:forEach var="phone" items="${requestScope.contact.phonesList}">
                    <c:out value="${phone}"/> <br>
                </c:forEach>
            </p>

            <p> <b>Adresse : </b> <c:out value="${requestScope.contact.address}"/> </p>
        </div>

        <div class="tab">
            <button class="tablinks" onclick="openTab(this, 'Comment')" id="defaultOpen">Comment</button>
            <button class="tablinks" onclick="openTab(this, 'History')">History</button>
        </div>

        <div id="Comment" class="tabcontent">
            <textarea name="commentContent" rows="10" cols="100" form="commentForm">
                <c:out value="${requestScope.comment.content}"/>
            </textarea>
            <form method="post" action="<c:url value="/saveComment"/>" id="commentForm">
                <input type="submit" value="Sauvegarder">
            </form>
        </div>

        <div id="History" class="tabcontent">
            <c:forEach var="event" items="${requestScope.contact.eventsList}">
                <div class="event">
                    <h4>${event.name} (<c:out value="${event.type}"/>)</h4>
                    <p><b>Date : </b> <c:out value="${event.date}"/></p>
                    <p><b>Description : </b> <c:out value="${event.description}"/></p>
                </div>
            </c:forEach>
        </div>
    </div>

    <script>
        function openTab(evt, tab){
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(tab).style.display = "block";
            evt.currentTarget.className += " active";
        }

        // Comment tab open by default
        document.getElementById("defaultOpen").click()
    </script>
</body>
</html>
