package ru.javaops.topjava2.web.dish;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.javaops.topjava2.util.validation.ValidationUtil.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController {

    DishRepository dishRepository;

    RestaurantRepository restaurantRepository;

    protected static final String REST_URL = "/api/admin/restaurants/{restId}/dishes";

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        Dish dish = dishRepository.getExisted(id);
        if (dish.getRestaurant().getId().equals(restId)) {
            return dish;
        } else {
            throw new IllegalRequestDataException(dish.getClass().getSimpleName() + " id = " + id + " - does not belong to this restaurant");
        }
    }

    @GetMapping
    public List<Dish> getAll(@PathVariable("restId") int restId) {
        return dishRepository.getAllByRestaurantId(restId);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable("restId") int restId) {
        checkNew(dish);
        if (dish.getDateCreated() == null) {
            dish.setDateCreated(LocalDate.now());
        }
        dish.setRestaurant(restaurantRepository.getReferenceById(restId));
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath().
                path(REST_URL + "/{id}").buildAndExpand(restId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id, @PathVariable int restId) {
        assureIdConsistent(dish, id);
        if (dish.getDateCreated() == null) {
            dish.setDateCreated(LocalDate.now());
        }
        if (dish.getRestaurant().getId().equals(restId)) {
            dishRepository.save(dish);
        } else {
            throw new IllegalRequestDataException(dish.getClass().getSimpleName() + " id = " + id + " - does not belong to this restaurant");
        }

    }

}
