package ma.fstt.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ligne_commande")
public class LigneDeCommande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantite", nullable = false)
    private int quantite;

    @Column(name = "sous_total", nullable = false)
    private double sousTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    public LigneDeCommande() { }

    public LigneDeCommande(Produit produit, int quantite) {
        this.produit  = produit;
        this.quantite = quantite;
        calculerSousTotal();
    }

    public void calculerSousTotal() {
        if (produit != null) this.sousTotal = produit.getPrix() * quantite;
    }

    // getters / setters
    public int getId()                  { return id; }
    public void setId(int id)           { this.id = id; }
    public int getQuantite()            { return quantite; }
    public void setQuantite(int q)      { this.quantite = q; calculerSousTotal(); }
    public double getSousTotal()        { return sousTotal; }
    public void setSousTotal(double s)  { this.sousTotal = s; }
    public Commande getCommande()       { return commande; }
    public void setCommande(Commande c) { this.commande = c; }
    public Produit getProduit()         { return produit; }
    public void setProduit(Produit p)   { this.produit = p; calculerSousTotal(); }
}