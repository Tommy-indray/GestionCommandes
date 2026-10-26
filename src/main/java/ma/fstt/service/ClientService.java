package ma.fstt.service;

import ma.fstt.entities.Client;
import java.util.List;

public interface ClientService {
    void ajouter(Client c);
    List<Client> lister();
    Client trouver(int id);
    void modifier(Client c);
    void supprimer(int id);
}