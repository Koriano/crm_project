<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<label for="name">Nom <span>*</span></label>
<input type="text" id="name" name="name" value="<c:out value="${requestScope.contact.name}"/>" size="20" maxlength="50" />
<span>${requestScope.form.errors['name']}</span>
<br>

<label for="surname">Prénom <span>*</span></label>
<input type="text" id="surname" name="surname" value="<c:out value="${requestScope.contact.surname}"/>" size="20" maxlength="50" />
<span>${requestScope.form.errors['surname']}</span>
<br>

<label for="role">Rôle <span>*</span></label>
<select id="role" name="role">
    <option value="" <c:out value="${empty requestScope.contact.role ? 'selected':''}"/>>Sélectionnez un rôle</option>
    <c:forEach var="role" items="${requestScope.roles}">
        <option value="<c:out value="${role}"/>" <c:out value="${requestScope.contact.role == role ? 'selected':''}"/>><c:out value="${role}"/></option>
    </c:forEach>
</select>
<span>${requestScope.form.errors['role']}</span>
<br>

<label for="entity">Entité </label>
<select id="entity" name="entity">
    <option value="" <c:out value="${empty requestScope.contact.entity.name ? 'selected':''}"/>>Sélectionnez une entité</option>
    <c:forEach var="entity" items="${requestScope.entities}">
        <option value="<c:out value="${entity.name}"/>" <c:out value="${requestScope.contact.entity.name == entity.name ? 'selected':''}"/>><c:out value="${entity.name}"/></option>
    </c:forEach>
</select>
<span>${requestScope.form.errors['entity']}</span>
<br>

<label for="address">Adresse </label>
<input type="text" id="address" name="address" value="<c:out value="${requestScope.contact.address}"/>" size="20"/>
<span>${requestScope.form.errors['address']}</span>
<br>

<label for="phones">Téléphone </label>
<input type="tel" id="phones" name="phone0" value="<c:out value="${requestScope.contact.phonesList[0]}"/>" size="20" maxlength="20"/>
<c:forEach var="phone" items="${requestScope.contact.phonesList}" varStatus="boucle">
    <c:if test="${boucle.count != 1}">
        <input type="tel" name="phone${boucle.count}" value="<c:out value="${requestScope.contact.phonesList[boucle.count]}"/>" size="20" maxlength="20"/>
    </c:if>
</c:forEach>
<button type="button" id="addPhone">+</button>
<span>${requestScope.form.errors['phone']}</span>
<br>

<label for="mails">Mail </label>
<input type="email" id="mails" name="mail0" value="<c:out value="${requestScope.contact.mailsList[0]}"/>" size="20" maxlength="60"/>
<c:forEach var="mail" items="${requestScope.contact.mailsList}" varStatus="boucle">
    <c:if test="${boucle.count != 1}">
        <input type="email" name="mail${boucle.count}" value="<c:out value="${requestScope.contact.mailsList[boucle.count]}"/>" size="20" maxlength="60"/>
    </c:if>
</c:forEach>
<button type="button" id="addMail">+</button>
<span>${requestScope.form.errors['mail']}</span>
<br>

<label for="reserved">Réservé ? </label>
<input type="checkbox" id="reserved" name="reserved" <c:out value="${requestScope.contact.reserved ? 'checked':''}"/>>
<br>

<div id="referentFormPart" <c:out value="${requestScope.contact.reserved ? '':'hidden'}"/>>
    <label for="referent">Référent </label>
    <select id="referent" name="referent">
        <option value="" <c:out value="${empty requestScope.contact.referent ? 'selected':''}"/>>Sélectionnez un référent</option>
        <c:forEach var="contactLoop" items="${requestScope.contacts}">
            <option value="<c:out value="${contactLoop.id}"/>" <c:out value="${requestScope.contact.referent.id == contactLoop.id ? 'selected':''}"/>><c:out value="${contactLoop.name} ${contactLoop.surname}"/></option>
        </c:forEach>
    </select>
</div>
<span>${requestScope.form.errors['referent']}</span>
<br>

