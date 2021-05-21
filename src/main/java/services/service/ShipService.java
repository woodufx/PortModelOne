package services.service;

import services.models.Ship;

import java.util.List;

public interface ShipService {

    void initShips();

    Ship doUnloading(Ship ship, int weight);

    List<Ship> getShipList();

}
