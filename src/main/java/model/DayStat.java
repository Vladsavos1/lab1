package model;

public class DayStat {
    private String winner;
    private double max;
    private double min;
    private double avg;


    public DayStat(String winner, double max, double min, double avg) {
        this.winner = winner;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public DayStat() {

    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }
}
