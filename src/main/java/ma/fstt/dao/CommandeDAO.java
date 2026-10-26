package ma.fstt.dao;

import jakarta.persistence.*;
import ma.fstt.entities.Commande;
import java.util.List;

public class CommandeDAO {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("gestionPU");

    public void save(Commande c) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin(); em.persist(c);
        em.getTransaction().commit(); em.close();
    }

    public List<Commande> findAll() {
        EntityManager em = EMF.createEntityManager();
        List<Commande> list = em.createQuery(
                        "SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.lignes", Commande.class)
                .getResultList();
        em.close(); return list;
    }

    public Commande findById(int id) {
        EntityManager em = EMF.createEntityManager();
        Commande c = em.createQuery(
                        "SELECT c FROM Commande c LEFT JOIN FETCH c.lignes WHERE c.id = :id", Commande.class)
                .setParameter("id", id).getSingleResult();
        em.close(); return c;
    }

    public void update(Commande c) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin(); em.merge(c);
        em.getTransaction().commit(); em.close();
    }

    public void delete(int id) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        Commande c = em.find(Commande.class, id);
        if (c != null) em.remove(c);
        em.getTransaction().commit(); em.close();
    }
}