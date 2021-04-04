package ensta.servlet;

import ensta.model.Abonnement;
import ensta.model.Membre;
import ensta.service.*;
import ensta.service.impl.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MembreDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/membre_details")){
            MembreService membreService = MembreServiceImpl.getInstance();
            EmpruntService empruntService = EmpruntServiceImpl.getInstance();
            try {
                request.setAttribute("membre", membreService.getById(Integer.parseInt(request.getParameter("id"))));
            } catch (Exception e) {
                throw new ServletException("Impossible d'obtenir le membre.", e);
            }
            try {
                request.setAttribute("emprunts", empruntService.getListCurrentByMembre(Integer.parseInt(request.getParameter("id"))));
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/membre_details.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MembreService membreService = MembreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();

        if (request.getParameter("prenom") == null || request.getParameter("prenom").equals("") || request.getParameter("nom") == null || request.getParameter("nom").equals("")){
            throw new ServletException("Nom ou prénom vide!");
        }
        try {
            Membre membre = membreService.getById(Integer.parseInt(request.getParameter("id")));
            String nom = new String(request.getParameter("nom").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String prenom = new String(request.getParameter("prenom").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String adresse = new String(request.getParameter("adresse").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            membre.setNom(nom);
            membre.setPrenom(prenom);
            membre.setAdresse(adresse);
            membre.setEmail(request.getParameter("email"));
            membre.setTelephone(request.getParameter("telephone"));
            if (request.getParameter("abonnement").equals("VIP")){
                membre.setAbonnement(Abonnement.VIP);
            } else if (request.getParameter("abonnement").equals("PREMIUM")){
                membre.setAbonnement(Abonnement.PREMIUM);
            } else {
                membre.setAbonnement(Abonnement.BASIC);
            }
            membreService.update(membre);
            request.setAttribute("id", membre.getId());
            request.setAttribute("emprunts", empruntService.getListCurrentByMembre(membre.getId()));

            response.sendRedirect(request.getContextPath() + "/membre_details?id=" + membre.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}