<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"/><title>Produit</title></head>
<body>
<h1>${empty produit ? 'Nouveau produit' : 'Modifier le produit'}</h1>

<c:if test="${not empty erreur}">
    <p style="color:red;">${erreur}</p>
</c:if>

<form action="${pageContext.request.contextPath}/produits" method="post">
    <input type="hidden" name="id" value="${produit.id}"/>

    <label>Libellé :
        <input type="text" name="libelle" value="${produit.libelle}" required/>
    </label><br/>

    <label>Prix (€) :
        <input type="number" step="0.01" min="0" name="prix"
               value="${produit.prix}" required/>
    </label><br/>

    <label>Stock :
        <input type="number" min="0" name="stock"
               value="${produit.stock}" required/>
    </label><br/>

    <button type="submit">Enregistrer</button>
    <a href="${pageContext.request.contextPath}/produits">Annuler</a>
</form>
</body>
</html>