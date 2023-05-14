package ru.javaops.topjava2.service.dish;

import org.springframework.stereotype.Service;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.DishTo;

import java.util.List;


public interface DishService {

    DishTo get(int id, int restId);

    List<DishTo> getAll(int restId);

    Dish create(DishTo dishTo, int restId);

    void update(DishTo dishTo, int id, int restId);

    void delete(int id);

}
