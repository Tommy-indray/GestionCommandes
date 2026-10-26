package ma.fstt.dao;

import jakarta.persistence.*;
import ma.fstt.entities.Client;
import java.util.List;

public class ClientDAO {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("gestionPU");

    public void save(Client c) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    public List<Client> findAll() {
        EntityManager em = EMF.createEntityManager();
        List<Client> list = em.createQuery("SELECT c FROM Client c ORDER BY c.id", Client.class)
                .getResultList();
        em.close();
        return list;
    }

    public Client findById(int id) {
        EntityManager em = EMF.createEntityManager();
        Client c = em.find(Client.class, id);
        em.close();
        return c;
    }

    public void update(Client c) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(int id) {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        Client c = em.find(Client.class, id);
        if (c != null) em.remove(c);
        em.getTransaction().commit();
        em.close();
    }
}