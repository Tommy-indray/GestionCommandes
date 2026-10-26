<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"/><title>Commande #${commande.id}</title></head>
<body>
<h1>Commande #${commande.id}</h1>
<p>Client : <strong>${commande.client.nom}</strong></p>
<p>Date : <fmt:formatDate value="${commande.dateCommande}" pattern="dd/MM/yyyy HH:mm"/></p>

<h2>Lignes</h2>
<table border="1">
    <thead>
        <tr><th>Produit</th><th>Prix unitaire</th><th>Quantité</th><th>Sous-total</th></tr>
    </thead>
    <tbody>
    <c:forEach var="l" items="${commande.lignes}">
        <tr>
            <td>${l.produit.libelle}</td>
            <td><fmt:formatNumber value="${l.produit.prix}" type="currency" currencySymbol="€"/></td>
            <td>${l.quantite}</td>
            <td><fmt:formatNumber value="${l.sousTotal}" type="currency" currencySymbol="€"/></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <th colspan="3">Total</th>
            <th><fmt:formatNumber value="${commande.total}" type="currency" currencySymbol="€"/></th>
        </tr>
    </tfoot>
</table>

<p><a href="${pageContext.request.contextPath}/commandes">← Retour aux commandes</a></p>
</body>
</html>