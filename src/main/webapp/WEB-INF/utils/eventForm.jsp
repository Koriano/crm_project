<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group pt-3">
    <label for="name">Nom <span>*</span></label>
    <input type="text" id="name" name="name" value="<c:out value="${requestScope.event.name}"/>" class="form-control" placeholder="Nom"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['name']}</div>

<div class="form-group pt-3">
    <label for="date">Date <span>*</span></label>
    <input type="date" id="date" name="date" value="<c:out value="${requestScope.date}"/>" class="form-control"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['date']}</div>

<div class="form-group pt-3">
    <label for="time">Heure <span>*</span></label>
    <input type="time" id="time" name="time" value="<c:out value="${requestScope.time}"/>" class="form-control"/>
</div>
<span>${requestScope.form.errors['time']}</span>

<div class="form-group pt-3">
    <label for="type">Type <span>*</span></label>
    <select id="type" name="type" class="form-control">
        <option value="" <c:out value="${empty requestScope.contact.role ? 'selected':''}"/>>Sélectionnez un type</option>
        <c:forEach var="type" items="${requestScope.types}">
            <option value="<c:out value="${type}"/>" <c:out value="${requestScope.event.type == type ? 'selected':''}"/>><c:out value="${type}"/></option>
        </c:forEach>
    </select>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['type']}</div>

<div class="form-group pt-3">
    <label for="description">Description</label>
    <textarea class="form-control rounded-0" id="description" name="description" rows="13" cols="40" form="event_form"><c:out value="${requestScope.event.description}"/></textarea>
</div>

<div class="form-group pt-3">
    <label for="contacts">Participants </label>
    <select id="contacts" name="contacts" class="form-control" multiple>
        <c:forEach var="contact" items="${requestScope.contacts}">

            <%-- Check if contact is selected or not --%>
            <c:set var="selected" value="${false}" scope="page"/>

            <c:forEach var="event_contact" items="${requestScope.event.contactsList}">
                <c:if test="${event_contact.id == contact.id}">
                    <c:set var="selected" value="${true}" scope="page"/>
                </c:if>
            </c:forEach>

            <option value="<c:out value="${contact.id}"/>" <c:out value="${selected ? 'selected':''}"/>> <c:out value="${contact.name} ${contact.surname}"/> </option>

        </c:forEach>
    </select>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['contacts']}</div>
