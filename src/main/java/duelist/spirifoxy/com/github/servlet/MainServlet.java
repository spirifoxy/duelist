package duelist.spirifoxy.com.github.servlet;

import duelist.spirifoxy.com.github.db.CommonDao;
import duelist.spirifoxy.com.github.db.UserDaoMysql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CommonDao userDao = new UserDaoMysql();
//        resp.setContentType("text/plain");
//        PrintWriter out = resp.getWriter();
//        out.println(userDao.getAll());

        PrintWriter out = resp.getWriter();
        out.println("Hello worldqwe!");
    }
}
