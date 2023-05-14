package ru.javaops.topjava2.service.dish;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.javaops.topjava2.error.DataConflictException;
import ru.javaops.topjava2.error.IllegalRequestDataException;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.DishTo;
import ru.javaops.topjava2.util.DishUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.javaops.topjava2.util.validation.ValidationUtil.assureIdConsistent;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository dishRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Dish get(int id, int restId) {
        Dish dish = dishRepository.getExisted(id);
        if (dish.getRestaurant().getId().equals(restId)) {
            return dish;
        } else {
            throw new IllegalRequestDataException(dish.getClass().getSimpleName() + " id = " + id + " - does not belong to this restaurant");
        }
    }

    @Override
    public List<Dish> getAll(int restId) {
        return dishRepository.findAllByRestaurantId(restId, Sort.by(Sort.Direction.DESC, "dateCreated"));
    }

    @Override
    public Dish create(DishTo dishTo, int restId) {
        Dish newDish = DishUtil.getFromTo(dishTo);
        newDish.setRestaurant(restaurantRepository.getReferenceById(restId));
        int existedId = getExistedId(newDish, restId);
        if (existedId == 0) {
            newDish = dishRepository.save(newDish);
        } else {
            throw new DataConflictException(newDish.getName() + " already offered today in this restaurant");
        }
        return newDish;
    }

    @Override
    public void update(DishTo dishTo, int id, int restId) {
        assureIdConsistent(dishTo, id);
        Dish dishUpdated = DishUtil.getFromTo(dishTo);
        dishUpdated.setRestaurant(restaurantRepository.getReferenceById(restId));
        int existedId = getExistedId(dishUpdated, restId);
        if (existedId != 0 && existedId != dishUpdated.id()) {
            throw new DataConflictException(dishUpdated.getName() + " already offered today in this restaurant");
        }
        dishRepository.save(dishUpdated);

    }

    @Override
    public void delete(int id) {
        dishRepository.deleteExisted(id);
    }

    private int getExistedId(Dish newDish, int restId) {
        int existedId = 0;
        Optional<Dish> dishDB = dishRepository.findByNameIgnoreCaseAndRestaurantIdAndAndDateCreated(newDish.getName(), restId, newDish.getDateCreated());
        if (dishDB.isPresent()) {
            existedId = dishDB.get().id();
        }
        return existedId;
    }
}
