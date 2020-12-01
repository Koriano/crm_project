<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title> <c:out value="Importer contacts"/></title>
    <link rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
    <link rel="stylesheet" href="<c:url value="/style/front.css"/>" />
</head>

<body>
<div id="wrapper">
    <div class="header">
        <c:import url="/WEB-INF/utils/menu.jsp"/>
    </div>

    <div class="container body">
        <h1>Import de contacts au format CSV</h1>
        <h5>Le CSV doit être organisé de la sorte (*champs obligatoires):</h5>
        <p>Adresse;Rôle*;Entité;Mail;Tél;Nom*;Prénom*</p>
        <p>Note: La première ligne du csv servant d'en-tête est ignorée</p><br>
        <form method="POST" action="/importContacts" enctype="multipart/form-data">
            File:
            <input type="file" name="file" id="file"/> <br/>
            <input type="submit" value="Upload" name="upload" id="upload"/>
        </form>
        <c:out value="${requestScope.successMessage}"/>
    </div>


    <div class="footer">
        <c:import url="/WEB-INF/utils/footer.jsp"/>
    </div>

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/style/scripts/bootstrap.min.js"/>"></script>

</body>
</html>
