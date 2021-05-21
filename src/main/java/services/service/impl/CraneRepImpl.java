package services.service.impl;

import services.models.EnumCargoType;
import services.models.Crane;
import org.springframework.stereotype.Service;
import services.service.CraneService;

import java.util.ArrayList;
import java.util.List;
@Service
public class CraneRepImpl implements CraneService {
    private static List<Crane> craneList = new ArrayList<>();

    @Override
    public void initCranes() {
        craneList.add(new Crane(EnumCargoType.BULK_CARGO, 2000));
        craneList.add(new Crane(EnumCargoType.LIQUID_CARGO, 1500));
        craneList.add(new Crane(EnumCargoType.CONTAINERS, 6500));
      //  craneList.add(new Crane(EnumCargoType.LIQUID_CARGO, 6000));
    }

    @Override
    public void startOfUnload(Crane crane) {
        List<Crane> cranes = getCranes();
        for (Crane c : cranes) {
            if (c.equals(crane)) c.setActive(true);
        }
        craneList = new ArrayList<>(cranes);
    }

    @Override
    public void endOfUnload(Crane crane) {
        List<Crane> cranes = getCranes();
        for (Crane c : cranes) {
            if (c.equals(crane)) c.setActive(false);
        }
        craneList = new ArrayList<>(cranes);
    }

    @Override
    public Crane getFreeCraneByType(EnumCargoType type) {
        List<Crane> cranes = getCranes();
        for (Crane c : cranes) {
            if (c.getCargoType().equals(type) && !c.isActive()) return c;
        }
        return null;
    }

    @Override
    public List<Crane> getCranes() {
        return craneList;
    }
}
