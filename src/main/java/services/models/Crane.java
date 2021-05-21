package services.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Crane {

    /**
     * @param cargoType -  тип разгрузочного крана (по грузу)
     */
    private EnumCargoType cargoType;

    /**
     * @param speed -   скорость разгрузки, тонн в час
     */
    private Integer speed;

    /**
     * @param isActive -   занят ли кран разгрузкой
     */
    private boolean isActive = false;

    public Crane(EnumCargoType cargoType, Integer speed) {
        this.cargoType = cargoType;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Кран " + cargoType + " ,скорость " + speed + " ,активен? " + isActive;
    }
}
