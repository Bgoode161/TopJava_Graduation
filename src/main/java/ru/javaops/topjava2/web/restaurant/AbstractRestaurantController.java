package ru.javaops.topjava2.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;


public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected DishRepository dishRepository;

    @Autowired
    protected VoteRepository voteRepository;

    public Restaurant get(int id) {
        return restaurantRepository.getExisted(id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Restaurant> getAllActual() {
        return restaurantRepository.getAllWithDishesByDate(LocalDate.now(), Sort.by(Sort.Direction.ASC, "name"));
    }

    public Restaurant getWithMenu(int id) {
        return restaurantRepository.getOneWithMenuByDate(id, LocalDate.now());
    }

    @Transactional
    public List<Restaurant> getVotingResult() {
        return restaurantRepository.findAllById(voteRepository.getMostRatedRestaurantsIds());
    }

}
