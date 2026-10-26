package ma.fstt.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_commande", nullable = false)
    private Date dateCommande;

    @Column(name = "total")
    private double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "commande",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<LigneDeCommande> lignes = new ArrayList<>();

    public Commande() { this.dateCommande = new Date(); }

    public void ajouterLigne(LigneDeCommande ligne) {
        ligne.setCommande(this);
        this.lignes.add(ligne);
        recalculerTotal();
    }

    public void retirerLigne(LigneDeCommande ligne) {
        this.lignes.remove(ligne);
        ligne.setCommande(null);
        recalculerTotal();
    }

    public void recalculerTotal() {
        double s = 0;
        for (LigneDeCommande l : lignes) s += l.getSousTotal();
        this.total = s;
    }

    // getters / setters
    public int getId()                     { return id; }
    public void setId(int id)              { this.id = id; }
    public Date getDateCommande()          { return dateCommande; }
    public void setDateCommande(Date d)    { this.dateCommande = d; }
    public double getTotal()               { return total; }
    public void setTotal(double t)         { this.total = t; }
    public Client getClient()              { return client; }
    public void setClient(Client c)        { this.client = c; }
    public List<LigneDeCommande> getLignes()             { return lignes; }
    public void setLignes(List<LigneDeCommande> lignes)  { this.lignes = lignes; }
}