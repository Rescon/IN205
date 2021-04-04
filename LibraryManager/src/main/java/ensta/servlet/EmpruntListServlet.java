package ensta.servlet;

import ensta.service.EmpruntService;
import ensta.service.impl.EmpruntServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmpruntListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/emprunt_list")) {
            EmpruntService empruntService = EmpruntServiceImpl.getInstance();
            try {
                if (request.getParameter("show") != null && request.getParameter("show").contains("all")) {
                    request.setAttribute("emprunts", empruntService.getList());
                    request.setAttribute("show", "all");
                } else {
                    request.setAttribute("emprunts", empruntService.getListCurrent());
                    request.setAttribute("show", "current");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp");
            dispatcher.forward(request, response);
        }
    }
}
