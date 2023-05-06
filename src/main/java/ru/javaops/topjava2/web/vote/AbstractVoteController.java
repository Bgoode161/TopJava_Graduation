package ru.javaops.topjava2.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.web.AuthUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


public abstract class AbstractVoteController {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    RestaurantRepository restaurantRepository;


    public List<Integer> getWinner() {
        return voteRepository.getMostRatedRestaurantsIds();
    }

}
