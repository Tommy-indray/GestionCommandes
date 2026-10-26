package ma.fstt.dao;

import jakarta.persistence.*;
import ma.fstt.entities.Produit;
import java.util.List;

public class ProduitDAO {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("gestionPU");

    public void save(Produit p) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin(); em.persist(p);
        em.getTransaction().commit(); em.close();
    }

    public List<Produit> findAll() {
        EntityManager em = EMF.createEntityManager();
        List<Produit> list = em.createQuery("SELECT p FROM Produit p ORDER BY p.id", Produit.class)
                .getResultList();
        em.close(); return list;
    }

    public Produit findById(int id) {
        EntityManager em = EMF.createEntityManager();
        Produit p = em.find(Produit.class, id);
        em.close(); return p;
    }

    public void update(Produit p) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin(); em.merge(p);
        em.getTransaction().commit(); em.close();
    }

    public void delete(int id) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        Produit p = em.find(Produit.class, id);
        if (p != null) em.remove(p);
        em.getTransaction().commit(); em.close();
    }
}