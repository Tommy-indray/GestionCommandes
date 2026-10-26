<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Liste des clients</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>Clients</h1>
<p><a class="btn" href="${pageContext.request.contextPath}/clients?action=new">+ Nouveau client</a></p>

<table>
    <thead>
        <tr><th>ID</th><th>Nom</th><th>Email</th><th>Actions</th></tr>
    </thead>
    <tbody>
    <c:forEach var="cli" items="${clients}">
        <tr>
            <td>${cli.id}</td>
            <td>${cli.nom}</td>
            <td>${cli.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/clients?action=edit&id=${cli.id}">Éditer</a>
                |
                <a href="${pageContext.request.contextPath}/clients?action=delete&id=${cli.id}"
                   onclick="return confirm('Supprimer ce client ?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.jsp">← Accueil</a></p>
</body>
</html>