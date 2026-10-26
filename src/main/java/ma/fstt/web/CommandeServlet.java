package ma.fstt.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;
import ma.fstt.entities.LigneDeCommande;
import ma.fstt.entities.Produit;
import ma.fstt.service.*;

import java.io.IOException;
import java.util.List;

/**
 * Contrôleur MVC pour les commandes.
 * URL : /commandes
 * Actions :
 *   GET  /commandes                     → liste des commandes
 *   GET  /commandes?action=new          → formulaire de création
 *   GET  /commandes?action=edit&id=N    → détail d'une commande
 *   GET  /commandes?action=delete&id=N  → suppression
 *   POST /commandes                     → création d'une commande avec lignes
 */
@WebServlet("/commandes")
public class CommandeServlet extends HttpServlet {

    private final CommandeService commandeService = new CommandeServiceImpl();
    private final ClientService   clientService   = new ClientServiceImpl();
    private final ProduitService  produitService  = new ProduitServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String ctx    = req.getContextPath();

        try {
            if ("new".equals(action)) {
                // On passe au formulaire les listes nécessaires
                req.setAttribute("clients",  clientService.lister());
                req.setAttribute("produits", produitService.lister());
                req.getRequestDispatcher("/WEB-INF/views/commande-form.jsp")
                        .forward(req, resp);
                return;
            }

            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Commande cmd = commandeService.trouver(id);
                if (cmd == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                            "Commande introuvable : id=" + id);
                    return;
                }
                req.setAttribute("commande", cmd);
                req.getRequestDispatcher("/WEB-INF/views/commande-detail.jsp")
                        .forward(req, resp);
                return;
            }

            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                commandeService.supprimer(id);
                resp.sendRedirect(ctx + "/commandes");
                return;
            }

            List<Commande> commandes = commandeService.lister();
            req.setAttribute("commandes", commandes);
            req.getRequestDispatcher("/WEB-INF/views/commandes.jsp")
                    .forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Identifiant invalide : " + e.getMessage());
        } catch (Exception e) {
            throw new ServletException("Erreur lors du traitement des commandes", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String ctx = req.getContextPath();

        try {
            // 1) Récupération du client
            int clientId = Integer.parseInt(req.getParameter("clientId"));
            Client client = clientService.trouver(clientId);
            if (client == null) {
                throw new IllegalArgumentException("Client introuvable : id=" + clientId);
            }

            // 2) Création de la commande
            Commande commande = new Commande();
            commande.setClient(client);

            // 3) Traitement des lignes (paramètres multiples : produitId et quantite)
            String[] produitIds = req.getParameterValues("produitId");
            String[] quantites  = req.getParameterValues("quantite");

            if (produitIds != null && quantites != null) {
                for (int i = 0; i < produitIds.length; i++) {
                    if (produitIds[i] == null || produitIds[i].trim().isEmpty()) continue;
                    int pid = Integer.parseInt(produitIds[i]);
                    int qte = Integer.parseInt(quantites[i]);
                    if (qte <= 0) continue;

                    Produit produit = produitService.trouver(pid);
                    if (produit == null) {
                        throw new IllegalArgumentException(
                                "Produit introuvable : id=" + pid);
                    }

                    // Décrémentation du stock + contrôle de disponibilité
                    produitService.decrementerStock(pid, qte);

                    LigneDeCommande ligne = new LigneDeCommande(produit, qte);
                    commande.ajouterLigne(ligne);
                }
            }

            if (commande.getLignes().isEmpty()) {
                throw new IllegalArgumentException(
                        "La commande doit contenir au moins une ligne.");
            }

            // 4) Sauvegarde (cascade ALL → sauvegarde des lignes + total)
            commandeService.creer(commande);

            resp.sendRedirect(ctx + "/commandes");

        } catch (NumberFormatException e) {
            req.setAttribute("erreur", "Valeur numérique invalide : " + e.getMessage());
            rechargeForm(req);
            req.getRequestDispatcher("/WEB-INF/views/commande-form.jsp").forward(req, resp);

        } catch (IllegalArgumentException | IllegalStateException e) {
            req.setAttribute("erreur", e.getMessage());
            rechargeForm(req);
            req.getRequestDispatcher("/WEB-INF/views/commande-form.jsp").forward(req, resp);
        }
    }

    /** Recharge les listes nécessaires au formulaire (clients, produits) en cas d'erreur. */
    private void rechargeForm(HttpServletRequest req) {
        req.setAttribute("clients",  clientService.lister());
        req.setAttribute("produits", produitService.lister());
    }
}