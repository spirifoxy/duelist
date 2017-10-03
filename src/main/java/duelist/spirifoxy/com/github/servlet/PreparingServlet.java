package duelist.spirifoxy.com.github.servlet;

import duelist.spirifoxy.com.github.model.Room;
import duelist.spirifoxy.com.github.model.Server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/preparing")
public class PreparingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("opponent") == null) {
            resp.sendRedirect("/duels");
        }

        Server server = Server.getInstance();
        req.getRequestDispatcher("/preparing.jsp").forward(req, resp);
    }

}
