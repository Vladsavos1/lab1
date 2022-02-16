package mypack;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

@WebServlet("/ski")
public class SkiRequest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            try {
                SkiResult sportsman1 = buildSkiResult(1, req);
                double avg1 = avg(sportsman1);
                Entry<Integer, Double> highestResult1 = theHighestResult(sportsman1);
                Entry<Integer, Double> lowestResult1 = theLowestResult(sportsman1);

                SkiResult sportsman2 = buildSkiResult(2, req);
                double avg2 = avg(sportsman2);
                Entry<Integer, Double> highestResult2 = theHighestResult(sportsman2);
                Entry<Integer, Double> lowestResult2 = theLowestResult(sportsman2);

                writer.println("<table border='1'>");
                writer.println("<tr><th>Спортсмен</th><th>День 1</th><th>День 2</th><th>День 3</th><th>Средний результат</th><th>Лучший результат</th><th>Худший результат</th></tr>");
                writer.println("<tr><td>" + sportsman1.getSportsman() + "</td><td>" + sportsman1.getResults().get(1) + "</td><td>" + sportsman1.getResults().get(2) + "</td><td>" + sportsman1.getResults().get(3) + "</td><td>" + avg1 + "</td><td>День " + highestResult1.getKey() + ". Высота: " + highestResult1.getValue() + "</td><td>День " + lowestResult1.getKey() + ". Высота: " + lowestResult1.getValue() + "</td></tr>");
                writer.println("<tr><td>" + sportsman2.getSportsman() + "</td><td>" + sportsman2.getResults().get(1) + "</td><td>" + sportsman2.getResults().get(2) + "</td><td>" + sportsman2.getResults().get(3) + "</td><td>" + avg2 + "</td><td>День " + highestResult2.getKey() + ". Высота: " + highestResult2.getValue() + "</td><td>День " + lowestResult2.getKey() + ". Высота: " + lowestResult2.getValue() + "</td></tr>");
                writer.println("</table>");
                writer.println("<h3>Победитель:</h3>");
                if (avg1 == avg2) {
                    writer.println("<span>Ничья</span>");
                } else {
                    SkiResult winner = avg1 > avg2
                            ? sportsman1
                            : sportsman2;
                    writer.println("<span>" + winner.getSportsman() + "</span>");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                writer.println("<h3 style='color:red'>Неверный запрос, попробуйте еще раз</h3>");
            }
        }
    }

    private SkiResult buildSkiResult(int sportsmanNum, HttpServletRequest req) {
        String[] values = req.getParameterValues("result" + sportsmanNum);
        SkiResult skiResult = new SkiResult("Спортсмен " + sportsmanNum);
        for (int i = 0; i < 3; i++) {
            double res = Double.parseDouble(values[i]);
            if (res < 0) {
                throw new IllegalArgumentException();
            }
            skiResult.putResult(i + 1, res);
        }
        return skiResult;
    }

    private double avg(SkiResult skiResult) {
        return skiResult.getResults()
                .values()
                .stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElseThrow();
    }

    private Entry<Integer, Double> theHighestResult(SkiResult skiResult) {
        return skiResult.getResults()
                .entrySet()
                .stream()
                .max(Entry.comparingByValue())
                .orElseThrow();
    }

    private Entry<Integer, Double> theLowestResult(SkiResult skiResult) {
        return skiResult.getResults()
                .entrySet()
                .stream()
                .min(Entry.comparingByValue())
                .orElseThrow();
    }
}
