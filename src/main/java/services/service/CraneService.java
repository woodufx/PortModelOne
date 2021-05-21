package services.service;

import services.models.EnumCargoType;
import services.models.Crane;

import java.util.List;

public interface CraneService {
    List<Crane> getCranes();

    void initCranes();

    void startOfUnload(Crane crane);

    void endOfUnload(Crane crane);

    Crane getFreeCraneByType(EnumCargoType type);
}
