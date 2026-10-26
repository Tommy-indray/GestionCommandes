package ma.fstt.service;

import jakarta.enterprise.context.ApplicationScoped;
import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Client;
import java.util.List;

@ApplicationScoped
public class ClientServiceImpl implements ClientService {

    private final ClientDAO dao = new ClientDAO();

    @Override public void ajouter(Client c)    { dao.save(c); }
    @Override public List<Client> lister()     { return dao.findAll(); }
    @Override public Client trouver(int id)    { return dao.findById(id); }
    @Override public void modifier(Client c)   { dao.update(c); }
    @Override public void supprimer(int id)    { dao.delete(id); }
}