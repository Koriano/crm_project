<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark " id="menu">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar"
            aria-controls="collapsibleNavbar" aria-expanded="false" aria-label="Afficher barre de navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse"></div>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <div class="container ">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0 justify-content-center">

                <li class="nav-item">
                    <a class="nav-link font-weight-bold pr-lg-3" style="font-size : 25px; " href="<c:out value="/home"/>">
                        Accueil
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link font-weight-bold pr-lg-3" style="font-size : 25px; " href="<c:out value="/research"/>">
                        Recherche
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link font-weight-bold pr-lg-3" style="font-size : 25px; " href="<c:out value="/myEvents"/>">
                        Mes événements
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link font-weight-bold pr-lg-3"  style="font-size : 25px; " href="<c:out value="/importContacts"/>">
                        Importer des contacts
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link font-weight-bold pr-lg-3"  style="font-size : 25px; "href="<c:out value="/exportContacts"/>">
                        Exporter des contacts
                    </a>
                </li>

            </ul>

            <ul class="navbar-nav justify-content-end">
                <li class="nav-item ">
                    <a href="<c:url value="/disconnect"/>">
                        <button class="btn btn-danger">
                            <svg width="1.5em" height="1.5em" viewBox="0 0 16 16" class="bi bi-box-arrow-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"></path>
                                <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"></path>
                            </svg>
                        </button>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>