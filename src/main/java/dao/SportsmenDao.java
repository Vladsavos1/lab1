package dao;

import model.DayResult;
import model.DayStat;
import mypack.SkiResult;
import org.decimal4j.util.DoubleRounder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class SportsmenDao {

    public final String FIND_ALL = "SELECT * FROM sports";
    private SportsmenDao() {

    }

    private static SportsmenDao sportsmenDao;

    public static SportsmenDao getInstance() {
        if (sportsmenDao == null) {
            synchronized (SportsmenDao.class) {
                if (sportsmenDao == null) {
                    sportsmenDao = new SportsmenDao();
                }
            }
        }
        return sportsmenDao;
    }

    public List<SkiResult> findAll() {
        List<SkiResult> skiResults = new ArrayList<>();

        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            SkiResult skiResult = null;

            while (resultSet.next()) {
                int row = resultSet.getRow();
                if (row == 1) {
                    skiResult = new SkiResult(resultSet.getString("sportsman"));
                    skiResults.add(skiResult);
                }

                String sportsman = resultSet.getString("sportsman");
                int day = resultSet.getInt("day");
                double result = resultSet.getDouble("result");

                assert skiResult != null;
                if (row != 1 && !sportsman.equals(skiResult.getSportsman())) {
                    skiResult = new SkiResult(sportsman);
                    skiResults.add(skiResult);
                }
                skiResult.putResult(day, result);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skiResults;
    }

    public List<DayResult> getDayResults() {
        List<SkiResult> results = findAll();
        List<DayResult> dayResults = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            DayResult dayResult = new DayResult(i + 1);
            dayResults.add(dayResult);

            for (SkiResult skiResult : results) {
                String sportsman = skiResult.getSportsman();
                double res = new ArrayList<>(skiResult.getResults().values()).get(i);
                dayResult.putResult(sportsman, res);
            }
        }

        return dayResults;

    }

    public DayStat[] getDayStats() {
        List<DayResult> dayResults = getDayResults();
        DayStat[] dayStats = new DayStat[3];

        for (int i = 0; i < dayResults.size(); i++) {
            dayStats[i] = new DayStat();
            DayResult dayResult = dayResults.get(i);

            DoubleSummaryStatistics collect = dayResult.getResults().entrySet()
                    .stream()
                    .collect(Collectors.summarizingDouble(Map.Entry::getValue));

            Map.Entry<String, Double> win = dayResults.get(i).getResults().entrySet()
                    .stream()
                    .filter(stringDoubleEntry -> stringDoubleEntry.getValue() == collect.getMax())
                    .findAny()
                    .orElse(null);

            dayStats[i].setWinner(win.getKey());
            dayStats[i].setMax(collect.getMax());
            dayStats[i].setMin(collect.getMin());
            double round = DoubleRounder.round(collect.getAverage(), 2);
            dayStats[i].setAvg(round);
        }
        return dayStats;
    }
}
