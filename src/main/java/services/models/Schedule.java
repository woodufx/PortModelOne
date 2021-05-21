package services.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Schedule implements Comparable<Schedule> {
    /**
     * @param arrivalDate - предварительная дата  прибытия корабля
     */
    private MyDate arrivalDate;
    /**
     * @param realArrivalDate - реальная дата  прибытия корабля
     */
    private MyDate realArrivalDate;
    /**
     * @param ship -   корабль
     */
    private Ship ship;
    /**
     * @param crane -   кран, который разгружает корабль
     */
    private Crane crane;
    /**
     * @param weight -  вес груза, тонн
     */
    private int weightCargo;
    /**
     * @param startOfUnloading -  начало разгрузки
     */
    private MyDate startOfUnloading;
    /**
     * @param endOfUnloading -  завершение разгрузки
     */
    private MyDate endOfUnloading;

    public Schedule(MyDate arrivalDate, Ship ship) {
        this.arrivalDate = arrivalDate;
        this.ship = ship;
        this.weightCargo = ship.getWeightCargo();

    }

    @Override
    public String toString() {
        return " ожидаемая дата прибытия " + arrivalDate + ", реальная " + realArrivalDate + ", вес груза " + weightCargo;
    }

    @Override
    public int compareTo(Schedule o) {
        if (this.realArrivalDate == null) return 1;
        return this.realArrivalDate.compareTo(o.realArrivalDate);
    }
}
