package duelist.spirifoxy.com.github.servlet;

import duelist.spirifoxy.com.github.db.CommonDao;
import duelist.spirifoxy.com.github.db.UserDaoMysql;
import duelist.spirifoxy.com.github.model.User;
import duelist.spirifoxy.com.github.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect("./menu");
            return;
        }

        req.getRequestDispatcher("./login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect("./menu");
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        UserDaoMysql userDao = new UserDaoMysql();
        User user = userDao.getByUsername(username);
        //TODO rewrite checkPassword
        if (user != null && Utils.checkPassword(password, user.getPassword())) {
            session.setAttribute("user", user);
            resp.sendRedirect("./menu");
        } else {
            resp.sendRedirect("./login");
        }
    }
}
