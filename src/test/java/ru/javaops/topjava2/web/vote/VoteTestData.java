package ru.javaops.topjava2.web.vote;

import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.to.VoteTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.time.LocalDate;

import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;

public class VoteTestData {

    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static final Vote vote_user_actual = new Vote(1, LocalDate.now(), null, 1, MONUMENT);
    public static final Vote vote_user = new Vote(3, LocalDate.of(2023, 05, 10), null, 1, MONUMENT);
    public static final Vote vote_user_1 = new Vote(4, LocalDate.of(2023, 05, 10), null, 4, MONUMENT);
    public static final Vote vote_user_2 = new Vote(5, LocalDate.of(2023, 05, 10), null, 5, WALTER);

    public static final int VOTE_USER_1_ID = vote_user.id();

    public static final int NOT_FOUND = 100;

    public static VoteTo getNewVoteTo() {
        return new VoteTo(6, LocalDate.now(), 5, 2);
    }
}
