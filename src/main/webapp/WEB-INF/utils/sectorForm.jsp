<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
    <label for="name">Nom <span>*</span></label>
    <input type="text" id="name" name="name" value="<c:out value="${requestScope.sector.name}"/>" maxlength="40" class="form-control" placeholder="Nom"/>
</div>
<span>${requestScope.form.errors['name']}</span>

<div class="form-group pt-3">
    <label for="contacts">Contacts de ce secteur</label>
    <select id="contacts" name="contacts" class="form-control" multiple>
        <c:forEach var="contact" items="${requestScope.contacts}">

            <%-- Check if contact is selected or not --%>
            <c:set var="selected" value="${false}" scope="page"/>

            <c:forEach var="sector_contact" items="${requestScope.sector.contactList}">
                <c:if test="${sector_contact.id == contact.id}">
                    <c:set var="selected" value="${true}" scope="page"/>
                </c:if>
            </c:forEach>

            <option value="<c:out value="${contact.id}"/>" <c:out value="${selected ? 'selected':''}"/>> <c:out value="${contact.name} ${contact.surname}"/> </option>
        </c:forEach>
    </select>
</div>
<span>${requestScope.form.errors['contacts']}</span>