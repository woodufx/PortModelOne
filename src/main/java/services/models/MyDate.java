package services.models;


@lombok.Data
public class MyDate implements Comparable<MyDate> {
    private int day;
    private int hour;

    public MyDate(int day, int hour) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Время от 0 до 23");
        }
        if (day < 0 || day > 31) {
            throw new IllegalArgumentException("День от 1 до 31");
        }
        this.day = day;
        this.hour = hour;
    }

    public MyDate(MyDate myDate) {
        this.day = myDate.getDay();
        this.hour = myDate.getHour();
    }

    private int getHour() {
        return hour;
    }

    private int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return day + " д. " + hour + " ч. ";
    }

    @Override
    public int compareTo(MyDate o) {
        if (o == null) return -1;
        int result = this.getDay() - o.getDay();
        if (result == 0) {
            result = this.getHour() - (o.getHour());
        }
        return result;
    }
}
