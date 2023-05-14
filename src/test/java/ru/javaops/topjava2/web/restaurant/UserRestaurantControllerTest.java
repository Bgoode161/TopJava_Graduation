package ru.javaops.topjava2.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.error.NotFoundException;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.service.vote.VoteService;
import ru.javaops.topjava2.util.VoteUtil;
import ru.javaops.topjava2.web.AbstractControllerTest;
import ru.javaops.topjava2.web.dish.DishTestData;
import ru.javaops.topjava2.web.vote.VoteTestData;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;
import static ru.javaops.topjava2.web.restaurant.UserRestaurantController.REST_URL;
import static ru.javaops.topjava2.web.user.UserTestData.*;
import static ru.javaops.topjava2.web.vote.VoteTestData.*;

public class UserRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL_SLASH = REST_URL + "/";

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteService voteService;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + WALTER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(WALTER));
        assertThrows(NotFoundException.class, () -> restaurantRepository.getExisted(VoteTestData.NOT_FOUND));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getWithActualMenu() throws Exception {
        MONUMENT.setDishes(DishTestData.monumentActualDishes);
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MONUMENT_ID + "/actual_menu"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER_WITH_DISHES.contentJson(MONUMENT));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllActual() throws Exception {
        MONUMENT.setDishes(DishTestData.monumentActualDishes);
        WALTER.setDishes(DishTestData.walterActualDishes);
        perform(MockMvcRequestBuilders.get(UserRestaurantController.REST_URL + "/actual"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(MONUMENT, WALTER));
    }

    @Test
    @WithUserDetails(value = USER_2_MAIL)
    void registerVote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL_SLASH + WALTER_ID + "/register_vote"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.VOTE_TO_MATCHER.contentJson(getNewVoteTo()));
    }
}
