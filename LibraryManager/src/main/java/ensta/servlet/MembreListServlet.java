package ensta.servlet;

import ensta.service.MembreService;
import ensta.service.impl.MembreServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MembreListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/membre_list")){
            MembreService membreService = MembreServiceImpl.getInstance();
            try {
                request.setAttribute("membres", membreService.getList());
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
            dispatcher.forward(request, response);
        }
    }
}