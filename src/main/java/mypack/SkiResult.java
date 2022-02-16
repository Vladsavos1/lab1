package mypack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SkiResult {

    private String sportsman;
    private Map<Integer, Double> results;

    public SkiResult(String sportsman, Map<Integer, Double> results) {
        this.sportsman = sportsman;
        this.results = results;
    }

    public SkiResult(String sportsman) {
        this.sportsman = sportsman;
        this.results = new HashMap<>();
    }

    public Double putResult(Integer dayNumber, Double result) {
        return results.put(dayNumber, result);
    }

    public String getSportsman() {
        return sportsman;
    }

    public void setSportsman(String sportsman) {
        this.sportsman = sportsman;
    }

    public Map<Integer, Double> getResults() {
        return results;
    }

    public void setResults(Map<Integer, Double> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkiResult skiResult = (SkiResult) o;
        return Objects.equals(sportsman, skiResult.sportsman) && Objects.equals(results, skiResult.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportsman, results);
    }

    @Override
    public String toString() {
        return "SkiResult{" +
                "sportsman='" + sportsman + '\'' +
                ", results=" + results +
                '}';
    }
}
