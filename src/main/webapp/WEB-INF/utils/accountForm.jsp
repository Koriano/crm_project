<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group pt-3">
    <label for="username">Nom d'utilisateur <span>*</span></label>
    <input type="text" id="username" name="username" value="<c:out value="${requestScope.account.username}"/>" maxlength="25" class="form-control" placeholder="Nom d'utilisateur"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['username']}</div>

<div class="form-group pt-3">
    <label for="name">Nom de compte <span>*</span></label>
    <input type="text" id="name" name="name" value="<c:out value="${requestScope.account.name}"/>" maxlength="30" class="form-control" placeholder="Nom de compte"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['name']}</div>

<c:if test="${requestScope.action == 'modify'}">
    <div class="form-group row pt-3">
        <div class="col-sm-10 pt-3">
            <div class="form-check">
                <input type="checkbox" id="password_change" name="password_change" class="form-check-input" <c:out value="${empty requestScope.password_change ? '':'checked'}"/>>
                <label class="form-check-label" for="password_change" >Changer de mot de passe ? </label>
            </div>
        </div>
    </div>
</c:if>
<div id="change_password_form_part">
    <c:import url="/WEB-INF/utils/passwordForm.jsp"/>
</div>

<div class="form-group pt-3">
    <label for="right">Type de droit <span>*</span></label>
    <select id="right" name="right" class="form-control">

        <%-- Select which right will be displayed --%>
        <c:set var="default_selected" value="${'Lecture seule'}" scope="page"/>

        <c:if test="${requestScope.account != null}">
            <c:set var="default_selected" value="${requestScope.account.right}" scope="page"/>
        </c:if>

        <c:forEach var="right" items="${requestScope.rights}">
            <option value="<c:out value="${right}"/>" <c:out value="${default_selected == right ? 'selected':''}"/>> <c:out value="${right}"/> </option>
        </c:forEach>
    </select>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['right']}</div>

<div class="form-group pt-3">
    <label for="contact">Contact associé <span>*</span></label>
    <select id="contact" name="contact" class="form-control">
        <option value="" <c:out value="${empty requestScope.account.contact ? 'selected':''}"/>> Sélectionnez un contact </option>
        <c:forEach var="contact" items="${requestScope.contacts}">
            <option value="<c:out value="${contact.id}"/>" <c:out value="${requestScope.account.contact.id == contact.id ? 'selected':''}"/>> <c:out value="${contact.name} ${contact.surname}"/> </option>
        </c:forEach>
    </select>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['contact']}</div>


<div class="form-group pt-3">
    <label for="sectors">Secteurs <span>*</span></label>
    <select id="sectors" name="sectors" class="form-control" multiple>
        <c:forEach var="sector" items="${requestScope.sectors}">

            <%-- Check if sector is selected or not --%>
            <c:set var="selected" value="${false}" scope="page"/>

            <c:forEach var="account_sector" items="${requestScope.account.sectors}">
                <c:if test="${account_sector.id == sector.id}">
                    <c:set var="selected" value="${true}" scope="page"/>
                </c:if>
            </c:forEach>

            <c:choose>
                <c:when test="${sector.name == 'Défaut'}">
                    <option value="<c:out value="${sector.id}"/>" <c:out value="${selected || empty requestScope.account ? 'selected':''}"/>> <c:out value="${sector.name}"/> </option>
                </c:when>
                <c:otherwise>
                    <option value="<c:out value="${sector.id}"/>" <c:out value="${selected ? 'selected':''}"/>> <c:out value="${sector.name}"/> </option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['sectors']}</div>
