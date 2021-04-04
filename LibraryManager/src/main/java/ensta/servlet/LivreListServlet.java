package ensta.servlet;

import ensta.service.LivreService;
import ensta.service.impl.LivreServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LivreListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/livre_list")){
            LivreService bookService = LivreServiceImpl.getInstance();
            try {
                request.setAttribute("livres", bookService.getList());
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_list.jsp");
            dispatcher.forward(request, response);
        }
    }
}