package ma.fstt.web;
import ma.fstt.service.ProduitServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.fstt.entities.Produit;
import ma.fstt.service.ProduitService;

import java.io.IOException;
import java.util.List;

/**
 * Contrôleur MVC pour les produits.
 * URL : /produits
 * Actions supportées :
 *   GET  /produits                  → liste
 *   GET  /produits?action=new       → formulaire vide
 *   GET  /produits?action=edit&id=N → formulaire pré-rempli
 *   GET  /produits?action=delete&id=N → suppression + redirection
 *   POST /produits                  → création ou mise à jour
 */
@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private final ProduitService service = new ProduitServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String ctx    = req.getContextPath();

        try {
            if ("new".equals(action) || "edit".equals(action)) {
                if ("edit".equals(action)) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Produit p = service.trouver(id);
                    if (p == null) {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                                "Produit introuvable : id=" + id);
                        return;
                    }
                    req.setAttribute("produit", p);
                }
                req.getRequestDispatcher("/WEB-INF/views/produit-form.jsp")
                        .forward(req, resp);
                return;
            }

            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                service.supprimer(id);
                resp.sendRedirect(ctx + "/produits");
                return;
            }

            List<Produit> produits = service.lister();
            req.setAttribute("produits", produits);
            req.getRequestDispatcher("/WEB-INF/views/produits.jsp")
                    .forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Identifiant invalide : " + e.getMessage());
        } catch (Exception e) {
            throw new ServletException("Erreur lors du traitement des produits", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String ctx = req.getContextPath();

        try {
            String idParam = req.getParameter("id");
            Produit p = new Produit();
            if (idParam != null && !idParam.isEmpty()) {
                p.setId(Integer.parseInt(idParam));
            }
            p.setLibelle(req.getParameter("libelle"));
            p.setPrix(Double.parseDouble(req.getParameter("prix")));
            p.setStock(Integer.parseInt(req.getParameter("stock")));

            if (p.getId() == 0) {
                service.ajouter(p);
            } else {
                service.modifier(p);
            }
            resp.sendRedirect(ctx + "/produits");

        } catch (NumberFormatException e) {
            req.setAttribute("erreur",
                    "Valeur numérique invalide : prix et stock doivent être des nombres.");
            req.getRequestDispatcher("/WEB-INF/views/produit-form.jsp")
                    .forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("erreur", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/produit-form.jsp")
                    .forward(req, resp);
        }
    }
}