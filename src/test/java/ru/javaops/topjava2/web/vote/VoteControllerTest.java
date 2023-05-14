package ru.javaops.topjava2.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.service.vote.VoteService;
import ru.javaops.topjava2.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.util.VoteUtil.createTo;
import static ru.javaops.topjava2.web.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.topjava2.web.user.UserTestData.USER_MAIL;
import static ru.javaops.topjava2.web.vote.VoteTestData.*;

public class VoteControllerTest extends AbstractControllerTest {

    @Autowired
    VoteService voteService;

    @Autowired
    VoteRepository voteRepository;

    static final String ADMIN_REST_URL_SLASH = AdminVoteController.REST_URL + "/";

    static final String USER_REST_URL = UserVoteController.REST_URL;


    static final LocalDate TEST_DATE = LocalDate.of(2023, 05, 10);

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminVoteController.REST_URL)
                .param("date_created", "2023-05-10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(createTo(vote_user), createTo(vote_user_1), createTo(vote_user_2)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_REST_URL_SLASH + VOTE_USER_1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(voteRepository.findById(vote_user.id()).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(ADMIN_REST_URL_SLASH + VoteTestData.NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(createTo(vote_user_actual), createTo(vote_user)));
    }


}
