package ru.javaops.topjava2.util;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.DishTo;

import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class DishUtil {

    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice(), dish.getRestaurant().getId());
    }

    public static Dish getFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), LocalDate.now(), dishTo.getPrice(), null);
    }

    public static List<DishTo> getTos(List<Dish> dishes) {
        return dishes.stream().map(dish -> createTo(dish)).toList();
    }

}
