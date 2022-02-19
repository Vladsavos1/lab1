package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class DayResult {
    private int day;
    private Map<String, Double> results;

    public DayResult(int day) {
        this.day = day;
        results = new LinkedHashMap<>();
    }

    public int getDay() {
        return day;
    }

    public Map<String, Double> getResults() {
        return results;
    }

    public void putResult(String name, double res) {
        results.put(name, res);
    }
}
