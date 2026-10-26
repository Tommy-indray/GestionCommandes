<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"/><title>Client</title></head>
<body>
<h1>${empty client ? 'Nouveau client' : 'Modifier le client'}</h1>

<form action="${pageContext.request.contextPath}/clients" method="post">
    <input type="hidden" name="id" value="${client.id}"/>
    <label>Nom :   <input type="text"   name="nom"   value="${client.nom}"   required/></label><br/>
    <label>Email : <input type="email"  name="email" value="${client.email}"/></label><br/>
    <button type="submit">Enregistrer</button>
    <a href="${pageContext.request.contextPath}/clients">Annuler</a>
</form>
</body>
</html>