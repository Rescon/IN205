package ensta.servlet;

import ensta.model.Livre;
import ensta.service.*;
import ensta.service.impl.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LivreDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/livre_details")){
            LivreService livreService = LivreServiceImpl.getInstance();
            EmpruntService empruntService = EmpruntServiceImpl.getInstance();
            try {
                request.setAttribute("livre", livreService.getById(Integer.parseInt(request.getParameter("id"))));
            } catch (Exception e) {
                throw new ServletException("Impossible d'obtenir le livre.", e);
            }
            try {
                request.setAttribute("emprunts", empruntService.getListCurrentByLivre(Integer.parseInt(request.getParameter("id"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/livre_details.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivreService livreService = LivreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();

        if (request.getParameter("titre") == null || request.getParameter("titre").equals("")) {
            throw new ServletException("Title Vide!");
        }
        try {
            Livre livre = livreService.getById(Integer.parseInt(request.getParameter("id")));
            String titre = new String(request.getParameter("titre").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String auteur = new String(request.getParameter("auteur").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livre.setIsbn(request.getParameter("isbn"));
            livreService.update(livre);
            request.setAttribute("id", livre.getId());
            request.setAttribute("emprunts", empruntService.getListCurrentByLivre(livre.getId()));

            response.sendRedirect(request.getContextPath() + "/livre_details?id=" + livre.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}