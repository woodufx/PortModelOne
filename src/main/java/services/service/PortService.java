package services.service;


import services.models.MyDate;
import services.models.Schedule;
import services.models.Ship;

import java.util.List;


public interface PortService {
    void initPort();

    List<Schedule> getScheduleList();

    void unloadShips(MyDate date);

    void checkUnload(MyDate date);

    void shipArrival(Ship ship, MyDate date);

    Ship getNotArrivedShipOrEmpty();


}
