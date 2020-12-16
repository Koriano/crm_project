<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

