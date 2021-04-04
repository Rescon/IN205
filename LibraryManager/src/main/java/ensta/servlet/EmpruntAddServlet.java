package ensta.servlet;

import ensta.service.*;
import ensta.service.impl.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class EmpruntAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/emprunt_add")){
            MembreService membreService = MembreServiceImpl.getInstance();
            LivreService bookService = LivreServiceImpl.getInstance();
            try {
                request.setAttribute("empruntPossibleMembreList", membreService.getListMembreEmpruntPossible());
                request.setAttribute("dispoLivreList", bookService.getListDispo());
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        LivreService bookService = LivreServiceImpl.getInstance();
        MembreService membreService = MembreServiceImpl.getInstance();
        try {
            if (request.getParameter("idMembre") == null || request.getParameter("idLivre") == null){
                throw new ServletException("L'ID de membre ou l'ID de livre est manquant.");
            } else{
                empruntService.create(Integer.parseInt(request.getParameter("idMembre")), Integer.parseInt(request.getParameter("idLivre")), LocalDate.now());
                request.setAttribute("currentEmprunts", empruntService.getListCurrent());
                request.setAttribute("empruntPossibleMembreList", membreService.getListMembreEmpruntPossible());
                request.setAttribute("dispoLivreList", bookService.getListDispo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/emprunt_list");
    }
}
