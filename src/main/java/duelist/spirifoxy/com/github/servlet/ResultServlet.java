package duelist.spirifoxy.com.github.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("isWinner") == null) {
            resp.sendRedirect("/");
            return;
        }

        boolean isUserWinner = (boolean) session.getAttribute("isWinner");
        session.removeAttribute("isWinner");
        req.setAttribute("isWinner", isUserWinner);

        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

}
