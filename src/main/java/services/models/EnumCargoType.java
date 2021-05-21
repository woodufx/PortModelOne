package services.models;

import java.util.Random;

public enum EnumCargoType {
    BULK_CARGO(1.3),
    LIQUID_CARGO(1.5),
    CONTAINERS(0.9);
    /**
     * @param unloadingRatio -  коэффициент скорости разгрузки
     */
    private final double unloadingRatio;

    EnumCargoType(double rate) {

        this.unloadingRatio = rate;
    }

    public double getRate() {
        return unloadingRatio;
    }


    public static EnumCargoType randomEnum() {
        int x = new Random().nextInt(EnumCargoType.values().length);
        return EnumCargoType.values()[x];
    }
}
