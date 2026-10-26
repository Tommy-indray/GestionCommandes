# Gestion des commandes — Atelier JEE (MVC1 + Standards JEE)

Application web de gestion de commandes (clients, produits, commandes)
réalisée dans le cadre de l'atelier **JEE – LSI – FSTT**.

## 🎯 Objectif

Mettre en place une application web respectant le patron **MVC1** et les
standards **Jakarta EE 10** (Servlet 6.0, JSP 3.1, JSTL 3.0, JPA 3.1 /
Hibernate 6) sur Tomcat 10.1.

## 🧱 Stack technique

| Couche | Technologie |
|---|---|
| Langage | Java 17 |
| Build | Maven 3.9 |
| Serveur | Apache Tomcat 10.1 |
| Base de données | MySQL 8 / MariaDB |
| Persistance | JPA 3.1 + Hibernate 6.4 |
| Vue | JSP + JSTL 3.0 |
| Contrôleur | Servlets (`jakarta.servlet`) |

## 📐 Architecture

- **Modèle** : entités JPA (`ma.fstt.entities`) + DAO (`ma.fstt.dao`)
- **Contrôleur** : Servlets (`ma.fstt.web`)
- **Vue** : JSP sous `WEB-INF/views/` (accès impossible en direct)
- **Services** : interfaces + implémentations (`ma.fstt.service`)
  respectant le principe SOLID (ségrégation d'interface, inversion de dépendances)

## 📂 Arborescence
GestionCommandes/
├── docs/ Diagramme UML (PlantUML + PNG)
├── sql/ Script de création de la base
├── src/main/java/ma/fstt/
│ ├── entities/ 4 entités JPA
│ ├── dao/ 4 DAO (JPA)
│ ├── service/ interfaces + implémentations
│ └── web/ 3 Servlets (Client, Produit, Commande)
├── src/main/resources/META-INF/persistence.xml
└── src/main/webapp/
├── index.jsp
├── css/style.css
└── WEB-INF/
├── web.xml
└── views/ 7 JSP

## 🚀 Lancement local

### Prérequis

- JDK 17
- Maven 3.9+
- Tomcat 10.1
- MySQL 8 / MariaDB

### Étapes

```bash
# 1. Cloner
git clone https://github.com/<ton-user>/GestionCommandes.git
cd GestionCommandes

# 2. Créer la base
mysql -u root -p < sql/schema.sql

# 3. Adapter les identifiants MySQL dans
#    src/main/resources/META-INF/persistence.xml

# 4. Construire
mvn clean package

# 5. Déployer sur Tomcat 10.1
sudo cp target/GestionCommandes.war /var/lib/tomcat10/webapps/
sudo systemctl restart tomcat10


## Accès
http://localhost:8080/GestionCommandes/

## Fonctionnalités
Clients : CRUD complet (créer, lister, modifier, supprimer)
Produits : CRUD complet + gestion du stock
Commandes : création avec lignes, calcul automatique du total,
décrémentation du stock, suppression en cascade
