package ru.javaops.topjava2.web.dish;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
    public DishTo get(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        log.info("get dish {} from restaurant {}", id, restId);
        return dishService.get(id, restId);
    }

    @GetMapping
    public List<DishTo> getAll(@PathVariable("restId") int restId) {
        log.info("get all dishes from restaurant {}", restId);
        return dishService.getAll(restId);
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo, @PathVariable("restId") int restId) {
        log.info("create dish {} for restaurant {}", dishTo, restId);
        Dish created = dishService.create(dishTo, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath().
                path(REST_URL + "/{id}").buildAndExpand(restId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int id, @PathVariable int restId) {
        log.info("update dish {} for user {}", dishTo, restId);
        dishService.update(dishTo, id, restId);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restId) {
        log.info("delete dish {} of restaurant {}", id, restId);
        dishService.delete(id);
    }
}


