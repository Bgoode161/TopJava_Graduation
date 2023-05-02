package ru.javaops.topjava2.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected DishRepository dishRepository;

    public Restaurant get(int id) {
        return restaurantRepository.getExisted(id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Restaurant> getAllActual() {
        return restaurantRepository.getAllActual(LocalDate.now());
    }

}
