<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark" id="menu">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar"
            aria-controls="collapsibleNavbar" aria-expanded="false" aria-label="Afficher barre de navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse"></div>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="/research"/>">Recherche</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="/myEvents"/>">Mes événements</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="/importContacts"/>">Importer des contacts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="/exportContacts"/>">Exporter des contacts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="/disconnect"/>">Déconnexion</a>
            </li>
        </ul>
    </div>
</nav>