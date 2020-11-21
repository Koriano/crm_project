<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un contact</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/style/lib/bootstrap.min.css"/>" />
</head>
<body>
    <c:import url="/WEB-INF/utils/menu.jsp"/>
    <form method="post" action="<c:url value="/addContact"/>">
        <fieldset>
            <c:import url="/WEB-INF/utils/contactForm.jsp"/>
            <input type="submit" value="Créer contact">
            <br>
            <span>${requestScope.form.result}</span>
        </fieldset>
    </form>

    <script src="<c:url value="/js/contact_form_script.js"/>"></script>
</body>
</html>
