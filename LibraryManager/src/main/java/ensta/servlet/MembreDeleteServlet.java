package ensta.servlet;

import ensta.service.MembreService;
import ensta.service.impl.MembreServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MembreDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/membre_delete")){
            MembreService membreService = MembreServiceImpl.getInstance();
            if (request.getParameter("id") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                if (id != -1){
                    try {
                        request.setAttribute("membre", membreService.getById(id));
                        request.setAttribute("id", id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/membre_delete.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MembreService membreService = MembreServiceImpl.getInstance();

        try {
            membreService.delete(Integer.parseInt(request.getParameter("id")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/membre_list");
    }
}