<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Commandes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>Liste des commandes</h1>

<p>
    <a class="btn" href="${pageContext.request.contextPath}/commandes?action=new">
        + Nouvelle commande
    </a>
</p>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Client</th>
            <th>Nb lignes</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="cmd" items="${commandes}">
        <tr>
            <td>${cmd.id}</td>
            <td><fmt:formatDate value="${cmd.dateCommande}" pattern="dd/MM/yyyy HH:mm"/></td>
            <td>${cmd.client.nom}</td>
            <td>${cmd.lignes.size()}</td>
            <td><fmt:formatNumber value="${cmd.total}" type="currency" currencySymbol="€"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/commandes?action=edit&id=${cmd.id}">
                    Détail
                </a>
                |
                <a href="${pageContext.request.contextPath}/commandes?action=delete&id=${cmd.id}"
                   onclick="return confirm('Supprimer cette commande ?');">
                    Supprimer
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty commandes}">
    <p><em>Aucune commande pour le moment.</em></p>
</c:if>

<p><a href="${pageContext.request.contextPath}/index.jsp">← Accueil</a></p>
</body>
</html>