package ma.fstt.dao;

import jakarta.persistence.*;
import ma.fstt.entities.LigneDeCommande;
import java.util.List;

public class LigneDeCommandeDAO {
    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("gestionPU");

    public List<LigneDeCommande> findByCommande(int commandeId) {
        EntityManager em = EMF.createEntityManager();
        List<LigneDeCommande> list = em.createQuery(
                        "SELECT l FROM LigneDeCommande l WHERE l.commande.id = :cid", LigneDeCommande.class)
                .setParameter("cid", commandeId).getResultList();
        em.close(); return list;
    }

    public void delete(int id) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        LigneDeCommande l = em.find(LigneDeCommande.class, id);
        if (l != null) em.remove(l);
        em.getTransaction().commit(); em.close();
    }
}