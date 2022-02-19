package mypack;

import dao.SportsmenDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/db")
public class DbServlet extends HttpServlet {
    private final SportsmenDao sportsmenDao = SportsmenDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dayResults", sportsmenDao.getDayResults());
        req.setAttribute("dayStats", sportsmenDao.getDayStats());
        req.getRequestDispatcher("/WEB-INF/pages/results.jsp").forward(req, resp);
    }
}
