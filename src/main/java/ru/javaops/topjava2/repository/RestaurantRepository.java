package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Restaurant;

import java.lang.annotation.Native;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    Optional<Restaurant> findByNameIgnoreCase(String name);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menu d WHERE d.dateCreated=:dateCreated")
    List<Restaurant> getAllActual(@Param("dateCreated") LocalDate dateCreated);

}
