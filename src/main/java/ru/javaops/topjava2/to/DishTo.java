package ru.javaops.topjava2.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
public class DishTo extends NamedTo {

    @NotNull
     Long price;

    @NotNull
    Integer restaurantId;

    public DishTo(Integer id, String name, Long price, Integer restaurantId) {
        super(id, name);
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public long getPrice() {
        return price;
    }


}
