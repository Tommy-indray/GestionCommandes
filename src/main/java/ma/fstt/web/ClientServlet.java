package ma.fstt.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.fstt.entities.Client;
import ma.fstt.service.ClientService;
import ma.fstt.service.ClientServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

    private final ClientService service = new ClientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        String ctx    = req.getContextPath();

        if ("edit".equals(action) || "new".equals(action)) {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("client", service.trouver(id));
            }
            req.getRequestDispatcher("/WEB-INF/views/client-form.jsp").forward(req, resp);
            return;
        }
        if ("delete".equals(action)) {
            service.supprimer(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(ctx + "/clients");
            return;
        }

        List<Client> clients = service.lister();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/WEB-INF/views/clients.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idParam = req.getParameter("id");
        Client c = new Client();
        if (idParam != null && !idParam.isEmpty()) c.setId(Integer.parseInt(idParam));
        c.setNom(req.getParameter("nom"));
        c.setEmail(req.getParameter("email"));

        if (c.getId() == 0) service.ajouter(c);
        else                service.modifier(c);

        resp.sendRedirect(req.getContextPath() + "/clients");
    }
}