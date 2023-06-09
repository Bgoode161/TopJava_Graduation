package ru.javaops.topjava2.web.restaurant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.error.NotFoundException;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.util.JsonUtil;
import ru.javaops.topjava2.web.AbstractControllerTest;
import ru.javaops.topjava2.web.dish.DishTestData;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.restaurant.AdminRestaurantController.REST_URL;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;
import static ru.javaops.topjava2.web.user.UserTestData.ADMIN_MAIL;

public class AdminRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = AdminRestaurantController.REST_URL + "/";

    @Autowired
    RestaurantRepository restaurantRepository;



    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + WALTER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(WALTER));
        assertThrows(NotFoundException.class, () -> restaurantRepository.getExisted(RestaurantTestData.NOT_FOUND));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getWithActualMenu() throws Exception {
        MONUMENT.setDishes(DishTestData.monumentActualDishes);
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MONUMENT_ID + "/actual_menu"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER_WITH_DISHES.contentJson(MONUMENT));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(MONUMENT, WALTER));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllActual() throws Exception {
        MONUMENT.setDishes(DishTestData.monumentActualDishes);
        WALTER.setDishes(DishTestData.walterActualDishes);
        perform(MockMvcRequestBuilders.get(REST_URL + "/actual"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER_WITH_DISHES.contentJson(MONUMENT, WALTER));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        Restaurant newRest = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRest)))
                .andExpect(status().isCreated());

        Restaurant created = RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.id();
        newRest.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRest);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(newId), newRest);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createDuplicateName() throws Exception {
      perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(WALTER)))
                .andExpect(status().isUnprocessableEntity());
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Restaurant updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MONUMENT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(MONUMENT_ID), updated);

    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + WALTER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + RestaurantTestData.NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
