package ma.fstt.service;

import jakarta.enterprise.context.ApplicationScoped;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Produit;

import java.util.List;

@ApplicationScoped
public class ProduitServiceImpl implements ProduitService {

    // Instanciation directe : Tomcat n'est pas un conteneur CDI,
    // donc @Inject ne fonctionne pas ici.
    private final ProduitDAO dao = new ProduitDAO();

    @Override
    public void ajouter(Produit p) {
        if (p.getPrix() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif.");
        }
        if (p.getStock() < 0) {
            throw new IllegalArgumentException("Le stock ne peut pas être négatif.");
        }
        dao.save(p);
    }

    @Override
    public List<Produit> lister() {
        return dao.findAll();
    }

    @Override
    public Produit trouver(int id) {
        return dao.findById(id);
    }

    @Override
    public void modifier(Produit p) {
        dao.update(p);
    }

    @Override
    public void supprimer(int id) {
        dao.delete(id);
    }

    @Override
    public void decrementerStock(int produitId, int quantite) {
        Produit p = dao.findById(produitId);
        if (p == null) {
            throw new IllegalArgumentException("Produit introuvable : id=" + produitId);
        }
        if (p.getStock() < quantite) {
            throw new IllegalStateException(
                    "Stock insuffisant pour « " + p.getLibelle() + " » (dispo : "
                            + p.getStock() + ", demandé : " + quantite + ")");
        }
        p.setStock(p.getStock() - quantite);
        dao.update(p);
    }
}