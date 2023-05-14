package ru.javaops.topjava2.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.DishTo;

import java.time.LocalDate;

@UtilityClass
public class DishUtil {

    public DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    public Dish getFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), LocalDate.now(), dishTo.getPrice(), null);
    }

}
