package ma.fstt.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "email", unique = true, length = 150)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Commande> commandes = new ArrayList<>();

    public Client() { }                 // constructeur vide obligatoire (cours JPA)

    public Client(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    // getters / setters
    public int getId()                { return id; }
    public void setId(int id)         { this.id = id; }
    public String getNom()            { return nom; }
    public void setNom(String nom)    { this.nom = nom; }
    public String getEmail()          { return email; }
    public void setEmail(String email){ this.email = email; }
    public List<Commande> getCommandes() { return commandes; }
    public void setCommandes(List<Commande> commandes) { this.commandes = commandes; }
}