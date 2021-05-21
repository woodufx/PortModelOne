package services.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Ship {
    /**
     * @param cargoType -  тип корабля
     */
    private EnumCargoType cargoType;
    /**
     * @param name -  название корабля
     */
    private String name;

    /**
     * @param weight -  осталось для разгрузки, тонн
     */
    private int weightCargo;

    @Override
    public String toString() {
        return "Корабль " + name + ", с грузом " + cargoType + "  " + weightCargo + "тонн";
    }

    public int getWeightCargo() {
        return weightCargo;
    }
}
