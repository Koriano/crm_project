<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
    <label for="password">Mot de passe <span>*</span></label>
    <input type="password" id="password" name="password" maxlength="100" class="form-control"/>
</div>

<div class="form-group">
    <label for="confirmation">Répétez le mot de passe <span>*</span></label>
    <input type="password" id="confirmation" name="confirmation" maxlength="100" class="form-control"/>
</div>
<div class="text-danger pb-1">${requestScope.form.errors['password']}</div>