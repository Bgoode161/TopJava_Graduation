package ru.javaops.topjava2.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    List<Dish> findAllByRestaurantId(int id, Sort sort);

    default Dish prepareAndSave(Dish dish) {
        if (dish.getDateCreated() == null) {
            dish.setDateCreated(LocalDate.now());
        }
        return save(dish);
    }

}
