package ru.javaops.topjava2.web.dish;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.service.dish.DishService;
import ru.javaops.topjava2.to.DishTo;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController {

    @Autowired
    DishService dishService;

    @Autowired
    RestaurantRepository restaurantRepository;

    protected static final String REST_URL = "/api/admin/restaurants/{restId}/dishes";

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        return dishService.get(id, restId);
    }

    @GetMapping
    public List<Dish> getAll(@PathVariable("restId") int restId) {
        return dishService.getAll(restId);
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody DishTo dishTo, @PathVariable("restId") int restId) {
        Dish created = dishService.create(dishTo, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath().
                path(REST_URL + "/{id}").buildAndExpand(restId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id, @PathVariable int restId) {
        dishService.update(dish, id, restId);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restId) {
        dishService.delete(id);
    }
}


