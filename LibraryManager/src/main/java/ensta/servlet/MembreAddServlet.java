package ensta.servlet;

import ensta.service.MembreService;
import ensta.service.impl.MembreServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MembreAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/membre_add")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MembreService membreService = MembreServiceImpl.getInstance();
        int id = -1;
        try {
            if (request.getParameter("nom") == null || request.getParameter("prenom") == null || request.getParameter("nom").equals("") || request.getParameter("prenom").equals("")){
                throw new ServletException("Nom ou prénom vide!");
            }else{
                String nom = new String(request.getParameter("nom").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                String prenom = new String(request.getParameter("prenom").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                String adresse = new String(request.getParameter("adresse").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                id = membreService.create(nom, prenom, adresse, request.getParameter("email"), request.getParameter("telephone"));
                request.setAttribute("id", id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/membre_details?id=" + id);
    }
}