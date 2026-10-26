package ma.fstt.service;

import ma.fstt.entities.Produit;
import java.util.List;

/**
 * Contrat métier pour la gestion des produits.
 * Respecte le principe SOLID "Interface Segregation" (cf. 1cours.pdf §1.4) :
 * l'interface ne déclare que les opérations réellement utiles aux produits.
 */
public interface ProduitService {

    /** Ajoute un nouveau produit au catalogue. */
    void ajouter(Produit p);

    /** Retourne la liste complète des produits. */
    List<Produit> lister();

    /** Recherche un produit par son identifiant. */
    Produit trouver(int id);

    /** Met à jour les informations d'un produit existant. */
    void modifier(Produit p);

    /** Supprime un produit du catalogue. */
    void supprimer(int id);

    /** Décrémente le stock d'un produit (utilisé lors d'une commande). */
    void decrementerStock(int produitId, int quantite);
}