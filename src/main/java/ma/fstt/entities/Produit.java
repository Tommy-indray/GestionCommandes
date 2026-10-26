package ma.fstt.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "libelle", nullable = false, length = 150)
    private String libelle;

    @Column(name = "prix", nullable = false)
    private double prix;

    @Column(name = "stock", nullable = false)
    private int stock;

    public Produit() { }

    public Produit(String libelle, double prix, int stock) {
        this.libelle = libelle;
        this.prix    = prix;
        this.stock   = stock;
    }

    // getters / setters
    public int getId()               { return id; }
    public void setId(int id)        { this.id = id; }
    public String getLibelle()       { return libelle; }
    public void setLibelle(String l) { this.libelle = l; }
    public double getPrix()          { return prix; }
    public void setPrix(double p)    { this.prix = p; }
    public int getStock()            { return stock; }
    public void setStock(int s)      { this.stock = s; }
}