package ru.javaops.topjava2.web.dish;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;

import java.net.URI;
import java.util.List;

import static ru.javaops.topjava2.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.topjava2.util.validation.ValidationUtil.checkNew;

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
        return dishRepository.findAllByRestaurantId(restId, Sort.by(Sort.Direction.DESC, "dateCreated"));
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable("restId") int restId) {
        checkNew(dish);
        dish.setRestaurant(restaurantRepository.getReferenceById(restId));
        Dish created = dishRepository.prepareAndSave(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath().
                path(REST_URL + "/{id}").buildAndExpand(restId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id, @PathVariable int restId) {
        assureIdConsistent(dish, id);
        dish.setRestaurant(restaurantRepository.getReferenceById(restId));
        dishRepository.prepareAndSave(dish);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restId) {
        dishRepository.deleteExisted(id);
    }
}


