package ru.javaops.topjava2.web.restaurant;

import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.web.MatcherFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");

    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER_WITH_DISHES = MatcherFactory.usingAssertions(Restaurant.class,
            (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("dishes.restaurant").isEqualTo(e),
            (a, e) -> assertThat(a).usingElementComparatorIgnoringFields("dishes.restaurant").isEqualTo(e));

    public static final int MONUMENT_ID = 1;
    public static final int WALTER_ID = 2;
    public static final int NOT_FOUND = 100;

    public static final String MONUMENT_ADDRESS = "Trg Republic, 1";
    public static final String WALTER_ADDRESS = "Zaplanska, 32";

    public static final Restaurant MONUMENT = new Restaurant(MONUMENT_ID, "Monument", MONUMENT_ADDRESS);
    public static final Restaurant WALTER = new Restaurant(WALTER_ID, "Walter", WALTER_ADDRESS);

    public static Restaurant getNew() {
        return new Restaurant(null, "New", "NewAddress");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(MONUMENT_ID, "UpdatedName", "UpdatedAddress");
    }


}


