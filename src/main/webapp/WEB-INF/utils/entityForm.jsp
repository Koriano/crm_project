<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="double" value="false" scope="session"/>

<c:if test="${requestScope.form.isDouble() and requestScope.entity != null}">

    <c:set var="double" value="true" scope="session"/>

    <!-- Trigger the modal with a button -->
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" id="confirm_button" hidden>Confirmation double</button>

    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <div class="container text-center justify-content-center">
                        <h4 class="modal-title">Confirmation</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <p>
                        L'application détecte une entité similaire à celui que vous venez de rentrer,
                        confirmez-vous vouloir créer cette entité dans l'application ?
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Confirmer</button>
                </div>
            </div>

        </div>
    </div>
</c:if>

<div class="form-group pt-3">
    <label for="name">Nom <span>*</span></label>
    <input type="text" id="name" name="name" value="<c:out value="${requestScope.entity.name}"/>" maxlength="50" class="form-control" placeholder="Nom"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['name']}</div>

<div class="form-group pt-3">
    <label for="type">Type entité <span>*</span></label>
    <select id="type" name="type" class="form-control">
        <option value="" <c:out value="${empty requestScope.entity.type ? 'selected':''}"/>>Sélectionnez un type d'entité</option>
        <c:forEach var="type" items="${requestScope.types}">
            <option value="<c:out value="${type}"/>" <c:out value="${requestScope.entity.type == type ? 'selected':''}"/>><c:out value="${type}"/></option>
        </c:forEach>
    </select>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['type']}</div>

<div class="form-group pt-3" id="newTypeDiv">
    <label for="newType">Nouveau type <span>*</span></label>
    <input type="text" id="newType" name="newType" value="<c:out value="${requestScope.newType}"/>" maxlength="20" class="form-control" placeholder="Nouveau type"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['newType']}</div>

<div class="form-group pt-3" id="siretDiv">
    <label for="siret">SIRET <span>*</span></label>
    <input type="text" id="siret" name="siret" value="<c:out value="${requestScope.entity.siret}"/>" maxlength="50" class="form-control" placeholder="SIRET"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['siret']}</div>

<div class="form-group pt-3">
    <label for="address">Adresse <span>*</span></label>
    <input type="text" id="address" name="address" value="<c:out value="${requestScope.entity.address}"/>" maxlength="80" class="form-control" placeholder="Adresse"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['address']}</div>

<div class="form-group pt-3">
    <label for="intern_nb">Nombre de stagiaires <span>*</span></label>
    <input type="number" id="intern_nb" name="intern_nb" min="0" value="<c:out value="${empty requestScope.entity.intern_nb ? 0 : requestScope.entity.intern_nb}"/>" class="form-control" placeholder="Nombre de stagiaires"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['intern_nb']}</div>

<div class="form-group pt-3">
    <label for="description">Description </label>
    <input type="text" id="description" name="description" value="<c:out value="${requestScope.entity.description}"/>" maxlength="1000" class="form-control" placeholder="Description"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['description']}</div>

<c:if test="${requestScope.form.isDouble()}">
    <script>
        document.addEventListener("DOMContentLoaded", function(){
            document.getElementById("confirm_button").click();
        });
    </script>
</c:if>