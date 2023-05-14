package ru.javaops.topjava2.web.dish;

import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.DishTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.MONUMENT;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.WALTER;


public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");
    public static final MatcherFactory.Matcher<DishTo> DISH_TO_MATCHER = MatcherFactory.usingAssertions(DishTo.class,
            (a, e) -> assertThat(a).usingRecursiveComparison().isEqualTo(e),
            (a, e) -> assertThat(a).usingElementComparatorIgnoringFields().isEqualTo(e));

    public static final int DISH_1_ID = 1;

    public static final int NOT_FOUND = 100;

    public static final Dish monument_dish_1 = new Dish(1, "Grilled Salmon", LocalDate.now(), 1200L, MONUMENT);
    public static final Dish monument_dish_2 = new Dish(2, "Caesar Salad", LocalDate.now(), 500L, MONUMENT);
    public static final Dish monument_dish_3 = new Dish(3, "Chicken Alfredo", LocalDate.now(), 400L, MONUMENT);
    public static final Dish monument_dish_4 = new Dish(4, "Margherita Pizza", LocalDate.now(), 800L, MONUMENT);
    public static final Dish monument_dish_5 = new Dish(5, "Beef Tacos", LocalDate.now(), 750L, MONUMENT);

    public static final Dish monument_dish_6 = new Dish(11, "Double Cheeseburger", LocalDate.of(2023, 05, 10), 500L, MONUMENT);

    public static final Dish monument_dish_7 = new Dish(12, "Spaghetti Carbonara", LocalDate.of(2023, 05, 10), 990L, MONUMENT);

    public static final Dish monument_dish_8 = new Dish(13, "Lasagna Bolognese", LocalDate.of(2023, 05, 10), 1190L, MONUMENT);

    public static final Dish monument_dish_9 = new Dish(14, "Cabbage Rolls", LocalDate.of(2023, 05, 10), 990L, MONUMENT);

    public static final Dish monument_dish_10 = new Dish(15, "Steak Frites", LocalDate.of(2023, 05, 10), 1490L, MONUMENT);

    public static final Dish walter_dish_1 = new Dish(6, "Sushi Roll", LocalDate.now(), 1000L, WALTER);
    public static final Dish walter_dish_2 = new Dish(7, "Pad Thai", LocalDate.now(), 600L, WALTER);
    public static final Dish walter_dish_3 = new Dish(8, "Spaghetti Carbonara", LocalDate.now(), 450L, WALTER);
    public static final Dish walter_dish_4 = new Dish(9, "Pho Soup", LocalDate.now(), 300L, WALTER);
    public static final Dish walter_dish_5 = new Dish(10, "Miso Ramen", LocalDate.now(), 400L, WALTER);
    public static final Dish walter_dish_6 = new Dish(16, "Beef Stroganoff",  LocalDate.of(2023, 05, 10), 1290L, WALTER);
    public static final Dish walter_dish_7 = new Dish(17, "Chicken Curry", LocalDate.of(2023, 05, 10), 990L, WALTER);
    public static final Dish walter_dish_8 = new Dish(18, "Wiener Schnitzel",  LocalDate.of(2023, 05, 10), 1490L, WALTER);
    public static final Dish walter_dish_9 = new Dish(19, "Vegetable Salad",  LocalDate.of(2023, 05, 10), 300L, WALTER);
    public static final Dish walter_dish_10 = new Dish(20, "Greek Salad",  LocalDate.of(2023, 05, 10), 400L, WALTER);

    public static final List<Dish> monumentDishes = List.of(monument_dish_1, monument_dish_2, monument_dish_3, monument_dish_4, monument_dish_5,
            monument_dish_6, monument_dish_7, monument_dish_8, monument_dish_9, monument_dish_10);

    public static final List<Dish> monumentActualDishes = List.of(monument_dish_1, monument_dish_2, monument_dish_3, monument_dish_4, monument_dish_5);

    public static final List<Dish> walterDishes = List.of(walter_dish_1, walter_dish_2, walter_dish_3, walter_dish_4, walter_dish_5,
            walter_dish_6, walter_dish_7, walter_dish_8, walter_dish_9, walter_dish_10);

    public static final List<Dish> walterActualDishes = List.of(walter_dish_1, walter_dish_2, walter_dish_3, walter_dish_4, walter_dish_5);
    public static DishTo getNewTo() {
        return new DishTo(null, "New Dish", 1000L, 1);
    }

    public static DishTo getUpdatedTo() {
        return new DishTo(DISH_1_ID, "UpdatedName", 1200L, 1);
    }
}


