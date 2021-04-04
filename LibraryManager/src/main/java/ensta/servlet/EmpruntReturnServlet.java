package ensta.servlet;

import ensta.service.EmpruntService;
import ensta.service.impl.EmpruntServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmpruntReturnServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if(action.equals("/emprunt_return")){
            EmpruntService loanService = EmpruntServiceImpl.getInstance();
            int id = -1;
            if (request.getParameter("id") != null){
                id = Integer.parseInt(request.getParameter("id"));
            }
            try {
                if (id != -1){
                    request.setAttribute("id", id);
                }
                request.setAttribute("emprunts", loanService.getListCurrent());
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        try {
            if (request.getParameter("id") == null){
                throw new ServletException("Impossible d'obtenir ID!");
            } else{
                empruntService.returnBook(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("emprunts", empruntService.getListCurrent());
            }
        } catch (Exception e) {
            throw new ServletException("Impossible de retour!", e);
        }

        response.sendRedirect(request.getContextPath() + "/emprunt_list");
    }
}
