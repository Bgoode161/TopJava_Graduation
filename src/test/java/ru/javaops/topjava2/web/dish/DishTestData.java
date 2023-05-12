package ru.javaops.topjava2.web.dish;

import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;


public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int DISH_1_ID = 1;

    public static final int NOT_FOUND = 100;

    public static final Dish monument_dish_1 = new Dish(1, "Grilled Salmon", LocalDate.now(), 1200L, MONUMENT);
    public static final Dish monument_dish_2 = new Dish(2, "Caesar Salad", LocalDate.now(), 500L, MONUMENT);
    public static final Dish monument_dish_3 = new Dish(3, "Chicken Alfredo", LocalDate.now(), 400L, MONUMENT);
    public static final Dish monument_dish_4 = new Dish(4, "Margherita Pizza", LocalDate.now(), 800L, MONUMENT);
    public static final Dish monument_dish_5 = new Dish(5, "Beef Tacos", LocalDate.now(), 750L, MONUMENT);

    public static final Dish walter_dish_1 = new Dish(6, "Sushi Roll", LocalDate.now(), 1000L, WALTER);
    public static final Dish walter_dish_2 = new Dish(7, "Pad Thai", LocalDate.now(), 600L, WALTER);
    public static final Dish walter_dish_3 = new Dish(8, "Spaghetti Carbonara", LocalDate.now(), 450L, WALTER);
    public static final Dish walter_dish_4 = new Dish(9, "Pho Soup", LocalDate.now(), 300L, WALTER);
    public static final Dish walter_dish_5 = new Dish(10, "Miso Ramen", LocalDate.now(), 400L, WALTER);

    public static final List<Dish> monumentDishes = List.of(monument_dish_1, monument_dish_2, monument_dish_3, monument_dish_4, monument_dish_5);

    public static final List<Dish> walterDishes = List.of(walter_dish_1, walter_dish_2, walter_dish_3, walter_dish_4, walter_dish_5);

    public static Dish getNew() {
        return new Dish(null, "New Dish", LocalDate.now(), 1000L, MONUMENT);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_1_ID, "UpdatedName", LocalDate.now(), 1200L, MONUMENT);
    }
}


