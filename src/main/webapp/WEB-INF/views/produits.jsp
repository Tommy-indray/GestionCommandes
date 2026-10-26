<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"/><title>Produits</title></head>
<body>
<h1>Catalogue des produits</h1>
<p>
    <a href="${pageContext.request.contextPath}/produits?action=new">+ Nouveau produit</a>
</p>
<table border="1">
    <thead>
        <tr><th>ID</th><th>Libellé</th><th>Prix</th><th>Stock</th><th>Actions</th></tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${produits}">
        <tr>
            <td>${p.id}</td>
            <td>${p.libelle}</td>
            <td><fmt:formatNumber value="${p.prix}" type="currency" currencySymbol="€"/></td>
            <td>${p.stock}</td>
            <td>
                <a href="${pageContext.request.contextPath}/produits?action=edit&id=${p.id}">Éditer</a>
                |
                <a href="${pageContext.request.contextPath}/produits?action=delete&id=${p.id}"
                   onclick="return confirm('Supprimer ce produit ?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="${pageContext.request.contextPath}/index.jsp">← Accueil</a></p>
</body>
</html>