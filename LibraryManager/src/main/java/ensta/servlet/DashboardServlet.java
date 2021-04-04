package ensta.servlet;

import ensta.service.*;
import ensta.service.impl.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/dashboard")) {
            EmpruntService empruntService = EmpruntServiceImpl.getInstance();
            LivreService bookService = LivreServiceImpl.getInstance();
            MembreService membreService = MembreServiceImpl.getInstance();
            try {
                request.setAttribute("currentEmprunts", empruntService.getListCurrent());
                request.setAttribute("numberOfEmprunts", empruntService.count());
                request.setAttribute("numberOfMembres", membreService.count());
                request.setAttribute("numberOfBooks", bookService.count());
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
            dispatcher.forward(request, response);
        }
    }
}
