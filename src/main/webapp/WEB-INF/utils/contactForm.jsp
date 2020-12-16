<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group pt-3">
    <label for="name">Nom <span>*</span></label>
    <input type="text" id="name" name="name" value="<c:out value="${requestScope.contact.name}"/>" maxlength="50" class="form-control" placeholder="Nom"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['name']}</div>

<div class="form-group pt-3">
    <label for="surname">Prénom <span>*</span></label>
    <input type="text" id="surname" name="surname" value="<c:out value="${requestScope.contact.surname}"/>" maxlength="50" class="form-control" placeholder="Prénom"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['surname']}</div>

<div class="form-group pt-3">
    <label for="role">Rôle <span>*</span></label>
    <select id="role" name="role" class="form-control">
        <option value="" <c:out value="${empty requestScope.contact.role ? 'selected':''}"/>>Sélectionnez un rôle</option>
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="<c:out value="${role}"/>" <c:out value="${requestScope.contact.role == role ? 'selected':''}"/>><c:out value="${role}"/></option>
        </c:forEach>
    </select>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['role']}</div>

<div class="form-group pt-3">
    <label for="entity">Entité </label>
    <select id="entity" name="entity" class="form-control">
        <option value="" <c:out value="${empty requestScope.contact.entity.name ? 'selected':''}"/>>Sélectionnez une entité</option>
        <c:forEach var="entity" items="${requestScope.entities}">
            <option value="<c:out value="${entity.id}"/>" <c:out value="${requestScope.contact.entity.id == entity.id ? 'selected':''}"/>><c:out value="${entity.name}"/></option>
        </c:forEach>
    </select>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['entity']}</div>

<div class="form-group pt-3">
    <label for="address">Adresse </label>
    <input type="text" id="address" name="address" value="<c:out value="${requestScope.contact.address}"/>" maxlength="80" class="form-control" placeholder="Adresse"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['address']}</div>

<div class="form-row align-items-end pt-3 pb-3">
    <div class="col justify-content-end" id="phoneInputs">
        <label for="phones">Téléphone </label>
        <input type="tel" id="phones" name="phone0" value="<c:out value="${requestScope.contact.phonesList[0]}"/>" maxlength="20" class="form-control" placeholder="01.23.45.67.89" >
        <c:forEach var="phone" items="${requestScope.contact.phonesList}" varStatus="boucle">
            <c:if test="${boucle.count != 0}">
                <div class="pt-2">
                    <input type="tel" name="phone${boucle.count}" value="<c:out value="${requestScope.contact.phonesList[boucle.count]}"/>" maxlength="20" class="form-control" placeholder="01.23.45.67.89"/>
                </div>
           </c:if>
        </c:forEach>
    </div>

    <div class="col-auto pt-4">
        <button class="btn btn-primary" type="button" id="addPhone">+</button>
    </div>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['phone']}</div>

<div class="form-row align-items-end pt-3">
    <div class="col justify-content-end" id="mailInputs">
        <label for="mails">Mail </label>
        <input type="email" id="mails" name="mail0" value="<c:out value="${requestScope.contact.mailsList[0]}"/>" maxlength="60" class="form-control" placeholder="email@exemple.com"/>
        <c:forEach var="mail" items="${requestScope.contact.mailsList}" varStatus="boucle">
            <c:if test="${boucle.count != 0}">
                <div class="pt-2">
                    <input type="email" name="mail${boucle.count}" value="<c:out value="${requestScope.contact.mailsList[boucle.count]}"/>" maxlength="60" class="form-control" placeholder="email@exemple.com"/>
                </div>
            </c:if>
        </c:forEach>
    </div>

    <div class="col-auto pt-3">
        <button class="btn btn-primary mt-3" type="button" id="addMail">+</button>
    </div>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['mail']}</div>

<div class="form-group row pt-3">
    <div class="col-sm-10 pt-3">
        <div class="form-check">
            <input type="checkbox" id="reserved" name="reserved" class="form-check-input" <c:out value="${requestScope.contact.reserved ? 'checked':''}"/>>
            <label class="form-check-label" for="reserved" >Réservé ? </label>
        </div>
    </div>
</div>

<div class="form-group pt-3" id="referentFormPart" <c:out value="${requestScope.contact.reserved ? '':'hidden'}"/>>
    <label for="referent">Référent </label>
    <select id="referent" name="referent" class="form-control">
        <option value="" <c:out value="${empty requestScope.contact.referent ? 'selected':''}"/>>Sélectionnez un référent</option>
        <c:forEach var="contactLoop" items="${requestScope.contacts}">
            <option value="<c:out value="${contactLoop.id}"/>" <c:out value="${requestScope.contact.referent.id == contactLoop.id ? 'selected':''}"/>><c:out value="${contactLoop.name} ${contactLoop.surname}"/></option>
        </c:forEach>
    </select>
    <div class="text-danger pb-1">${requestScope.form.errors['referent']}</div>
</div>

