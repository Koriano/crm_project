<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group pt-3">
    <label for="name">Nom <span>*</span></label>
    <input type="text" id="name" name="name" value="<c:out value="${requestScope.entity.name}"/>" maxlength="50" class="form-control" placeholder="Nom"/>
</div>
<span>${requestScope.form.errors['name']}</span>

<div class="form-group pt-3">
    <label for="type">Type entité <span>*</span></label>
    <select id="type" name="type" class="form-control">
        <option value="" <c:out value="${empty requestScope.entity.type ? 'selected':''}"/>>Sélectionnez un type d'entité</option>
        <c:forEach var="type" items="${requestScope.types}">
            <option value="<c:out value="${type}"/>" <c:out value="${requestScope.entity.type == type ? 'selected':''}"/>><c:out value="${type}"/></option>
        </c:forEach>
    </select>
</div>
<span>${requestScope.form.errors['type']}</span>

<div class="form-group pt-3" id="siretDiv">
    <label for="siret">SIRET <span>*</span></label>
    <input type="text" id="siret" name="siret" value="<c:out value="${requestScope.entity.siret}"/>" maxlength="50" class="form-control" placeholder="SIRET"/>
</div>
<span>${requestScope.form.errors['siret']}</span>

<div class="form-group pt-3">
    <label for="address">Adresse <span>*</span></label>
    <input type="text" id="address" name="address" value="<c:out value="${requestScope.entity.address}"/>" class="form-control" placeholder="Adresse"/>
</div>
<span>${requestScope.form.errors['address']}</span>

<div class="form-group pt-3">
    <label for="intern_nb">Nombre de stagiaires <span>*</span></label>
    <input type="number" id="intern_nb" name="intern_nb" min="0" value="<c:out value="${empty requestScope.entity.intern_nb ? 0 : requestScope.entity.intern_nb}"/>" class="form-control" placeholder="Nombre de stagiaires"/>
</div>
<span>${requestScope.form.errors['intern_nb']}</span>

<div class="form-group pt-3">
    <label for="description">Description </label>
    <input type="text" id="description" name="description" value="<c:out value="${requestScope.entity.description}"/>" class="form-control" placeholder="Description"/>
</div>
<span>${requestScope.form.errors['description']}</span>
