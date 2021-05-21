package services.service.impl;

import services.models.Crane;
import services.models.MyDate;
import services.models.Schedule;
import services.models.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.service.*;

import java.util.*;

@Service
public class PortRepImpl implements PortService {
    private static List<Schedule> scheduleList = new ArrayList<>();
    private final ShipService shipRep;
    private final MyDateService myDate;
    private final CraneService craneService;
    private Random random = new Random();

    @Autowired
    public PortRepImpl(ShipService shipRep, MyDateService myDate, CraneService craneService) {
        this.shipRep = shipRep;
        this.craneService = craneService;
        this.myDate = myDate;
    }

    @Value("${result.count}")
    private int count;

    @Override
    public void initPort() {
        shipRep.initShips();
        craneService.initCranes();
        List<Ship> ships = shipRep.getShipList();
        for (int i = 0; i < count; i++) {
            scheduleList.add(new Schedule(myDate.getRandomDate(), ships.get(i)));
        }
    }

    @Override
    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    @Override
    public void unloadShips(MyDate date) {
        List<Schedule> schedules = getScheduleList();
        for (Schedule sc : schedules) {
            //разгружаем корабль
            if (sc.getCrane() != null && sc.getEndOfUnloading() == null) {
                int unWeight = (int) (sc.getCrane().getSpeed() * sc.getCrane().getCargoType().getRate());

                sc.setShip(shipRep.doUnloading(sc.getShip(), unWeight));
                if (sc.getShip().getWeightCargo() != 0) {
                    System.out.print("Разгружаем корабль " + sc.getShip().getName() + " " + sc.getShip().getCargoType().name());
                    System.out.println(" начальный вес : " + sc.getWeightCargo() + " ,текущий вес " + sc.getShip().getWeightCargo());
                }
            }
            //не закончилась ли разгрузка
            if (sc.getShip().getWeightCargo() == 0 && sc.getEndOfUnloading() == null) {
                System.out.println("Завершение разгрузки корабля " + sc.getShip().getName() + " " + sc.getShip().getCargoType().name());
                sc.setEndOfUnloading(date);
                craneService.endOfUnload(sc.getCrane());
            }
        }
        scheduleList = new ArrayList<>(schedules);
    }


    @Override
    public void shipArrival(Ship ship, MyDate date) {
        if (ship != null) {
            System.out.print("Прибытие корабля " + ship.getName() + " , информация  о  расписании: ");
            List<Schedule> list = getScheduleList();
            for (Schedule sch : list) {
                if (sch.getShip().equals(ship)) {
                    sch.setRealArrivalDate(date);
                    System.out.println(sch.toString());
                }
            }
            scheduleList = new ArrayList<>(list);
        }
    }

    @Override
    public Ship getNotArrivedShipOrEmpty() {
        List<Schedule> list = getScheduleList();
        int indOfSchedule = random.nextInt(count - 1);
        Schedule schedule = list.get(indOfSchedule);
        if (schedule.getRealArrivalDate() == null) {
            return schedule.getShip();
        }
        return null;
    }

    static int ll = 0;

    @Override
    public void checkUnload(MyDate date) {
        ll++;
        List<Schedule> schedules = getScheduleList();
        Collections.sort(schedules);
        if (ll == 7) {
            int y;
            System.out.print("");
        }
        for (Schedule schedule : schedules) {
            if (schedule.getRealArrivalDate() != null && schedule.getCrane() == null) {
                Crane crane = craneService.getFreeCraneByType(schedule.getShip().getCargoType());
                //Начинаем разгрузку
                if (crane != null) {
                    int indexSchedule = schedules.indexOf(schedule);
                    System.out.println("Начинаем разгрузку корабля " + schedule.getShip().getName() + " " + schedule.getShip().getCargoType().name());
                    schedules.get(indexSchedule).setCrane(crane);
                    schedules.get(indexSchedule).setStartOfUnloading(date);
                    craneService.startOfUnload(crane);
                } else {
                    System.out.println("Корабль " + schedule.getShip().getName() + " " + schedule.getShip().getCargoType().name() + " ожидает разгрузки");
                }
            }
        }
        scheduleList = new ArrayList<>(schedules);
    }
}
