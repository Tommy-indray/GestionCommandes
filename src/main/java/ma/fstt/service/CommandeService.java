package ma.fstt.service;

import ma.fstt.entities.Commande;
import ma.fstt.entities.LigneDeCommande;
import java.util.List;

public interface CommandeService {
    void creer(Commande c);
    List<Commande> lister();
    Commande trouver(int id);
    void ajouterLigne(int commandeId, LigneDeCommande ligne);
    void supprimer(int id);
}