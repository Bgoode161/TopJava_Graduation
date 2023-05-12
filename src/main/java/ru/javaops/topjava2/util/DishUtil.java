package ru.javaops.topjava2.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.DishTo;

@UtilityClass
public class DishUtil {
    public Dish getFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), null, dishTo.getPrice(), null);
    }

}
