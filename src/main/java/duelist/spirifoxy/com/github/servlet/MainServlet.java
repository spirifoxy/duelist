package duelist.spirifoxy.com.github.servlet;

import duelist.spirifoxy.com.github.db.CommonDao;
import duelist.spirifoxy.com.github.db.UserDaoMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/menu")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            System.out.println("No session in GET request to Game!");
            resp.sendRedirect("./login");
            return;
        }

        PrintWriter out = resp.getWriter();
        out.println("Hello world");

    }
}
