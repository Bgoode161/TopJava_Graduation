package ru.javaops.topjava2.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
public class DishTo extends NamedTo {

    @NotNull
    private Long price;

    public DishTo(Integer id, String name, Long price) {
        super(id, name);
        this.price = price;

    }

    public long getPrice() {
        return price;
    }


}
