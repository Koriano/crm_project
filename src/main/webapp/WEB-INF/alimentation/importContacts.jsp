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

        <div class="row text-center">
            <div class="col-lg-6 col-md-8 col-sm-9 mx-auto pt-5 pb-4">
                <h2>Import de contacts au format CSV</h2><br>
                <div>

                <h6>Le CSV doit être organisé de la sorte <b>(*champs obligatoires)</b>:</h6>
                    <p><b>Adresse;Rôle*;Entité;Mail;Tél;Nom*;Prénom*</b></p>
                    <p><i>Note: La première ligne du csv servant d'en-tête est ignorée</i></p>

                    <div class="container pt-5">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="card-body">
                                <form method="POST" action="/importContacts" enctype="multipart/form-data">
                                    <div class="form-group">
                                        File:

                                        <div class="row pt-3 justify-content-center">
                                            <div class="mb-3">
                                                <div class="custom-file">
                                                    <input type="file" class="custom-file-input" name="file" id="file">
                                                    <label class="custom-file-label" for="file">Choisir un fichier</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row justify-content-center pt-3">
                                            <button class="btn btn-primary" type="submit" value="Upload" name="Télécharger" id="upload">
                                                Télécharger
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <c:out value="${requestScope.successMessage}"/>
                </div>
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
