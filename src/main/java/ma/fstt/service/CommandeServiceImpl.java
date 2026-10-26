package ma.fstt.service;

import jakarta.enterprise.context.ApplicationScoped;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.entities.Commande;
import ma.fstt.entities.LigneDeCommande;
import java.util.List;

@ApplicationScoped
public class CommandeServiceImpl implements CommandeService {

    private final CommandeDAO dao = new CommandeDAO();

    @Override public void creer(Commande c)      { dao.save(c); }
    @Override public List<Commande> lister()     { return dao.findAll(); }
    @Override public Commande trouver(int id)    { return dao.findById(id); }
    @Override public void supprimer(int id)      { dao.delete(id); }

    @Override
    public void ajouterLigne(int commandeId, LigneDeCommande ligne) {
        Commande c = dao.findById(commandeId);
        c.ajouterLigne(ligne);
        dao.update(c);
    }
}