package Model;

public class Revenue {
    private int year;
    private int month;
    private float totalRevenue;

    public Revenue(int year, int month, float totalRevenue) {
        this.year = year;
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public float getRevenue() {
        return totalRevenue;
    }
}
