<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Nouvelle commande</title>
    <script>
        // Ajout dynamique de lignes
        function ajouterLigne() {
            const container = document.getElementById('lignes');
            const row = document.createElement('div');
            row.innerHTML = `
                <select name="produitId">
                    <c:forEach var="p" items="${produits}">
                        <option value="${p.id}">${p.libelle} (${p.prix} €, stock ${p.stock})</option>
                    </c:forEach>
                </select>
                <input type="number" name="quantite" min="1" value="1"/>
                <button type="button" onclick="this.parentNode.remove()">−</button><br/>`;
            container.appendChild(row);
        }
    </script>
</head>
<body>
<h1>Nouvelle commande</h1>

<c:if test="${not empty erreur}">
    <p style="color:red;">${erreur}</p>
</c:if>

<form action="${pageContext.request.contextPath}/commandes" method="post">

    <label>Client :
        <select name="clientId" required>
            <c:forEach var="cli" items="${clients}">
                <option value="${cli.id}">${cli.nom} (${cli.email})</option>
            </c:forEach>
        </select>
    </label><br/><br/>

    <fieldset>
        <legend>Lignes de commande</legend>
        <div id="lignes"></div>
        <button type="button" onclick="ajouterLigne()">+ Ajouter une ligne</button>
    </fieldset>

    <br/>
    <button type="submit">Enregistrer la commande</button>
    <a href="${pageContext.request.contextPath}/commandes">Annuler</a>
</form>

<script>ajouterLigne();</script>
</body>
</html>