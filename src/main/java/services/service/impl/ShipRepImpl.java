package services.service.impl;

import services.models.EnumCargoType;
import services.models.Ship;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.service.ShipService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipRepImpl implements ShipService {
    private final static int max = 15000;
    private final static int min = 3200;
    private List<Ship> shipList = new ArrayList<>();
    @Value("${result.count}")
    private int count;

    @Override
    public void initShips() {
        String name;
        int weight;
        for (int i = 0; i < count; i++) {
            name = RandomStringUtils.random(5, true, true);
            weight = (int)( Math.random() * (max - min) + min);
            shipList.add(new Ship(EnumCargoType.randomEnum(), name, weight));
        }
    }


    @Override
    public Ship doUnloading(Ship ship, int weight) {
        List<Ship> ships = getShipList();
        for (Ship sc : ships) {
            if (sc.equals(ship)) {
                if (sc.getWeightCargo() - weight < 0) {
                    sc.setWeightCargo(0);
                } else {
                    sc.setWeightCargo(ship.getWeightCargo() - weight);
                }
                return sc;
            }
        }
        shipList = new ArrayList<>(ships);

        return null;
    }

    @Override
    public List<Ship> getShipList() {
        return shipList;
    }


}
