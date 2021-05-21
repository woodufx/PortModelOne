package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import services.models.MyDate;
import services.models.Ship;
import services.service.MyDateService;
import services.service.PortService;


@SpringBootApplication
@ComponentScan(basePackages = {"services"})
public class DemoApplication {

    private static PortService portService = null;
    private static MyDateService myDateService = null;

    @Autowired
    public DemoApplication(PortService portService, MyDateService myDateService) {
        this.portService = portService;
        this.myDateService = myDateService;
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DemoApplication.class, args);

        MyDate date = new MyDate(1, 0);
        portService.initPort();

        while (true) {
            System.out.println("--------------" + date.toString() + "--------------");
            portService.unloadShips(date);
            Ship ship = portService.getNotArrivedShipOrEmpty();
            if (ship != null) {
                portService.shipArrival(ship, new MyDate(date));
            }
            portService.checkUnload(date);
            date = myDateService.plusHour(date);
            Thread.sleep(500);
        }
    }
}
