package duelist.spirifoxy.com.github.servlet;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect("./");
            return;
        }

        req.getRequestDispatcher("./login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect("./");
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDaoMysql userDao = new UserDaoMysql();
        User user = userDao.getByUsername(username);

        boolean isPasswordCorrect = false;
        try {
            isPasswordCorrect = Utils.checkPassword(password, user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user != null) {
            if (isPasswordCorrect) {
                session.setAttribute("user", user);
                resp.sendRedirect("./");
            } else {
                //TODO error handling
                resp.sendRedirect("./login");
            }
        } else { //register new user
            String passwordHash = null;
            try {
                passwordHash = Utils.getSaltedHash(password);
            } catch (Exception e) {
                throw new IllegalArgumentException("Ошибка при регистрации пользователя");
            }
            user = new User(username, passwordHash);
            userDao.insert(user);
            session.setAttribute("user", user);
            resp.sendRedirect("./");
        }
    }
}
