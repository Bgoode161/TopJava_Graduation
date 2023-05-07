package ru.javaops.topjava2.web.restaurant;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javaops.topjava2.HasId;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.RestaurantRepository;

@Component
@AllArgsConstructor
public class UniqueNameValidator implements Validator {

    public static final String EXCEPTION_DUPLICATE_NAME = "Restaurant with this name already exists";

    private final RestaurantRepository restaurantRepository;

    private final HttpServletRequest request;


    @Override
    public boolean supports(Class<?> clazz) {
        return Restaurant.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Restaurant restaurant = (Restaurant) target;

        restaurantRepository.findByNameIgnoreCase(restaurant.getName()).ifPresent(dbRest -> {
            if (request.getMethod().equals("PUT")) {
                int dbId = dbRest.id();
                if (restaurant.getId() != null && restaurant.id() == dbId) return;
            }
            errors.rejectValue("name", "", EXCEPTION_DUPLICATE_NAME);
        });

    }
}
